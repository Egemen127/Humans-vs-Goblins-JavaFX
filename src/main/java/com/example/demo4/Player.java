package com.example.demo4;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.example.demo4.HelloController.TILE_SIZE;

public abstract class Player {
    int att;
    int health;
    Image img = new Image("file:src/img_1.png");
    ImageView token= new ImageView(img);
    boolean[] items = {false,false,false};
    Player(int att,int health,int x,int y,Image img){
        this.att = att;
        this.health=health;
        this.img = img;
        this.token = new ImageView(img);
        this.token.setFitHeight(TILE_SIZE);
        this.token.setFitWidth(TILE_SIZE);
        this.token.setX(x*TILE_SIZE);
        this.token.setY(y*TILE_SIZE);

    }
    Player(int att,int health,int x,int y){
        this.att = att;
        this.health=health;
        token.setX(x*TILE_SIZE);
        token.setY(y*TILE_SIZE);
        token.setFitHeight(TILE_SIZE);
        token.setFitWidth(TILE_SIZE);
        this.img = img;
        this.token = token;

    }
    Player(){
        this.att = 3;
        this.health = 3;
        this.token.setY(0);
        this.token.setX(0);
    }
    public String toString(){
        String items = "";
        if(this.items[0]){
            items+="Unstable Dynamite(10% Chance of critical damage)\n";
        }
        if(this.items[1]){
            items+="Invisibility Cloak(%5 Chance of parry the next attack)\n";}
        if(this.items[2]){
            items+="Wooden Shield(Reduces the damage taken by 1)\n";
        }
        return "STATS\n"+"Attack: "+this.att+"\n Health: "+this.health+"\n"+"Items:\n"+items;
    }
}
