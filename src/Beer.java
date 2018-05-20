import processing.core.PApplet;

public class Beer {

    private static int BEER_WIDTH = 15;
    private static int BEER_HEIGHT = 30;
    private int currentX;
    private int currentY;
    private boolean isMovingForward = true;

    private static PApplet p = MainClass.processing;

    public Beer(int x, int y){
        currentX = x - 20;
        currentY = y + 10;
    }

    public void moveForward(){
        // yellow
        p.fill(252,252,0);
        p.noStroke();
        p.rect(this.currentX,this.currentY,BEER_WIDTH,BEER_HEIGHT);

//        if (currentX > Bar.getStartX()) {
            currentX = currentX - 1;
//        }
    }

    public void moveBackward(){
        // white
        p.fill(256,256,256);
        p.noStroke();
        p.rect(this.currentX,this.currentY,BEER_WIDTH,BEER_HEIGHT);

//        if (currentX > Bar.getStartX()) {
        currentX = currentX + 1;
//        }
    }
    public void stop(){
        // white
        p.fill(256,256,256);
        p.noStroke();
        p.rect(this.currentX,this.currentY,BEER_WIDTH,BEER_HEIGHT);

    }

    public int getCurrentX() {
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
}
