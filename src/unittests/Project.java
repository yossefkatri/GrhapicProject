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

        Sphere sphere1=new Sphere(new Color(0, 18, 0),100, new Point3D(0,0,-800));
        Sphere sphere2=new Sphere(new Color(0, 0, 100),100, new Point3D(100,0,-600));
        Sphere sphere3=new Sphere(new Color(0, 50, 50),200, new Point3D(-200,-200,-900));
        Sphere sphere4=new Sphere(new Color(50,50,0),150,new Point3D(400,0,-800));

        triangle1.setMaterial(new Material(1,1,0,1.0,5));
        triangle2.setMaterial(new Material(1,1,0,1.0,5));
        triangle3.setMaterial(new Material(1,1,0,1.0,5));
        triangle4.setMaterial(new Material(1,1,0,1.0,5));
        Plane.setMaterial(new Material(1,1,1,0,1));
        sphere1.setMaterial(new Material(1,1,0,1,20));
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
        scene.addLight(new pointLight(new Color(50, 50,50), new Point3D(0 ,0,-800)
                , 0, 0.000001, 0.0000005));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.BLACK)));
        ImageWriter imageWriter = new ImageWriter( "Project", 500, 500, 500, 500);

        Render render = new Render(imageWriter,scene);

        render.renderImage();
        render.printGrid(50);
        render.writeToImage();
    }
    @Test
    void Projec12() {
        Scene scene = new Scene("kik");
        scene.setBackground(new Color(57, 0, 0));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.05));
        scene.setCameraAndDistance(new Camera(), 200);
        Sphere sphere1 = new Sphere(200, new Point3D(-200, 0, -1000));
        Sphere sphere2 = new Sphere(100, new Point3D(100, 0, -1000));
        Sphere sphere3 = new Sphere(50, new Point3D(250, 0, -1000));
        Sphere sphere4 = new Sphere(20, new Point3D(260, 20, -1000));
        Sphere sphere6 = new Sphere(100, new Point3D(-300, 0, -1000));

        sphere1.setMaterial(new Material(1, 1, 0, 1, 20));
        sphere2.setMaterial(new Material(1, 1, 0, 0, 20));
        sphere3.setMaterial(new Material(1, 1, 0, 0.5, 20));
        sphere4.setMaterial(new Material(1, 1, 0, 0, 20));
        sphere6.setMaterial(new Material(1, 1, 0, 0, 20));

        sphere1.setEmmission(new Color(48, 48, 48));
        sphere2.setEmmission(new Color(152, 152, 152));
        sphere3.setEmmission(new Color(43, 43, 43));
        sphere4.setEmmission(new Color(4, 0, 255));
        sphere6.setEmmission(new Color(255, 10, 0));

        scene.addGeometry(sphere1);
        scene.addGeometry(sphere2);
        scene.addGeometry(sphere3);
        scene.addGeometry(sphere4);
        scene.addGeometry(sphere6);

        Plane plane = new Plane(new Point3D(-400, 0, 0), new vector(1, 0, 0));
        plane.setMaterial(new Material(1, 1, 0.5, 0, 20));
        plane.setEmmission(new Color(0, 107, 0));
        scene.addGeometry(plane);

        Triangle triangle3 = new Triangle(new Point3D(250, 100, -1000), new Point3D(250, 50, -1000), new Point3D(220, 40, -1000));
        triangle3.setMaterial(new Material(1, 1, 0, 0, 20));
        triangle3.setEmmission(new Color(255, 101, 0));
        scene.addGeometry(triangle3);

        scene.addLight(new spotLight(new Color(255, 0, 0), new Point3D(100, 500, -900),        new vector(-2, -2, -3),0, 0.00001, 0.000005));

        scene.addLight(new spotLight(new Color(0, 18, 255), new Point3D(100, -500, -900),new vector(-2, 2, -3),0, 0.00001, 0.000005));

        scene.addLight(new pointLight(new Color(255, 255, 0), new Point3D(1000, 800, -1000),
                0, 0.00001, 0.000005));


        ImageWriter imageWriter = new ImageWriter("Our Test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        //render.printGrid(50);
        render.writeToImage();
    }
}
