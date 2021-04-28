import java.awt.*;

public class Hitbox {

    private int [] blackList = {Map.BRICK, Map.WATER, Map.WALL};
    private int padding;
    Sprite body;
    HitboxListener listener;
    Map map = Map.getInstance();

    public Hitbox (Sprite s, int padding, HitboxListener listener) {
        this.body = s;
        this.padding = padding;
        this.listener = listener;
    }

    public void update () {
        checkMapCollision();
    }

    private void checkMapCollision() {

        Point[] points = getCornerPoints();
        for (Point p : points) {
            int col = map.getColByX(p.x);
            int row = map.getRowByY(p.y);
            int block = map.getBlock(row, col);

            for (int curBlock : blackList) {
                if (curBlock == block) {


                    if(listener != null) {

                        HitboxEvent event = new HitboxEvent();
                        event.block = block;
                        event.x = p.x;
                        event.y = p.y;

                        listener.onCollision(event);
                    }
                    return;
                }
            }
        }
        return;
    }



    private Point[] getCornerPoints () {
        double x = body.getX();
        double y = body.getY();
        Point p0 = new Point((int)x + padding, (int)y + padding);
        Point p1 = new Point((int)x + Map.BLOCK_SIZE - padding, (int)y + padding);
        Point p2 = new Point((int)x + Map.BLOCK_SIZE - padding, (int)y + Map.BLOCK_SIZE - padding);
        Point p3 = new Point((int)x + padding, (int)y + Map.BLOCK_SIZE - padding);

        return new Point[] {p0, p1, p2, p3};

    }














}
