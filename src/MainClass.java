import processing.core.PApplet;

public class MainClass extends PApplet {

    //reference to processing
    public static PApplet processing;

    public static void main(String [] args) {
        PApplet.main("MainClass", args);
    }

    public void setup(){
        processing = this;
    }
    public void settings() {
        size(800, 600);
    }
    public void draw() {

        Bartender bartender = new Bartender();
        bartender.draw();

        Bar bar = new Bar();
        bar.draw();

        Customer customer = new Customer();
        customer.draw();

        Beer beer = new Beer();
        beer.draw();
    }

}
