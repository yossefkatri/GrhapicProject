package unittests;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTests {
    Triangle tr = new Triangle(
            new Point3D(0.0, 1.0, 0.0),
            new Point3D(1.0, 0.0, 0.0),
            new Point3D(0.0, 0.0, 1.0)
    );

    @Test
    void getNormal() {
        vector n = tr.getNormal(null);
        assertEquals("Vector{head=Point3D{x=0.5773502691896258, y=0.5773502691896258, z=0.5773502691896258}}", n.toString());
    }

    @Test
    void getLengthNormal() {
        vector v = tr.getNormal(null);
        double l = v.length();
        assertEquals(1.0, l);
    }

    @Test
    void findInterctions1() {
        Point3D x = new Point3D(-100, -100, -200);
        Point3D y = new Point3D(100, -100, -200);
        Point3D z = new Point3D(0, 100, -200);
        Triangle t = new Triangle(x, y, z);
        vector v = new vector(0, -1, -3);
        Point3D point3D = new Point3D(0, 0, 0);
        Point3D point = t.findIntersections(new Ray(point3D, v)).get(0);
        assertEquals(-66.6666, point.gety().get(), 0.01);
    }

    @Test
    void findInterctions2() {
        Point3D x = new Point3D(10, 10, 50);
        Point3D y = new Point3D(20, 50, -20);
        Point3D z = new Point3D(30, 40, -10);
        Triangle t = new Triangle(x, y, z);
        vector v = new vector(1, 5, 0);
        Point3D point3D = new Point3D(0, 0, 0);
        List<Point3D> g = t.findIntersections(new Ray(point3D, v));

        boolean flag = false;
        if (g.isEmpty())
            flag = true;

        assertEquals(flag, true);
    }
}