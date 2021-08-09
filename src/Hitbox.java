import java.awt.*;

public class Hitbox {

    private final int [] blackList = {Map.BRICK, Map.WATER, Map.WALL};
    private final int padding;
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
        checkUnitCollision();
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
                        event.type = HitboxEvent.BLOCK;
                        listener.onCollision(event);
                    }
                    return;
                }
            }
        }
        return;
    }

    private void checkUnitCollision() {
        Point[] points = getCornerPoints();
        Rectangle r = new Rectangle((int)points[0].getX(), (int)points[0].getY(), (int)(points[2].getX()-points[0].getX()), (int)(points[2].getY()-points[0].getY()));
        for (Unit curUnit : UnitCollection.list) {
            if (UnitCollection.list.indexOf(curUnit) == UnitCollection.list.indexOf(listener)) {
                continue;
            } else {
                Point[] points2 = curUnit.hb.getCornerPoints();
                Rectangle r2 = new Rectangle((int)points2[0].getX(), (int)points2[0].getY(), (int)(points2[2].getX()-points2[0].getX()), (int)(points2[2].getY()-points2[0].getY()));
                if (r.intersects(r2)) {
                    HitboxEvent event = new HitboxEvent();
                    event.type = curUnit.type;

                    listener.onCollision(event);
                }
            }

        }
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
