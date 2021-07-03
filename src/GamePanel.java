import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.server.UnicastRemoteObject;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

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
        player.setBot(new Bot(player));
        for (int i = 0; i < 2; i++) {
            UnitCollection.spawnTank(8, 8);
        }
    }

    private void controlPlayer(int ms) {
        if (!mapEditor.isActive()) {
            if (keyState.keyDown(KeyEvent.VK_DOWN) || keyState.keyDown(KeyEvent.VK_S)) {
                player.down();
                player.setSpeed(55);
                player.update(15);
            } else if (keyState.keyDown(KeyEvent.VK_UP) || keyState.keyDown(KeyEvent.VK_W)) {
                player.up();
                player.setSpeed(55);
                player.update(15);
            } else if (keyState.keyDown(KeyEvent.VK_LEFT) || keyState.keyDown(KeyEvent.VK_A)) {
                player.left();
                player.setSpeed(55);
                player.update(15);
            } else if (keyState.keyDown(KeyEvent.VK_RIGHT) || keyState.keyDown(KeyEvent.VK_D)) {
                player.right();
                player.setSpeed(55);
                player.update(15);
            } else {
                player.setSpeed(0);
                player.update(15);
            }
        }
        keyState.update();
    }

    private void update() {
        w = getWidth();
        h = getHeight();
        t2 = System.currentTimeMillis();
        int ms = (int) (t2 - t1);

        if (!mapEditor.isActive()) {
            UnitCollection.update(ms);
            camera.setPosition(player.getX() - getWidth() / 2.0 - 16, player.getY() - getHeight() / 2.0 - 16, getWidth(), getHeight());
        } else {
            point.update(ms);
            camera.setPosition(point.getX(), point.getY(), getWidth(), getHeight());
        }


        t1 = t2;
        keyState.update();
        mapEditor.update(ms);

        if (keyState.keyDown(KeyEvent.VK_SPACE)) {
            UnitCollection.spawnBullet(player.getX(), player.getY(), player.top.getAlpha(), ms);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int ms = (int) (t2 - t1);
        controlPlayer(ms);

        map.paint(g);
        UnitCollection.paint(g);
        player.paint(g);
        mapEditor.paint(g);

        update();

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
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent){

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            Menu menu = new Menu();
            int screenwidth = Toolkit.getDefaultToolkit().getScreenSize().width;
            int screenheight = Toolkit.getDefaultToolkit().getScreenSize().height;
            int thisheight = 550;
            int thiswidth = 400;
            menu.setLocation((screenwidth - thiswidth) / 2, 50);
            menu.setSize(thiswidth, thisheight);
            JFrame ancestor = (JFrame) SwingUtilities.getWindowAncestor(this);
            ancestor.setVisible(false);
            ancestor.dispose();
            menu.setVisible(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}