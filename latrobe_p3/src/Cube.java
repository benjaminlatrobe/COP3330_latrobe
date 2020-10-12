public class Cube extends Shape3D{
    private double side;

    public Cube(double side){
        this.side = side;
    }

    public String getName(){
        return "cube";
    }

    public double getArea(){
        return (this.side*this.side*6);
    }

    public double getVolume(){
        return (this.side*this.side*this.side);
    }
}
