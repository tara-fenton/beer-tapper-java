import processing.core.PApplet;

public class Customer {

    private static double CUSTOMER_START_X = 170;
    private int CUSTOMER_WIDTH = 40;
    private int CUSTOMER_HEIGHT = 80;
    private double currentX;
    private int currentY;
    private double speed;
    private boolean isMovingForward = true;

    public Customer(double x, int y, double s){
        currentX = x;
        currentY = y;
        speed = s;
    }

    private static PApplet p = MainClass.processing;

    public void moveForward(){
        // blue
        p.fill(0, 0, 256);
        p.noStroke();
        p.rect((int) this.currentX,this.currentY,CUSTOMER_WIDTH,CUSTOMER_HEIGHT);

        this.currentX = this.currentX + this.speed;
    }

    public void moveBackward(){
        // green
        p.fill(0, 256, 0);
        p.noStroke();
        p.rect((int) this.currentX,this.currentY,CUSTOMER_WIDTH,CUSTOMER_HEIGHT);

        this.currentX = this.currentX - 0.5;
    }

    public void stop(){
        // red
        p.fill(256,0,0);
        p.noStroke();
        p.rect((int) this.currentX,this.currentY,CUSTOMER_WIDTH,CUSTOMER_HEIGHT);
    }

    public static double getStartX(){
        return CUSTOMER_START_X;
    }

    public double getCurrentX(){
        return this.currentX;
    }
    public int getCurrentY(){
        return currentY;
    }

    public boolean getMovingForward() {
        return isMovingForward;
    }
    public boolean setMovingForward(boolean value){
        return isMovingForward = value;
    }
}