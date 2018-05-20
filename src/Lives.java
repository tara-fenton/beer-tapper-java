import processing.core.PApplet;

public class Lives {

    private int amount;
    private static PApplet p = MainClass.processing;


    public Lives(int amount){
        this.amount = amount;
    }

    public void draw(){
        // yellow
        for (int i = 0; i < this.amount; i++) {
            p.fill(252,252,0);
            p.noStroke();
            p.rect(350 + (Beer.BEER_WIDTH + Beer.BEER_WIDTH) * i ,20,Beer.BEER_WIDTH,Beer.BEER_HEIGHT);
        }

    }
    public int getLives() {
        return this.amount;
    }
    public int setLives(int n) {
        return this.amount = n;
    }
}
