public class TaskItem {
    private String title;
    private String description;
    private String dueDate;
    private boolean completionStatus;

    public TaskItem(String title, String description, String dueDate){

        if(isTitleValid(title)){
            this.title = title;
        } else {
            throw new InvalidTitleException("A title must be 1 or characters in length!");
        }

        this.description = description;

        if(isDateValid(dueDate)){
            this.dueDate = dueDate;
        } else {
                throw new InvalidDateException("A due date must be in the format of YYYY-MM-DD!");
        }

        this.completionStatus = false;
    }

    private boolean isTitleValid(String title) {
        return (title.length() >= 1);
    }

    private boolean isDateValid(String dueDate) {
        if(dueDate.length() != 10){
            return false;
        }
        return dueDate.charAt(4) == '-' &&
                dueDate.charAt(7) == '-';
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

    public String getDueDate(){
        return this.dueDate;
    }

    public boolean getCompletionStatus() {return this.completionStatus;}

    public void setTitle(String title){
        if(isTitleValid(title)){
            this.title = title;
        } else {
            System.out.println("A title must be 1 or characters in length! The title did not change!");
        }
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setDueDate(String dueDate){
        if(isDateValid(dueDate)){
            this.dueDate = dueDate;
        } else {
            System.out.println("A due date must be in the format of YYYY-MM-DD! The due date did not change!");
        }
    }

    public void setCompletionStatus(boolean status){
        this.completionStatus = status;
    }

    @Override
    public String toString(){
        return "[" + dueDate + "] " + title + ": " + description;
    }
}

class InvalidTitleException extends IllegalArgumentException {
    public InvalidTitleException(String msg){
        super(msg);
    }
}

class InvalidDateException extends IllegalArgumentException {
    public InvalidDateException(String msg){
        super(msg);
    }
}