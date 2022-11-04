package main.main;
public class GameService {
    private int point = 0;
    public GameService(){
        
    }
    public void incrPoint(){
        point += 100;
    }
    public int getPoint(){
        return point;
    }
    public void setPoint(int point){
        this.point = point;
    }
}
