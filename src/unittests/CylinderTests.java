package unittests;
import geometries.Cylinder;
import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTests {
    Cylinder cy=new Cylinder(5,new Ray(new Point3D(0,0,0),new vector(0,0,2)),5);
    @Test
    void getNormal() {
        vector v1=cy.getNormal(new Point3D(0,5,1));
        assertEquals("Vector{head=Point3D{x=0.0, y=1.0, z=0.0}}",v1.toString());
        vector upper=cy.getNormal(new Point3D(0,0,5));
        assertEquals("Vector{head=Point3D{x=0.0, y=0.0, z=1.0}}",upper.toString());
        vector down=cy.getNormal(new Point3D(5,0,0));
        assertEquals("Vector{head=Point3D{x=0.0, y=0.0, z=-1.0}}",down.toString());

    }

}