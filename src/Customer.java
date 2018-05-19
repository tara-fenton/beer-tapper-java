import processing.core.PApplet;

public class Customer {

    private static double CUSTOMER_X = 140;
    private int CUSTOMER_WIDTH = 40;
    private int CUSTOMER_HEIGHT = 80;
    private int currentY;

    public Customer(int y){
        currentY = y;
    }

    private static PApplet p = MainClass.processing;

    public void draw(){
        p.fill(256,0,0);
        p.noStroke();
        p.rect((int) CUSTOMER_X,this.currentY,CUSTOMER_WIDTH,CUSTOMER_HEIGHT);


        if (CUSTOMER_X < 600) {
            CUSTOMER_X = CUSTOMER_X + 0.8;
        }
    }


}
