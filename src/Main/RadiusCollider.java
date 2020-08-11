package Main;

public class RadiusCollider {
    protected Vec3 pos;
    protected float radius;
    protected float height;

    public RadiusCollider(Vec3 pos, float radius, float height) {
        this.pos = pos;
        this.radius = radius;
        this.height = height;
    }

    public Vec3 getPos() {
        return pos;
    }

    public float getRadius() {
        return radius;
    }

    public float getHeight() {
        return height;
    }
}
