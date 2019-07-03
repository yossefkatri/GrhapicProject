package primitives;


import java.util.Objects;

public class vector {
    private Point3D head;
    public static vector ZERO=new vector();
    /********** Constructors ***********/
    public vector(Point3D head) {
        if(head.equals(new Point3D()))
            throw new IllegalArgumentException("not explicit Point(0,0,0) allowed");
        this.head = head;
    }
    public vector(double x, double y, double z) {
        this(new Point3D(x, y, z));
    }
    public vector() {
        this.head=new Point3D();
    }

    public vector(Point3D p1,Point3D p2) //vector from p1 to p2
    {
            if(p1.equals(p2))
                throw new IllegalArgumentException("p1==p2");
            this.head = p2.substract(p1).head;
    }
    public vector(vector other) {
        this.head=new Point3D(other.head);

    }

    /************** Getters/Setters *******/

    public Point3D gethead() {
        return head;
    }
    public void sethead(Point3D head) {
        this.head = head;
    }

    /*************** Admin *****************/
    @Override
    public String toString() {
        return "Vector{" +
                "head=" + gethead().toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof vector)) return false;
        vector vector = (vector) o;
        return Objects.equals(gethead(), vector.gethead());
    }

    @Override
    public int hashCode() {
        return Objects.hash(gethead());
    }
    /************** Operations ***************/
    public vector proj(vector other){
        vector u=new vector(this);
        vector v=new vector(other);

        v.normalize();

        return v.multiply(u.dotProduct(v));
    }//projection this on other
    public vector add(vector other) {
        this.head= new vector(head.add(other)).head;
        if(this.length()==0)
            throw new IllegalArgumentException("zero vector!!!");
        return this;
    }//change the original
    public vector substract(vector other){
       vector minousVector;
        try {
             minousVector = new Point3D().substract(other.head);
       }
       catch (Exception ex)
       {
           throw ex;
       }
        return this.add(minousVector);
    }//change the original
    public vector multiply(double scale){
        if(scale==0)
            throw new IllegalArgumentException("zero vector!!!");
        vector v=new vector(new Point3D(this.head.getx().scale(scale),this.head.gety().scale(scale),this.head.getz().scale(scale)));
        this.sethead(v.head);
        return this;
    }//change the original
    public double dotProduct(vector other) {
        double result= Util.uadd(Util.uadd(this.head.getx().multiply(other.head.getx()).get(),this.head.gety().multiply(other.head.gety()).get()),this.head.getz().multiply(other.head.getz()).get());
        return result;
    }
    public vector crossproduct(vector other) {
        double x1 = this.gethead().getx().get();
        double y1 = this.gethead().gety().get();
        double z1 = this.gethead().getz().get();

        double x2 = other.gethead().getx().get();
        double y2 = other.gethead().gety().get();
        double z2 = other.gethead().getz().get();

        Coordinate x3=new Coordinate(Util.usubtract(Util.uscale(y1, z2), Util.uscale(z1, y2)));
        Coordinate y3=new Coordinate(Util.usubtract(Util.uscale(z1, x2), Util.uscale(x1, z2)));
        Coordinate z3=new Coordinate(Util.usubtract(Util.uscale(x1, y2), Util.uscale(y1, x2)));

        vector result=new vector(new Point3D(x3,y3,z3));
        if(result.equals(new vector()))
            throw new IllegalArgumentException("zero vector!!!");
        return result;
    }
    public double length() {
        double result= this.head.distance(new Point3D());
        return result;
    }
    public vector normalize()  {
        double length=this.length();
        if ( length==0)
            throw new ArithmeticException();
        Point3D newHead=new Point3D(new Coordinate(this.head.getx().get()/this.length()),new Coordinate(this.head.gety().get()/this.length()),new Coordinate(this.head.getz().get()/this.length()));
        this.sethead(newHead);
        if(this.length()==0)
            throw new IllegalArgumentException("zero vector!!!");
        return this;
    }//change the original
}
