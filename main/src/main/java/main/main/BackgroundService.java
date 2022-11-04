package main.main;


import java.awt.*;
import java.net.URL;
import java.util.*;
import javax.swing.*;
public class BackgroundService extends JFrame implements Runnable {
    private Image img;
    private JFrame window;
    public JPanel bgPanel[] = new JPanel[10];
    private ImageIcon bgIcon,bird_img;
    private GameService Gserv;
    private JLabel labelScore = new JLabel("Score: 0");
    private JLabel background,bird;
    private Vector<JLabel> catImages = new Vector<JLabel>();
    Vector<Egg> ammo = new Vector<Egg>();
    private Vector<Cat> cats = new Vector<Cat>();
    String path = System.getProperty("user.dir") + "source path";
    private ImageIcon catImg = new ImageIcon(path + "cat.png");
    private Thread thread;
    private int birdX = 200,birdY = 150;
    private int backX =0,backY =0;
    private String bgImg;
    boolean stg1 = true,stg2 =false,stg3 = false;
    private int stg1_size = 33,stg2_size = 51,stg3_size =58; 
    public BackgroundService(GameService Gserv,String bgImg){
        bgIcon = new ImageIcon(bgImg);
        this.bgImg = bgImg;
        bird_img = new ImageIcon(path + "chicken.png");
        this.background =  new JLabel("",bgIcon,JLabel.CENTER);
        this.bird =  new JLabel("",bird_img,JLabel.CENTER);
        this.Gserv = Gserv;
        labelScore.setBounds(5, 5, 100, 50);
        labelScore.setFont(new Font("Copperplate",Font.BOLD,15));
    }
    public void setBg(){    
         this.background.setBounds(backX,backY,6000,9000);
         this.bird.setBounds(birdX,birdY,200,200);
         this.background.add(bird);
         window.add(this.background);
         thread = new Thread(this);
         thread.start();
    }
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
    public void openWin(int width,int height){
        this.window = new JFrame();
        this.window.setSize(width,height);
        window.setLayout(null);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setVisible(true);
        window.addKeyListener(new KeyEvents(Gserv,this));
        window.add(labelScore);
    }
    public void printScore(){
        int temp = Gserv.getPoint();
        String p =  "Score: " + String.valueOf(temp);
        labelScore.setText(p);
    }
    public JLabel getBg(){
        return this.background;
    }
    public void bulletBoundry(){
        for(int i=0; i<ammo.size();i++){
            if(ammo.get(i).getP().isVisible() == false) continue;
            for(int j=0;j<cats.size();j++){
                int lx =  cats.get(j).getX()-5,rx = cats.get(j).getX() + 100,uy = cats.get(j).getY() + 15,dy = cats.get(j).getY() + 75;
                if(ammo.get(i).getX()<=rx && ammo.get(i).getX() >= lx && ammo.get(i).getY()>= uy && ammo.get(i).getY()<=dy && catImages.get(j).isVisible() == true){
                    catImages.get(j).setVisible(false);
                    Gserv.incrPoint();
                    ammo.get(i).setP(false);
                    printScore();
                }
            }
        }
    }
    public void specialCat(Vector<Integer> specials,int size){
        for(int i=0;i<size;i++){
            cats.get(specials.get(i)).setX(cats.get(specials.get(i)).getX()+cats.get(specials.get(i)).getD());
            if(cats.get(specials.get(i)).getX()>400 || cats.get(specials.get(i)).getX()<-10){
                cats.get(specials.get(i)).setD(cats.get(specials.get(i)).getD()*-1);
            }
            catImages.get(specials.get(i)).setBounds(cats.get(specials.get(i)).getX(),cats.get(specials.get(i)).getY(),150,150);
        }
    }
    public void init(Vector<Integer> specials){
        for(int i=0;i<specials.size();i++){
            if(i%4 == 0 && i!= 0){
                cats.get(specials.get(i)).setD(cats.get(specials.get(i)).getD()*2);
            }
            else if(i%3==0 && i!=0 && stg3){
                cats.get(specials.get(i)).setD(cats.get(specials.get(i)).getD()*2);
            }
            else if(i%10==0 && i!=0 && stg3){
                cats.get(specials.get(i)).setD(cats.get(specials.get(i)).getD()*3);
            }
        }
    }
    public void utilize(){
        int size = ammo.size();
        for(int i=0;i<size;i++) ammo.get(i).utility(birdY);
    }
    public int boundry(int x,int y,int a,int size){
        for(int i=0;i<size;i++){
            int lx =  cats.get(i).getX()-a-50;
            int rx = cats.get(i).getX() + a;
            int uy = cats.get(i).getY() -a-10;
            int dy = cats.get(i).getY() + a;
            if(x<=rx && x >= lx && y>= uy && y<=dy && catImages.get(i).isVisible() == true){
                return -1;
            }
        }
        return 0;
    }
    @Override
    public void run() {
        int[][] locations = {{300,400},{500,750},{800,950},{1050,1200},{1250,1400},{1500,1650},{1750,1900},{2000,2150},{2200,2350},{2400,2550},
        {2620,2750},{2800,2925},{2975,3000},{3100,3125},{3225,3300},{3400,3500},{3600,3700},{3800,3900},{4000,4050},{4125,4200},
        {4300,4325},{4400,4425},{4500,4550},{4650,4700},{4775,4800},{4875,5000},{5100,5200},{5300,5400},{5500,5600},{5675,5700},
        {5775,5800},{5875,5900},{5975,6000},{6075,6100},{6200,6300},{6400,6500},{6600,6700},{6800,6900},{6950,7000},{7100,7110},      
        {7200,7300},{7400,7500},{7600,7700},{7800,7810},{7900,7910},{8000,8100},{8200,8220},{8310,8400},{8500,8600},{8700,8800},
        {8900,9000},{9100,9200},{9300,9400},{9500,9600},{9700,9800},{9900,10000},{10100,10200},{10300,10400}};
        Vector<Integer> specials = new Vector<>();
        for(int i=0;i<stg1_size;i++){
                catImages.add(new JLabel("", (Icon) catImg,JLabel.CENTER));
                cats.add(new Cat(locations[i][0],locations[i][1]));
                boolean overall = false;
                for(int j=0;j<i;j++){
                    int t = boundry(cats.get(i).getX(),cats.get(i).getY(),80,cats.size()-1);
                    if(t==-1) overall = true;
                }
                if(overall){
                    catImages.remove(i);
                    cats.remove(i);
                    i--;
                }else{
                catImages.get(i).setBounds(cats.get(i).getX(),cats.get(i).getY(),150,150);
                this.background.add(catImages.get(i));
                }
            }
        int b = 7000;
        init(specials);
        this.bird.setBounds(birdX,birdY,200,200);
        this.background.setBounds(backX,backY,600,b);
        JOptionPane.showMessageDialog(window,"Game Rules: \nd: move right\na: move left\nspace: fire");
        while(true){
            birdY+=2;
            backY-=2;
            this.bird.setBounds(birdX,birdY,200,200);
            this.background.setBounds(backX,backY,600,b);
            specialCat(specials,specials.size());
            if(backY<-6300 && stg2 == false ){
                backY = 0;
                birdY = 0;
                stg2 = true;
                for(int i=0;i<catImages.size();i++){
                    catImages.get(i).setVisible(false);
                }
                specials.add(0);specials.add(1);specials.add(3);specials.add(6);specials.add(7);specials.add(10);specials.add(11);specials.add(12);
                specials.add(14);specials.add(16);specials.add(19);specials.add(20);specials.add(25);specials.add(30);specials.add(31);specials.add(32);
                specials.add(38);specials.add(42);specials.add(45);specials.add(50);specials.add(50);
                b= 10000;
                cats.removeAllElements();
                catImages.removeAllElements();
                for(int i=0;i<stg2_size;i++){
                    catImages.add(new JLabel("", (Icon) catImg,JLabel.CENTER));
                    cats.add(new Cat(locations[i][0],locations[i][1]));
                    catImages.get(i).setBounds(cats.get(i).getX(),cats.get(i).getY(),150,150);
                    this.background.add(catImages.get(i));
                }
                this.background.setIcon(null);
                this.background.setBackground(Color.BLACK);
                this.background.setIcon(new ImageIcon(path + "bg2.png")); // BACKGROUND 2
                init(specials);
            }
            else if(backY<-9150 && stg3 == false  && stg2 == true){
                backY = 0;
                birdY = 0;
                stg3 = true;
                for(int i=0;i<catImages.size();i++){
                    catImages.get(i).setVisible(false);
                }
                cats.removeAllElements();
                catImages.removeAllElements();
                b= 12000;
                specials.removeAllElements();
                specials.add(0);specials.add(1);specials.add(3);specials.add(4);specials.add(6);specials.add(7);specials.add(9);specials.add(10);
                specials.add(11);specials.add(12);specials.add(14);specials.add(16);specials.add(19);specials.add(20);specials.add(25);
                specials.add(30);specials.add(31);specials.add(32);specials.add(38);specials.add(42);specials.add(45);specials.add(50);specials.add(52);
                specials.add(54);specials.add(55);specials.add(57);
                for(int i=0;i<stg3_size;i++){
                    catImages.add(new JLabel("", (Icon) catImg,JLabel.CENTER));
                    cats.add(new Cat(locations[i][0],locations[i][1]));
                    catImages.get(i).setBounds(cats.get(i).getX(),cats.get(i).getY(),150,150);
                    this.background.add(catImages.get(i));
                }
                bgIcon = new ImageIcon(bgImg);
                this.background.setIcon(null);
                this.background.setBackground(Color.BLACK);
                // BACKGROUND 3
                this.background.setIcon(new ImageIcon(path + "bg3.png"));
                init(specials);
            }
            else if(backY<-10850 && stg3 == true){
                thread.interrupt();
                String message = String.format("CONGRATULATIONS!!! YOU WON YOUR SCORE: %d",Gserv.getPoint());;
                JOptionPane.showMessageDialog(window,message);
                return;
            }
            utilize();
            int temp = boundry(birdX,birdY,75,cats.size());
            bulletBoundry();
            if(temp == -1){
                thread.interrupt();
                String message = String.format("GAME OVER YOUR SCORE: %d",Gserv.getPoint());;
                JOptionPane.showMessageDialog(window,message);
                return;
            }
            try{
                thread.sleep(25);
            }
            catch(InterruptedException e){
                System.out.println("Error in exe thread: " + e);
            }
        }
    }
    public int getBX(){
        return birdX;
    }
    public int getBY(){
        return birdY;
    }
    public int getBGY(){
        return backY;
    }
    public void setBX(int x){
        birdX = x;
    }
    public void setBY(int y){
        birdY = y;
    }
    public void setBGY(int y){
        backY = y;
    }
    
}
