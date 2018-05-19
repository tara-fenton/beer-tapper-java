import processing.core.PApplet;

public class Bartender {

    // positions for controlling bartender
    private static int BARTENDER_START_X = 620;
    private static int BARTENDER_START_Y = 100;
    private static int BARTENDER_WIDTH = 40;
    private static int BARTENDER_HEIGHT = 80;
    private static int currentX = BARTENDER_START_X;
    private static int currentY = BARTENDER_START_Y;

    private static PApplet p = MainClass.processing;

    public void draw(){
        p.fill(196,37,215);
        p.noStroke();
        p.rect(currentX,currentY,BARTENDER_WIDTH,BARTENDER_HEIGHT);
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
