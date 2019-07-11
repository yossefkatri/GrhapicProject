package unittests;

import elements.*;
import geometries.Cylinder;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import static org.junit.jupiter.api.Assertions.*;

public class Project {
    @Test
    void Project()
    {
        Scene scene = new Scene("pointLightTest");
        scene.setCameraAndDistance(new Camera(new Point3D(),new vector(0,-1,0),scene.getCamera().getvTo()),scene.getScreenDistance());
        Plane Plane = new Plane(new Color(0, 18, 0),new Point3D(0,1000,0),new vector(0,-1,1));

        Triangle triangle1=new Triangle(new Color(0,0,0),new Point3D(-1200,-400,-1400),new Point3D(1200,-400,-1400),new Point3D(0,-400,-200));
        Triangle triangle2=new Triangle(new Color(0,0,0),new Point3D(-1200,-400,-1400),new Point3D(-600,400,-600),new Point3D(0,-400,-200));
        Triangle triangle3=new Triangle(new Color(0,0,0),new Point3D(1200,-400,-1400),new Point3D(600,400,-600),new Point3D(0,-400,-200));
        Triangle triangle4=new Triangle(new Color(0,0,0),new Point3D(-600,400,-600),new Point3D(600,400,-600),new Point3D(0,-400,-200));

        Sphere sphere1=new Sphere(new Color(18, 62, 18),100, new Point3D(0,0,-800));
        Sphere sphere2=new Sphere(new Color(0, 0, 100),100, new Point3D(100,0,-600));
        Sphere sphere3=new Sphere(new Color(0, 50, 50),200, new Point3D(-200,-200,-900));
        Sphere sphere4=new Sphere(new Color(50,50,0),150,new Point3D(400,0,-800));

        triangle1.setMaterial(new Material(1,1,0,1.0,5));
        triangle2.setMaterial(new Material(1,1,0,1.0,5));
        triangle3.setMaterial(new Material(1,1,0,1.0,5));
        triangle4.setMaterial(new Material(1,1,0,1.0,5));
        Plane.setMaterial(new Material(1,1,1,0,1));
        sphere1.setnShininess(20);
        sphere2.setnShininess(20);
        sphere3.setnShininess(20);
        sphere4.setnShininess(20);

        scene.addGeometry(sphere1);
        scene.addGeometry(sphere2);
        scene.addGeometry(sphere3);
        scene.addGeometry(sphere4);
        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);
        scene.addGeometry(Plane);
        scene.addLight(new pointLight(new Color(50, 50,50), new Point3D(-300 ,-400,0)
                , 0, 0.000001, 0.0000005));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.BLACK)));
        ImageWriter imageWriter = new ImageWriter( "Project", 500, 500, 500, 500);

        Render render = new Render(imageWriter,scene);

        render.renderImage();
        //render.printGrid(50);
        render.writeToImage();
    }
}
