public class Bartender {
    // bartender display
    //var $bartenderDiv = $("<div id='bartender'></div>");
    //$bartenderDiv.css("top", BARTENDER_START_Y + "px");
    //$bartenderDiv.css("left", BARTENDER_START_X + "px");

    // positions for controlling bartender
    private static int BARTENDER_START_X = 620;
    private static int BARTENDER_START_Y = 100;
    private static int BARTENDER_WIDTH = 40;
    private static int BARTENDER_HEIGHT = 80;
    private static int currentYbartender = 0;
    private static int newYbartender = 0;
    private static int currentXbartender = 0;
    private static int newXbartender = 0;

    public void draw(){
        MainClass.processing.fill(196,37,215);
        MainClass.processing.noStroke();
        MainClass.processing.rect(BARTENDER_START_X,BARTENDER_START_Y,BARTENDER_WIDTH,BARTENDER_HEIGHT);
    }
}
