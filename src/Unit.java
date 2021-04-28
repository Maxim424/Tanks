import java.awt.*;

public class Unit implements HitboxListener {
    protected Sprite bottom;
    protected Sprite top;
    protected Hitbox hb;

    public Unit(int x, int y) {
        this.bottom = new Sprite(x, y, Map.EMPTY, Map.EMPTY, 0, 0 );
        this.top =  new Sprite(x, y, Map.EMPTY, Map.EMPTY, 0, 0 );
        this.hb = new Hitbox(bottom, 5, this);
    }

    public void up () {
        bottom.setAlpha(-Math.PI/2);
    }
    public void down () {
        bottom.setAlpha(Math.PI/2);
    }
    public void left () {
        bottom.setAlpha(Math.PI);
    }
    public void right () {
        bottom.setAlpha(0);
    }

    public double getAlpha() {
        return bottom.getAlpha();
    }

    public void setAlpha(double alpha) {
        bottom.setAlpha(alpha);
    }

    public double getSpeed() {
        return bottom.getSpeed();
    }

    public void setSpeed(double speed) {
        bottom.setSpeed(speed);
    }

    public double getX() {
        return this.bottom.getX();
    }

    public double getY() {
        return this.bottom.getY();
    }

    public void update(int ms) {
        bottom.update(ms);
        top.update(ms);
        hb.update();
    }
    public void paint(Graphics g) {
        bottom.paint(g);
        top.paint(g);
    }



    @Override
    public void onCollision(HitboxEvent event) {

    }
}
