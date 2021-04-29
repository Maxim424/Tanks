import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.server.UnicastRemoteObject;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {


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
        UnitCollection.add(player);
    }

    private void update () {
        w = getWidth();
        h = getHeight();
        t2 = System.currentTimeMillis();
        int ms = (int) (t2 - t1);

        if (!mapEditor.isActive()) {
            UnitCollection.update(ms);
            camera.setPosition(player.getX()-getWidth()/2.0-16, player.getY()-getHeight()/2.0-16, getWidth(), getHeight());
        } else {
            point.update(ms);
            camera.setPosition(point.getX(), point.getY(), getWidth(), getHeight());
        }


        t1 = t2;
        keyState.update();
        mapEditor.update(ms);

        if (keyState.keyDown(KeyEvent.VK_Q)) {
            UnitCollection.spawnBullet(player.getX(), player.getY(), player.top.getAlpha(), ms);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        update();

        map.paint(g);
        UnitCollection.paint(g);
        mapEditor.paint(g);

        addMouseListener(mapEditor);
        addMouseMotionListener(this);
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int x2 = camera.getScreenX(player.getCenterX());
        int y2 = camera.getScreenY(player.getCenterY());
        int dy = y - y2;
        int dx = x - x2;
        double alpha = Math.atan2(dy, dx);
        player.setTopAlpha(alpha);
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

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {

    }
}
