import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class UnitCollection {

    public static ArrayList <Unit> list = new ArrayList<>(100);

    public static void setTarget() {
        if (System.currentTimeMillis()%2000<=50) {
            double _rnd;
            if (UnitCollection.list.get(0).active) {
                int rnd = (int)(Math.random()*5 + 4);
                if (UnitCollection.list.get(rnd).active) {
                    UnitCollection.list.get(0).bot.target = UnitCollection.list.get(rnd);
                }

            }
            _rnd = Math.random();
            if (UnitCollection.list.get(1).active) {
                int rnd = (int)(Math.random()*5 + 4);
                if (UnitCollection.list.get(rnd).active) {
                    UnitCollection.list.get(1).bot.target = UnitCollection.list.get(rnd);
                }
            }
            _rnd = Math.random();
            if (UnitCollection.list.get(2).active) {
                int rnd = (int)(Math.random()*5 + 4);
                if (UnitCollection.list.get(rnd).active) {
                    UnitCollection.list.get(2).bot.target = UnitCollection.list.get(rnd);
                }
            }
            _rnd = Math.random();
            if (UnitCollection.list.get(3).active) {
                int rnd = (int)(Math.random()*5 + 4);
                if (UnitCollection.list.get(rnd).active) {
                    UnitCollection.list.get(3).bot.target = UnitCollection.list.get(rnd);
                }
            }
            _rnd = Math.random();
            if (_rnd > 0.5) {
                UnitCollection.list.get(4).bot.target = GamePanel.player;
            }
            else {
                if (UnitCollection.list.get(4).active) {
                    int rnd = (int)(Math.random()*4);
                    if (UnitCollection.list.get(rnd).active) {
                        UnitCollection.list.get(4).bot.target = UnitCollection.list.get(rnd);
                    }
                }
            }
            _rnd = Math.random();
            if (_rnd > 0.5) {
                UnitCollection.list.get(5).bot.target = GamePanel.player;
            }
            else {
                if (UnitCollection.list.get(5).active) {
                    int rnd = (int)(Math.random()*4);
                    if (UnitCollection.list.get(rnd).active) {
                        UnitCollection.list.get(5).bot.target = UnitCollection.list.get(rnd);
                    }
                }
            }
            _rnd = Math.random();
            if (_rnd > 0.5) {
                UnitCollection.list.get(6).bot.target = GamePanel.player;
            }
            else {
                if (UnitCollection.list.get(6).active) {
                    int rnd = (int)(Math.random()*4);
                    if (UnitCollection.list.get(rnd).active) {
                        UnitCollection.list.get(6).bot.target = UnitCollection.list.get(rnd);
                    }
                }
            }
            _rnd = Math.random();
            if (_rnd > 0.5) {
                UnitCollection.list.get(7).bot.target = GamePanel.player;
            }
            else {
                if (UnitCollection.list.get(7).active) {
                    int rnd = (int)(Math.random()*4);
                    if (UnitCollection.list.get(rnd).active) {
                        UnitCollection.list.get(7).bot.target = UnitCollection.list.get(rnd);
                    }
                }
            }
            _rnd = Math.random();
            if (_rnd > 0.5) {
                UnitCollection.list.get(8).bot.target = GamePanel.player;
            }
            else {
                if (UnitCollection.list.get(8).active) {
                    int rnd = (int)(Math.random()*4);
                    if (UnitCollection.list.get(rnd).active) {
                        UnitCollection.list.get(8).bot.target = UnitCollection.list.get(rnd);
                    }
                }
            }
        }
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

    public static void spawnBullets() {
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
    }

    public static void update(int ms) {
        for (Unit u : list) {
            u.update(ms);
            u.animation.update(ms);
            if (!u.active && System.currentTimeMillis()%1000<=100) {
                u.hb.body.setX(-Map.BLOCK_SIZE);
                u.hb.body.setY(-Map.BLOCK_SIZE);
            }
        }
    }

    public static void paint(Graphics g) {
        for (Unit u : list) {
            u.paint(g);
        }
    }
}
