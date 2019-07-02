package unittests;

import geometries.Plane;
import primitives.Ray;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTests {
Plane plane=new Plane(new Point3D(3,0,0),new vector(1,0,0));
    @Test
    void getNormal() {
        vector result=plane.getNormal(null);
        assertEquals("Vector{head=Point3D{x=1.0, y=0.0, z=0.0}}",result.toString());
    }
    @Test
    void findInterctions1(){
        Plane p=new Plane(new Point3D(100, 0,0),new vector(-1,0,0));
        vector v=new vector(1,2,0);
        Point3D point3D=new Point3D(0,0,0);
        Point3D point=p.findIntersections(new Ray(point3D,v)).get(0);
        System.out.println(point);
        assertEquals(100, point.getx().get());
        assertEquals(200, point.gety().get());
    }

    @Test
    void findInterctions2() {
        Plane p=new Plane(new Point3D(30, 0,0),new Point3D(5,1,1),new Point3D(20,1,0));
        vector v=new vector(1,2,0);
        Point3D point3D=new Point3D(0,0,0);
        Point3D point=p.findIntersections(new Ray(point3D,v)).get(0);
        System.out.println(point);
        assertEquals(1.43, point.getx().get(), 0.01);
        assertEquals(2.86, point.gety().get(), 0.01);

    }

    @Test
    void findInterctions3() {
        Plane p=new Plane(new Point3D(30, 0,0),new Point3D(5,1,1),new Point3D(100,1,0));
        vector v=new vector(1,2,0);
        Point3D point3D=new Point3D(0,0,0);
        List<Point3D> g = p.findIntersections(new Ray(point3D,v));
        boolean flag = false;
        if(g.isEmpty())
            flag = true;

        assertEquals(flag,true);

    }
}