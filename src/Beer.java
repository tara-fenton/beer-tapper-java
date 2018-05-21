import processing.core.PApplet;

public class Beer {

    public static int BEER_WIDTH = 15;
    public static int BEER_HEIGHT = 30;
    public double currentX;
    private int currentY;
    private boolean isMovingForward = true;
    private boolean isCollected = false;

    private static PApplet p = MainClass.processing;

    public Beer(double x, int y){
        currentX = x - 20;
        currentY = y + 10;
    }

    public void moveForward(){
        // yellow
        p.fill(252,252,0);
        p.noStroke();
        p.rect((int) this.currentX,this.currentY,BEER_WIDTH,BEER_HEIGHT);

        currentX = currentX - 2;
    }

    public void moveBackward(){
        // white
        p.fill(256,256,256);
        p.noStroke();
        p.rect((int) this.currentX,this.currentY,BEER_WIDTH,BEER_HEIGHT);

        currentX = currentX + 0.5;
    }

    public void stop(){
        // red
        p.fill(256,0,0);
        p.noStroke();
        p.rect((int) this.currentX,this.currentY,BEER_WIDTH,BEER_HEIGHT);
    }

    public void empty(){
        // disappear
        p.rect(0,0,0,0);
    }

    public double getCurrentX() {
        return this.currentX;
    }
    public int getCurrentY() {
        return this.currentY;
    }

    public boolean getMovingForward() {
        return isMovingForward;
    }
    public boolean setMovingForward(boolean value){
        return isMovingForward = value;
    }

    public boolean getCollected() {
        return isCollected;
    }
    public boolean setCollected(boolean value){
        return isCollected = value;
    }
}
