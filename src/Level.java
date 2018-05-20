import processing.core.PApplet;

public class Level {

    private int amount;
    private static PApplet p = MainClass.processing;

    public Level(int amount){
        this.amount = amount;
    }

    public void draw(){
        p.textSize(32);
        p.text(amount, 760, 560);
        p.fill(0, 102, 153);
    }

    public int getLevel() {
        return this.amount;
    }
    public int setLevel(int n) {
        return this.amount = n;
    }
}