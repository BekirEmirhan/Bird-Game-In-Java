package main.main;

import javax.swing.*;
public class Egg {
    private int x;
    private int y;
    private boolean checker = true;
    private JLabel picture;
    public Egg(int x,int y,JLabel p){
        this.x = x+80;
        this.y = y+85;
        this.picture = p;
        picture.setBounds(this.x,this.y,30,30);
    }
    int getX(){
        return this.x;
    }
    int getY(){
        return this.y;
    }
    void setX(int x){
        this.x = x;
    }
    void setY(int y){
        this.y = y;
    }
    JLabel getP(){
        return this.picture;
    }
    void setP(boolean b){
        this.picture.setVisible(b);
        checker = b;
    }
    void utility(int limit){
        if(checker){
            this.y+=4;
            picture.setBounds(this.x,this.y,30,30);
            if(this.y - limit> 500){
                this.picture.setVisible(false);
                checker = false;
            }
        }
    }
}

