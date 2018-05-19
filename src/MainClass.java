import processing.core.PApplet;

import static java.awt.Event.CAPS_LOCK;
import static java.awt.Event.UP;

public class MainClass extends PApplet {

    //reference to processing
    public static PApplet processing;
    private static int downLimit;

    public static void main(String [] args) {
        PApplet.main("MainClass", args);
    }

    Beer beer;
    Bar bar;
    Customer customer;
    Bartender bartender;

    public void setup(){
        processing = this;

        bar = new Bar();

        customer = new Customer();

        beer = new Beer();

        bartender = new Bartender();

        downLimit = Bartender.getStartY() + Bar.getPadding() * Bar.getAmount();
    }
    public void settings() {
        size(800, 600);
    }

    public void draw() {

        processing.background(0);

        beer.setup();
        beer.draw();

        bar.setup();

        customer.setup();
        customer.draw();

        bartender.draw();
    }


    public void keyPressed() {
        if (key == CODED) {
            if (keyCode == UP || keyCode == CAPS_LOCK || keyCode == RETURN) {
                // move the bartender up
                Bartender.setCurrentY(Bartender.getCurrentY() - Bar.getPadding());

                // check if bartender needs to loop around from the top to the bottom
                if(Bartender.getCurrentY() < Bartender.getStartY()){
                    Bartender.setCurrentY(Bartender.getStartY() + Bar.getPadding() * (Bar.getAmount() - 1));
                }

                // check if bartender needs to get sent back to tap
                if(Bartender.getCurrentX() < Bartender.getStartX()){
                    Bartender.setCurrentX(Bartender.getStartX());
                }
            }

            if (keyCode == DOWN || keyCode == SHIFT){
                // move the bartender down
                Bartender.setCurrentY(Bartender.getCurrentY() + Bar.getPadding());

                // check if bartender needs to loop around from the bottom to the top
                if (Bartender.getCurrentY() >= downLimit) {
                    Bartender.setCurrentY(Bartender.getStartY());
                }

                // check if bartender needs to get sent back to tap
                if(Bartender.getCurrentX() < Bartender.getStartX()){
                    Bartender.setCurrentX(Bartender.getStartX());
                }
            }
            // a doesn't work for now
            if (keyCode == LEFT || key == 'a'){
                // move the bartender left
                Bartender.setCurrentX(Bartender.getCurrentX() - 5);

                // restrict from moving past the left of bar
                if (Bartender.getCurrentX() < Bar.getStartX()) {
                    Bartender.setCurrentX(Bar.getStartX());
                }
            }
            // s doesn't work for now
            if (keyCode == RIGHT || key == 's'){
                // move the bartender right
                Bartender.setCurrentX(Bartender.getCurrentX() + 5);

                // restrict from moving past the right
                if (Bartender.getCurrentX() > Bartender.getStartX()) {
                    Bartender.setCurrentX(Bartender.getStartX());
                }
            }
        }
    }
    public void keyReleased(){
        if (keyCode == UP){
//            System.out.println("released: " + keyCode);
        }
    }

}
