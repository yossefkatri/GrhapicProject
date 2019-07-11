package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.vector;

import java.util.ArrayList;
import java.util.List;

public class Camera {
    private Point3D P0;
    private vector vUp;
    private vector vTo;
    private vector vRight;
    /************** Getters/Setters *******/
    public Point3D getP0() {
        return P0;
    }

    public vector getvUp() {
        return vUp;
    }

    public vector getvTo() {
        return vTo;
    }

    public vector getvRight() {
        return vRight;
    }
    /********** Constructors ***********/
    public Camera(Point3D location,vector up,vector To) {
        P0 = new Point3D(location);
        if (!(up.dotProduct(To) == 0))
            throw new IllegalArgumentException("the vectors should be vertical");
        up.normalize();
        To.normalize();
        vUp = new vector(up);
        vTo = new vector(To);
        vRight = (up.crossproduct(To)).normalize();
    }
    /**
     * Default constructor
     */
    public Camera() {
        P0=new Point3D(0,0,0);
        vTo=new vector(0,0,-1);
        vUp=new vector(1,0,0);
        vRight=new vector(0,1,0);
    }
    /************** Operations ***************/
    public Ray constructRayThroughPixel(int Nx ,int Ny,double i,double j,double screenDistance,double screenWidth,double screenHeight) {
        Point3D Pc=getP0().add(new vector(vTo).multiply(screenDistance));//pc=p0+d*vTo
        double Rx=screenWidth/Nx;
        double Ry=screenHeight/Ny;
        double yj=(j-Ny/2.0)*Ry-Ry/2.0;
        double xi=(i-Nx/2.0)*Rx-Rx/2.0;
        Point3D Pij= Pc.add(new vector(vRight).multiply(xi).substract(new vector(vUp).multiply(yj)));//Pij=pc+(xi*vRight-yj*vUp)
        vector Vij=new vector(getP0(),Pij);
        Vij.normalize();
        return new Ray(getP0(),Vij);
    }

    public List<Ray> constructRaysThroughPixel(int Nx ,int Ny,int i,int j,double screenDistance,double screenWidth,double screenHeight) {
        List<Ray> rays=new ArrayList<Ray>();
        Ray center=constructRayThroughPixel(Nx,Ny,i,j,screenDistance,screenWidth,screenHeight);
        Ray Right=constructRayThroughPixel(Nx,Ny,i+0.4,j,screenDistance,screenWidth,screenHeight);
        Ray Left=constructRayThroughPixel(Nx,Ny,i-0.4,j,screenDistance,screenWidth,screenHeight);
        Ray Up=constructRayThroughPixel(Nx,Ny,i,j+0.4,screenDistance,screenWidth,screenHeight);
        Ray Down=constructRayThroughPixel(Nx,Ny,i,j-0.4,screenDistance,screenWidth,screenHeight);
        rays.add(center);
        rays.add(Right);
        rays.add(Left);
        rays.add(Up);
        rays.add(Down);
        return rays;
    }
}
