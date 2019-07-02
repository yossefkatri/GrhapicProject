package unittests;
import primitives.*;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point3D;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTests {
Sphere sphere=new Sphere(5,new Point3D(0,0,0));
    @Test
    void getNormal() {
        vector v1=sphere.getNormal(new Point3D(0,0,5));
        assertEquals("Vector{head=Point3D{x=0.0, y=0.0, z=1.0}}",v1.toString());
    }
    @Test
    public void  findInterctions1() {
        Sphere s=new Sphere(10,new Point3D(20,20,0));
        Ray ray=new Ray(new Point3D(0,0,0),new vector(1,1,0));
        List<Point3D> g=s.findIntersections(ray);
        if(g.isEmpty())
            System.out.println("is empty!");

        System.out.println(g.get(0));
        System.out.println(g.get(1));

        assertEquals(12.93,g.get(1).getx().get(),0.01);
        assertEquals(27.07,g.get(0).getx().get(),0.01);
        //assertEquals("Vector{head=Point3D{x=1.0, y=0.0, z=0.0}}",vector.toString());
    }
    @Test
    public void  findInterctions2() {
        Sphere s=new Sphere(10,new Point3D(0,0,0));
        Ray ray=new Ray(new Point3D(0,0,0),new vector(1,1,0));
        List<Point3D> g=s.findIntersections(ray);
        if(g.isEmpty())
            System.out.println("is empty!");

        System.out.println(g.get(0));

        assertEquals(7.07,g.get(0).getx().get(),0.01);
        //assertEquals("Vector{head=Point3D{x=1.0, y=0.0, z=0.0}}",vector.toString());
    }
    @Test
    public void  findInterctions3() {
        Sphere s=new Sphere(10,new Point3D(20,20,0));
        Ray ray=new Ray(new Point3D(0,0,0),new vector(10,1,0));
        List<Point3D> g=s.findIntersections(ray);
        String a = new String();

        if(g.isEmpty()) {
            a = new String("is empty!");
        }

        assertEquals(a,"is empty!");
        //assertEquals("Vector{head=Point3D{x=1.0, y=0.0, z=0.0}}",vector.toString());
    }
}