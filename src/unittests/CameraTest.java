package unittests;

import elements.*;
import geometries.Cylinder;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CameraTest {
    Camera c;
    @Test
    void CameraConstruct() {
        try {
            c = new Camera(new Point3D(), new vector(1, 1, 2), new vector(2, 2, 4));
        }
        catch(Exception ex)
        {
            assertEquals("the vectors should be vertical",ex.getMessage());
        }
        c=new Camera(new Point3D(),new vector(0,0,1),new vector(0,1,0));
    }
    @Test
    void constructRayThroughPixel(){

    }

}