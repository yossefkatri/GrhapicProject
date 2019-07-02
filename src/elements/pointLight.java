package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.vector;

public class pointLight extends Light implements LightSource{
    private Point3D position;
    private double Kc,Kl,Kq;
    /********** Constructors ***********/
    public pointLight(Color color, Point3D Position, double KC, double KL, double KQ){
        super(color);
        this.position=Position;
        this.Kc=KC;
        this.Kl=KL;
        this.Kq=KQ;
    }
    /*************** Admin *****************/
    @Override
    public Color getIntensity() {
        return null;
    }

    @Override
    public Color getInternsity(Point3D point) {
        double distance=point.distance(position);
        double distancePow=point.distancePow(position);
        Color IL=new Color(scaleColor(new Color(this.color),1/(Kc+Kl*distance+Kq*distancePow)));
        return IL;
    }

    @Override
    public vector getL(Point3D point) {
        return new vector(this.position,point).normalize();
    }
    /************** Getters/Setters *******/

    public Point3D getPosition() {
        return position;
    }

    public void setPosition(Point3D position) {
        this.position = position;
    }

    public double getKc() {
        return Kc;
    }

    public void setKc(double kc) {
        Kc = kc;
    }

    public double getKl() {
        return Kl;
    }

    public void setKl(double kl) {
        Kl = kl;
    }

    public double getKq() {
        return Kq;
    }

    public void setKq(double kq) {
        Kq = kq;
    }
    /************** Operations ***************/

}
