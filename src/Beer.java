import processing.core.PApplet;
import processing.core.PGraphics;

public class Beer {

    private static int BEER_START_X = 602;
    private static int BEER_START_Y = 110;
    private static int BEER_WIDTH = 15;
    private static int BEER_HEIGHT = 30;
    private int currentX;
    private int currentY;

    private static PApplet p = MainClass.processing;

    private static PGraphics pg =  p.createGraphics(BEER_WIDTH,BEER_HEIGHT);

    public Beer(int x, int y){
        currentX = x;
        currentY = y;
    }

    public void draw(){
        pg.beginDraw();
        pg.background(252,252,0);
        pg.noStroke();
        pg.rect(this.currentX,this.currentY,BEER_WIDTH,BEER_HEIGHT);
        pg.endDraw();
        p.image(pg, this.currentX,this.currentY,BEER_WIDTH,BEER_HEIGHT);

        currentX = currentX - 1;
    }
}
