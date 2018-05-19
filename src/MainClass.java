import processing.core.PApplet;

import static java.awt.Event.CAPS_LOCK;
import static java.awt.Event.UP;

public class MainClass extends PApplet {

    //reference to processing
    public static PApplet processing;

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

        bartender.setup();
        bartender.draw();
    }

    public void keyPressed() {
        if (key == CODED) {
            if (keyCode == UP || keyCode == CAPS_LOCK || keyCode == RETURN) {
                System.out.println("pressed: " + Bartender.getCurrentYbartender());
//                System.out.println("pressed: " + Bar.getPadding());
//                System.out.println("pressed: " + Bartender.getHeight());

                    Bartender.setCurrentYbartender(Bartender.getCurrentYbartender() - Bar.getPadding() - Bartender.getHeight() / 2);

                    // loop around from the top to the bottom
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

//                    newYbartender = currentYbartender + BAR_PADDING + BARTENDER_HEIGHT / 2;
//
//                    // loop around from the bottom to the top and from the top to the bottom
//                    var downLimit =
//                            BARTENDER_START_Y +
//                                    BAR_PADDING * (BARS_AMOUNT + 1) +
//                                    BARTENDER_HEIGHT / 2;
//                    if (newYbartender >= downLimit) {
//                        newYbartender = BARTENDER_START_Y;
//                    }
//                    // set the y position of bartender
//                    newYbartender += "px";
//                    $bartenderDiv.css("top", newYbartender);
//                    // set the x position of the bartender
//                    $bartenderDiv.css("left", BARTENDER_START_X);
                    // TO DO : stop pouring by moving to another row
            }
        }
    }
    public void keyReleased(){
        if (keyCode == UP){
//            System.out.println("released: " + keyCode);
        }
    }

}
