import java.awt.*;
import java.util.Random;

public class Drop {
    private int xPosition;
    private int yPosition;
    private int width;
    private int height;
    private RenderLayer map;
    private Random rnd;

    public Drop(RenderLayer l, int shift) {
        this.rnd = new Random();

        this.map = l;
        this.width = this.rnd.nextInt(2) + 1;
        this.height = this.rnd.nextInt(8) + 1;
        this.xPosition = rnd.nextInt(shift + l.getWidth());
        this.yPosition = rnd.nextInt(shift + 10);
    }

    public void render(Graphics g) {
        g.setColor(new Color(189, 209, 229));
        g.fillRect(this.xPosition, this.yPosition, this.width, this.height);
    }

    public void update() {
        int maxA = 20;
        int minA = 5;
        this.yPosition += this.rnd.nextInt(maxA - minA) + minA;
    }
}
