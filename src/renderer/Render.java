package renderer;

import elements.Light;
import elements.LightSource;
import geometries.FlatGeometry;
import geometries.Geometries;
import geometries.Geometry;
import geometries.Intersectable;
import primitives.*;
import scene.Scene;

import java.util.*;

import static java.lang.Math.pow;
import static java.lang.StrictMath.max;

public class Render {
    private ImageWriter imageWriter;
    private Scene scene;
    private final int RECURSION_LEVEL = 3;
    // ***************** Constructors ********************** //

    public Render(ImageWriter imageWriter, Scene scene) {
        this.imageWriter = new ImageWriter(imageWriter);
        this.scene = new Scene(scene);
    }
    /************** Operations ***************/
    private Color calcColor(Geometry geometry,Point3D point, Ray inRay)
    {
        return calcColor(geometry,point,inRay,0);
    }
    private Color calcColor(Geometry geometry,Point3D point, Ray inRay,int level){
        if(level==RECURSION_LEVEL) return new Color(0,0,0);

        Color ambientLight=scene.getAmbientLight().getIntensity();

        Color emissionLight=geometry.getEmmission();

        Color I0=new Color(ambientLight.getColor().getRed()+emissionLight.getColor().getRed()
                          ,ambientLight.getColor().getGreen()+emissionLight.getColor().getGreen()
                           ,ambientLight.getColor().getBlue()+emissionLight.getColor().getBlue());

        Iterator<LightSource> lights=scene.getLightsIterator();
        Color diffuseLight=new Color(0,0,0);
        Color specularLight=new Color(0,0,0);
        double Kd=geometry.getMaterial().getKd();
        double Ks=geometry.getMaterial().getKs();
        vector n=geometry.getNormal(point).normalize();
        vector MV=new vector(point, scene.
                getCamera().getP0()).normalize();
        while (lights.hasNext()) {
            LightSource Current = lights.next();
            vector l=Current.getL(point).normalize();
            Color c=Current.getInternsity(point);
            int nShininess=geometry.getnShininess();
            if(!occluded(Current,point,geometry)) {
                diffuseLight = diffuseLight.add(calcDiffusiveComp(Kd, n, l,c));
                specularLight = specularLight.add(calcSpecularComp(Ks, MV,n,l,nShininess,c));
            }
        }

        //Recursive call for a reflected ray
        Ray reflectedRay=constructReflectedRay(geometry.getNormal(point),point,inRay);
        Map.Entry<Geometry,Point3D> reflectedEntry=findClosesntIntersection(reflectedRay);
        Color reflectedColor;
        if(reflectedEntry==null)
            reflectedColor=new Color(0,0,0);
        else
        {
            reflectedColor=calcColor(reflectedEntry.getKey(),reflectedEntry.getValue(),reflectedRay,level+1);
        }
        double kr=geometry.getMaterial().getKr();
        Color reflectedLight=new Color(scaleColor(reflectedColor,kr));

        //Recursive call for a refracted ray
        Ray refractedRay=constructRefractedRay(geometry.getNormal(point),point,inRay);
        Map.Entry<Geometry,Point3D> refractedEntry=findClosesntIntersection(refractedRay);
        Color refractedColor;
        if(refractedEntry==null)
            refractedColor=new Color(0,0,0);
        else{
            refractedColor=calcColor(refractedEntry.getKey(),refractedEntry.getValue(),refractedRay,level+1);
        }
        double kt=geometry.getMaterial().getKt();
        Color refractedLight=new Color(scaleColor(refractedColor,kt));

        return new Color(ambientLight.add(emissionLight,diffuseLight,specularLight,refractedLight,reflectedLight));
    }



    private Map.Entry<Geometry, Point3D> findClosesntIntersection(Ray ray) {

        Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);

