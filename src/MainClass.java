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
        bar.draw();

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
//                    if (newYbartender < BARTENDER_START_Y) {
//                        newYbartender =
//                                BARTENDER_START_Y + BAR_PADDING * BARS_AMOUNT + Bartender.getHeight() / 2;
//                    }
//                    // set the y position of bartender
//                    newYbartender += "px";
//                    $bartenderDiv.css("top", newYbartender);
//                    // set the x position of the bartender
//                    $bartenderDiv.css("left", BARTENDER_START_X);
//                    // TO DO : stop pouring by moving to another row
//                    break;

            }
            if (keyCode == DOWN || keyCode == SHIFT){
                // move the bartender down
                Bartender.setCurrentY(Bartender.getCurrentY() + Bar.getPadding());

                // check if bartender needs to loop around from the bottom to the top
                if (Bartender.getCurrentY() >= downLimit) {
                    Bartender.setCurrentY(Bartender.getStartY());
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
