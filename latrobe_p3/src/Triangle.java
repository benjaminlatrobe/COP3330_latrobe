public class Triangle extends Shape2D{
    private double base;
    private double height;

    public Triangle(double base, double height){
        this.base = base;
        this.height = height;
    }

    public String getName(){
        return "triangle";
    }

    public double getArea(){
        return (this.base*this.height/2);
    }
}
