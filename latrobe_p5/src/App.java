import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

    public static void main(String[] args) {
        applicationMenu();
    }

    private static void applicationMenu() {
        int mainInput = getApplicationInput();
        while(mainInput != 3){
            switch (mainInput) {
                case 1 -> TaskApp.mainMenu();
                case 2 -> ContactApp.mainMenu();
                default -> System.out.println("ERROR");
            }
            mainInput = getApplicationInput();
        }
    }

    private static int getApplicationInput() {
        displayApplicationMenu();
        int input;
        while(true) {
            input = getIntegerInput();
            if(input >= 1 && input <=3) {
                System.out.println("> " + input);
                return input;
            }
            System.out.println("Input 1, 2 or 3!");
        }
    }

    private static int getIntegerInput() {
        int input;
        while(true) {
            try {
                input = scanner.nextInt();
                break;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Input an integer!");
                scanner.nextLine();
            }
        }
        return input;
    }

    private static void displayApplicationMenu() {
        System.out.println("Select Your Application!");
        System.out.println("--------------");
        System.out.println();
        System.out.println("1) task list");
        System.out.println("2) contact list");
        System.out.println("3) quit :(");
        System.out.println();
    }


}
