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

    private ConcurrentHashMap<String, Beer> beers = new ConcurrentHashMap<>();
    private int beerCount;

    private GetReady getReady;
    private Lives lives;
    private Points points;
    private Level level;

    public void settings() {
        size(800, 600);
    }

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

        setLevelUp();
    }

    public void setLevelUp(){
        level.setReady(true);
        getReady.setTime(0);
        // make customers
        for (int i = 0; i < customerAmount*level.getLevel(); i++) {
            customers[i] = new Customer(Customer.getStartX(), Bartender.getStartY() + Bar.getPadding() * i );
        }
        returningCustomers = 0;

        // remove all beers
        for (String key : beers.keySet()) {
            beers.remove(key);
        }
        beerCount = 0;
    }

    public void draw() {

        processing.background(0);

        if (!level.getReady()) {
            bar.setup();

            bartender.draw();

            makeCustomersMove();

            makeBeersMove();

            lives.draw();
            points.draw();
            level.draw();

            checkForWin();

        } else {

            displayGetReady();
        }
    }
    public void checkForWin() {
        // check if all customers are returning - WON LEVEL
        if (returningCustomers == customerAmount * level.getLevel()) {
//                level.setLevel(level.getLevel()+1);
            setLevelUp();

        } else returningCustomers = 0;
    }
    public void makeCustomersMove(){
        // make customers move
        for (int i = 0; i < customerAmount * level.getLevel(); i++)
            if (Bartender.getAlive()) {
                // check if moving forward boolean is true
                if (customers[i].getMovingForward()) {
                    customers[i].moveForward();

                    for (String key : beers.keySet()) {
                        // check if beers collide with customer
                        if (customers[i].getCurrentX() + 40 > beers.get(key).getCurrentX() &&
                                customers[i].getCurrentY() == beers.get(key).getCurrentY() - 10) {
                            customers[i].setMovingForward(false);
                            beers.get(key).setMovingForward(false);
                            // 50 Points for each saloon patron you send off his aisle
                            points.setPoints(points.getPoints() + 50);
                        }
                        // check if beer reaches the end of the bar
                        if (beers.get(key).getCurrentX() > Bar.getEnd()) {
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


    }
    public void makeBeersMove() {
        // make beers move
        for (String key : beers.keySet()) {
            // check if bartender is alive and beer is not collected
            if (Bartender.getAlive() && !beers.get(key).getCollected()) {
                // check if beer is moving forward boolean is true
                if (beers.get(key).getMovingForward()) {
                    beers.get(key).moveForward();
                    // check if it reaches the end without colliding with customer
                    if (beers.get(key).getCurrentX() < Bar.getStartX()) {
                        //kill bartender
                        Bartender.setAlive(false);
                        lives.setLives(lives.getLives() - 1);
                    }
                }
                // beer moving forward boolean is false - going back to bartender
                else {
                    // check if bartender collides to collect glass
                    if (beers.get(key).getCurrentX() + 15 > Bartender.getCurrentX() &&
                            beers.get(key).getCurrentY() - 10 == Bartender.getCurrentY()) {
                        beers.get(key).setCollected(true);
                        // 100 Points for each empty mug you pick up
                        points.setPoints(points.getPoints() + 100);
                        // else if - check if beer reached the end of the bar without bartender collecting
                    } else if (beers.get(key).getCurrentX() > Bar.getEnd()) {
                        //kill bartender
                        Bartender.setAlive(false);
                        lives.setLives(lives.getLives() - 1);
                        // else - keep moving it toward the bartender
                    } else {
                        beers.get(key).moveBackward();
                    }
                }
                // check if beer is collected
            } else {
                if (beers.get(key).getCollected()) {
                    beers.get(key).empty();
                    //remove beer from beers causes ConcurrentModificationException error?
                    //beers.remove(key);

                } else {
                    // stop beers, bartender killed
                    beers.get(key).stop();
                }
            }
        }
    }
    public void displayGetReady() {
        // draw ready to serve timer
        if (getReady.getTime() < 100){
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
            beers.put("beer"+beerCount, new Beer(Bartender.getCurrentX(),Bartender.getCurrentY()));
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