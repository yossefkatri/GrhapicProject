/*package unittests;
import geometries.Cylinder;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTests {
    Cylinder cy=new Cylinder(5,new Ray(new Point3D(0,0,0),new vector(0,0,2)),5);
    @Test
    void getNormal() {
        vector v1=cy.getNormal(new Point3D(0,5,1));
        assertEquals("Vector{head=Point3D{x=0.0, y=1.0, z=0.0}}",v1.toString());
    }
    @Test
    void findIntersection()
    {
        List<Point3D> intersections=cy.findIntersections(new Ray(new Point3D(8,8,8),new vector(new Point3D(-1,-1,-1))));
        assertEquals("Point3D{x=3.54, y=3.54, z=3.54}",intersections.get(0));
    }
}*/
