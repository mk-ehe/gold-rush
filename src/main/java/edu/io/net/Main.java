package edu.io.net;

import edu.io.net.command.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var gsc = new GameServerConnector("tcp://localhost:1313", new SocketConnector());
        gsc.connect();
        if (!gsc.isConnected()) {
            System.err.println("Could not connect to server");
            return;
        }

        var in = new Scanner(System.in);
        System.out.print("Enter your name: ");
        var name = in.nextLine().strip();


        gsc.issueCommand(new Handshake.Cmd("1.1.17"), res -> {
            if (res instanceof CommandAck ack) {
                if (ack.resCmd() instanceof Handshake.CmdRe handshakeRes) {
                    var status = handshakeRes.status();
                    System.out.println("Handshake: " + status);

                    if (status == Handshake.CmdRe.Status.OK) {
                        System.out.println(Handshake.CmdRe.Status.OK.msg);

                    } else if (status == Handshake.CmdRe.Status.LIB_VERSION_TOO_LOW) {
                        System.out.println(Handshake.CmdRe.Status.LIB_VERSION_TOO_LOW.msg);
                        System.out.println("Disconnecting Client.");
                        gsc.disconnect();
                        System.exit(1);

                    } else if (status == Handshake.CmdRe.Status.LIB_VERSION_MALFORMED) {
                        System.out.println(Handshake.CmdRe.Status.LIB_VERSION_MALFORMED.msg);
                        System.out.println("Disconnecting Client.");
                        gsc.disconnect();
                        System.exit(1);

                    } else {
                        System.out.println("Unexpected Error.");
                        System.out.println("Disconnecting Client.");
                        gsc.disconnect();
                        System.exit(1);
                    }
                }
            } else {
                System.out.println(res);
            }
        });


        gsc.issueCommand(new JoinGame.Cmd(name), res -> {
            if (res instanceof CommandAck ack) {
                if (ack.resCmd() instanceof JoinGame.CmdRe joingameRes){
                    var status = joingameRes.status();
                    System.out.println("Joining game status: " + status);

                    if (status == JoinGame.CmdRe.Status.OK) {
                        System.out.println(JoinGame.CmdRe.Status.OK.msg);

                    } else if (status == JoinGame.CmdRe.Status.NAME_ALREADY_EXISTS) {
                        System.out.println(JoinGame.CmdRe.Status.NAME_ALREADY_EXISTS.msg);

                    } else if (status == JoinGame.CmdRe.Status.ALREADY_CONNECTED) {
                        System.out.println(JoinGame.CmdRe.Status.ALREADY_CONNECTED.msg);

                    } else {
                        System.out.println("Unexpected Error.");
                        System.out.println("Disconnecting Client.");
                        gsc.disconnect();
                        System.exit(1);
                    }
                }
            } else {
                System.out.println("Unexpected Error.");
                System.out.println("Disconnecting Client.");
                gsc.disconnect();
                System.exit(1);
            }
        });

        Thread.sleep(500);
        System.out.print("Would you like to leave the game (yes/no)?: ");
        var leave = in.nextLine().strip().toLowerCase();

        if (leave.equals("yes")) {
            gsc.issueCommand(new LeaveGame.Cmd(), res -> {
                if (res instanceof CommandAck ack) {
                    if (ack.resCmd() instanceof LeaveGame.CmdRe leavegameRes) {
                        var status = leavegameRes.status();
                        gsc.disconnect();
                    }
                }
            });
        } else {
            System.out.print("You are staying in the game.");
        }

        new Scanner(System.in).nextLine();
    }
}