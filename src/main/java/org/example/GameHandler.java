package org.example;

import java.util.logging.*;

public class GameHandler extends Thread {
    private PlayerHandler player1;
    private PlayerHandler player2;

    public GameHandler(PlayerHandler player1, PlayerHandler player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void run() {
        player1.setOpponent(player2);
        player2.setOpponent(player1);
        boolean isNotWaiting = true;
        while (true) {
            while (player1.getMove() == null || player2.getMove() == null) {
                if (player1.getMove() == null && player2.getMove() != null && isNotWaiting) {
                    player2.send("Ожидайте хода противника");
                    isNotWaiting = false;
                }
                if (player2.getMove() == null && player1.getMove() != null && isNotWaiting) {
                    player1.send("Ожидайте хода противника");
                    isNotWaiting = false;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (player1.getMove().equals(player2.getMove())) {
                player1.send("Ничья! Попробуйте еще раз.");
                player2.send("Ничья! Попробуйте еще раз.");
                player1.setMove(null);
                player2.setMove(null);
                isNotWaiting = true;
            } else if ((player1.getMove().equals("k") && player2.getMove().equals("n")) ||
                    (player1.getMove().equals("n") && player2.getMove().equals("b")) ||
                    (player1.getMove().equals("b") && player2.getMove().equals("k"))) {
                player1.send(message(player1, player2));
                player2.send(message(player2, player1));
                player1.send("Вы выиграли! Игра окончена.");
                player2.send("Вы проиграли. Игра окончена.");
                player1.closeSession();
                player2.closeSession();
                break;
            } else {
                player1.send(message(player1, player2));
                player2.send(message(player2, player1));
                player1.send("Вы проиграли. Игра окончена.");
                player2.send("Вы выиграли! Игра окончена.");
                player1.closeSession();
                player2.closeSession();
                break;
            }
        }
    }

    private String message(PlayerHandler player, PlayerHandler playerOpponent) {
        String choice1 = getChoice(player);

        String choice2 = getChoice(playerOpponent);

        return "Вы выбрали " + choice1 + ", а игрок " + playerOpponent.getUsername() + " выбрал " + choice2;
    }

    private String getChoice(PlayerHandler player) {
        String choice = "";
        if (player.getMove().equals("k"))
            choice = "камень";
        else if (player.getMove().equals("n"))
            choice = "ножницы";
        else
            choice = "бумага";
        return choice;
    }


}
