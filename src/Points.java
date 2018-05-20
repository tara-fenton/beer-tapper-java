import processing.core.PApplet;

public class Points {

    private int amount;
    private static PApplet p = MainClass.processing;

    public Points(int amount){
        this.amount = amount;
    }

    public void draw(){
        p.textSize(32);
        p.fill(256, 256, 256);
        p.text(amount, 20, 40);
    }

    public int getPoints() {
        return this.amount;
    }
    public int setPoints(int n) {
        return this.amount = n;
    }
}