import processing.core.PApplet;
import processing.core.PGraphics;

public class Bartender {

    // positions for controlling bartender
    private static int BARTENDER_START_X = 620;
    private static int BARTENDER_START_Y = 100;
    private static int BARTENDER_WIDTH = 40;
    private static int BARTENDER_HEIGHT = 80;
    private static int currentX = BARTENDER_START_X;
    private static int currentY = BARTENDER_START_Y;

    private static PApplet p = MainClass.processing;
    private static PGraphics pg = p.createGraphics(BARTENDER_WIDTH,BARTENDER_HEIGHT);


    public void draw(){
        pg.beginDraw();
        pg.background(196,37,215);
        pg.noStroke();
        pg.rect(currentX,currentY,BARTENDER_WIDTH,BARTENDER_HEIGHT);
        pg.endDraw();
        p.image(pg, currentX,currentY,BARTENDER_WIDTH,BARTENDER_HEIGHT);
    }

    public static int getStartX(){
        return BARTENDER_START_X;
    }

    public static int getStartY(){
        return BARTENDER_START_Y;
    }

    public static int getCurrentX(){
        return currentX;
    }

    public static int setCurrentX(int x){
        return currentX = x;
    }

    public static int getCurrentY(){
        return currentY;
    }

    public static void setCurrentY(int y){
        currentY = y;
    }

}
