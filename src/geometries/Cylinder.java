package geometries;

import primitives.*;
import primitives.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


/*public class Cylinder extends RadialGeometry{
    private final static double Zero = 1e-4;
    protected Ray ray;
    protected double length;
    /********** Constructors ***********/
   /* public Cylinder(double _radius, Ray ray, double length) {
        super(_radius);
        this.ray=ray;
        this.length = length;
    }
    public Cylinder(Color color, double _radius, Ray ray, double length) {
        super(color,_radius);
        this.ray=ray;
        this.length = length;
    }
    /************** Getters/Setters *******/
    /*public double getLength() {
        return length;
    }
    /*************** Admin *****************/
    /*@Override
    /*public String toString() {
        return "Cylinder{" +
                "ray=" + ray +
                ", length=" + length +
                ", _radius=" + _radius +
                '}';
    }
    @Override
    public vector getNormal(Point3D p1) {
        vector C=new vector(ray.getDirection());
        C.normalize();
        vector Tmp=new vector(ray.getP00(),p1);
        double t=Tmp.dotProduct(C)/C.dotProduct(ray.getDirection());
        Point3D Pt=ray.getP00().add(ray.getDirection().multiply(t));
        vector n=new vector(Pt,p1);
        n.normalize();
        return n;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray)
    /*{
        vector origin=new vector(ray.getP00());
        vector _center=new vector(this.ray.getP00());
        vector direction=new vector(this.ray.getDirection());
        double _height=length;
        var intersectionPoint = origin.substract(_center);
        origin=new vector(ray.getP00());
        var isBelongToCylinderBase = false;
        var ts1 = (_height - origin.gethead().gety().get() + _center.gethead().gety().get()) / direction.gethead().gety().get();
        var point = intersectionPoint.add(direction.multiply(ts1));
        direction=new vector(this.ray.getDirection());
        intersectionPoint = origin.substract(_center);
        origin=new vector(ray.getP00());

        if (point.gethead().getx().get() * point.gethead().getx().get() + point.gethead().getz().get() * point.gethead().getz().get() - _radius * _radius < Zero)
            isBelongToCylinderBase = true;

        var ts2 = (-_height - origin.gethead().gety().get() + _center.gethead().gety().get()) / direction.gethead().gety().get();
        point = intersectionPoint.add(direction.multiply(ts2));
        direction=new vector(this.ray.getDirection());
        origin=new vector(ray.getP00());

        if (point.gethead().getx().get() * point.gethead().getx().get() + point.gethead().getz().get() * point.gethead().getz().get() - _radius * _radius < Zero)
            isBelongToCylinderBase = true;

        var a = direction.gethead().getx().get() * direction.gethead().getx().get() + direction.gethead().getz().get() * direction.gethead().getz().get();
        var b =
                (origin.gethead().getx().get() * direction.gethead().getx().get() - direction.gethead().getx().get() * _center.gethead().getx().get() + origin.gethead().getz().get() * direction.gethead().getz().get() - direction.gethead().getz().get() * _center.gethead().getz().get());
        var c = origin.gethead().getx().get() * origin.gethead().getx().get() + _center.gethead().getx().get() * _center.gethead().getx().get() + origin.gethead().getz().get() * origin.gethead().getz().get() + _center.gethead().getz().get() * _center.gethead().getz().get() -
                2 * (origin.gethead().getx().get() * _center.gethead().getx().get() + origin.gethead().getz().get() * _center.gethead().getz().get()) - _radius * _radius;

        var discriminant = b * b - a * c;

        if (discriminant < 0.001)
        {
            if (isBelongToCylinderBase)
            {
                double t = Math.min(ts1, ts2);
                List<Point3D> intersectionsPoint=new ArrayList<Point3D>();
                intersectionsPoint.add(ray.getP00().add(new vector(ray.getDirection()).multiply(t)));//p0+t*v
                return intersectionsPoint;
            }
            return EMPTY_LIST;
        }
        double t;
        var t1 = (-b - Math.sqrt(discriminant)) / a;
        var t2 = (-b + Math.sqrt(discriminant)) / a;

        t = t1;
        if (t1 < 0.001)
            t = t2;

        if (!(Math.abs(origin.gethead().gety().get() + t * direction.gethead().gety().get() - _center.gethead().gety().get()) > _height))
        {
            if(t>Zero)
            {
                List<Point3D> intersectionsPoint=new ArrayList<Point3D>();
                intersectionsPoint.add(ray.getP00().add(new vector(ray.getDirection()).multiply(t)));//p0+t*v
                return intersectionsPoint;
            }
            else
            {
               return EMPTY_LIST;
            }
        }
        if (!isBelongToCylinderBase) return EMPTY_LIST;
        t = Math.min(ts1, ts2);
        List<Point3D> intersectionsPoint=new ArrayList<Point3D>();
        intersectionsPoint.add(ray.getP00().add(new vector(ray.getDirection()).multiply(t)));//p0+t*v
        return intersectionsPoint;
    }*/
    /*{
        double Aq,Bq,Cq,Det,FirstT,SecondT,Int1,Int2;
        List<Point3D> intersections=new ArrayList<Point3D>();
        vector K,D;
        double T1,T2,T3;
        T2=new vector(ray.getP00()).dotProduct(this.ray.getDirection())-new vector(this.ray.getP00()).dotProduct(this.ray.getDirection());
        T1=T2/length;
        vector v=new vector(this.ray.getDirection());
        K=(ray.getP00().substract(this.ray.getP00())).substract(v.multiply(T1));
        T3=ray.getDirection().dotProduct(this.ray.getDirection());
        T1=T3/length;
        D=new vector(ray.getDirection()).substract(new vector(this.ray.getDirection()).multiply(T1));
        Aq=D.dotProduct(D);
        Bq=2*D.dotProduct(K);
        Cq=K.dotProduct(K)-this.get_radius()*this.get_radius();
        Det=Bq*Bq-4*Aq*Cq;
        if(Det>=0)
        {
            Int1=Math.sqrt(Det);
            Int2=2*Aq;
            FirstT=(-Bq+Int1)/Int2;
            if(FirstT<0.001)
            {
                SecondT=(-Bq-Int1)/Int2;
                if(SecondT< 0.001)
                    return EMPTY_LIST;   //ray missed
                T1=(T2+SecondT*T3)/length;
                if(T1>=0&&T1<=1) {
                    Point3D p1=ray.getP00().add(new vector(ray.getDirection()).multiply(SecondT));//p1=p0+SecondT*V0
                    intersections.add(p1);
                }
            }
            else{
                SecondT=(-Bq-Int1)/Int2;
                if(FirstT<SecondT)
                {
                    T1=(T2+FirstT*T3)/length;
                    if(T1>=0&&T1<=1)
                    {
                        Point3D p1=ray.getP00().add(new vector(ray.getDirection()).multiply(FirstT));//p1=p0+FirstT*V0
                        intersections.add(p1);
                    }

                    T1=(T2+SecondT*T3)/length;
                    if(T1>=0&&T1<=1)
                    {
                        Point3D p1=ray.getP00().add(new vector(ray.getDirection()).multiply(SecondT));//p1=p0+SecondT*V0
                        intersections.add(p1);
                    }
                  //  SmallerT=FirstT;
                  //  LargerT=SecondT;
                }
                else if(SecondT<0.001)
                {
                    T1=(T2+FirstT*T3)/length;
                    if(T1>=0&&T1<=1)
                    {
                        Point3D p1=ray.getP00().add(new vector(ray.getDirection()).multiply(FirstT));//p1=p0+FirstT*V0
                        intersections.add(p1);
                    }
                }
                else
                {
                    T1=(T2+FirstT*T3)/length;
                    if(T1>=0&&T1<=1)
                    {
                        Point3D p1=ray.getP00().add(new vector(ray.getDirection()).multiply(FirstT));//p1=p0+FirstT*V0
                        intersections.add(p1);
                    }

                    T1=(T2+SecondT*T3)/length;
                    if(T1>=0&&T1<=1)
                    {
                        Point3D p1=ray.getP00().add(new vector(ray.getDirection()).multiply(SecondT));//p1=p0+SecondT*V0
                        intersections.add(p1);
                    }
                }
                return intersections;
            }
        }
        return EMPTY_LIST;
    }
}*/