package primitives;

import java.util.Objects;
public class Point3D extends Point2D{

    private Coordinate z;
    public static Point3D ZERO=new Point3D();
    /********** Constructors ***********/
    public Point3D() {

        this.z=z.ZERO;
    }
    public Point3D(Point3D other) {

        super(other.x,other.y);
        this.z=new Coordinate(other.z);
    }
    public Point3D(double x,double y,double z)
    {
        super(new Coordinate(x),new Coordinate(y));
        this.z=new Coordinate(z);
    }
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        super(x,y);
        this.z = z;
    }
    /************** Getters/Setters *******/
    public Coordinate getz() {
        return z;
    }

    public void setz(Coordinate z) {
        this.z = z;
    }

    /*************** Admin *****************/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o==null)return false;
        if (!(o instanceof Point3D)) return false;
        if (!super.equals(o)) return false;
        Point3D point3D = (Point3D) o;
        return super.equals(point3D)&&z.equals(point3D.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), z);
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + super.getx() +
                ", y=" + super.gety() +
                ", z=" + getz() +
                '}';
    }
    /************** Operations ***************/
    public vector substract(Point3D other)  {
            vector result=new vector(new Point3D(this.x.subtract(other.x),this.y.subtract(other.y),this.z.subtract(other.z)));
            return result;
    }
    public Point3D add(vector vector)
    {
        return new Point3D(this.x.add(vector.gethead().x),this.y.add(vector.gethead().y),this.z.add(vector.gethead().z));
    }
    public double distance(Point3D other)
    {
        if(distancePow(other)<0)
            throw new ArithmeticException();
        return Math.sqrt(distancePow(other));
    }
    public double distancePow(Point3D other) {
        double substractX=this.x.subtract(other.x).get();
        double substractY=this.y.subtract(other.y).get();
        double substractZ=this.z.subtract(other.z).get();
        return Util.uadd(Util.uadd(Util.uscale(substractX,substractX),Util.uscale(substractY,substractY)),Util.uscale(substractZ,substractZ));
    }

}
