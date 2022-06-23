package com.example.demo4;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class HelloController {
    static final int BOARD_SIZE = 20;
    static final int TILE_SIZE = 25;
    private Stage stage;
    private Scene scene;
    private AnchorPane root;
    public void onHelloButtonClick(ActionEvent event) {
        ArrayList<Goblin> Goblins = new ArrayList<>();
       Goblins.add(new Goblin(3,15,9,5));
        Goblins.add(new Goblin(3,15,3,0,new Image("file:src/img_6.png")));
        Goblins.add(new Goblin(3,12,9,0,new Image("file:src/img_7.png")));
       Goblins.add(new Goblin(13,5,2,8));
       Goblins.add(new Goblin(3,15,5,5));
        Human h = new Human(3,10,0,0);
        Board b = new Board(Goblins,BOARD_SIZE,h);
        root = new AnchorPane();
        Label nameLabel = new Label();
        Label combat = new Label("Combat Summary");
        combat.setLayoutX(BOARD_SIZE*TILE_SIZE+200);
        combat.setLayoutY(0);
        root.getChildren().add(combat);
        Label statLabel = new Label();

        statLabel.setLayoutY(BOARD_SIZE*TILE_SIZE+5);
        statLabel.setLayoutX(TILE_SIZE);
        statLabel.setText(b.human.toString());
        statLabel.setTextFill(Color.DODGERBLUE);
        nameLabel.setLayoutX(BOARD_SIZE*TILE_SIZE+200);
        nameLabel.setLayoutY(TILE_SIZE);
        root.getChildren().add(nameLabel);
        root.getChildren().add(statLabel);
        for(var i:b.tiles) {
            root.getChildren().add(i);
        }
        for(var g:b.goblins){
            EventHandler<MouseEvent> eventHandler2 = e -> {
                statLabel.setText(b.human.toString());
                statLabel.setTextFill(Color.DODGERBLUE);
            };
            EventHandler<MouseEvent> eventHandler = e -> {
                statLabel.setText(g.toString());
                statLabel.setTextFill(Color.RED);
                statLabel.setFont(Font.font("Lucida Console"));
            };
            g.token.addEventFilter(MouseEvent.MOUSE_ENTERED,eventHandler);
            g.token.addEventFilter(MouseEvent.MOUSE_EXITED,eventHandler2);
            root.getChildren().add(g.token);
        }
        for(var i:b.treasures){
            root.getChildren().add(i.token);
        }
        root.getChildren().add(h.token);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("file:src/index.css");
        scene.setOnKeyPressed(event1 -> {
            switch (event1.getCode()) {
                case UP:  {
                    b.human.move_up();
                    for (var i :b.goblins) {i.GoblinMove(b);}
                }; break;
                case DOWN: {
                    b.human.move_down();

                    for (var i :b.goblins) {i.GoblinMove(b);}
                } break;
                case LEFT:  {
                    b.human.move_left();
                    for (var i :b.goblins) {i.GoblinMove(b);}
                } break;
                case RIGHT: {
                    b.human.move_right();
                    for (var i :b.goblins) {i.GoblinMove(b);}
                } break;
            }
            if(b.isCombat()!=null){
                Random r = new Random();
                int H_Health = b.human.health;
                int G_Health = b.isCombat().health;
                int H_Att = b.human.att;
                int G_Att = b.isCombat().att;
                boolean[] H_Items = b.human.items;
                boolean[] G_Items = b.isCombat().items;
                String username="";
                while(true){

                    double dmg = Math.floor(Math.random()*10%H_Att)+1;
                    if(G_Items[2]){
                        G_Health-= dmg-1;
                    }
                    if(G_Items[1]){
                        int a = r.nextInt(20);
                        if(a==10){
                            username+="Enemy avoided your attack!\n";
                            dmg=0;
                        }}else
                    if(H_Items[0]){
                        int a = r.nextInt(10);
                        if(a==0){
                            username+="Critical hit!\n";
                            username+="Human attacks: "+dmg+"\n";
                            G_Health -= dmg;}}

                    G_Health-= dmg;
                    username+="Human attacks: "+dmg+"\n";
                    username+="Goblin health left "+ G_Health+"\n";
                    System.out.println();
                    if(G_Health<=0){
                        username+="Human Won.\n";
                        root.getChildren().add(b.addTreasure().token);
                        int f = r.nextInt(4);
                        if(f==0)
                            username+="You didn't get any item from this goblin\n";
                        else if(f==1){
                            username+="You obtained: Unstable Dynamite(10% Chance of critical damage)\n";
                            H_Items[0]=true;
                        }
                        else if(f==2){
                            username+="You obtained: Invisibility Cloak(%5 Chance of parry the next attack)";
                            H_Items[1]=true;
                        }
                        else {
                            username+="You obtained: Wooden Shield(Reduces the damage taken by 1)\n";
                            H_Items[2]=true;
                        }
                        b.isCombat().token.setImage(new Image("file:src/img_3.png"));
                        b.goblins.remove(b.isCombat());
                        nameLabel.setText(username);
                        break;
                        }

                    dmg=Math.floor(Math.random()*10%G_Att)+1;
                    if(H_Items[2]){
                        H_Health-= dmg-1;
                    }
                    if(H_Items[1]){
                        int a = r.nextInt(20);
                        if(a==10){
                            username+="You avoided enemies attack!\n";
                            dmg=0;
                        }}else
                    if(G_Items[0]){
                        int a = r.nextInt(10);
                        if(a==0){
                            username+="Critical hit!\n";
                            username+="Goblin attacks: "+dmg+"\n";
                            H_Health -= dmg;}}

                    H_Health-= dmg;
                    username+="Goblin attacks: "+dmg+"\n";
                    username+="Human health left "+H_Health+"\n";
                    if(H_Health<=0){
                        username+="Goblin Won.\n";
                        final Stage dialog = new Stage();
                        dialog.initModality(Modality.APPLICATION_MODAL);
                        dialog.initOwner(stage);
                        Button mainMenu = new Button("Return to main screen");
                        mainMenu.addEventFilter(MouseEvent.MOUSE_CLICKED,e->{
            try {
               Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
               Scene scene1 = new Scene(root);
               stage.setScene(scene1);
                stage.show();
                dialog.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
                        VBox dialogVbox = new VBox(20);
                        dialogVbox.getChildren().add(new Label("You Lost. Game Over"));
                        dialogVbox.getChildren().add(mainMenu);
                        Scene dialogScene = new Scene(dialogVbox);
                        dialog.setScene(dialogScene);
                        dialog.setResizable(false);
                        dialog.setTitle("Game Over");
                        dialog.show();
                        nameLabel.setText(username);
                        b.human.token = null;
                        break;
                        }
                }
            }
            if(b.isTreasure()!=null){
                int t = b.isTreasure().type;
                if(t==0){
                    b.human.att++;
                } else
                    b.human.health++;

                b.isTreasure().token.setImage(null);
                b.treasures.remove(b.isTreasure());
            }
        });
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}