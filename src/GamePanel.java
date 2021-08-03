import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

    private final Unit player;
    private final Empty point;
    private final MapEditor mapEditor = MapEditor.getInstance();
    private final Map map = Map.getInstance();
    private final Camera camera = Camera.getInstance();
    private KeyState keyState = KeyState.getInstance();

    public static boolean endgame;

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
                player.update(15);
            } else if (keyState.keyDown(KeyEvent.VK_UP) || keyState.keyDown(KeyEvent.VK_W)) {
                player.up();
                player.setSpeed(100);
                player.update(15);
            } else if (keyState.keyDown(KeyEvent.VK_LEFT) || keyState.keyDown(KeyEvent.VK_A)) {
                player.left();
                player.setSpeed(100);
                player.update(15);
            } else if (keyState.keyDown(KeyEvent.VK_RIGHT) || keyState.keyDown(KeyEvent.VK_D)) {
                player.right();
                player.setSpeed(100);
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

            if (System.currentTimeMillis()%3000<=50) {
                if (UnitCollection.list.get(0).active) {
                    UnitCollection.spawnBullet(UnitCollection.list.get(0));
                }
                if (UnitCollection.list.get(1).active) {
                    UnitCollection.spawnBullet(UnitCollection.list.get(1));
                }
                if (UnitCollection.list.get(2).active) {
                    UnitCollection.spawnBullet(UnitCollection.list.get(2));
                }
                if (UnitCollection.list.get(3).active) {
                    UnitCollection.spawnBullet(UnitCollection.list.get(3));
                }
                if (UnitCollection.list.get(4).active) {
                    UnitCollection.spawnBullet(UnitCollection.list.get(0));
                }
                if (UnitCollection.list.get(5).active) {
                    UnitCollection.spawnBullet(UnitCollection.list.get(5));
                }
                if (UnitCollection.list.get(6).active) {
                    UnitCollection.spawnBullet(UnitCollection.list.get(6));
                }
                if (UnitCollection.list.get(7).active) {
                    UnitCollection.spawnBullet(UnitCollection.list.get(7));
                }
                if (UnitCollection.list.get(8).active) {
                    UnitCollection.spawnBullet(UnitCollection.list.get(8));
                }
            }
            if (System.currentTimeMillis()%2000<=50) {
                if (UnitCollection.list.get(0).active) {
                    int rnd = (int)(Math.random()*5 + 4);
                    if (UnitCollection.list.get(rnd).active) {
                        UnitCollection.list.get(0).bot.target = UnitCollection.list.get(rnd);
                    }

                }
                if (UnitCollection.list.get(1).active) {
                    int rnd = (int)(Math.random()*5 + 4);
                    if (UnitCollection.list.get(rnd).active) {
                        UnitCollection.list.get(1).bot.target = UnitCollection.list.get(rnd);
                    }
                }
                if (UnitCollection.list.get(2).active) {
                    int rnd = (int)(Math.random()*5 + 4);
                    if (UnitCollection.list.get(rnd).active) {
                        UnitCollection.list.get(2).bot.target = UnitCollection.list.get(rnd);
                    }
                }
                if (UnitCollection.list.get(3).active) {
                    int rnd = (int)(Math.random()*5 + 4);
                    if (UnitCollection.list.get(rnd).active) {
                        UnitCollection.list.get(3).bot.target = UnitCollection.list.get(rnd);
                    }
                }
                if (UnitCollection.list.get(4).active) {
                    int rnd = (int)(Math.random()*4);
                    if (UnitCollection.list.get(rnd).active) {
                        UnitCollection.list.get(4).bot.target = UnitCollection.list.get(rnd);
                    }
                }
                if (UnitCollection.list.get(5).active) {
                    int rnd = (int)(Math.random()*4);
                    if (UnitCollection.list.get(rnd).active) {
                        UnitCollection.list.get(5).bot.target = UnitCollection.list.get(rnd);
                    }
                }
                if (UnitCollection.list.get(6).active) {
                    int rnd = (int)(Math.random()*4);
                    if (UnitCollection.list.get(rnd).active) {
                        UnitCollection.list.get(6).bot.target = UnitCollection.list.get(rnd);
                    }
                }
                if (UnitCollection.list.get(7).active) {
                    int rnd = (int)(Math.random()*4);
                    if (UnitCollection.list.get(rnd).active) {
                        UnitCollection.list.get(7).bot.target = UnitCollection.list.get(rnd);
                    }
                }
                if (UnitCollection.list.get(8).active) {
                    int rnd = (int)(Math.random()*4);
                    if (UnitCollection.list.get(rnd).active) {
                        UnitCollection.list.get(8).bot.target = UnitCollection.list.get(rnd);
                    }
                }
            }


            camera.setPosition(player.getX() - getWidth() / 2.0 - 16, player.getY() - getHeight() / 2.0 - 16, getWidth(), getHeight());
        } else{
            point.update(ms);
            camera.setPosition(point.getX(), point.getY(), getWidth(), getHeight());
        }

        if (!player.active) {
            endgame = true;
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
        if (!endgame) {
            update();
        }
        else {
            g.setColor(Color.RED);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
            g.drawString("Your tank has been destroyed.", w/2 - 200, h/2);
            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
            g.drawString("Press [esc] to exit to the main menu.", w/2 - 100, h/2 + 60);
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