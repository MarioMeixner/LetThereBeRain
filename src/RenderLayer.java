import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class RenderLayer extends Canvas implements Runnable {
    private boolean isRunning;
    private ArrayList<Drop> drops;

    public RenderLayer() {
        super();

        this.isRunning = false;

        this.setSize(new Dimension(800, 600));

        this.drops = new ArrayList<Drop>();

        this.letThereBeRain();
    }

    @Override
    public void run() {
        long lastTimeCycle = System.nanoTime();
        long lastTimeOutput = System.currentTimeMillis();
        double unprocessedTicks = 0;
        double nsPerTick = Math.pow(10, 9) / 60;
        int FPS = 0;
        int ticks = 0;

        while (this.isRunning) {
            long nowTimeCycle = System.nanoTime();
            unprocessedTicks += (nowTimeCycle - lastTimeCycle) / nsPerTick;
            lastTimeCycle = nowTimeCycle;

            while (unprocessedTicks >= 1) {
                ticks++;
                unprocessedTicks--;
                this.update();
            }

            FPS++;
            this.render();

            if (System.currentTimeMillis() - lastTimeOutput > 1000) {
                lastTimeOutput += 1000;
                FPS = 0;
                ticks = 0;
            }
        }
    }

    private void render() {
        BufferStrategy buffer = this.getBufferStrategy();
        if (buffer == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = buffer.getDrawGraphics();
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (Drop d: this.drops) {
            d.render(g);
        }

        g.dispose();
        buffer.show();
    }

    private void update() {
        letThereBeRain();
        for (Drop d: this.drops) {
            d.update();
        }
    }

    public void start() {
        this.isRunning = true;
        Thread t = new Thread(this);
        t.start();
    }

    public void letThereBeRain() {
        for (int i = 0; i < 20; i++) {
            this.drops.add(new Drop(this, i));
        }
    }
}
