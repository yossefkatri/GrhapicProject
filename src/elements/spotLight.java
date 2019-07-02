package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.vector;

public class spotLight extends pointLight {
    private vector Direction;
    /********** Constructors ***********/
    public spotLight(Color color, Point3D position, vector direction,double kc, double kl, double kq) {
        super(color, position, kc, kl, kq);
        Direction = new vector(direction);
        Direction.normalize();
    }
    /*************** Admin *****************/
    @Override
    public Color getIntensity()
    {
    return null;
    }

    @Override
    public Color getInternsity(Point3D point) {
        Color I0=super.getInternsity(point);

        vector dir=new vector(Direction).normalize();
        vector I=this.getL(point).normalize();

        Color IL=this.scaleColor(I0,dir.dotProduct(I));
        return IL;
    }

}
