package primitives;

public class Material {
    private double Kd;
    private double Ks;
    private double Kr;
    private double Kt;
    private int nShininess;
    /********** Constructors ***********/
    public Material(double kd, double ks, int nShininess) {
        Kd = kd;
        Ks = ks;
        this.nShininess=nShininess;
        Kr=1;
        Kt=1;
    }
    public Material() {
        Kd = 1.0;
        Ks = 1.0;
        nShininess=4;

    }
    public Material(double kd, double ks, double kr, double kt, int nShininess) {
        Kd = kd;
        Ks = ks;
        Kr = kr;
        Kt = kt;
        this.nShininess = nShininess;
    }

    /************** Getters/Setters *******/
    public double getKd() {
        return Kd;
    }

    public void setKd(double kd) {
        Kd = kd;
    }

    public double getKs() {
        return Ks;
    }

    public void setKs(double ks) {
        Ks = ks;
    }

    public int getnShininess() {
        return nShininess;
    }

    public void setnShininess(int nShininess) {
        this.nShininess = nShininess;
    }
}
