package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.vector;

import java.util.*;

public class Sphere extends RadialGeometry {
    private Point3D center;
    /********** Constructors ***********/
    public Sphere(double _radius, Point3D center) {
        super(_radius);
        this.center = center;
    }
    public Sphere(Color color, double _radius, Point3D center) {
        super(color,_radius);
        this.center = center;
    }
    /************** Getters/Setters *******/
    public Point3D getCenter() {
        return center;
    }
    /*************** Admin *****************/
    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +super.toString()+
                '}';
    }

    @Override
    public vector getNormal(Point3D p1)  {
        try {
            vector n = new vector(center, p1);
            n.normalize();
            return n;
        }
        catch (Exception ex)
        {
            return new vector();
        }
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        vector u;
        try {
           u=new vector(ray.getP00(),this.getCenter());
        }
        catch (Exception ex)
        {
            u=new vector();
        }
        double tm=u.dotProduct(ray.getDirection());
        double d=Math.sqrt(u.length()*u.length()-tm*tm);
        if(d>this.get_radius())
            return EMPTY_LIST;
        double th=Math.sqrt(this.get_radius()*this.get_radius()-d*d);
        double t1=tm+th;
        double t2=tm-th;
        List<Point3D> Intersections=new ArrayList<Point3D>();
        vector v=new vector(ray.getDirection());
        if(t1>0)
            Intersections.add(ray.getP00().add(v.multiply(t1)));
        if(t2>0)
            Intersections.add(ray.getP00().add(new vector(ray.getDirection()).multiply(t2)));
        if(t1==0||t2==0)
            Intersections.add(ray.getP00());
        return Intersections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sphere)) return false;
        Sphere sphere = (Sphere) o;
        return Objects.equals(center, sphere.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center);
    }
}
