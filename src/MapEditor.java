import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class MapEditor implements MouseListener {

    private int w, h;
    private boolean active;
    private int currentBlock = Map.GROUND;
    private int currentObject = Map.GROUND;
    private int mode;
    private int brush;
    private int[] shiftArray;
    private int elapsedFrameTime;
    private int tapTime;

    private BufferedImage dock;
    private BufferedImage brick;
    private BufferedImage wall;
    private BufferedImage ground;
    private BufferedImage water;
    private BufferedImage bullet;
    private BufferedImage bullet_3;
    private BufferedImage delete;
    private BufferedImage tick;

    private Map map = Map.getInstance();
    private Camera camera = Camera.getInstance();
    private KeyState keyState = KeyState.getInstance();
    private static MapEditor instance = null;

    private MapEditor(int w, int h) {
        this.w = w;
        this.h = h;
        active = true;
        mode = 1;
        shiftArray = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 10};
        brush = 7;
        tapTime = 100;
        try {
            dock = ImageIO.read(new File("src/Dock.png"));
            brick = ImageIO.read(new File("src/Brick.png"));
            wall = ImageIO.read(new File("src/Wall.png"));
            ground = ImageIO.read(new File("src/Ground.png"));
            water = ImageIO.read(new File("src/Water.png"));
            bullet = ImageIO.read(new File("src/Bullet.png"));
            bullet_3 = ImageIO.read(new File("src/Bullet_3.png"));
            delete = ImageIO.read(new File("src/Delete.png"));
            tick = ImageIO.read(new File("src/Tick.png"));
        }
        catch (Exception e) {
            System.out.println("Файл не загружен ");
            System.exit(0);
        }

    }

    public static MapEditor getInstance() {
        if (instance == null) {
            instance = new MapEditor(1600, 900);
        }
        return instance;
    }

    public void paint(Graphics g) {
        if(active) {
            g.drawImage(dock, GamePanel.w/2-307, GamePanel.h-130, null);
            g.drawImage(brick, GamePanel.w/2-294, GamePanel.h-100 - shiftArray[0], null);
            g.drawImage(wall, GamePanel.w/2-294+75, GamePanel.h-100 - shiftArray[1], null);
            g.drawImage(ground, GamePanel.w/2-294+75*2, GamePanel.h-100 - shiftArray[2], null);
            g.drawImage(water, GamePanel.w/2-294+75*3, GamePanel.h-100 - shiftArray[3], null);
            g.drawImage(bullet, GamePanel.w/2-294+75*4, GamePanel.h-100 - shiftArray[4], null);
            g.drawImage(bullet_3, GamePanel.w/2-294+75*5, GamePanel.h-100 - shiftArray[5], null);
            g.drawImage(delete, GamePanel.w/2-294+75*6, GamePanel.h-100 - shiftArray[6], null);
            g.drawImage(tick, GamePanel.w/2-294+75*7 + 2, GamePanel.h-100 - shiftArray[7], null);
        }
    }

    public void update(int ms) {
        elapsedFrameTime += ms;
        if(elapsedFrameTime >= tapTime) {
            elapsedFrameTime = 0;
            if (keyState.keyDown(KeyEvent.VK_RIGHT)) {
                brush = (brush+1)%8;
            }
            else if (keyState.keyDown(KeyEvent.VK_LEFT)) {
                brush = (8+brush-1)%8;
            }

        }

        if (active) {
            if (brush==0) {
                currentBlock = Map.BRICK;
            } else if (brush==1) {
                currentBlock = Map.WALL;
            } else if (brush==2) {
                currentBlock = Map.GROUND;
            } else if (brush==3) {
                currentBlock = Map.WATER;
            } else if (brush==4) {
                currentObject = Map.BULLET_1;
            } else if (brush==5) {
                currentObject = Map.BULLET_3;
            } else if (brush==6) {
                currentObject = Map.EMPTY;
            }
        }

        for (int i = 0; i < shiftArray.length; i++) {
            shiftArray[i] = 0;
        }
        shiftArray[brush] = 10;

        if (keyState.keyDown(KeyEvent.VK_ENTER) && brush==7) {
            active = false;
        }

    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(active) {
            int screenX, screenY;
            screenX = e.getX();
            screenY = e.getY();
            if(brush<=3) {
                map.spawnBlock(camera.getWorldX(screenX), camera.getWorldY(screenY), currentBlock );
            }
            else if (brush>3 && brush<=6) {
                map.spawnObject(camera.getWorldX(screenX), camera.getWorldY(screenY), currentObject );
            }


        }

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
