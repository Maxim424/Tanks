import java.awt.*;

public class Unit implements HitboxListener {
    protected Sprite bottom;
    protected Sprite top;
    protected Hitbox hb;
    protected Bot bot;
    public int type;
    public boolean active;

    public Unit(int x, int y) {
        this.bottom = new Sprite(x, y, Map.EMPTY, Map.EMPTY, 0, 0 );
        this.top =  new Sprite(x, y, Map.EMPTY, Map.EMPTY, 0, 0 );
        this.hb = new Hitbox(bottom, 5, this);
        this.bot = new Bot(this);
    }

    public void up () {
        bottom.setAlpha(-Math.PI/2);
        //top.setAlpha(-Math.PI/2);
    }
    public void down () {
        bottom.setAlpha(Math.PI/2);
        //top.setAlpha(Math.PI/2);
    }
    public void left () {
        bottom.setAlpha(Math.PI);
        //top.setAlpha(Math.PI);
    }
    public void right () {
        bottom.setAlpha(0);
        //top.setAlpha(0);
    }

    public double getCenterX() {
        return bottom.getX() + Map.BLOCK_SIZE/2;
    }
    public double getCenterY() {
        return bottom.getY() + Map.BLOCK_SIZE/2;
    }
    public void setTopAlpha(double alpha) {
        top.setAlpha(alpha);
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
        bot.update(ms);
    }
    public void paint(Graphics g) {
        bottom.paint(g);
        top.paint(g);
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onCollision(HitboxEvent event) {

    }
}
