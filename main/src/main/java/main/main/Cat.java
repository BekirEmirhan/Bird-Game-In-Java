package main.main;

import java.util.Random;

public class Cat {
    private int x;
    private int y;
    private boolean isVisible = true;
    private int direction = -2;
    public Cat(int lb,int ub){
        Random rand = new Random(System.currentTimeMillis());
        this.x = rand.nextInt((380 - 5) + 1) + 5;
        this.y = rand.nextInt((ub - lb) + 1) + lb;
    }
     public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setD(int d){
        this.direction = d;
    }
    public int getD(){
        return this.direction;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public boolean getV(){
        return this.isVisible;
    }
    public void setV(boolean b){
        isVisible = b;
    }
}

