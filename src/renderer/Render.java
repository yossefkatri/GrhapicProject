package renderer;

import elements.Light;
import elements.LightSource;
import geometries.Geometries;
import geometries.Geometry;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.vector;
import scene.Scene;

import java.util.*;

public class Render {
    private ImageWriter imageWriter;
    private Scene scene;
    // ***************** Constructors ********************** //

    public Render(ImageWriter imageWriter, Scene scene) {
        this.imageWriter = new ImageWriter(imageWriter);
        this.scene = new Scene(scene);
    }
    /************** Operations ***************/
    //private Color 
    private Color calcColor(Geometry geometry,Point3D point){
        Color ambientLight=scene.getAmbientLight().getIntensity();

        Color emissionLight=geometry.getEmmission();

        //Color I0=new Color(ambientLight.getColor().getRed()+emissionLight.getColor().getRed()
        //                    ,ambientLight.getColor().getGreen()+emissionLight.getColor().getGreen()
        //                   ,ambientLight.getColor().getBlue()+emissionLight.getColor().getBlue());

        Iterator<LightSource> lights=scene.getLightsIterator();
        Color diffuseLight=new Color(0,0,0);
        Color specularLight=new Color(0,0,0);
        while (lights.hasNext())
        {
            LightSource Current=lights.next();
             diffuseLight=diffuseLight.add(calcDiffusiveComp(geometry.getMaterial().getKd(),
                                                    geometry.getNormal(point),
                                                    Current.getL(point),
                                                    Current.getInternsity(point)));
             specularLight=specularLight.add(calcSpecularComp(geometry.getMaterial().getKs(),
                                                    new vector(point, scene.
                                                                getCamera().getP0()).normalize(),
                                                                geometry.getNormal(point).normalize(),
                                                                Current.getL(point).normalize(),
                                                                geometry.getnShininess(),
                                                                Current.getInternsity(point)));

        }
        return new Color(ambientLight.add(emissionLight,diffuseLight,specularLight));
    }

    private Color calcSpecularComp(double ks, vector minusVector, vector normal, vector l, int nShininess, Color internsity) {
        minusVector.normalize();
        normal.normalize();
        l.normalize();
        vector R=new vector(l).substract(new vector(normal).multiply(2*normal.dotProduct(l))).normalize();
        double dot=minusVector.dotProduct(R);
        if(nShininess==0)
            dot=1;
        for(int i=1;i<nShininess;i++) {
            dot*=dot;
        }//pow by nShininess
        Color Spec=new Color(scaleColor( internsity,ks*dot));

        return Spec;
    }

    private Color calcDiffusiveComp(double kd, vector normal, vector l, Color internsity) {
     double dotProduct=normal.dotProduct(l);
     Color Diff=new Color(scaleColor(internsity,kd*dotProduct));
     return Diff;
    }
    public Color scaleColor(Color c, double factor) {
        double red=Math.min(Math.abs((c.getColor().getRed() * factor)), 255);
        double green= Math.min(Math.abs((c.getColor().getGreen() * factor)), 255);
        double blue=Math.min(Math.abs( (c.getColor().getBlue() * factor)), 255);
        return new Color(red,green,blue);
    }
    private Map<Geometry,Point3D> getClosestPoint(Map<Geometry,List<Point3D>> intersectionPoints){
        double distance=Double.MAX_VALUE;
        Point3D P0=scene.getCamera().getP0();
        Map<Geometry,Point3D> minDistancePoint=new HashMap<Geometry,Point3D>();
        for (Map.Entry<Geometry,List<Point3D>> entry: intersectionPoints.entrySet()) {
            for (Point3D point: entry.getValue())
            {
                if(P0.distance(point)<distance)
                {
                    minDistancePoint.clear();
                    minDistancePoint.put(entry.getKey(),new Point3D(point));
                    distance=P0.distance(point);
                }
            }
        }
        return minDistancePoint;
    }
    public void renderImage() {
        for(int i=0;i<imageWriter.getWidth();i++)
            for(int j=0;j<imageWriter.getHeight();j++)
            {
                Ray ray=scene.getCamera().constructRayThroughPixel(imageWriter.getNx(),imageWriter.getNy(),i,j,scene.getScreenDistance(),imageWriter.getWidth(),imageWriter.getWidth());
                Map<Geometry,List<Point3D>> intersectionPoints=getSceneRayIntersections(ray);
                if(intersectionPoints.isEmpty())
                    imageWriter.writePixel(i,j,scene.getBackground().getColor());
                else
                {
                     Map<Geometry,Point3D> closestPoint=getClosestPoint(intersectionPoints);
                    for (Map.Entry<Geometry,Point3D> point: closestPoint.entrySet()) {
                        imageWriter.writePixel(i, j, calcColor(point.getKey(),point.getValue() ).getColor());
                    }
                }
            }
    }
    public void printGrid(int interval) {

        int height = (int)imageWriter.getHeight();
        int width = (int)imageWriter.getWidth();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                if (i % interval == 0 || j % interval == 0)
                    imageWriter.writePixel(j, i, java.awt.Color.white);

            }
        }
    }
    private Map<Geometry,List<Point3D>> getSceneRayIntersections(Ray ray){
        return scene.getGeometries().findIntersections(ray);
    }
    public void writeToImage() {
        imageWriter.writeToimage();
    }
}
