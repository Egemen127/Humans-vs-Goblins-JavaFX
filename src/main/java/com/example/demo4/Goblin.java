package com.example.demo4;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

import static com.example.demo4.HelloController.TILE_SIZE;

public class Goblin extends Player {
Goblin(int att,int health,int x,int y){
    super(att,health,x,y);
    Random r = new Random();
    this.items[0] = r.nextBoolean();
    this.items[1] = r.nextBoolean();
    this.items[2] = r.nextBoolean();
}
    Goblin(int att, int health, int x, int y, Image img){
        super(att,health,x,y,img);
        Random r = new Random();
        this.items[0] = r.nextBoolean();
        this.items[1] = r.nextBoolean();
        this.items[2] = r.nextBoolean();
    }
    public void GoblinMove(Board b){
            if(this.token.getY()<b.human.token.getY())
            {this.token.setY(this.token.getY()+TILE_SIZE);
            } else
            if(this.token.getY()>b.human.token.getY())
            { this.token.setY(this.token.getY()-TILE_SIZE);
            }else
            if(this.token.getX()>b.human.token.getX())
            {this.token.setX(this.token.getX()-TILE_SIZE);
            }else
            if(this.token.getX()<b.human.token.getX())
            {this.token.setX(this.token.getX()+TILE_SIZE);
            }

        }
    }

