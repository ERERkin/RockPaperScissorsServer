package org.example;

import java.io.*;
import java.net.*;

public class PlayerHandler extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String username;
    private PlayerHandler opponent;

    private volatile String move;

    private volatile boolean isWorking = true;

    public PlayerHandler(Socket socket) {
        this.socket = socket;
    }

    public void setOpponent(PlayerHandler opponent) {
        this.opponent = opponent;
    }

    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("Добро пожаловать! Введите ваш ник: ");
            username = reader.readLine();
            writer.println("Привет, " + username + "! Ожидайте начала игры.");

            while (opponent == null) {
                Thread.sleep(100);
            }

            writer.println("Начинаем игру против " + opponent.username);

            while (isWorking) {
                if (move != null) {
                    continue;
                }
                writer.println("Введите букву k(камень), n(ножницы) или b(бумагу): ");
                String move = reader.readLine();
                this.move = move;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void send (String message) {
        writer.println(message);
    }

    public void closeSession() {
        try {
            socket.close();
            isWorking = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }
}
