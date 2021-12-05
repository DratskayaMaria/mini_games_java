package main;

import javax.swing.*;
import snake_game.*;
import tetris.main.*;
import game_2048.*;
import tetris.main.StartTetris;

import java.nio.charset.MalformedInputException;

public class MainGames extends JFrame {
    private JPanel GamesPanel;
    private JButton TetrisButton;
    private JButton Game2048Button;
    private JButton SnakeGame;

    public MainGames(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(GamesPanel);
        this.pack();
        this.setSize(900, 700);

        TetrisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tetrisButtonActionPerformed(evt);
            }
        });

        Game2048Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                game2048ButtonActionPerformed(evt);
            }
        });

        SnakeGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snakeButtonActionPerformed(evt);
            }
        });
    }

    private void tetrisButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        boolean endOfGame = StartTetris.start();
        if (endOfGame){
            MainGames mainGames = new MainGames("Игры");
            this.dispose();
            mainGames.setLocationRelativeTo(null);
            mainGames.setVisible(true);
        }
    }

    private void game2048ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        Start2048Game.start();
    }

    private void snakeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        StartSnakeGame.start();
    }
}
