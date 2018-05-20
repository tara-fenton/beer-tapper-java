public class Bar {

    private static int BARS_AMOUNT = 4;
    private static int BAR_PADDING = 120;
    private static int BAR_START_X = 200;
    private static int BAR_START_Y = 140;
    private static int BAR_WIDTH = 400;
    private static int BAR_HEIGHT = 40;

    public void setup(){
        for (int i = 0; i < BARS_AMOUNT; i++) {
            MainClass.processing.fill(165,55,55);
            MainClass.processing.noStroke();
            MainClass.processing.rect(BAR_START_X,BAR_START_Y+i*BAR_PADDING,BAR_WIDTH,BAR_HEIGHT);
        }
    }

    public static int getPadding(){
        return BAR_PADDING;
    }
    public static int getAmount(){
        return BARS_AMOUNT;
    }
    public static int getStartX(){
        return BAR_START_X;
    }
    public static int getEnd() { return BAR_START_X + BAR_WIDTH; }
}
