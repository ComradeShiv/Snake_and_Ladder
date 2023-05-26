package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {

    public static final int tileSize = 55, width = 10, height = 10;
    public static final int buttonLine = height*tileSize + 35, infoLine = height*tileSize + 15;
    private static Dice dice = new Dice();
    private static Player playerOne, playerTwo;
    private boolean gameStarted = false, playerOneTurn = false, playerTwoTurn = false;
    private Pane createContent(){

        Pane root = new Pane();
        root.setPrefSize(tileSize*width, tileSize*height + 100);
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {

                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }

        Image img = new Image("C:\\Users\\asus\\IdeaProjects\\Snake Ladder\\src\\main\\snake-ladder-board-game.jpg");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);

        Button playerOneButton = new Button("Player One");
        Button playerTwoButton = new Button("Player two");
        Button startButton = new Button("Start");

        playerOneButton.setDisable(true);
        playerTwoButton.setDisable(true);

        playerOneButton.setTranslateX(40);
        playerOneButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(440);
        playerTwoButton.setTranslateY(buttonLine);
        startButton.setTranslateX(255);
        startButton.setTranslateY(buttonLine);

        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Label diceLabel = new Label("Start The Game");

        playerOneLabel.setTranslateY(infoLine);
        playerOneLabel.setTranslateX(40);
        playerTwoLabel.setTranslateY(infoLine);
        playerTwoLabel.setTranslateX(440);
        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(235);

        playerOne = new Player(tileSize-10, Color.NAVY, "Shiv");
        playerTwo = new Player(tileSize-20, Color.DARKGRAY, "Shivam");

//        Player Action
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted) {
                    if(playerOneTurn) {
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : " + diceValue);
                        playerOne.movePlayer(diceValue);
//                        Winning condition cross check
                        if(playerOne.isWinner()) {
                            diceLabel.setText("Winner is " + playerOne.getName());

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");
                        } else{
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false);
                            playerTwoLabel.setText("Your Turn : " + playerTwo.getName());
                        }
                    }
                }
            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted) {
                    if(playerTwoTurn) {
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : " + diceValue);
                        playerTwo.movePlayer(diceValue);
//                        Winning condition cross check
                        if(playerTwo.isWinner()) {
                            diceLabel.setText("Winner is " + playerTwo.getName());

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");
                        } else{
                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your Turn : " + playerOne.getName());
                        }
                    }
                }
            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                diceLabel.setText("Game Started");
                startButton.setDisable(true);

                playerOneTurn = true;
                playerOneLabel.setText("Your Turn : " + playerOne.getName());
                playerOneButton.setDisable(false);
                playerOne.startingPosition();

                playerTwoTurn = false;
                playerTwoLabel.setText("");
                playerTwoButton.setDisable(true);
                playerTwo.startingPosition();
            }
        });

        root.getChildren().addAll(board,
                playerOneButton, playerTwoButton, startButton,
                playerOneLabel, playerTwoLabel, diceLabel,
                playerOne.getCoin(), playerTwo.getCoin()
        );

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}