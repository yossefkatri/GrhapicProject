package primitives;

import java.util.Objects;

public class Ray {
    private Point3D p00;
    private vector direction;
    /********** Constructors ***********/
    public Ray(Point3D p00, vector direction) {
        this.p00 = p00;
        this.direction = direction;
        this.direction.normalize();
    }

    public Ray() {
        this.p00=new Point3D();
        this.direction=new vector();
    }
    public Ray(Ray other) {
        this.p00=new Point3D(other.p00);
        this.direction=new vector(other.direction);
        this.direction.normalize();
    }
    /************** Getters/Setters *******/
    public Point3D getP00() {
        return p00;
    }

    public void setP00(Point3D p00) {
        this.p00 = p00;
    }

    public vector getDirection() {
        return direction;
    }

    public void setDirection(vector direction) {
        this.direction = direction;
    }
    /*************** Admin *****************/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return Objects.equals(p00, ray.p00) &&
                Objects.equals(direction, ray.direction);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p00=" + p00 +
                ", direction=" + direction +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(p00, direction);
    }
}
