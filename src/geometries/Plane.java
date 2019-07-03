package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.vector;

import java.util.ArrayList;
import java.util.List;

public class Plane extends Geometry implements FlatGeometry{
    protected Point3D P;
    protected vector normal;
    /********** Constructors ***********/
    public Plane(Point3D p, vector normal) {
        P = p;
        this.normal = normal;
    }
    public Plane(Point3D p1,Point3D p2,Point3D p3) {

            vector v1 = new vector(p1, p2);
            vector v2 = new vector(p1, p3);
            vector n = new vector(v1.crossproduct(v2));
            n.normalize();
            n.multiply(-1);

            P = new Point3D(p1);
            normal = new vector(n);
        }
    public Plane(Color color, Point3D p, vector normal) {
        super(color);
        P = p;
        this.normal = normal;
    }
    public Plane(Color color,Point3D p1,Point3D p2,Point3D p3) {
        super(color);
        vector v1 = new vector(p1, p2);
        vector v2 = new vector(p1, p3);
        vector n = new vector(v1.crossproduct(v2));
        n.normalize();
        n.multiply(-1);

        P = new Point3D(p1);
        normal = new vector(n);
    }


    /************** Getters/Setters *******/
    public Point3D getP() {
        return P;
    }

    public vector getNormal() {
        return normal;
    }
    /*************** Admin *****************/
    @Override
    public String toString() {
        return "Plane{" +
                "P=" + P +
                ", normal=" + normal +
                '}';
    }

    @Override
    public vector getNormal(Point3D p1) {
        return new vector(getNormal());
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
    List<Point3D> Intersections=new ArrayList<Point3D>();
    Point3D P0=ray.getP00();
    Point3D Q0=this.getP();
    vector normal=this.getNormal(null);
    vector v=ray.getDirection();
    if(normal.dotProduct(v)==0)
    {
        return EMPTY_LIST;
    }
    vector u=new vector(P0,Q0);
    double t=(normal.dotProduct(u))/normal.dotProduct(v);
    if(t>0)
        Intersections.add(ray.getP00().add(v.multiply(t)));
    if(t==0)
        Intersections.add(ray.getP00());
    return Intersections;
    }
}
