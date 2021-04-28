import java.awt.event.KeyEvent;

public class Empty {
    private double x;
    private double y;
    private double alpha, speed;
    private KeyState keyState = KeyState.getInstance();

    public Empty(double x, double y) {
        this.x = x;
        this.y = y;
        alpha = 0;
        speed = 0;
    }

    public void update(int ms) {
        x =  (x + Math.cos(alpha) * speed * ms / 1000.0);
        if (x<0) {
            x = 0;
        } else if (x>GamePanel.w) {
            x =  GamePanel.w;
        }
        y =  (y + Math.sin(alpha) * speed * ms / 1000.0);
        if (y<0) {
            y = 0;
        } else if (y>GamePanel.h) {
            y =  GamePanel.h;
        }
        if (keyState.keyDown(KeyEvent.VK_S)) {
            down();
            setSpeed(400);
        }
        else if (keyState.keyDown(KeyEvent.VK_W)) {
            up();
            setSpeed(400);
        }
        else if (keyState.keyDown(KeyEvent.VK_A)) {
            left();
            setSpeed(400);
        }
        else if (keyState.keyDown(KeyEvent.VK_D)) {
            right();
            setSpeed(400);
        }
        else {
            setSpeed(0);
        }
    }

    public void up () {
        setAlpha(-Math.PI/2);
    }
    public void down () {
        setAlpha(Math.PI/2);
    }
    public void left () {
        setAlpha(Math.PI);
    }
    public void right () {
        setAlpha(0);
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
