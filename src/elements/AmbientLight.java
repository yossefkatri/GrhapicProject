package elements;

import primitives.*;
public class AmbientLight extends Light
{
    private double Ka=0.3;
    /********** Constructors ***********/
    public AmbientLight() {
        super(new Color(java.awt.Color.white));
    }
    public AmbientLight(double KA) {
        super(new Color(java.awt.Color.white));
        Ka=KA;
    }
    public AmbientLight(Color IA)
    {
        super(IA);
    }
    public AmbientLight(Color IA,double KA) {
        super(IA);
        this.Ka=KA;
    }
    /************** Operations ***************/
    public Color getIntensity()
    {
        return scaleColor(color,Ka);
    }
}
