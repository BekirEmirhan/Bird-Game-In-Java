package main.main;

import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
public class KeyEvents implements KeyListener{
    private GameService Gserv;
    private BackgroundService bs;
    private Bird bird = new Bird();
    String path = System.getProperty("user.dir") + "source path";
    private ImageIcon ammoPicture = new ImageIcon(path + "egg.png");
    public KeyEvents(GameService Gserv,BackgroundService bs){
        this.Gserv = Gserv;
        this.bs = bs;
        bird.setX(bs.getBX());
        bird.setY(bs.getBY());
    }
    public void keyPressed(KeyEvent k){
        char get = k.getKeyChar();
        if(get=='d'){
            if(bird.getX()>400) {
                bird.setX(400);
                bs.setBX(400);
            }
            bird.setX(bird.getX()+8);
            bs.setBX(bird.getX()+8);
        }
        else if(get=='a'){
            if(bird.getX()<-10){
                bird.setX(-10);
                bs.setBX(-10);
            }
            bird.setX(bird.getX()-8);
            bs.setBX(bird.getX()-8);
        }
    }
    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        char get = e.getKeyChar();
       if(get == ' '){
            JLabel tLabel = new JLabel("",ammoPicture,JLabel.CENTER);
            Egg temp = new Egg(bs.getBX(),bs.getBY()+10,tLabel);
            bs.ammo.add(temp);
            bs.getBg().add(bs.ammo.lastElement().getP());
        }
    }
}

