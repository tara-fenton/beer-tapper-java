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
    private static int currentYbartender = 0;
    private static int newYbartender = 0;
    private static int currentXbartender = 0;
    private static int newXbartender = 0;

    private static PApplet p = MainClass.processing;
//    private static PGraphics pg = p.PGraphics;
    private static PGraphics pg = p.createGraphics(BARTENDER_WIDTH,BARTENDER_HEIGHT);

    public void setup(){


    }
    public void settings() {
//        p.size(100, 100);
        //pg =
    }
    public void draw(){
        pg.beginDraw();
       pg.background(196,37,215);
        pg.noStroke();
//        pg.stroke(255);
//        pg.line(20, 20, p.mouseX, p.mouseY);
        pg.rect(BARTENDER_START_X,BARTENDER_START_Y,BARTENDER_WIDTH,BARTENDER_HEIGHT);
        pg.endDraw();
        p.image(pg, BARTENDER_START_X,BARTENDER_START_Y,BARTENDER_WIDTH,BARTENDER_HEIGHT);
//        p.fill(196,37,215);
//        p.noStroke();
//        System.out.println(BARTENDER_START_Y);

    }

    public static int getCurrentXbartender(){
        return currentXbartender;
    }

    public static int setCurrentXbartender(int x){
        return currentXbartender = x;
    }

    public static int getCurrentYbartender(){
        return BARTENDER_START_Y;
    }

    public static void setCurrentYbartender(int y){
        BARTENDER_START_Y = y;
    }

    public static int getHeight(){
        return BARTENDER_HEIGHT;
    }


}
