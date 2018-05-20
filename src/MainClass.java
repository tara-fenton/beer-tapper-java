import processing.core.PApplet;

import java.util.HashMap;

import static java.awt.Event.CAPS_LOCK;

public class MainClass extends PApplet {

    //reference to processing
    public static PApplet processing;
    private static int downLimit;

    public static void main(String [] args) {
        PApplet.main("MainClass", args);
    }

    private Bar bar;
    private Bartender bartender;
    private Customer[] customers = new Customer[4];

    private static HashMap<String, Beer> hashMap = new HashMap<>();
    private int beerCount = 0;

    private Lives lives;
    private Points points;

    public void setup(){
        processing = this;

        bar = new Bar();

        bartender = new Bartender();
        downLimit = Bartender.getStartY() + Bar.getPadding() * Bar.getAmount();

        // make 4 customers
        for (int i = 0; i < 4; i++) {
            customers[i] = new Customer(Customer.getStartX(), Bartender.getStartY() + Bar.getPadding() * i );
        }

        lives = new Lives(3);
        points = new Points(0);
    }

    public void settings() {
        size(800, 600);
    }

    public void draw() {

        processing.background(0);

        bar.setup();

        bartender.draw();

        // make customers move
        for (int i = 0; i < 4; i++)
            if(Bartender.getAlive()) {
                // check if moving forward boolean is true
                if (customers[i].getMovingForward()) {
                    customers[i].moveForward();
                    // check if beers collide with customer
                    for (String key : hashMap.keySet()) {
                        if (customers[i].getCurrentX() + 40 > hashMap.get(key).getCurrentX() &&
                                customers[i].getCurrentY() == hashMap.get(key).getCurrentY() - 10) {
                            customers[i].setMovingForward(false);
                            hashMap.get(key).setMovingForward(false);
                            points.setPoints(points.getPoints()+50);
                        }
                    }
                } else {
                    // moving forward boolean is false
                    if (customers[i].getCurrentX() > Bar.getStartX()) customers[i].moveBackward();
                }
            } else {
                // stop beers
                customers[i].stop();
            }

        // make beers move
        for (String key : hashMap.keySet()) {
            if(Bartender.getAlive()) {
                // check if moving forward boolean is true
                if (hashMap.get(key).getMovingForward()) {
                    hashMap.get(key).moveForward();
                    // check if it reaches the end without colliding with customer
                    if(hashMap.get(key).getCurrentX() < Bar.getStartX()){
                        //kill bartender
                        Bartender.setAlive(false);
                        lives.setLives(lives.getLives()-1);
                    }
                }
                // moving forward boolean is false
                else hashMap.get(key).moveBackward();
            } else {
                // stop beers
                hashMap.get(key).stop();
            }
        }

        lives.draw();
        points.draw();
    }

    // bartender movement - why do i have to call the class rather than the instance?
    public void keyPressed() {
        // space bar
        if (key == ' ') {
            // check if bartender needs to get sent back to tap
            if(Bartender.getCurrentX() < Bartender.getStartX()) Bartender.setCurrentX(Bartender.getStartX());
            // create a beer
            hashMap.put("beer"+beerCount, new Beer(Bartender.getCurrentX(),Bartender.getCurrentY()));
            beerCount++;
        }
        if (key == CODED) {
            if (keyCode == UP || keyCode == CAPS_LOCK || keyCode == RETURN) {
                // move the bartender up
                Bartender.setCurrentY(Bartender.getCurrentY() - Bar.getPadding());

                // check if bartender needs to loop around from the top to the bottom
                if(Bartender.getCurrentY() < Bartender.getStartY())
                    Bartender.setCurrentY(Bartender.getStartY() + Bar.getPadding() * (Bar.getAmount() - 1));

                // check if bartender needs to get sent back to tap
                if(Bartender.getCurrentX() < Bartender.getStartX()) Bartender.setCurrentX(Bartender.getStartX());
            }

            if (keyCode == DOWN || keyCode == SHIFT){
                // move the bartender down
                Bartender.setCurrentY(Bartender.getCurrentY() + Bar.getPadding());

                // check if bartender needs to loop around from the bottom to the top
                if (Bartender.getCurrentY() >= downLimit) Bartender.setCurrentY(Bartender.getStartY());

                // check if bartender needs to get sent back to tap
                if(Bartender.getCurrentX() < Bartender.getStartX()) Bartender.setCurrentX(Bartender.getStartX());
            }
            // 'a' doesn't work for now
            if (keyCode == LEFT || key == 'a'){
                // move the bartender left
                Bartender.setCurrentX(Bartender.getCurrentX() - 5);

                // restrict from moving past the left of bar
                if (Bartender.getCurrentX() < Bar.getStartX()) Bartender.setCurrentX(Bar.getStartX());
            }
            // 's' doesn't work for now
            if (keyCode == RIGHT || key == 's'){
                // move the bartender right
                Bartender.setCurrentX(Bartender.getCurrentX() + 5);

                // restrict from moving past the right
                if (Bartender.getCurrentX() > Bartender.getStartX()) Bartender.setCurrentX(Bartender.getStartX());
            }
        }
    }
    public void keyReleased(){
        if (keyCode == UP){
//            System.out.println("released: " + keyCode);
        }
    }

}
