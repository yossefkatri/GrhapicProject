package geometries;

import primitives.*;
import primitives.Color;
import sun.invoke.empty.Empty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Cylinder extends RadialGeometry{
    protected Ray ray;
    protected double length;
    /********** Constructors ***********/
    public Cylinder(double _radius, Ray ray, double length) {
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
    public double getLength() {
        return length;
    }
    /*************** Admin *****************/
    @Override
    public String toString() {
        return "Cylinder{" +
                "ray=" + ray +
                ", length=" + length +
                ", _radius=" + _radius +
                '}';
    }
    @Override
    public vector getNormal(Point3D p1) {

        if(p1.distance());
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
    {
        double Aq,Bq,Cq,Det,FirstT,SecondT,Int1,Int2;
        List<Point3D> intersections=new ArrayList<Point3D>();
        vector K,D;
        double T1,T2,T3;
        T2=new vector(ray.getP00()).dotProduct(this.ray.getDirection())-new vector(this.ray.getP00()).dotProduct(this.ray.getDirection());
        T1=T2/length;
        K=(ray.getP00().substract(this.ray.getP00())).substract(this.ray.getDirection().multiply(T1));
        T3=ray.getDirection().dotProduct(this.ray.getDirection());
        T1=T3/length;
        D=ray.getDirection().substract(this.ray.getDirection().multiply(T1));
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
                if(T1>=0&&T1<=0) {
                    Point3D p1=ray.getP00().add(ray.getDirection().multiply(SecondT));//p1=p0+SecondT*V0
                    intersections.add(p1);
                    return intersections;
                }
            }
            else{
                SecondT=(-Bq-Int1)/Int2;
                if(FirstT<SecondT)
                {
                    T1=(T2+FirstT*T3)/length;
                    if(T1>=0&&T1<=0)
                    {
                        Point3D p1=ray.getP00().add(ray.getDirection().multiply(FirstT));//p1=p0+FirstT*V0
                        intersections.add(p1);
                    }

                    T1=(T2+SecondT*T3)/length;
                    if(T1>=0&&T1<=0)
                    {
                        Point3D p1=ray.getP00().add(ray.getDirection().multiply(SecondT));//p1=p0+SecondT*V0
                        intersections.add(p1);
                    }
                  //  SmallerT=FirstT;
                  //  LargerT=SecondT;
                }
                else if(SecondT<0.001)
                {
                    T1=(T2+FirstT*T3)/length;
                    if(T1>=0&&T1<=0)
                    {
                        Point3D p1=ray.getP00().add(ray.getDirection().multiply(FirstT));//p1=p0+FirstT*V0
                        intersections.add(p1);
                    }
                }
                else
                {
                    T1=(T2+FirstT*T3)/length;
                    if(T1>=0&&T1<=0)
                    {
                        Point3D p1=ray.getP00().add(ray.getDirection().multiply(FirstT));//p1=p0+FirstT*V0
                        intersections.add(p1);
                    }

                    T1=(T2+SecondT*T3)/length;
                    if(T1>=0&&T1<=0)
                    {
                        Point3D p1=ray.getP00().add(ray.getDirection().multiply(SecondT));//p1=p0+SecondT*V0
                        intersections.add(p1);
                    }
                }
                return intersections;
            }
        }
        return EMPTY_LIST;
    }
}

