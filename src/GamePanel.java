import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

    private final Unit player;
    private final Empty point;
    private final MapEditor mapEditor = MapEditor.getInstance();
    private final Map map = Map.getInstance();
    private final Camera camera = Camera.getInstance();
    private KeyState keyState = KeyState.getInstance();

    public static boolean endgame;
    public static boolean isthisfree;

    private long t1, t2;
    public static int w;
    public static int h;

    public GamePanel() {
        t1 = System.currentTimeMillis();
        point = new Empty(0, 0);
        player = new Tank(Map.BLOCK_SIZE*8, Map.BLOCK_SIZE*8, "red");

        player.setBot(new Bot(player));
        player.type = HitboxEvent.TANK_RED_TEAM;
        player.setSpeed(100);
        endgame = false;
    }

    public void activateEditor(){
        mapEditor.setActive(true);
    }

    public void createBots(){
        UnitCollection.spawnTank(12, 4, HitboxEvent.TANK_RED_TEAM);
        UnitCollection.spawnTank(10, 6, HitboxEvent.TANK_RED_TEAM);
        UnitCollection.spawnTank(6, 10, HitboxEvent.TANK_RED_TEAM);
        UnitCollection.spawnTank(4, 12, HitboxEvent.TANK_RED_TEAM);

        UnitCollection.spawnTank(Map.WORLD_SIZE - 12, Map.WORLD_SIZE - 4, HitboxEvent.TANK_BLUE_TEAM);
        UnitCollection.spawnTank(Map.WORLD_SIZE - 10, Map.WORLD_SIZE - 6, HitboxEvent.TANK_BLUE_TEAM);
        UnitCollection.spawnTank(Map.WORLD_SIZE - 8, Map.WORLD_SIZE - 8, HitboxEvent.TANK_BLUE_TEAM);
        UnitCollection.spawnTank(Map.WORLD_SIZE - 6, Map.WORLD_SIZE - 10, HitboxEvent.TANK_BLUE_TEAM);
        UnitCollection.spawnTank(Map.WORLD_SIZE - 4, Map.WORLD_SIZE - 12, HitboxEvent.TANK_BLUE_TEAM);

    }

    private void controlPlayer(int ms) {
        if (!mapEditor.isActive()) {
            if (keyState.keyDown(KeyEvent.VK_DOWN) || keyState.keyDown(KeyEvent.VK_S)) {
                player.down();
                player.setSpeed(100);
                player.update(ms);
            } else if (keyState.keyDown(KeyEvent.VK_UP) || keyState.keyDown(KeyEvent.VK_W)) {
                player.up();
                player.setSpeed(100);
                player.update(ms);
            } else if (keyState.keyDown(KeyEvent.VK_LEFT) || keyState.keyDown(KeyEvent.VK_A)) {
                player.left();
                player.setSpeed(100);
                player.update(ms);
            } else if (keyState.keyDown(KeyEvent.VK_RIGHT) || keyState.keyDown(KeyEvent.VK_D)) {
                player.right();
                player.setSpeed(100);
                player.update(ms);
            } else {
                player.setSpeed(0);
                player.update(ms);
            }
            if (keyState.keyDown(KeyEvent.VK_SPACE)) {
                UnitCollection.spawnBullet(player.getX(), player.getY(), player.top.getAlpha(), ms);
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
            UnitCollection.spawnBullets();
            UnitCollection.setTarget();
            camera.setPosition(player.getX() - getWidth() / 2.0 - 16, player.getY() - getHeight() / 2.0 - 16, getWidth(), getHeight());
        } else{
            point.update(ms);
            camera.setPosition(point.getX(), point.getY(), getWidth(), getHeight());
        }
        if (!player.active) {
            endgame = true;
        }
        int cnt = 0;
        for (int i = 4; i<=8; i++) {
            if (!UnitCollection.list.get(i).active) {
                cnt++;
            }
        }
        if (cnt == 5) {
            endgame = true;
        }
        keyState.update();
        mapEditor.update(ms);
        controlPlayer(ms);
        t1 = t2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        map.paint(g);
        UnitCollection.paint(g);
        player.paint(g);
        mapEditor.paint(g);
        if (!endgame) {
            update();
        }
        else {

            if (player.active) {
                try {
                    g.drawImage(ImageIO.read(new File("images/YouWon.png")), w/2 - 360, h/2 - 202, null);
                }
                catch (Exception e) {
                    g.setColor(Color.GREEN);
                    g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                    g.drawString("You just won the battle", w/2 - 200, h/2);
                    g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
                    g.drawString("Press [esc] to exit to the main menu", w/2 - 100, h/2 + 60);
                }
            }
            else {
                try {
                    g.drawImage(ImageIO.read(new File("images/GameOver.png")), w/2 - 360, h/2 - 202, null);
                }
                catch (Exception e) {
                    g.setColor(Color.RED);
                    g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                    g.drawString("Your tank has been destroyed", w/2 - 200, h/2);
                    g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
                    g.drawString("Press [esc] to exit to the main menu", w/2 - 100, h/2 + 60);
                }
            }
        }



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
            if (!endgame) {
                if (isthisfree) {
                    PauseFreeMenu pauseFreeMenu = new PauseFreeMenu();
                    int screenwidth = Toolkit.getDefaultToolkit().getScreenSize().width;
                    int screenheight = Toolkit.getDefaultToolkit().getScreenSize().height;
                    int thisheight = 550;
                    int thiswidth = 400;
                    pauseFreeMenu.setLocation((screenwidth - thiswidth) / 2, 50);
                    pauseFreeMenu.setSize(thiswidth, thisheight);
                    JFrame ancestor = (JFrame) SwingUtilities.getWindowAncestor(this);
                    ancestor.setVisible(false);
                    ancestor.dispose();
                    pauseFreeMenu.setVisible(true);
                }
                else{
                    PauseLevelMenu pauseLevelMenu = new PauseLevelMenu();
                    int screenwidth = Toolkit.getDefaultToolkit().getScreenSize().width;
                    int screenheight = Toolkit.getDefaultToolkit().getScreenSize().height;
                    int thisheight = 400;
                    int thiswidth = 300;
                    pauseLevelMenu.setLocation((screenwidth - thiswidth) / 2, 50);
                    pauseLevelMenu.setSize(thiswidth, thisheight);
                    JFrame ancestor = (JFrame) SwingUtilities.getWindowAncestor(this);
                    ancestor.setVisible(false);
                    ancestor.dispose();
                    pauseLevelMenu.setVisible(true);
                }
            }
            else {
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
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}