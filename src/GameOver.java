import processing.core.PApplet;
import processing.core.PFont;

public class GameOver {

    private static PApplet p = MainClass.processing;

    public GameOver() {
    }

    public void draw() {

        PFont mina;
        mina = p.createFont("fonts/Mina-Regular.ttf", 60);
        p.textFont(mina);
        p.text("GAME OVER", 260, 280);
        p.fill(252, 252, 0);

        p.textFont(mina);
        p.text("[ INSERT QUARTER ]", 160, 360);
        p.fill(196,37,215);
    }
}

