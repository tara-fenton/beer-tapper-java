import processing.core.PApplet;

public class Customer {

    private static int y;
    private static double CUSTOMER_START_X = 140;
    private static int CUSTOMER_WIDTH = 40;
    private static int CUSTOMER_HEIGHT = 80;
    private int currentY = 0;

    public Customer(int y){
        currentY = y;
    }

    private static PApplet p = MainClass.processing;

    public void draw(){
        p.fill(256,0,0);
        p.noStroke();
        p.rect((int) CUSTOMER_START_X,this.currentY,CUSTOMER_WIDTH,CUSTOMER_HEIGHT);

        CUSTOMER_START_X = CUSTOMER_START_X + 0.8;
    }


}
