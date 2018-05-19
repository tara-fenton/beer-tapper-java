public class Bar {
//    function createBarElements() {
//        for (var i = 0; i < BARS_AMOUNT; i++) {
//            var $barDiv = $("<div class='bar'></div>");
//            $("#container").append($barDiv);
//            $barDiv.attr("id", "data-bar-index" + i);
//            $barDiv.css("left", BAR_PADDING + "px");
//            $barDiv.css("top", BAR_PADDING * i + BAR_START_Y + "px");
//        }
//    }
//create bars
//var BARS_AMOUNT = 4;
//    var BAR_PADDING = 80;


    private static int BARS_AMOUNT = 4;
    private static int BAR_PADDING = 120;
    private static int BAR_START_X = 200;
    private static int BAR_START_Y = 140;
    private static int BAR_WIDTH = 400;
    private static int BAR_HEIGHT = 40;

    public void draw(){
        for (int i = 0; i < BARS_AMOUNT; i++) {
            MainClass.processing.fill(165,55,55);
            MainClass.processing.noStroke();
            MainClass.processing.rect(BAR_START_X,BAR_START_Y+i*BAR_PADDING,BAR_WIDTH,BAR_HEIGHT);
        }

    }
}
