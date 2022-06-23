package com.example.demo4;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.example.demo4.HelloController.BOARD_SIZE;
import static com.example.demo4.HelloController.TILE_SIZE;

public class Human extends Player{
    Human(int att,int health,int x,int y){
        super(att,health,x,y);
        Image img = new Image("file:src/img.png");
        ImageView htoken= new ImageView(img);
        htoken.setFitWidth(TILE_SIZE);
        htoken.setFitHeight(TILE_SIZE);
        this.token = htoken;
        this.token.setX(x*TILE_SIZE);
        this.token.setY(y*TILE_SIZE);
    }
    public double move_up(){
        double y = this.token.getY();
        if(y>0)
            this.token.setY(y-=TILE_SIZE);
        return this.token.getY();

    }
    public double move_down(){
        double y = this.token.getY();
        if(y<TILE_SIZE*(BOARD_SIZE-1))
            this.token.setY(y+=TILE_SIZE);
        return this.token.getY();
    }
    public double move_left(){
        double x = this.token.getX();
        if(x>0)
            this.token.setX(x-=TILE_SIZE);
        return this.token.getX();
    }
    public double move_right(){
        double x = this.token.getX();
        if(x<TILE_SIZE*(BOARD_SIZE-1))
            this.token.setX(x+=TILE_SIZE);
        return this.token.getX();
    }
}
