import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.*;

public class ContactList {

    List<ContactItem> contactItems;

    public ContactList(){
        contactItems = new ArrayList<>();
    }

    public int size() {
        return contactItems.size();
    }

    public void add(ContactItem contact) {
        contactItems.add(contact);
    }

    public ContactItem get(int i) {
        if(isIndexInvalid(i)){
            throw new InvalidParameterException("Index must be valid!");
        }
        return contactItems.get(i);
    }

    private boolean isIndexInvalid(int i) {
        return (i >= contactItems.size()) || (i < 0);
    }

    public void remove(int i) {
        if(isIndexInvalid(i)){
            throw new InvalidParameterException("Index must be valid!");
        }
        contactItems.remove(i);
    }

    public void save(String fileName) {
        if(size()>0) {
            try (Formatter output = new Formatter(fileName)) {
                output.format("%d\n", contactItems.size());
                for (ContactItem contactItem : contactItems) {
                    output.format("%s\n", contactItem.getFirstName());
                    output.format("%s\n", contactItem.getLastName());
                    output.format("%s\n", contactItem.getPhoneNumber());
                    output.format("%s\n", contactItem.getEmailAddress());
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
                String firstName = input.next();
                String lastName = input.next();
                String phoneNumber = input.next();
                String emailAddress = input.next();
                ContactItem contact = new ContactItem(firstName,lastName,phoneNumber,emailAddress);
                add(contact);
            }
        }
        catch(InputMismatchException inputMismatchException){
            System.out.println("Error occurred: InputMismatchException!");
        }
        catch(IOException ioException){
            System.out.println("Error occurred: IOException!");
        }
    }

    public void display() {
        int i = 0;
        while(i < contactItems.size()){
            System.out.printf("%d) ", i);
            System.out.println(contactItems.get(i));
            i++;
        }
        System.out.println();
        System.out.println();
    }
}
