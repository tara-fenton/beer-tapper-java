import processing.core.PApplet;
import processing.core.PGraphics;

public class Bartender {
    // bartender display
    //var $bartenderDiv = $("<div id='bartender'></div>");
    //$bartenderDiv.css("top", BARTENDER_START_Y + "px");
    //$bartenderDiv.css("left", BARTENDER_START_X + "px");

    // positions for controlling bartender
    private static int BARTENDER_START_X = 620;
    private static int BARTENDER_START_Y = 100;
    private static int BARTENDER_WIDTH = 40;
    private static int BARTENDER_HEIGHT = 80;
    private static int currentXbartender = BARTENDER_START_X;
    private static int currentYbartender = BARTENDER_START_Y;
    private static int newYbartender = 0;
    private static int newXbartender = 0;

    private static PApplet p = MainClass.processing;
    private static PGraphics pg = p.createGraphics(BARTENDER_WIDTH,BARTENDER_HEIGHT);


    public void draw(){
        pg.beginDraw();
        pg.background(196,37,215);
        pg.noStroke();
        pg.rect(currentXbartender,currentYbartender,BARTENDER_WIDTH,BARTENDER_HEIGHT);
        pg.endDraw();
        p.image(pg, currentXbartender,currentYbartender,BARTENDER_WIDTH,BARTENDER_HEIGHT);
    }

    public static int getBartenderStartX(){
        return BARTENDER_START_X;
    }

    public static int getStartY(){
        return BARTENDER_START_Y;
    }

    public static int getCurrentXbartender(){
        return currentXbartender;
    }

    public static int setCurrentXbartender(int x){
        return currentXbartender = x;
    }

    public static int getCurrentY(){
        return currentYbartender;
    }

    public static void setCurrentY(int y){
        currentYbartender = y;
    }

    public static int getHeight(){
        return BARTENDER_HEIGHT;
    }


}
