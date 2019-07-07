package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.vector;

import java.util.ArrayList;
import java.util.List;

public class Tube extends RadialGeometry {
    protected Ray ray;
    /********** Constructors ***********/
    public Tube(double _radius, Ray ray) {
        super(_radius);
        this.ray = ray;
    }
    public Tube(Color color, double _radius, Ray ray) {
        super(color,_radius);
        this.ray = ray;
    }
    /************** Getters/Setters *******/
    public Ray getRay() {
        return ray;
    }
    /*************** Admin *****************/
    @Override
    public String toString() {
        return "Tube{" +
                "ray=" + ray +super.toString()+
                '}';
    }

    @Override
    public vector getNormal( Point3D p1) {
        vector temp=new vector(getRay().getP00(),p1);
        double PowRadius=get_radius()*get_radius();
        if (temp.length()*temp.length()-PowRadius<0)
            throw new ArithmeticException();
        double Distance =Math.sqrt(temp.length()*temp.length()-PowRadius);
        double cosAngle=(temp.dotProduct(getRay().getDirection()))/(temp.length()*getRay().getDirection().length());
       if(cosAngle>0)
       {
           vector direction=new vector(getRay().getDirection().normalize().multiply(Distance));
           Point3D center=new Point3D(getRay().getP00().add(direction));
           return new vector(center,p1).normalize();
       }
       else
       {
           vector direction=new vector(getRay().getDirection().normalize().multiply(-1*Distance));
           Point3D center=new Point3D(getRay().getP00().add(direction));
           return new vector(center,p1).normalize();
       }
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
       vector v=new vector(ray.getDirection());
       v.normalize();
       vector v0=new vector(this.getRay().getDirection());
       v0.normalize();
       double r=super.get_radius();
       Point3D p=new Point3D(ray.getP00());
       Point3D p0=new Point3D(this.getRay().getP00());
        vector deltaP=new vector(p0,p);

        vector temp=v.substract(v.proj(v0));
        vector temp1=deltaP.substract(deltaP.proj(v0));
        double A=temp.dotProduct(temp);
       double B=2*temp.dotProduct(temp1)/(temp1.length()*temp1.length());
       double C=temp1.dotProduct(temp1)-r*r;

       //solve At^2+Bt+C=0
        double delta=B*B-4*A*C;
        v=new vector(ray.getDirection());
        v.normalize();
        if(delta<0)
        {
            return EMPTY_LIST;
        }
        else if(delta==0)
        {
            List<Point3D> Intersections=new ArrayList<Point3D>();
            double t=-B/(2*A);
            Intersections.add(p.add(v.multiply(t)));
            return Intersections;
        }
        else
        {
            List<Point3D> Intersections=new ArrayList<Point3D>();
            double t1=(-B+Math.sqrt(delta))/(2*A);
            double t2=(-B-Math.sqrt(delta))/(2*A);

            Intersections.add(p.add(v.multiply(t1)));
            v.normalize();
            Intersections.add(p.add(v.multiply(t2)));
            return Intersections;
        }
    }
}
