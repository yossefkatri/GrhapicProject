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
        geometries.add(geometry);
    }
    public Map<Geometry,List<Point3D>> findIntersections(Ray ray){
        Map<Geometry,List<Point3D>> Intersections=new HashMap<Geometry,List<Point3D>>();
        for (Intersectable geometry: geometries) {
            if(geometry.findIntersections(ray)!=Intersectable.EMPTY_LIST && !geometry.findIntersections(ray).isEmpty())
            Intersections.put((Geometry)geometry,geometry.findIntersections(ray));
        }

        return Intersections;
    }
}
