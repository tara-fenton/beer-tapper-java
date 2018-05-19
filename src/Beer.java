public class Beer {

    private static int BEER_START_X = 602;
    private static int BEER_START_Y = 110;
    private static int BEER_WIDTH = 15;
    private static int BEER_HEIGHT = 30;
    
    public void draw(){
        MainClass.processing.fill(252,252,0);
        MainClass.processing.noStroke();
        MainClass.processing.rect(BEER_START_X,BEER_START_Y,BEER_WIDTH,BEER_HEIGHT);
    }
}
