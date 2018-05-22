import processing.core.PApplet;

public class Level {

    private int amount;
    private boolean isPlaying = false;

    private static PApplet p = MainClass.processing;

    public Level(int amount){
        this.amount = amount;
    }

    public void draw(){
        p.textSize(32);
        p.fill(256, 256, 256);
        p.text(amount, 760, 560);
    }

    public int getLevel() {
        return this.amount;
    }
    public int setLevel(int n) {
        return this.amount = n;
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }
    public boolean setIsPlaying(boolean value){
        return isPlaying = value;
    }
}