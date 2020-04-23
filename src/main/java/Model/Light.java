package Model;


import javafx.geometry.Point2D;

public class Light {

    private final double ambient;
    private final double diffuse;
    private final double shininess;
    private final double attenuation;
    private final Point2D pos;

    public Light(Point2D pos, double ambient, double diffuse, double shininess, double attenuation) {
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.shininess = shininess;
        this.pos = pos;
        this.attenuation = attenuation;
    }

    public double getAmbient() {
        return ambient;
    }

    public double getDiffuse() {
        return diffuse;
    }

    public double getShininess() {
        return shininess;
    }

    public Point2D getPosition() {
        return pos;
    }

    public double getAttenuation() {
        return attenuation;
    }
}
