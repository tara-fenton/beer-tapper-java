import processing.core.PApplet;

public class Customer {

    private static double CUSTOMER_START_X = 140;
    private int CUSTOMER_WIDTH = 40;
    private int CUSTOMER_HEIGHT = 80;
    private double currentX;
    private int currentY;
    private boolean isMovingForward = true;

    public Customer(double x, int y){
        currentX = x;
        currentY = y;
    }

    private static PApplet p = MainClass.processing;

    public void moveForward(){
        // red
        p.fill(256, 0, 0);
        p.noStroke();
        p.rect((int) this.currentX,this.currentY,CUSTOMER_WIDTH,CUSTOMER_HEIGHT);

//        if (CUSTOMER_X < 600) {
            currentX = currentX + 0.5;
//        }
    }
    public void moveBackward(){
        // green
        p.fill(0, 256, 0);
        p.noStroke();
        p.rect((int) this.currentX,this.currentY,CUSTOMER_WIDTH,CUSTOMER_HEIGHT);

        //if (CUSTOMER_X > 100) {
        this.currentX = this.currentX - 0.5;
        //}
    }
    public static double getStartX(){
        return CUSTOMER_START_X;
    }
    public double getCurrentX(){
        return this.currentX;
    }
    public double setCurrentX(double x){
         return this.currentX = x;
    }

    public boolean isMovingForward() {
        return isMovingForward;
    }
    public boolean setMovingForward(boolean value){
        return isMovingForward = value;
    }
    public int getCurrentY(){
        return this.currentY;
    }


}
