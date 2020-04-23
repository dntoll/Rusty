package Model;


import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class ShadingModel {
    private final Point2D position;
    private final Point2D normal;
    private final Point2D viewerPos;
    private double I = 0.0;
    private final Material mat;

    public ShadingModel(Material mat, Point2D position, Point2D normal, Point2D viewerPos) {
        this.mat = mat;
        this.position = position;
        this.normal = normal;
        this.viewerPos = viewerPos;
    }

    
    public void addDirectLight(Light light) {
        addLight(light.getPosition(), light.getAmbient(), light.getDiffuse(), light.getShininess(), light.getAttenuation());
    }

    public void addLight(Point2D lightPosition, double lightAmbient, double lightDiffuse, double lightShininess, double lightAttenuation) {
        var Lm = lightPosition.subtract(position); //towards the light
        Lm = Lm.normalize();

        var N = normal;
        var Ks = mat.getSpecular();
        var Kd = mat.getDiffuse();
        var Ka = mat.getAmbient();
        var shininess = mat.getShininess();

        //https://www.gamedev.net/forums/topic/510581-2d-reflection/
        var doubleProjectionOnNormal = 2.0 * Lm.dotProduct(N);
        var toBeRemoved = N.multiply(doubleProjectionOnNormal);
        var R = toBeRemoved.subtract(Lm).normalize();

        var V = viewerPos.subtract(position).normalize(); //towards the viewer

        var ambientComponent = Ka * lightAmbient;
        var diffuseComponent = Kd * Lm.dotProduct(N) * lightDiffuse;

        var rdotV = R.dotProduct(V);
        var specularComponent = Ks * Math.pow(rdotV, shininess)*lightShininess;

        //https://www.tomdalling.com/blog/modern-opengl/07-more-lighting-ambient-specular-attenuation-gamma/
        double distanceToLight = position.distance(lightPosition);
        double attenuation = 1.0 / (1.0 + lightAttenuation * Math.pow(distanceToLight, 2.0));

        I += Math.max(0.0, ambientComponent) + attenuation * (Math.max(0.0, diffuseComponent) + Math.max(0.0, specularComponent));
    }

    public Color getColor() {
        I = Math.min(I, 1.0);
        return Color.color(I,I,I);
    }

    public void getSecondaryLight(double lightIntensity, Point2D positionAtSecondary, Point2D normalAtSecondary, Point2D primaryPosition) {
        addLight(positionAtSecondary, lightIntensity*0.1, lightIntensity*0.1, lightIntensity*0.1, 10.0 );
    }

    public double getIntensity() {
        return I;
    }
}
