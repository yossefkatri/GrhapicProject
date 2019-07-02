package geometries;
import primitives.*;

import java.util.ArrayList;
import java.util.List;

public interface Intersectable {
    public static final List<Point3D> EMPTY_LIST = new ArrayList<>();
    /************** Operations ***************/
    List<Point3D> findIntersections(Ray ray);
}
