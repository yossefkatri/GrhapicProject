package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Geometries{
    protected List<Intersectable> geometries;
    /********** Constructors ***********/
    public Geometries()
    {
        geometries=new ArrayList<Intersectable>();
    }
    /************** Operations ***************/
    public void add(Intersectable geometry)
    {
        geometries.add(geometry);//add the geometry to the list
    }
    public Map<Geometry,List<Point3D>> findIntersections(Ray ray){
        Map<Geometry,List<Point3D>> Intersections=new HashMap<Geometry,List<Point3D>>();
        for (Intersectable geometry: geometries) {
            if(geometry.findIntersections(new Ray(ray))!=Intersectable.EMPTY_LIST && !geometry.findIntersections(new Ray(ray)).isEmpty())//if there are intersections with the geometry
            Intersections.put((Geometry)geometry,geometry.findIntersections(new Ray(ray)));//put the geometry and the intersections in the Map
        }

        return Intersections;//return the intersections
    }
}
