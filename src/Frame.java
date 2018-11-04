import javax.swing.JFrame;

public class Frame {

    private static final String TITLE = "RAIN";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;


    public static void main(String[] args) {

        JFrame frame = new JFrame(TITLE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        RenderLayer layer = new RenderLayer();
        frame.add(layer);
        layer.start();
    }
}
