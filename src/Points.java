import processing.core.PApplet;

public class Points {

    private int amount;
    private static PApplet p = MainClass.processing;

    public Points(int amount){
        this.amount = amount;
    }

    public void draw(){
        p.textSize(32);
        p.text(amount, 10, 30);
        p.fill(0, 102, 153);
    }

    public int getPoints() {
        return this.amount;
    }
    public int setPoints(int n) {
        return this.amount = n;
    }
}