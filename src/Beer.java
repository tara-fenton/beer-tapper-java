import processing.core.PApplet;
import processing.core.PGraphics;

public class Beer {

    private static int BEER_START_X = 602;
    private static int BEER_START_Y = 110;
    private static int BEER_WIDTH = 15;
    private static int BEER_HEIGHT = 30;
    private int currentX;

    private static PApplet p = MainClass.processing;

    private static PGraphics pg =  p.createGraphics(BEER_WIDTH,BEER_HEIGHT);

    public Beer(int x){
        currentX = x;
    }

    public void draw(){
        pg.beginDraw();
        pg.background(252,252,0);
        pg.noStroke();
        pg.rect(this.currentX,BEER_START_Y,BEER_WIDTH,BEER_HEIGHT);
        pg.endDraw();
        p.image(pg, this.currentX,BEER_START_Y,BEER_WIDTH,BEER_HEIGHT);

        currentX = currentX - 1;
    }
}
