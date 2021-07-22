public class Bot implements HitboxListener {

    protected Unit unit;
    public Unit target;

    public Bot (Unit u) {
        this.unit = u;
    }


    public  void update(int ms) {

    }

    @Override
    public void onCollision(HitboxEvent event) {

    }
}