import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.*;

public class TaskList {
    List<TaskItem> taskItems;

    public TaskList(){
        taskItems = new ArrayList<>();
    }

    public void complete(int i){
        if(isIndexValid(i)){
            taskItems.get(i).setCompletionStatus(true);
        } else {
            throw new InvalidParameterException("Enter a valid index!");
        }
    }

    public void uncomplete(int i){
        if(isIndexValid(i)){
            taskItems.get(i).setCompletionStatus(false);
        } else {
            throw new InvalidParameterException("Enter a valid index!");
        }
    }

    private boolean isIndexValid(int i) {
        return (i < taskItems.size()) && (i >= 0);
    }

    public TaskItem get(int i){
        if(!isIndexValid(i)){
            throw new InvalidParameterException("Index must be valid!");
        }
        return taskItems.get(i);
    }

    public void add(TaskItem task){
        taskItems.add(task);
    }

    public void remove(int i){
        if(!isIndexValid(i)){
            throw new InvalidParameterException("Index must be valid!");
        }
        taskItems.remove(i);
    }

    public void save(String fileName){
        if(size()>0) {
            try (Formatter output = new Formatter(fileName)) {
                output.format("%d\n", taskItems.size());
                for (TaskItem taskItem : taskItems) {
                    output.format("%s\n", taskItem.getTitle());
                    output.format("%s\n", taskItem.getDescription());
                    output.format("%s\n", taskItem.getDueDate());
                    output.format("%s\n", taskItem.getCompletionStatus());
                }
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("Unable to find file!");
            }
        } else {
            System.out.println("Can't save a list with zero items");
        }
    }

    public void load(String fileName) {
        try(Scanner input = new Scanner(Paths.get(fileName)).useDelimiter("\\n")){
            int numLists = input.nextInt();
            for(int i = 0; i < numLists; i++){
                String title = input.next();
                String description = input.next();
                String date = input.next();
                boolean status = input.nextBoolean();
                TaskItem item = new TaskItem(title,description,date);
                add(item);
                get(i).setCompletionStatus(status);
            }
        }
        catch(InputMismatchException inputMismatchException){
            System.out.println("Error occurred: InputMismatchException!");
        }
        catch(IOException ioException){
            System.out.println("Error occurred: IOException!");
        }
    }

    public int size(){
        return taskItems.size();
    }

    public void display() {
        int i = 0;
        while(i < taskItems.size()){
            if(taskItems.get(i).getCompletionStatus()){
                System.out.print("***");
            }
            System.out.printf("%d) ", i);
            System.out.println(taskItems.get(i));
            i++;
        }
        System.out.println();
        System.out.println();
    }

    public void displayUncomplete() {
        System.out.println("Uncomplete Tasks");
        System.out.println("-----------------");
        System.out.println();
        int i = 0;
        while(i < taskItems.size()){
            if(!taskItems.get(i).getCompletionStatus()){
                System.out.printf("%d) ", i);
                System.out.println(taskItems.get(i));
            }
            i++;
        }
        System.out.println();
    }

    public void displayComplete() {
        System.out.println("Complete Tasks");
        System.out.println("-----------------");
        System.out.println();
        int i = 0;
        while(i < taskItems.size()){
            if(taskItems.get(i).getCompletionStatus()){
                System.out.printf("%d) ", i);
                System.out.println(taskItems.get(i));
            }
            i++;
        }
        System.out.println();
    }
}


