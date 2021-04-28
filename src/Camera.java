import java.awt.*;

public class Camera {

    private double x = 0, y = 0;

    private static Camera instance = null;

    private Camera() {

    }

    public static Camera getInstance() {
        if (instance == null) {
            instance = new Camera();
        }
        return instance;
    }

    public double getLeft() {
        return x;
    }

    public double getTop() {
        return y;
    }

    public double getRight() {
        return x + GamePanel.w - 1;
    }

    public double getBottom() {
        return y + GamePanel.h - 1;
    }

    public void setPosition (double x, double y, double w, double h) {
        this.x = x;
        this.y = y;

        if (x < 0) {
            this.x = 0;
        }
        if (y < 0) {
            this.y = 0;
        }
        if (x+GamePanel.w>Map.WORLD_SIZE*Map.BLOCK_SIZE) {
            this.x = Map.WORLD_SIZE*Map.BLOCK_SIZE-GamePanel.w;
        }
        if (y+GamePanel.h>Map.WORLD_SIZE*Map.BLOCK_SIZE) {
            this.y = Map.WORLD_SIZE*Map.BLOCK_SIZE-GamePanel.h;
        }

    }


    public double getWorldX (double screenX) {
        return (screenX + x);
    }
    public double getWorldY (double screenY) {
        return (screenY + y);
    }

    public int getScreenX (double wX) {
        return (int)(wX - x);
    }
    public int getScreenY (double wY) {
        return (int)(wY - y);
    }

}
