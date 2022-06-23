package com.example.demo4;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Treasure {
    private Random r = new Random();
    ImageView token = new ImageView(new Image("file:src/img_2.png"));

    int type = r.nextInt(2);
}

