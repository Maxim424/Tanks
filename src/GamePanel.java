import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements MouseListener {


    private final Unit player = new Tank(400, 300, "red");
    private final Empty point;
    private final MapEditor mapEditor = MapEditor.getInstance();
    private final Map map = Map.getInstance();
    private final Camera camera = Camera.getInstance();
    private KeyState keyState = KeyState.getInstance();

    private long t1, t2;
    public static int w;
    public static int h;

    public GamePanel() {
        t1 = System.currentTimeMillis();
        point = new Empty(0, 0);
    }

    private void update () {
        w = getWidth();
        h = getHeight();
        t2 = System.currentTimeMillis();
        int ms = (int) (t2 - t1);

        if (!mapEditor.isActive()) {
            player.update(ms);
            camera.setPosition(player.getX()-getWidth()/2.0-16, player.getY()-getHeight()/2.0-16, getWidth(), getHeight());
        } else {
            point.update(ms);
            camera.setPosition(point.getX(), point.getY(), getWidth(), getHeight());
        }


        t1 = t2;
        keyState.update();
        mapEditor.update(ms);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        update();

        map.paint(g);
        player.paint(g);
        mapEditor.paint(g);

        addMouseListener(mapEditor);
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
