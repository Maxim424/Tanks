import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        ImageHelper.crop(1, 0, Map.WALL);
        ImageHelper.crop(0, 3, Map.WATER);
        ImageHelper.crop(0, 2, Map.GROUND);
        ImageHelper.crop(1, 1, Map.BRICK);
        ImageHelper.crop(0, 4, Map.EMPTY);
        ImageHelper.crop(4, 3, Map.BULLET_1);
        ImageHelper.crop(4, 2, Map.BULLET_3);


        // 10 - 19 Красная платформа
        for (int i = 0; i < 10; i++) {
            ImageHelper.crop(2, i, 10+i);
        }

        // 21 - 30 Синяя платформа
        for (int i = 0; i < 10; i++) {
            ImageHelper.crop(10, i, 21+i);
        }


        // 20 Красная башня
        ImageHelper.crop(3, 0, 20);

        // 31 Синяя башня
        ImageHelper.crop(3, 2, 31);

        // 32 Пуля башня
        ImageHelper.crop(4, 1, 32);

        JFrame jFrame = new JFrame();
        jFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        jFrame.setMinimumSize(new Dimension(1000, 700));
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.addKeyListener(KeyState.getInstance());

        GamePanel panel = new GamePanel();
        jFrame.add(panel);

        jFrame.revalidate();

    }
}
