import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class UnitCollection {

    private static ArrayList <Unit> list = new ArrayList<>(100);
    private final static int shootTime = 150;
    private static int elapsedTime;

    public static void add (Unit t) {
        list.add(t);
    }


    public static Unit getTarget(Unit owner ) {
        if(list.get(1) == owner) {
            return list.get(0);
        }
        return list.get(1);
    }

    public static void spawnTank (int c, int r) {
        Unit t;
        if (list.size() == 1) {
            t = new Tank(c * Map.BLOCK_SIZE, r * Map.BLOCK_SIZE, "blue");
        }
        else {
            t = new Tank(c * Map.BLOCK_SIZE, r * Map.BLOCK_SIZE, "red");
        }
        list.add(t);

    }

    public static void spawnBullet (double x, double y, double alpha, int ms) {

        elapsedTime += ms;
        if(elapsedTime >= shootTime) {
            elapsedTime = 0;
            Unit t;
            t = new Bullet(x, y, alpha);
            list.add(t);
        }

    }

    public static void update(int ms) {

        for (Unit u : list) {
            u.setSpeed(50);
            u.update(ms);
        }

    }
    public static void paint(Graphics g) {
        for (Unit u : list) {
            u.paint(g);
        }

    }



}
