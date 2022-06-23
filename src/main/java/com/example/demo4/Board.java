package com.example.demo4;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

import static com.example.demo4.HelloController.TILE_SIZE;

public class Board {
     ArrayList<Rectangle> tiles= new ArrayList<>();
     ArrayList<Goblin> goblins;
     ArrayList<Treasure> treasures = new ArrayList<>();
     Human human;
    int size;
    public Board(ArrayList<Goblin> goblins,int size,Human h) {
        this.size = size;
        this.human = h;
        double x = 0.0;
        double y = 0.0;
        boolean color = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            Rectangle r = new Rectangle();
            r.setX(x);
            r.setY(y);
            r.setWidth(TILE_SIZE);
            r.setHeight(TILE_SIZE);
            if(color)
                r.setFill(Color.CORNSILK);
            else
                r.setFill(Color.LIMEGREEN);
            color = !color;
            x+=TILE_SIZE;
            tiles.add(r);
            }
            y+=TILE_SIZE;
            x=0;
            if(size%2==0)
            color = !color;
        }
        this.goblins = goblins;
        for (int i =0;i<5;i++)
            addTreasure();
    }
    public Treasure addTreasure(){
        Random r =new Random();
        int c1= r.nextInt(size);
        int c2= r.nextInt(size);
        Treasure t = new Treasure();
        t.token.setFitWidth(TILE_SIZE);
        t.token.setFitHeight(TILE_SIZE);
        t.token.setY(c1*TILE_SIZE);
        t.token.setX(c2*TILE_SIZE);
        treasures.add(t);
        return t;
    }

    public Goblin isCombat(){
        for(var i:this.goblins){
            if(i.token.getX()==this.human.token.getX()&&i.token.getY()==this.human.token.getY())
                return i;
        }
        return null;
    }
    public Treasure isTreasure(){
        for(var i:this.treasures){
            if(i.token.getX()==this.human.token.getX()&&i.token.getY()==this.human.token.getY())
                return i;
        }
        return null;
    }
}
