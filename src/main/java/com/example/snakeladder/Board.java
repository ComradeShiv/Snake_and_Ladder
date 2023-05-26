package com.example.snakeladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer, Integer>> positionCoordinates;
    ArrayList<Integer> snakeLadderPosition;

    public Board() {
        positionCoordinates = new ArrayList<>();
        populatePositionCoordinates();
        populateSnakeLadder();
    }

    private void populatePositionCoordinates() {
        positionCoordinates.add(new Pair<>(0, 0));
        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {

                int xCord = 0;
                if(i%2 == 0) {
                    xCord = j*SnakeLadder.tileSize + SnakeLadder.tileSize/2;
                } else{
                    xCord = SnakeLadder.height*SnakeLadder.tileSize - j*SnakeLadder.tileSize - SnakeLadder.tileSize/2;
                }
                int yCord = SnakeLadder.height*SnakeLadder.tileSize - i*SnakeLadder.tileSize - SnakeLadder.tileSize/2;
                positionCoordinates.add(new Pair<>(xCord, yCord));
            }
        }
    }

    private void populateSnakeLadder() {
        snakeLadderPosition = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeLadderPosition.add(i);
        }
        snakeLadderPosition.set(8, 14);
        snakeLadderPosition.set(10, 6);
        snakeLadderPosition.set(11, 32);
        snakeLadderPosition.set(16, 36);
        snakeLadderPosition.set(17, 3);
        snakeLadderPosition.set(26, 87);
        snakeLadderPosition.set(28, 15);
        snakeLadderPosition.set(38, 56);
        snakeLadderPosition.set(41, 62);
        snakeLadderPosition.set(50, 72);
        snakeLadderPosition.set(63, 21);
        snakeLadderPosition.set(68, 49);
        snakeLadderPosition.set(75, 33);
        snakeLadderPosition.set(78, 99);
        snakeLadderPosition.set(82, 80);
        snakeLadderPosition.set(89, 19);
        snakeLadderPosition.set(90, 92);
        snakeLadderPosition.set(95, 58);
        snakeLadderPosition.set(97, 88);
    }

    public int getNewPosition(int currentPosition) {
        if(currentPosition > 0 && currentPosition <= 100) {
            return snakeLadderPosition.get(currentPosition);
        }
        return -1;
    }

    int getXCoordinate(int position) {
        if(position >= 1 && position <= 100)
            return positionCoordinates.get(position).getKey();
        return -1;
    }

    int getYCoordinate(int position) {
        if(position >= 1 && position <= 100)
            return positionCoordinates.get(position).getValue();
        return -1;
    }

//    public static void main(String[] args) {
//        Board board = new Board();
//        for (int i = 0; i < board.positionCoordinates.size(); i++) {
//            System.out.println(i + " $  x : " + board.positionCoordinates.get(i).getKey() +
//                    "    y : " + board.positionCoordinates.get(i).getValue());
//        }
//    }
}
