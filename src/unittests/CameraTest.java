package unittests;

import elements.*;
import geometries.Cylinder;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.vector;

import static org.junit.jupiter.api.Assertions.*;

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
    public void func1()
    {
        Camera c=new Camera();
        Ray ray=c.constructRayThroughPixel(5,5,3,3,10,5,5);
        assertEquals(  "Ray{p00=Point3D{x=0.0, y=0.0, z=0.0}, direction=Vector{head=Point3D{x=0.0, y=0.0, z=-1.0}}}",ray.toString());
    }
    @Test
    public void func2()
    {
        Camera c=new Camera();
        Ray ray=c.constructRayThroughPixel(10,7,1,1,10,5,5);
        assertEquals("Ray{p00=Point3D{x=0.0, y=0.0, z=0.0}, direction=Vector{head=Point3D{x=0.20463518708865727, y=-0.21486694644309015, z=-0.9549642064137339}}}",ray.toString());
    }
}