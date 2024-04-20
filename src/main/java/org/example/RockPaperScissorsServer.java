package org.example;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class RockPaperScissorsServer {
    private static final int PORT = 12345;
    private static ConcurrentLinkedQueue<PlayerHandler> waitingPlayers = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                PlayerHandler player = new PlayerHandler(clientSocket);
                player.start();

                PlayerHandler opponent = waitingPlayers.poll();
                if (opponent != null) {
                    new GameHandler(player, opponent).start();
                } else {
                    waitingPlayers.add(player);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
