package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Cylinder extends Tube {
    protected double length;
    /********** Constructors ***********/
    public Cylinder(double _radius, Ray ray, double length) {
        super(_radius, ray);
        this.length = length;
    }
    public Cylinder(Color color, double _radius, Ray ray, double length) {
        super(color,_radius, ray);
        this.length = length;
    }
    /************** Getters/Setters *******/
    public double getLength() {
        return length;
    }
    /*************** Admin *****************/
    @Override
    public String toString() {
        return "Cylinder{" +
                "length=" + length +super.toString()+
                '}';
    }
    @Override
    public vector getNormal(Point3D p1) {
        vector v=new vector(this.getRay().getDirection());
        v.normalize();
        vector u=new vector(this.getRay().getP00(),p1);
        if(u.dotProduct(v)==0)
            return v.multiply(-1);
        vector proj=u.proj(v);//the proj of u on v
        if(proj.length()==this.getLength()) {
            return v;
        }
        return super.getNormal(p1);
    }

    @Override
    public List<Point3D> findIntersections(Ray ray)
    {
        List<Point3D> Intersections = new ArrayList<Point3D>();
        Intersections = super.findIntersections(ray);
        vector v0 = new vector(this.getRay().getDirection());
        v0.normalize();
        Point3D p1 = new Point3D(this.getRay().getP00());
        Point3D p2 = new Point3D(new Point3D(p1).add(new vector(v0).multiply(this.getLength())));//p2=p1+v0*length
        if (!Intersections.isEmpty()) {
            for (int i = 0; i < Intersections.size(); i++) {
                Point3D qi = Intersections.get(i);//qi=p+v*ti
                vector qiSUBp1 = qi.substract(p1);
                vector qiSUBp2 = qi.substract(p2);
                double test1 = v0.dotProduct(qiSUBp1) / (qiSUBp1.length() * qiSUBp1.length());
                double test2 = v0.dotProduct(qiSUBp2) / (qiSUBp2.length() * qiSUBp2.length());
                vector qiSUBp = new vector(qi.substract(ray.getP00()));
                double ti = qiSUBp.gethead().getz().get() / ray.getDirection().gethead().getz().get();
                if (!(ti > 0 && test1 > 0 && test2 < 0)) {
                    Intersections.remove(i);
                }
            }
        }
        Plane upper = new Plane(p2, new vector(v0));
        Plane down = new Plane(p1, new vector(v0));
        //the caps interections
        List<Point3D> IntersectionsPlaneUpper = new ArrayList<Point3D>();
        List<Point3D> IntersectionsPlaneDown = new ArrayList<Point3D>();

        IntersectionsPlaneUpper = upper.findIntersections(ray);
        IntersectionsPlaneDown = down.findIntersections(ray);

        if (!IntersectionsPlaneUpper.isEmpty())
        {
            Point3D q4 = IntersectionsPlaneUpper.get(0);//q4=p+v*t4
            vector q4SUBp2 = q4.substract(p2);
            double test = q4SUBp2.dotProduct(q4SUBp2);
            vector q4SUBp = new vector(q4.substract(ray.getP00()));
            double t4 = q4SUBp.gethead().getz().get() / ray.getDirection().gethead().getz().get();
            if (!(t4 > 0 && test < get_radius()*get_radius()) ){
                IntersectionsPlaneUpper.remove(0);
            }
        }
        if (!IntersectionsPlaneDown.isEmpty())
        {
            Point3D q3 = IntersectionsPlaneDown.get(0);//q3=p+v*t3
            vector q3SUBp1 = q3.substract(p1);
            double test = q3SUBp1.dotProduct(q3SUBp1);
            vector q3SUBp = new vector(q3.substract(ray.getP00()));
            double t3 = q3SUBp.gethead().getz().get() / ray.getDirection().gethead().getz().get();
            if (!(t3 > 0 && test < get_radius()*get_radius()) ){
                IntersectionsPlaneDown.remove(0);
            }

        }
        Intersections.addAll(IntersectionsPlaneUpper);
        Intersections.addAll(IntersectionsPlaneDown);
        return Intersections;
    }
}
