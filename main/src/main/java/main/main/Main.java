package main.main;

public class Main {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + "source folder";
        GameService Gserv = new GameService();
        BackgroundService bs1 = new BackgroundService(Gserv,path + "bg1.png"); // BACKGROUND 1
        KeyEvents key = new KeyEvents(Gserv,bs1);
        bs1.openWin(600, 700);
        bs1.setBg();
        key = null;
        bs1 = null;
    }
}
