// Code written by Benjamin Latrobe
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();

        while (moreInput()) {
            double height = getUserHeight();
            double weight = getUserWeight();
            in.nextLine();

            BodyMassIndex bmi = new BodyMassIndex(height, weight);
            bmiData.add(bmi);

            displayBmiInfo(bmi);
        }

        displayBmiStatistics(bmiData);
    }

    private static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData) {
        double sum = 0;
        for (BodyMassIndex bmiDatum : bmiData) {
            sum = sum + bmiDatum.BMIscore;
        }
        System.out.printf("Average BMI score: %.2f\n", (sum/bmiData.size()));
    }

    private static void displayBmiInfo(BodyMassIndex bmi) {
        String category = bmi.determineCategory(bmi.height, bmi.weight);
        System.out.printf("BMI: %.2f, %s\n", bmi.BMIscore, category);
    }

    private static double getUserWeight() {
        System.out.println("Enter weight in pounds");
        return getPositiveNumber();
    }

    private static double getUserHeight() {
        System.out.println("Enter height in inches");
        return getPositiveNumber();
    }

    private static double getPositiveNumber() {
        double input = in.nextDouble();

        boolean badInput = true;

        while (badInput) {
            if (input <= 0) {
                System.out.println("Enter a positive number");
                input = in.nextDouble();
            } else {
                badInput = false;
            }
        }
        return input;
    }

    public static boolean moreInput() {
        System.out.println("More input? Enter Y or N");

        String input = in.next();

        if(input.equalsIgnoreCase("Y")){
            return true;
        } else if(input.equalsIgnoreCase("N")){
            return false;
        } else {
            return moreInput();
        }
    }

}
