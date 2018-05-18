public class Bartender {
    // bartender display
    //var $bartenderDiv = $("<div id='bartender'></div>");
    //$bartenderDiv.css("top", BARTENDER_START_Y + "px");
    //$bartenderDiv.css("left", BARTENDER_START_X + "px");

    // positions for controlling bartender
    private static int BARTENDER_HEIGHT = 80;
    private static int BARTENDER_WIDTH = 40;
    private static int BARTENDER_START_Y = 100;
    private static int BARTENDER_START_X = 500;
    private static int currentYbartender = 0;
    private static int newYbartender = 0;
    private static int currentXbartender = 0;
    private static int newXbartender = 0;

    public void draw(){
        MainClass.processing.rect(5,5,50,50);
    }
}
