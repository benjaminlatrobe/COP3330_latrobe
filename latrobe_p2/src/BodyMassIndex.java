// Code written by Benjamin Latrobe

public class BodyMassIndex {

    double height;
    double weight;
    double BMIscore;

    public BodyMassIndex(double height, double weight) {
        this.height = height;
        this.weight = weight;
        this.BMIscore = calculateBMI(height, weight);
    }

    public double calculateBMI(double height, double weight){
        return 703 * weight / (height*height);
    }

    public String determineCategory(double height, double weight){
        double index = calculateBMI(height, weight);
        if(index >= 30){
            return "Obesity";
        } else if(index >= 25){
            return "Overweight";
        } else if(index >= 18.5){
            return "Normal weight";
        } else if(index > 0){
            return "Underweight";
        } else{
            return "Error";
        }
    }

}
