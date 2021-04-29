public class TankBot extends Bot {

    private int  timeToChangeOrientation        = 0;
    private int  elapsedTimeToChangeOrientation = 0;

    private Unit target;

    public TankBot(Unit u) {
        super(u);
    }


    private void rotateGun() {
        if (target==null) {
            return;
        }

        int x = (int)target.getCenterX();
        int y = (int)target.getCenterY();

        int x2 = (int)unit.getCenterX();
        int y2 = (int)unit.getCenterY();
        int dy = y - y2;
        int dx = x - x2;
        double alpha = Math.atan2(dy, dx);
        unit.setTopAlpha(alpha);
    }


    private void changeOrientation2() {
        if (target==null) {
            return;
        }

        if (Math.random() < 0.5) {
            // Сближение по горизонтали
            if (target.getX() < unit.getX()) {
                unit.left();
            }
            else {
                unit.right();
            }
        }
        else {
            if (target.getY() < unit.getY()) {
                unit.up();
            }
            else {
                unit.down();
            }
        }

    }


    @Override
    public void onCollision(HitboxEvent event) {
        super.onCollision(event);
        changeOrientation();
    }

    @Override
    public void update(int ms) {
        super.update(ms);

        target = UnitCollection.getTarget(unit);

        elapsedTimeToChangeOrientation += ms;
        if (elapsedTimeToChangeOrientation >= timeToChangeOrientation) {
            if (Math.random() < 0.8) {
                changeOrientation2();
            }
            else {
                changeOrientation();
            }
            timeToChangeOrientation = (int)(Math.random() * 2000 + 1500);
            elapsedTimeToChangeOrientation = 0;
        }

        rotateGun();

    }

    private void changeOrientation() {
        int orientation = (int)(Math.random() * 4);
        switch (orientation) {
            case 0:
                unit.right();
                break;
            case 1:
                unit.left();
                break;
            case 2:
                unit.up();
                break;
            case 3:
                unit.down();
                break;
        }
    }
}
