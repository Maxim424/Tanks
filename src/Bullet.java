import java.awt.*;
import java.awt.event.KeyEvent;

public class Bullet extends Unit {

    private boolean active;
    private Map map;

    public Bullet(double x, double y, double alpha) {
        super((int)x, (int)y);
        this.bottom = new Sprite(x, y, 32, 32, alpha, 200 );
        active = true;
        map = Map.getInstance();
        hb = new Hitbox(bottom, 5, this);
    }

    @Override
    public void update(int ms) {
        if (active) {
            super.update(ms);
            top.setX( bottom.getX() );
            top.setY( bottom.getY() );
            if (bottom.getX()<=0 || bottom.getY()<=0 || bottom.getY()>=Map.WORLD_SIZE*Map.BLOCK_SIZE ||
                    bottom.getX()>=Map.WORLD_SIZE*Map.BLOCK_SIZE) {
                active = false;
            }
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
        map.destroyBlock(event.x, event.y, event.block);
        if (event.block!=Map.WATER) {
            active = false;
        }

    }
}
