package unittests;

import elements.*;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import javafx.scene.PointLight;
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
        Plane Plane = new Plane(new Color(18, 62, 18),new Point3D(0,1000,0),new vector(0,-1,1));

        Triangle triangle1=new Triangle(new Color(50,50,50),new Point3D(-2000,-400,-1400),new Point3D(2000,-400,-1400),new Point3D(0,-400,-200));
        Triangle triangle2=new Triangle(new Color(50,50,50),new Point3D(-2000,-400,-1400),new Point3D(-800,400,-600),new Point3D(0,-400,-200));
        Triangle triangle3=new Triangle(new Color(50,50,50),new Point3D(2000,-400,-1400),new Point3D(800,400,-600),new Point3D(0,-400,-200));
        Triangle triangle4=new Triangle(new Color(50,50,50),new Point3D(-800,400,-600),new Point3D(800,400,-600),new Point3D(0,-400,-200));

        Sphere sphere1=new Sphere(new Color(0, 18, 0),100, new Point3D(0,0,-800));
        Sphere sphere2=new Sphere(new Color(0, 0, 100),100, new Point3D(200,0,-600));
        Sphere sphere3=new Sphere(new Color(0, 50, 50),200, new Point3D(-300,-200,-900));
        Sphere sphere4=new Sphere(new Color(114,61,141),150,new Point3D(400,0,-800));

        Sphere sphere5=new Sphere(new Color(0,0,100),100,new Point3D(-500,-600,-400));
        Sphere sphere6=new Sphere(new Color(100,0,0),100,new Point3D(500,-600,-400));

        triangle1.setMaterial(new Material(1,1,0,1.0,5));
        triangle2.setMaterial(new Material(1,1,0,1.0,5));
        triangle3.setMaterial(new Material(1,1,0,1.0,5));
        triangle4.setMaterial(new Material(1,1,0,1.0,5));
        Plane.setMaterial(new Material(1,1,1,0,1));

        sphere5.setMaterial(new Material(1,1,0,1,10));
        sphere6.setMaterial(new Material(1,1,0,1,10));

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
        scene.addGeometry(sphere5);
        scene.addGeometry(sphere6);
        //scene.addLight(new pointLight(new Color(50, 50,50), new Point3D(-1000,0,-400)
        //       , 0.01, 0.00001, 0.0000005));
        pointLight light=new pointLight(new Color(250, 100,100), new Point3D(-500,-600,-400) , 0.01, 0.001, 0.00005);
       pointLight light1=new pointLight(new Color(250, 100,100), new Point3D(500,-600,-400) , 0.01, 0.001, 0.00005);
        //scene.addLight(light);
        scene.addLight(new pointLight(new Color(250,100,100),new Point3D(-500,-600,-400), 0.01, 0.001, 0.00005));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.BLACK)));
        ImageWriter imageWriter = new ImageWriter( "Project", 500, 500, 500, 500);

        Render render = new Render(imageWriter,scene);

        render.renderImage();
        render.printGrid(50);
        render.writeToImage();
    }
    @Test
    public void ourtest(){
        Scene scene = new Scene("jjjj");
        scene.setBackground(new Color(57, 0, 0));
        scene.setAmbientLight(new AmbientLight(new Color(255,255,255),0.05));
        scene.setCameraAndDistance(new Camera(),200);
        Sphere sphere1=new Sphere(200, new Point3D(-200,0,-1000));
        Sphere sphere2=new Sphere(100, new Point3D(100,0,-1000));
        Sphere sphere3=new Sphere(50, new Point3D(250,0,-1000));
        Sphere sphere4=new Sphere(20, new Point3D(260,20,-1000));
        Sphere sphere6=new Sphere(100, new Point3D(-300,0,-1000));

        sphere1.setMaterial(new Material(1,1,0,1,20));
        sphere2.setMaterial(new Material(1,1,0,0,20));
        sphere3.setMaterial(new Material(1,1,0,0.5,20));
        sphere4.setMaterial(new Material(1,1,0,0,20));
        sphere6.setMaterial(new Material(1,1,0,0,20));

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

        Plane plane=new Plane(new Point3D(-400,0,0),new vector(1,0,0));
        plane.setMaterial(new Material(1,1,0.5,0,20));
        plane.setEmmission(new Color(0, 107, 0));
        scene.addGeometry(plane);

        Triangle triangle3=new Triangle(new Point3D(250,100,-1000),new Point3D(250,50,-1000),new Point3D(220,40,-1000));
        triangle3.setMaterial(new Material(1,1,0,0,20));
        triangle3.setEmmission(new Color(255, 101, 0));
        scene.addGeometry(triangle3);

        scene.addLight(new spotLight(new Color(255, 0, 0),  new Point3D(100, 500, -900),
                new vector(-2, -2, -3),   0, 0.00001, 0.000005));

        scene.addLight(new spotLight(new Color(0, 18, 255),  new Point3D(100, -500, -900),
                new vector(-2, 2, -3),0, 0.00001, 0.000005));

        scene.addLight(new pointLight(new Color(255, 255, 0),  new Point3D(1000, 800, -1000), 0, 0.00001, 0.000005));


        ImageWriter imageWriter = new ImageWriter("Our Test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void f()
    {
        Scene scene = new Scene("jjjj");
        scene.setBackground(new Color(57, 0, 0));
        scene.setAmbientLight(new AmbientLight(new Color(255,255,255),0.05));

        Triangle triangle1=new Triangle(new Color(50,50,50),new Point3D(500,400,-300),new Point3D(-500,400,-300),new Point3D(0,-100,-600));
        Triangle triangle2=new Triangle(new Color(50,50,50),new Point3D(-400,400,-600),new Point3D(-500,400,-300),new Point3D(0,-100,-600));
        Triangle triangle3=new Triangle(new Color(50,50,50),new Point3D(400,400,-600),new Point3D(-400,400,-600),new Point3D(0,-100,-600));
        Triangle triangle4=new Triangle(new Color(50,50,50),new Point3D(500,400,-300),new Point3D(400,400,-600),new Point3D(0,-100,-600));

        Plane plane=new Plane(new Point3D(0,400,0),new vector(0,1,0));

        Sphere sphere1=new Sphere(new Color(4, 0, 255),50, new Point3D(0,300,-450));
        Sphere sphere4=new Sphere(new Color(62, 131, 125),100,new Point3D(-200,300,-500));

        Sphere sphere2=new Sphere(new Color(30,97,113),50,new Point3D(600,350,-450));
        Sphere sphere3=new Sphere(new Color(130,97,13),50,new Point3D(-600,350,-450));

        Sphere sphere5=new Sphere(new Color(130, 131, 34),50,new Point3D(100,300,-500));
        Sphere sphere6=new Sphere(new Color(114,61,141),50,new Point3D(200,200,-500));


        plane.setMaterial(new Material(1,1,0.5,0,20));
        plane.setEmmission(new Color(0, 57, 0));
        triangle1.setMaterial(new Material(1,1,0,1.0,5));
        triangle2.setMaterial(new Material(1,0,1.0,0,5));
        triangle3.setMaterial(new Material(1,1,0,1.0,5));
        triangle4.setMaterial(new Material(1,0,1.0,0,5));


        scene.addGeometry(plane);
        scene.addGeometry(sphere1);
        scene.addGeometry(sphere3);
        scene.addGeometry(sphere4);
        scene.addGeometry(sphere5);
        scene.addGeometry(sphere6);
        scene.addGeometry(sphere2);
        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);



        scene.addLight(new pointLight(new Color(150, 150, 150),  new Point3D(0,-100,-600), 0, 0.00001, 0.000005));
        scene.addLight(new spotLight(new Color(100, 100, 100),  new Point3D(0, -500, -900),
                new vector(-5, 106, 300),0, 0.00001, 0.000005));
        scene.addLight(new spotLight(new Color(0,0,255),new Point3D(-1100,0,-200),new vector(599.5,237,-306.6),0, 0.00001, 0.000005));
        scene.addLight(new spotLight(new Color(255,0,0),new Point3D(1100,0,-200),new vector(-579.5,263,-311.6),0, 0.00001, 0.000005));
        ImageWriter imageWriter = new ImageWriter("Our Test1", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImageWithSupersampling();
        render.writeToImage();
    }
}
