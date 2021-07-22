import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank extends Unit {

    private KeyState keyState = KeyState.getInstance();

    public Tank(int x, int y) {
        super(x, y);

        bottom = new Sprite(x, y, 10, 19, 0, 2);
        top = new Sprite(x, y, 20, 20, 0, 0);
        hb = new Hitbox(bottom, 5, this);
    }

    public Tank(int x, int y, String str) {
        super(x, y);

        if(str=="red") {
            bottom = new Sprite(x, y, 10, 19, 0, 2);
            top = new Sprite(x, y, 20, 20, 0, 0);
            type = HitboxEvent.TANK_RED_TEAM;
        }
        else if (str=="blue") {
            bottom = new Sprite(x, y, 21, 30, 0, 2);
            top = new Sprite(x, y, 31, 31, 0, 0);
            type = HitboxEvent.TANK_BLUE_TEAM;
        }



        hb = new Hitbox(bottom, 5, this);
        bot = new TankBot(this);
        active = true;
    }

    @Override
    public void update(int ms) {
        if (active) {
            super.update(ms);
            top.setX( bottom.getX() );
            top.setY( bottom.getY() );
        }

    }

    @Override
    public void paint(Graphics g) {
        if (active) {
            super.paint(g);
        }

    }

    @Override
    public void onCollision(HitboxEvent event) {
        super.onCollision(event);
        if ((event.type==HitboxEvent.BULLET_RED_TEAM || event.type==HitboxEvent.BULLET_BLUE_TEAM) && type!=HitboxEvent.PLAYER && !(type == HitboxEvent.TANK_RED_TEAM && event.type==HitboxEvent.BULLET_RED_TEAM) && !(type == HitboxEvent.TANK_BLUE_TEAM && event.type==HitboxEvent.BULLET_BLUE_TEAM)) {
            active = false;
        }
        if (event.type != HitboxEvent.BULLET_BLUE_TEAM && event.type != HitboxEvent.BULLET_RED_TEAM) {
            bottom.undo();
        }

    }


}
