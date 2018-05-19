public class Customer {
    // create customers
    //var CUSTOMER_AMOUNT = 4;
//    var customersObj = {};
    // customer positions for collisions
//    var cutomerPositionX = 0;
//    var cutomerPositionY = 0;
//    // customer collisions
//    var checkForBeers = false;
//    var totalCustomers = 0;
//    var countCustomersReturning = 0;
//    var totalBeers = 0;
//    var countBeersCollected = 0;
    private static int CUSTOMER_START_X = 140;
    private static int CUSTOMER_START_Y = 100;
    private static int CUSTOMER_WIDTH = 40;
    private static int CUSTOMER_HEIGHT = 80;

    public void setup(){
    }
    public void draw(){
        MainClass.processing.fill(256,0,0);
        MainClass.processing.noStroke();
        MainClass.processing.rect(CUSTOMER_START_X,CUSTOMER_START_Y,CUSTOMER_WIDTH,CUSTOMER_HEIGHT);
    }
}
