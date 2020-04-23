package Model;

public class Material {

    private final double specular;
    private final double diffuse;
    private final double ambient;
    private final double shininess;

    public Material(double specular, double diffuse, double ambient, double shininess) {
        this.specular = specular;
        this.diffuse = diffuse;
        this.ambient = ambient;
        this.shininess = shininess;
    }

    public double getSpecular() {
        return specular;
    }

    public double getDiffuse() {
        return diffuse;
    }

    public double getAmbient() {
        return ambient;
    }

    public double getShininess() {
        return shininess;
    }
}
