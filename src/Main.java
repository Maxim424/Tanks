import javax.swing.*;
import java.awt.*;

public class Main {

    private final Map map = Map.getInstance();
    private final GamePanel gamePanel = new GamePanel();

    public Main() {
        ImageHelper.crop(1, 0, Map.WALL);
        ImageHelper.crop(0, 3, Map.WATER);
        ImageHelper.crop(0, 2, Map.GROUND);
        ImageHelper.crop(1, 1, Map.BRICK);
        ImageHelper.crop(0, 4, Map.EMPTY);
        ImageHelper.crop(4, 3, Map.BULLET_1);
        ImageHelper.crop(4, 2, Map.BULLET_3);

        // 10 - 19 Red platform.
        for (int i = 0; i < 10; i++) {
            ImageHelper.crop(2, i, 10 + i);
        }

        // 21 - 30 Blue platform.
        for (int i = 0; i < 10; i++) {
            ImageHelper.crop(10, i, 21 + i);
        }

        // 20 Red tank tower.
        ImageHelper.crop(3, 0, 20);

        // 31 Blue tank tower.
        ImageHelper.crop(3, 2, 31);

        // 32 Bullet.
        ImageHelper.crop(4, 1, 32);

        // 33 - 42 Explosion.
        for (int i = 4; i <= 8; i++) {
            ImageHelper.crop(8, i, 33 + i - 4);
        }
        for (int i = 4; i <= 8; i++) {
            ImageHelper.crop(9, i, 38 + i - 4);
        }

        JFrame jFrame = new JFrame();
        jFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        jFrame.setUndecorated(false);
        jFrame.setMinimumSize(new Dimension(720, 405));
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.addKeyListener(KeyState.getInstance());
        jFrame.addKeyListener(gamePanel);
        jFrame.addMouseListener(gamePanel);
        jFrame.add(gamePanel);

        jFrame.revalidate();
    }

    public void newGame() {
        UnitCollection.list.clear();
        map.createWorld(Map.WORLD_SIZE, Map.WORLD_SIZE);
        gamePanel.createBots();
        GamePanel.endgame = false;
        GamePanel.isFree = true;
        MapEditor mapEditor = MapEditor.getInstance();
        mapEditor.setActive(true);
    }

    public void newMap() {
        map.createWorld(Map.WORLD_SIZE, Map.WORLD_SIZE);
        GamePanel.isFree = true;
        gamePanel.activateEditor();
    }

    public void continueGame() {
        GamePanel.player.bottom.setX(GamePanel.savedX);
        GamePanel.player.bottom.setY(GamePanel.savedY);
    }

    public void loadGame(int slot) {
        map.loadMatrix(slot);
        GamePanel.isFree = true;
    }

    public void loadGameFromStart(int slot) {
        UnitCollection.list.clear();
        gamePanel.createBots();
        map.loadMatrix(slot);
        GamePanel.endgame = false;
        GamePanel.isFree = true;
    }

    public void loadLevel(int slot) {
        map.loadMatrixLevel(slot);
        GamePanel.isFree = false;
    }

    public void loadLevelFromStart(int slot) {
        UnitCollection.list.clear();
        gamePanel.createBots();
        map.loadMatrixLevel(slot);
        GamePanel.endgame = false;
        GamePanel.isFree = false;
    }

    public void saveGame(int slot, String name) {
        map.saveMatrix(slot, name);
        GamePanel.isFree = true;
        continueGame();
    }
}
