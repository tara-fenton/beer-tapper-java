import processing.core.PApplet;
import processing.core.PFont;

public class GetReady {

    private int amount;

    private static PApplet p = MainClass.processing;

    public GetReady(int amount){
        this.amount = amount;
    }

    public void draw(){

        PFont mina;
        mina = p.createFont("fonts/Mina-Regular.ttf", 60);
        p.textFont(mina);
        p.text("get ready to serve", 160, 320);
        p.fill(252, 252, 0);
    }

    public int getTime() {
        return this.amount;
    }
    public int setTime(int n) {
        return this.amount = n;
    }
}