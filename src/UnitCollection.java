import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class UnitCollection {

    public static ArrayList <Unit> list = new ArrayList<>(100);
    private final static int shootTime = 200;
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

    public static void spawnTank (int c, int r, int type) {
        Unit t;
        if (type == HitboxEvent.TANK_RED_TEAM) {
            t = new Tank(c * Map.BLOCK_SIZE, r * Map.BLOCK_SIZE, "red");
            t.type = HitboxEvent.TANK_RED_TEAM;

        }
        else {
            t = new Tank(c * Map.BLOCK_SIZE, r * Map.BLOCK_SIZE, "blue");
            t.type = HitboxEvent.TANK_BLUE_TEAM;
        }
        t.setSpeed(100);
        list.add(t);

    }

    public static void spawnBullet (double x, double y, double alpha, int ms) {

        elapsedTime += ms;
        if(elapsedTime >= shootTime) {
            elapsedTime = 0;
            Unit t;
            t = new Bullet(x, y, alpha);
            t.setSpeed(500);
            t.type = HitboxEvent.BULLET_RED_TEAM;
            list.add(t);
        }

    }

    public static void spawnBullet (Unit unit) {
        double x = unit.getX();
        double y = unit.getY();
        double alpha = unit.top.getAlpha();
        Unit t;
        t = new Bullet(x, y, alpha);
        t.setSpeed(500);
        if (unit.type == HitboxEvent.TANK_RED_TEAM) {
            t.type = HitboxEvent.BULLET_RED_TEAM;
        }
        else {
            t.type = HitboxEvent.BULLET_BLUE_TEAM;
        }
        list.add(t);

    }

    public static void update(int ms) {

        for (Unit u : list) {
            u.update(ms);
            if (u.active==false && System.currentTimeMillis()%1000<=100) {
                u.hb.body.setX(0);
                u.hb.body.setY(0);
            }
        }

    }
    public static void paint(Graphics g) {
        for (Unit u : list) {
            u.paint(g);
        }

    }



}
