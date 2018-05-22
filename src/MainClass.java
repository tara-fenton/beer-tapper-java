
import java.util.concurrent.ConcurrentHashMap;

import static java.awt.Event.CAPS_LOCK;

import processing.core.PApplet;

public class MainClass extends PApplet {

    //reference to processing
    public static PApplet processing;

    public static void main(String [] args) {
        PApplet.main("MainClass", args);
    }


    private Bar bar;
    private Bartender bartender;
    private static int downLimit;
    private ConcurrentHashMap<String, Customer> customers = new ConcurrentHashMap<>();
    private int customerCounter = 0;
    private int customerAmount = 4;
    private int returningCustomers = 0;

    private ConcurrentHashMap<String, Beer> beers = new ConcurrentHashMap<>();
    private int beerCount;

    private GetReady getReady;
    private Lives lives;
    private Points points;
    private Level level;
    private GameOver gameOver;

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

        setLevelUp();
        level.setIsPlaying(true);
    }

    public void setLevelUp(){
        // level.setReady(true);
        getReady.setTime(0);
        // remove all customers
        for (String key : customers.keySet()) {
            customers.remove(key);
        }
        // used for customer speed
        double lower = 0.01;
        double upper = 1.34;
        // make customers
        for (int i = 0; i < customerAmount*level.getLevel(); i++) {
            // used for customer speed
            double result = Math.random() * (upper - lower) + lower;

            customers.put("customers"+i, new Customer(Customer.getStartX(),
                    Bartender.getStartY() + Bar.getPadding() * customerCounter, result ));
            customerCounter++;
            // check if customerCounter needs to be reset
            if (customerCounter >= customerAmount) customerCounter = 0;
        }
        // reset returning customers
        returningCustomers = 0;

        // remove all beers
        for (String key : beers.keySet()) {
            beers.remove(key);
        }
        // reset beer count
        beerCount = 0;
    }

    /* Interval
     * */
    public void draw() {

        processing.background(0);

        if (!level.getIsPlaying()) {
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
    /* Checks for win.
     * */
    public void checkForWin() {
        // check if all customers are returning - WON LEVEL
        if (returningCustomers == customerAmount * level.getLevel()) {
            level.setLevel(level.getLevel()+1);
            setLevelUp();
            level.setIsPlaying(false);

        } else returningCustomers = 0;
    }
    /* For each of the customers are moving if the bartender is alive.
    * If the customer is moving forward then draw it orElse the customer moving backward to beginning of bar.
    * Next check through all the beer to see if they collided with the customer. If they collided
    * change the directions of the customer and the beer and give the player points. If the beer makes it to the
    * of the bar kill the bartender. If the customer gets to the end of the bar kill the bartender.
    * */
    public void makeCustomersMove(){
        // make each customer move
        for (String customer : customers.keySet())
            // check if bartender is alive
            if (Bartender.getAlive()) {
                // check if customer is moving forward is true
                if (customers.get(customer).getMovingForward()) {

                    // draw customer
                    customers.get(customer).moveForward();

                    // for each beer for each customer
                    for (String key : beers.keySet()) {
                        // check if beers collide with customer
                        if (customers.get(customer).getCurrentX() + 40 > beers.get(key).getCurrentX() &&
                                customers.get(customer).getCurrentY() == beers.get(key).getCurrentY() - 10) {
                            // set customer moving to bartender false
                            customers.get(customer).setMovingForward(false);
                            // set  beer moving towards customer to false
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
                    if (customers.get(customer).getCurrentX() > Bar.getEnd()) {
                        //kill bartender
                        Bartender.setAlive(false);
                        lives.setLives(lives.getLives() - 1);
                    }
                    // else - customer is moving forward is false, customers moving backward to beginning of bar
                } else {
                    if (customers.get(customer).getCurrentX() > Bar.getStartX()) customers.get(customer).moveBackward();
                    // count the number of returning customers
                    returningCustomers++;
                }
                // else - the bartender died, what to do??
            } else {
                // stop customers
                // customers.get(customer).stop();
                setLevelUp();
                level.setIsPlaying(true);
                //displayGetReady();
            }
    }

    /* For each of the beers are moving if the bartender is alive and this beer is not collected.
     * If the beer is moving forward then draw it.
     * If the beer reaches the end without colliding with a customer kill the bartender.
     * If bartender collides to collect glass, make the glass disappear and give the player points.
     * */
    public void makeBeersMove() {
        // make beers move
        for (String key : beers.keySet()) {
            // check if bartender is alive and beer is not collected
            if (Bartender.getAlive() && !beers.get(key).getCollected()) {
                // check if beer is moving forward is true
                if (beers.get(key).getMovingForward()) {
                    // draw beer
                    beers.get(key).moveForward();
                    // check if it reaches the end without colliding with customer
                    if (beers.get(key).getCurrentX() < Bar.getStartX()) {
                        //kill bartender
                        Bartender.setAlive(false);
                        lives.setLives(lives.getLives() - 1);
                        //displayGetReady();
                    }
                }
                // beer moving forward is false - going back to bartender
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
                        System.out.println("kill bartender");
                        Bartender.setAlive(false);
                        lives.setLives(lives.getLives() - 1);
                        //getReady.setTime(0);
                        //displayGetReady();
                        // else - keep moving it toward the bartender
                    } else {
                        // going back to the bartender
                        beers.get(key).moveBackward();
                    }
                }
                // make sure bartender is alive to fix freezing bug
            } else if (Bartender.getAlive()) {
                // only happens once because of the && in the if statement
                // check if beer is collected
                if (beers.get(key).getCollected()) {
                     // make beer disappear
                    beers.get(key).empty();
                } else {
                    // stop beers, bartender killed
                    beers.get(key).stop();
                    setLevelUp();
                    level.setIsPlaying(true);
                    // displayGetReady();
                }
            }
        }
    }
    /* In between screens
     *
     * */
    public void displayGetReady() {
        // check if bartender is alive
        if (!Bartender.getAlive() && lives.getLives() != 0) {
            if (getReady.getTime() < 50){
//                System.out.println(getReady.getTime());
                // getReady.draw();
                getReady.setTime(getReady.getTime()+1);
            } else if (getReady.getTime() < 130){
                // draw ready to serve timer
                getReady.draw();
                getReady.setTime(getReady.getTime()+1);
            } else {
                level.setIsPlaying(false);
                getReady.setTime(0);
                Bartender.setAlive(true);
            }
        }
        if (lives.getLives() <= 0){
            gameOver = new GameOver();
            gameOver.draw();
            // lives.setLives(3);
        }

    }

    /* When the space bar is pressed pour a beer and send bartender to tap if needed.
     * Up and Down moves the bartender and loops around if needed
     * Left and Right moves the bartender with restrictions
     * */
    public void keyPressed() {
        // space bar
        if (key == ' ') {
            // check if bartender needs to get sent back to tap
            if(Bartender.getCurrentX() < Bartender.getStartX()) Bartender.setCurrentX(Bartender.getStartX());
            // create a beer
            beers.put("beer"+beerCount, new Beer((double) Bartender.getCurrentX(),Bartender.getCurrentY()));
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