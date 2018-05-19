import processing.core.PApplet;
import processing.core.PGraphics;

public class Beer {

    private static int BEER_START_X = 602;
    private static int BEER_START_Y = 110;
    private static int BEER_WIDTH = 15;
    private static int BEER_HEIGHT = 30;

    private static PApplet p = MainClass.processing;

    // create beers
//    var beersObj = {};
//    var beerCount = 0;
//    var pouring = false;
//    var pouringSent = false;
//
//    // beer positions for collisons
//    var beerPositionX = 0;
//    var beerPositionY = 0;

    private static PGraphics pg =  p.createGraphics(BEER_WIDTH,BEER_HEIGHT);
    public void setup(){
//        MainClass.processing.fill(252,252,0);
//        MainClass.processing.noStroke();
//        MainClass.processing.rect(BEER_START_X,BEER_START_Y,BEER_WIDTH,BEER_HEIGHT);
        //pg =
    }
    public void draw(){
      //  p.background(200);
        pg.beginDraw();
        pg.background(252,252,0);
        //pg.fill(252,252,0);
        pg.noStroke();
        pg.rect(BEER_START_X,BEER_START_Y,BEER_WIDTH,BEER_HEIGHT);
        pg.endDraw();
        //p.image(pg, 50, 50);
        p.image(pg, BEER_START_X,BEER_START_Y,BEER_WIDTH,BEER_HEIGHT);


//        BEER_START_Y = BEER_START_Y +2;
//        System.out.println("hi");
    }
}