        if (intersectionPoints.size() == 0)
            return null;

        Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
        Map.Entry<Geometry, Point3D> entry = closestPoint.entrySet().iterator().next();
        return entry;
    }
    private Ray constructRefractedRay(vector normal, Point3D point, Ray inRay) {
        vector v= inRay.getDirection().normalize();
        vector no=new vector(normal).normalize();
        no.multiply(-2);
        Point3D p=new Point3D(point);
        p=p.add(no);

        Ray r=new Ray(p,v);
        return r;
    }

    private Ray constructReflectedRay(vector normal, Point3D point, Ray inRay) {
        vector n=new vector(normal);
        vector DirectionRay=new vector(inRay.getDirection().normalize());

        double dotProduct=DirectionRay.dotProduct(n);

        vector R=DirectionRay.substract(n.multiply(dotProduct*2));
        R.normalize();

        vector v = new vector(normal);
        v.multiply(2);
        Point3D p=new Point3D(point);
        p.add(v);

        return new Ray(p,R);
    }

    private boolean occluded(LightSource light, Point3D point, Geometry geometry) {
        vector lightDirection=light.getL(point).normalize();
        lightDirection.multiply(-1);

        Point3D geometryPoint=new Point3D(point);

        vector epsVector = new vector(geometry.getNormal(geometryPoint));
        epsVector.multiply((epsVector.dotProduct(lightDirection) > 0) ? 2 : -2);
        geometryPoint = point.add(epsVector);

        Ray lightRay =new Ray(geometryPoint,lightDirection);
        Map<Geometry,List<Point3D>> intersectionPoints=getSceneRayIntersections(lightRay);

        if(geometry instanceof FlatGeometry) {
            intersectionPoints.remove(geometry);
        }
        for (Map.Entry<Geometry,List<Point3D>> entry: intersectionPoints.entrySet()){
            if(entry.getKey().getMaterial().getKt()==0)
                return true;
        }
        return false;
    }
   /* private Color calcSpecularComp(double ks, vector v, vector normal,
                                   vector l, double shininess, Color lightIntensity) {

        v.normalize();
        normal.normalize();
        l.normalize();

        normal.multiply(2 * l.dotProduct(normal));
        l.add(normal);
        vector R = new vector(l);
        R.normalize();

        double k = 0;

        if (v.dotProduct(R) > 0) // prevents glowing at edges
            k = ks * Math.pow(v.dotProduct(R), shininess);

        return new Color((int) (lightIntensity.getColor().getRed() * k),
                (int) (lightIntensity.getColor().getGreen() * k),
                (int) (lightIntensity.getColor().getBlue() * k));
    }*/
    /*private Color calcSpecularComp(double ks, vector minusVector, vector normal, vector l, int nShininess, Color internsity) {
        minusVector.normalize();
        vector n=new vector(normal.normalize());
        l.normalize();
        vector R=new vector(l).substract(new vector(normal).multiply(2*normal.dotProduct(l))).normalize();
        double dot= (Util.isOne(minusVector.dotProduct(R))?1:minusVector.dotProduct(R));
        double k=0;
        if (nShininess == 0)
            dot = 1;
        for (int i = 1; i < nShininess; i++) {
            dot =(double) dot * dot;
        }//pow by nShininess

        k=max(0,ks*dot);
        Color Spec=new Color(scaleColor( internsity,k));

        return Spec;
    }*/
    private Color calcSpecularComp(double ks, vector minusVector, vector normal, vector l, int nShininess, Color internsity) {
        minusVector.normalize();
        vector n=new vector(normal.normalize());
        l.normalize();
        vector R=new vector(l).substract(new vector(normal).multiply(2*normal.dotProduct(l)));
        double dot=minusVector.dotProduct(R);

        double k=max(0,ks*pow(dot,nShininess));
        Color Spec=new Color(scaleColor( internsity,k));

        return Spec;
    }
    private Color calcDiffusiveComp(double kd, vector normal, vector l, Color internsity) {
     double dotProduct=normal.dotProduct(l);
     Color Diff;
     Diff=new Color(scaleColor(internsity,kd*dotProduct));
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
                        imageWriter.writePixel(i, j, calcColor(point.getKey(),point.getValue() ,ray).getColor());
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
    public Color add(Color a,Color b)
    {
        Color re=new Color();
        re.setColor(Math.min(a.getColor().getRed()+a.getColor().getRed(),255),Math.min(a.getColor().getGreen()+a.getColor().getGreen(),255),Math.min(a.getColor().getBlue()+a.getColor().getBlue(),255));
        return re;

    }
}
