package geometries;
import primitives.*;
public abstract class Geometry implements Intersectable{
    private Material material;
    private Color emmission;
    /********** Constructors ***********/
    public Geometry() {
        emmission=new Color(java.awt.Color.BLACK);
        material=new Material();
    }
    public Geometry(Color color){
        emmission=new Color(color);
        material=new Material();

    }
    public Geometry(Material material, Color emmission) {
        this.material = material;
        this.emmission = emmission;
    }
    /************** Getters/Setters *******/
    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Color getEmmission() {
        return emmission;
    }

    public void setEmmission(Color emmission) {
        this.emmission = emmission;
    }

    public int getnShininess() {
        return material.getnShininess();
    }

    public void setnShininess(int nShininess) {
        this.material.setnShininess( nShininess);
    }
    /************** Operations ***************/
    public abstract vector getNormal(Point3D p1);


}
