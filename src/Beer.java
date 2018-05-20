import processing.core.PApplet;

public class Beer {

    private static int BEER_WIDTH = 15;
    private static int BEER_HEIGHT = 30;
    private int currentX;
    private int currentY;

    private static PApplet p = MainClass.processing;

    public Beer(int x, int y){
        currentX = x - 20;
        currentY = y + 10;
    }

    public void draw(){

        p.fill(252,252,0);
        p.noStroke();
        p.rect(this.currentX,this.currentY,BEER_WIDTH,BEER_HEIGHT);

        if (currentX > Bar.getStartX()) {
            currentX = currentX - 1;
        }
    }
    public int getCurrentX() {
        return this.currentX;
    }
}
