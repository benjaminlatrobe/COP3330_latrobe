public class Square extends Shape2D{
    private double side;

    public Square(double side){
        this.side = side;
    }

    public String getName(){
        return "square";
    }

    public double getArea(){
        return (this.side*this.side);
    }
}
