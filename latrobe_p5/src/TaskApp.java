import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskApp {

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

    private static void listOperationMenu(TaskList list) {
        int listInput = getListInput();
        while(listInput != 8){
            switch (listInput) {
                case 1 -> viewList(list);
                case 2 -> addToList(list);
                case 3 -> editListItem(list);
                case 4 -> removeListItem(list);
                case 5 -> markListItemCompleted(list);
                case 6 -> unmarkListItem(list);
                case 7 -> saveCurrentList(list);
                default -> System.out.println("ERROR");
            }
            listInput = getListInput();
        }
    }

    private static void unmarkListItem(TaskList list) {
        list.displayComplete();
        int i = getUncompleteTask(list);
        if(i<0){
            System.out.println("There are no items in the list");
        } else {
            list.uncomplete(i);
        }
    }

    private static int getUncompleteTask(TaskList list) {
        System.out.println("Which task will you mark as uncomplete?");
        return getTaskIndex(list);
    }

    private static void removeListItem(TaskList list) {
        list.display();
        int i = getTaskToRemove(list);
        if(i<0){
            System.out.println("There are no items to remove");
        } else {
            list.remove(i);
        }
    }

    private static int getTaskToRemove(TaskList list) {
        System.out.println("Which task will you remove?");
        return getTaskIndex(list);
    }

    private static void saveCurrentList(TaskList list) {
        String file = getFileName();
        list.save(file);
    }

    private static void markListItemCompleted(TaskList list) {
        list.displayUncomplete();
        int i = getCompleteTask(list);
        if(i<0){
            System.out.println("There are no items in the list");
        } else {
            list.complete(i);
        }
    }

    private static int getCompleteTask(TaskList list) {
        System.out.println("Which task will you mark as complete?");
        return getTaskIndex(list);
    }

    private static void editListItem(TaskList list) {
        viewList(list);
        int i = getTaskToEdit(list);
        if(i<0){
            System.out.println("There are no items to edit");
        } else {
            editTask(list, i);
        }
    }

    private static void editTask(TaskList list, int i) {
        String title = getTaskTitle();
        scanner.nextLine();
        String description = getTaskDescription();
        scanner.nextLine();
        String date = getTaskDate();
        scanner.nextLine();
        try{
            list.get(i).setTitle(title);
            list.get(i).setDescription(description);
            list.get(i).setDueDate(date);
        }
        catch(InvalidDateException invalidDateException){
            System.out.println("A due date must be in the format of YYYY-MM-DD!");
        }
        catch(InvalidTitleException invalidTitleException){
            System.out.println("A title must be 1 or characters in length!");
        }
    }

    private static int getTaskToEdit(TaskList list) {
        System.out.println("Which task will you edit?");
        return getTaskIndex(list);
    }

    private static int getTaskIndex(TaskList list){
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

    private static void viewList(TaskList list) {
        System.out.println("Current Tasks");
        System.out.print("--------------\n\n");
        list.display();
    }

    private static void addToList(TaskList list) {
        String title = getTaskTitle();
        scanner.nextLine();
        String description = getTaskDescription();
        scanner.nextLine();
        String date = getTaskDate();
        scanner.nextLine();
        try{
            list.add(new TaskItem(title, description, date));
        }
        catch(InvalidDateException invalidDateException){
            System.out.println("A due date must be in the format of YYYY-MM-DD!");
        }
        catch(InvalidTitleException invalidTitleException){
            System.out.println("A title must be 1 or characters in length!");
        }
    }

    private static String getTaskTitle() {
        System.out.println("Task title: ");
        return getStringInput();
    }

    private static String getTaskDate() {
        System.out.println("Task due date (YYYY-MM-DD): ");
        return getStringInput();
    }

    private static String getTaskDescription() {
        System.out.println("Task description: ");
        return getStringInput();
    }

    private static String getStringInput() {
        return scanner.next();
    }

    private static int getListInput() {
        displayListOperationMenu();
        int input;
        while(true) {
            input = getIntegerInput();
            if(input >= 1 && input <=8) {
                System.out.println("> " + input);
                return input;
            }
            System.out.println("Input 1 through 8!");
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
        System.out.println("5) mark an item as complete");
        System.out.println("6) unmark an item as complete");
        System.out.println("7) save the current list");
        System.out.println("8) quit to the main menu");
        System.out.println();
    }

    private static void loadList() {
        String fileName = getFileName();
        TaskList list = new TaskList();
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

    public static boolean isFileNameValid(String fileName) {
        return (fileName.endsWith(".txt"));
    }

    private static void createList() {
        TaskList list = new TaskList();
        System.out.print("New list has been created!\n\n");
        listOperationMenu(list);
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

    private static void displayMainMenu(){
        System.out.println("Main Menu");
        System.out.println("--------------");
        System.out.println();
        System.out.println("1) create a new list");
        System.out.println("2) load an existing list");
        System.out.println("3) quit");
        System.out.println();
    }

}
