package elements;

import geometries.Sphere;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.vector;

public class SphereLight extends Light implements LightSource {

    pointLight light;
    Sphere sphere;
    /********** Constructors ***********/

    public SphereLight(pointLight light, Sphere sphere) {
        super(light.getIntensity());
        this.light = light;
        this.sphere = sphere;
        sphere.setMaterial(new Material(1, 1, 0, 1, 20));
        this.light.setPosition(sphere.getCenter());
    }

    /*************** Admin *****************/

    @Override
    public Color getInternsity(Point3D point) {
       // if (point.distance(sphere.getCenter()) <= sphere.get_radius()) {
       //     return light.getInternsity(point);
       // }
        return light.getInternsity(point).add(sphere.getEmmission());
    }

    @Override
    public vector getL(Point3D point) {
        return light.getL(point);
    }

    @Override
    public Color getIntensity() {
        return new Color(0,0,0);
    }

    /************** Getters/Setters *******/
    public pointLight getLight() {
        return light;
    }

    public Sphere getSphere() {
        return sphere;
    }
}
