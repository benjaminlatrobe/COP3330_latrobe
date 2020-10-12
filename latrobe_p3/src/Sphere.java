public class Sphere extends Shape3D{
    private double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    public String getName(){
        return "sphere";
    }

    public double getArea(){
        return (4*Math.PI*Math.pow(this.radius,2));
    }

    public double getVolume(){
        return (4*Math.PI*Math.pow(this.radius,3)/3);
    }
}
