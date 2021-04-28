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
        }
        else if (str=="blue") {
            bottom = new Sprite(x, y, 21, 30, 0, 2);
            top = new Sprite(x, y, 31, 31, 0, 0);
        }



        hb = new Hitbox(bottom, 5, this);
    }

    @Override
    public void update(int ms) {
        super.update(ms);
        top.setX( bottom.getX() );
        top.setY( bottom.getY() );

        if (keyState.keyDown(KeyEvent.VK_DOWN) || keyState.keyDown(KeyEvent.VK_S)) {
            down();
            setSpeed(50);
        }
        else if (keyState.keyDown(KeyEvent.VK_UP) || keyState.keyDown(KeyEvent.VK_W)) {
            up();
            setSpeed(50);
        }
        else if (keyState.keyDown(KeyEvent.VK_LEFT) || keyState.keyDown(KeyEvent.VK_A)) {
            left();
            setSpeed(50);
        }
        else if (keyState.keyDown(KeyEvent.VK_RIGHT) || keyState.keyDown(KeyEvent.VK_D)) {
            right();
            setSpeed(50);
        }
        else {
            setSpeed(0);
        }
    }

    @Override
    public void onCollision(HitboxEvent event) {
        super.onCollision(event);

        bottom.undo();
    }


}
