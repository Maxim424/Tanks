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
    }

    @Override
    public void update(int ms) {
        super.update(ms);
        top.setX( bottom.getX() );
        top.setY( bottom.getY() );

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
        map.destroyBlock(event.x, event.y);
        active = false;
    }
}
