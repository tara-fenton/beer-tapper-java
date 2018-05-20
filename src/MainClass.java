import processing.core.PApplet;

import java.util.concurrent.ConcurrentHashMap;

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
    private Customer[] customers;
    private int customerAmount = 4;
    private int returningCustomers = 0;

    private ConcurrentHashMap<String, Beer> hashMap = new ConcurrentHashMap<>();
    private int beerCount = 0;

    private GetReady getReady;
    private Lives lives;
    private Points points;
    private Level level;

    public void setup(){
        processing = this;

        bar = new Bar();

        bartender = new Bartender();
        downLimit = Bartender.getStartY() + Bar.getPadding() * Bar.getAmount();

        getReady = new GetReady(0);
        lives = new Lives(3);
        points = new Points(0);
        level = new Level(1);

        customers = new Customer[customerAmount*level.getLevel()];

        // make customers
        for (int i = 0; i < customerAmount*level.getLevel(); i++) {
            customers[i] = new Customer(Customer.getStartX(), Bartender.getStartY() + Bar.getPadding() * i );
        }
    }

    public void settings() {
        size(800, 600);
    }

    public void draw() {

        processing.background(0);
level.setReady(true);
        if (!level.getReady()) {
            bar.setup();

            bartender.draw();

            // make customers move
            for (int i = 0; i < customerAmount * level.getLevel(); i++)
                if (Bartender.getAlive()) {
                    // check if moving forward boolean is true
                    if (customers[i].getMovingForward()) {
                        customers[i].moveForward();

                        for (String key : hashMap.keySet()) {
                            // check if beers collide with customer
                            if (customers[i].getCurrentX() + 40 > hashMap.get(key).getCurrentX() &&
                                    customers[i].getCurrentY() == hashMap.get(key).getCurrentY() - 10) {
                                customers[i].setMovingForward(false);
                                hashMap.get(key).setMovingForward(false);
                                // 50 Points for each saloon patron you send off his aisle
                                points.setPoints(points.getPoints() + 50);
                            }
                            // check if beer reaches the end of the bar
                            if (hashMap.get(key).getCurrentX() > Bar.getEnd()) {
                                //kill bartender
                                Bartender.setAlive(false);
                                lives.setLives(lives.getLives() - 1);
                            }
                        }
                        // check if customer reaches the end of the bar
                        if (customers[i].getCurrentX() > Bar.getEnd()) {
                            //kill bartender
                            Bartender.setAlive(false);
                            lives.setLives(lives.getLives() - 1);
                        }
                        // else - moving forward boolean is false, customers moving backward to beginning of bar
                    } else {
                        if (customers[i].getCurrentX() > Bar.getStartX()) customers[i].moveBackward();
                        // count the number of returning customers
                        returningCustomers++;
                    }
                } else {
                    // stop customers
                    customers[i].stop();
                }


            // make beers move
            for (String key : hashMap.keySet()) {
                // check if bartender is alive and beer is not collected
                if (Bartender.getAlive() && !hashMap.get(key).getCollected()) {
                    // check if beer is moving forward boolean is true
                    if (hashMap.get(key).getMovingForward()) {
                        hashMap.get(key).moveForward();
                        // check if it reaches the end without colliding with customer
                        if (hashMap.get(key).getCurrentX() < Bar.getStartX()) {
                            //kill bartender
                            Bartender.setAlive(false);
                            lives.setLives(lives.getLives() - 1);
                        }
                    }
                    // beer moving forward boolean is false - going back to bartender
                    else {
                        // check if bartender collides to collect glass
                        if (hashMap.get(key).getCurrentX() + 15 > Bartender.getCurrentX() &&
                                hashMap.get(key).getCurrentY() - 10 == Bartender.getCurrentY()) {
                            hashMap.get(key).setCollected(true);
                            // 100 Points for each empty mug you pick up
                            points.setPoints(points.getPoints() + 100);
                            // else if - check if beer reached the end of the bar without bartender collecting
                        } else if (hashMap.get(key).getCurrentX() > Bar.getEnd()) {
                            //kill bartender
                            Bartender.setAlive(false);
                            lives.setLives(lives.getLives() - 1);
                            // else - keep moving it toward the bartender
                        } else {
                            hashMap.get(key).moveBackward();
                        }
                    }
                    // check if beer is collected
                } else {
                    if (hashMap.get(key).getCollected()) {
                        hashMap.get(key).empty();
                        //remove beer from hashmap causes ConcurrentModificationException error?
                        //hashMap.remove(key);

                    } else {
                        // stop beers, bartender killed
                        hashMap.get(key).stop();
                    }
                }
            }


            lives.draw();
            points.draw();
            level.draw();

            // check if all customers are returning - WON LEVEL
            if (returningCustomers == customerAmount * level.getLevel()) {
//                level.setLevel(level.getLevel()+1);
                level.setReady(true);
//                returningCustomers = 0;
                getReady.setTime(0);
                // make new customers
                for (int i = 0; i < customerAmount*level.getLevel(); i++) {
                    customers[i] = new Customer(Customer.getStartX(), Bartender.getStartY() + Bar.getPadding() * i );
                }
                // remove all beers
                for (String key : hashMap.keySet()) {
                    hashMap.remove(key);
                }

                beerCount = 0;

            } else returningCustomers = 0;
        } else {

            displayGetReady();
        }
    }

    public void displayGetReady() {
        // draw ready to serve timer
        if (getReady.getTime() < 1000){
            getReady.draw();
            getReady.setTime(getReady.getTime()+1);
        } else {
            level.setReady(false);
            returningCustomers = 0;
        }
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
