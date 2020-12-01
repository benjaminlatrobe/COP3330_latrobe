import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactApp {
    private static final Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

    public static void mainMenu() {
        int mainInput = getMainMenuInput();
        while(mainInput != 3){
            switch (mainInput) {
                case 1 -> createList();
                case 2 -> loadList();
                default -> System.out.println("ERROR");
            }
            mainInput = getMainMenuInput();
        }
    }

    private static void loadList() {
        String fileName = getFileName();
        ContactList list = new ContactList();
        list.load(fileName);
        listOperationMenu(list);
    }

    private static String getFileName() {
        String fileName = "fail";
        while(!isFileNameValid(fileName)){
            System.out.println("Enter the file name ending in .txt: ");
            fileName = scanner.next();
        }
        return fileName;
    }

    private static boolean isFileNameValid(String fileName) {
        return (fileName.endsWith(".txt"));
    }

    private static void createList() {
        ContactList list = new ContactList();
        System.out.print("New list has been created!\n\n");
        listOperationMenu(list);
    }

    private static void listOperationMenu(ContactList list) {
        int listInput = getListInput();
        while(listInput != 6){
            switch (listInput) {
                case 1 -> viewList(list);
                case 2 -> addToList(list);
                case 3 -> editListItem(list);
                case 4 -> removeListItem(list);
                case 5 -> saveCurrentList(list);
                default -> System.out.println("ERROR");
            }
            listInput = getListInput();
        }
    }

    private static void saveCurrentList(ContactList list) {
        String file = getFileName();
        list.save(file);
    }

    private static void removeListItem(ContactList list) {
        list.display();
        int i = getContactToRemove(list);
        if(i<0){
            System.out.println("There are no items to remove");
        } else {
            list.remove(i);
        }
    }

    private static int getContactToRemove(ContactList list) {
        System.out.println("Which contact will you remove?");
        return getContactIndex(list);
    }

    private static void editListItem(ContactList list) {
        viewList(list);
        int i = getContactToEdit(list);
        if(i<0){
            System.out.println("There are no items to edit");
        } else {
            editContact(list, i);
        }
    }

    private static void editContact(ContactList list, int i) {
        String first = getFirst();
        scanner.nextLine();
        String last = getLast();
        scanner.nextLine();
        String phone = getPhone();
        scanner.nextLine();
        String email = getEmail();
        try{
            list.get(i).editContact(first,last,phone,email);
        }
        catch(InvalidContactException invalidContactException){
            System.out.println("A contact item shall contain at least one of " +
                    "[first name], [last name], [phone number], or [email address]");
        }
    }

    private static int getContactToEdit(ContactList list) {
        System.out.println("Which task will you edit?");
        return getContactIndex(list);
    }

    private static int getContactIndex(ContactList list) {
        if (list.size() == 0){
            return -1;
        }
        int input = getIntegerInput();
        while(input >= list.size() || input < 0){
            System.out.println("Select a valid task number!");
            input = getIntegerInput();
        }
        return input;
    }

    private static void addToList(ContactList list) {
        String first = getFirst();
        scanner.nextLine();
        String last = getLast();
        scanner.nextLine();
        String phone = getPhone();
        scanner.nextLine();
        String email = getEmail();
        try{
            list.add(new ContactItem(first,last,phone,email));
        }
        catch(InvalidContactException invalidContactException){
            System.out.println("A contact item shall contain at least one of " +
                    "[first name], [last name], [phone number], or [email address]");
        }
     }

    private static String getFirst() {
        System.out.println("First name: ");
        return getStringInput();
    }

    private static String getLast() {
        System.out.println("Last name: ");
        return getStringInput();
    }

    private static String getPhone() {
        System.out.println("Phone number (xxx-xxx-xxxx): ");
        return getStringInput();
    }

    private static String getEmail() {
        System.out.println("Email address (x@y.z): ");
        return getStringInput();
    }

    private static String getStringInput() {
        return scanner.next();
    }

    private static void viewList(ContactList list) {
        System.out.println("Current Contacts");
        System.out.print("--------------\n\n");
        list.display();
    }

    private static int getListInput() {
        displayListOperationMenu();
        int input;
        while(true) {
            input = getIntegerInput();
            if(input >= 1 && input <=6) {
                System.out.println("> " + input);
                return input;
            }
            System.out.println("Input 1 through 6!");
        }
    }

    private static void displayListOperationMenu() {
        System.out.println("List Operation Menu");
        System.out.println("--------------");
        System.out.println();
        System.out.println("1) view the list");
        System.out.println("2) add an item");
        System.out.println("3) edit an item");
        System.out.println("4) remove an item");
        System.out.println("5) save the current list");
        System.out.println("6) quit to the main menu");
        System.out.println();
    }

    private static int getMainMenuInput() {
        displayMainMenu();
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

    private static void displayMainMenu() {
        System.out.println("Main Menu");
        System.out.println("--------------");
        System.out.println();
        System.out.println("1) create a new list");
        System.out.println("2) load an existing list");
        System.out.println("3) quit");
        System.out.println();
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
}
