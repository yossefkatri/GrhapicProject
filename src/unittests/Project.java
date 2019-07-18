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
    public void Project()
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
        ImageWriter imageWriter = new ImageWriter("MyTest", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImageWithSupersampling();
        render.writeToImage();
    }
}
