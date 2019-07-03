package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.vector;

public class DirectionalLight extends Light implements LightSource {
    private vector Direction;
    /********** Constructors ***********/
    public DirectionalLight(Color color, vector direction){
        super(color);
        Direction=new vector(direction);
        Direction.normalize();
    }
    /*************** Admin *****************/
    @Override
    public Color getIntensity() {
    return new Color(this.color);
    }

    @Override
    public Color getInternsity(Point3D point) {
        return new Color(this.color);
    }

    @Override
    public vector getL(Point3D point) {
        return Direction.normalize();
    }
}
