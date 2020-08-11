package Main;

public class RadiusCollider {
    private Vec3 pos;
    private float radius;
    private float height;

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
