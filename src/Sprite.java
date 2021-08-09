import java.awt.*;

public class Sprite {

    public int startFrame, lastFrame;
    private int currentFrame;
    private int elapsedFrameTime;
    private final int frameTime = 80;
    private int type;
    private double x, y;
    private double _x, _y;
    private double alpha, speed;
    public static final int UNIT_PART = 1;
    public static final int ANIMATION = 2;
    private final Camera camera = Camera.getInstance();

    public Sprite(double x, double y, int startFrame, int lastFrame,  double alpha, double speed) {
        this.startFrame = startFrame;
        this.currentFrame = startFrame;
        this.lastFrame = lastFrame;
        this.x = x;
        this.y = y;
        this.alpha = alpha;
        this.speed = speed;
        this.type = Sprite.UNIT_PART;
    }

    public Sprite(double x, double y, int startFrame, int lastFrame,  double alpha, double speed, int type) {
        this.startFrame = startFrame;
        this.currentFrame = startFrame;
        this.lastFrame = lastFrame;
        this.x = x;
        this.y = y;
        this.alpha = alpha;
        this.speed = speed;
        this.type = type;
    }

    public void undo() {
        x = _x;
        y = _y;
    }

    public void paint(Graphics g) {
        ImageHelper.paint(g, currentFrame, camera.getScreenX(x), camera.getScreenY(y), this.alpha);
    }

    private void updateXY(int ms) {
        _x = x ;
        _y = y;
        x = x + Math.cos(alpha) * speed * ms / 1000.0;
        y = y + Math.sin(alpha) * speed * ms / 1000.0;
    }

    private void updateFrames(int ms) {
        elapsedFrameTime += ms;
        if(elapsedFrameTime >= frameTime) {
            elapsedFrameTime = 0;
            nextFrame();
        }
    }

    private void nextFrame() {
        if (type == Sprite.ANIMATION) {
            if(currentFrame < lastFrame) {
                currentFrame++;
            }
        }
        else {
            if(speed!=0) {
                currentFrame++;
            }
            if(currentFrame > lastFrame) {
                currentFrame = startFrame;
            }
        }
    }

    public void update(int ms) {
        updateXY(ms);
        updateFrames(ms);
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int getStartFrame() {
        return startFrame;
    }

    public int getLastFrame() {
        return lastFrame;
    }
}
