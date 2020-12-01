import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

public class ContactListTest {

    @Test
    public void addingItemsIncreasesSize(){
        ContactList list = new ContactList();
        assertEquals(list.size(),0);
        ContactItem contact = new ContactItem("first","last","123-456-7890","email@email.com");
        list.add(contact);
        assertEquals(list.size(),1);
    }

    @Test
    public void editingItemsFailsWithAllBlankValues(){
        ContactList list = new ContactList();
        ContactItem contact = new ContactItem("first","last","123-456-7890","email@email.com");
        list.add(contact);
        assertThrows(InvalidContactException.class, () -> list.get(0).editContact("","","", ""));
    }

    @Test
    public void editingItemsFailsWithInvalidIndex(){
        ContactList list = new ContactList();
        ContactItem contact = new ContactItem("first","last","123-456-7890","email@email.com");
        list.add(contact);
        assertThrows(InvalidParameterException.class, () -> list.get(2).editContact("new","new","new","new"));
    }

    @Test
    public void editingSucceedsWithBlankFirstName(){
        ContactList list = new ContactList();
        ContactItem contact = new ContactItem("first","last","123-456-7890","email@email.com");
        list.add(contact);
        list.get(0).editContact("","last","123-456-7890","email@email.com");
        assertEquals(list.get(0).getFirstName(),"");
    }

    @Test
    public void editingSucceedsWithBlankLastName(){
        ContactList list = new ContactList();
        ContactItem contact = new ContactItem("first","last","123-456-7890","email@email.com");
        list.add(contact);
        list.get(0).editContact("first","","123-456-7890","email@email.com");
        assertEquals(list.get(0).getLastName(),"");
    }

    @Test
    public void editingSucceedsWithBlankPhone(){
        ContactList list = new ContactList();
        ContactItem contact = new ContactItem("first","last","123-456-7890","email@email.com");
        list.add(contact);
        list.get(0).editContact("first","last","","email@email.com");
        assertEquals(list.get(0).getPhoneNumber(),"");
    }

    @Test
    public void editingSucceedsWithBlankEmail(){
        ContactList list = new ContactList();
        ContactItem contact = new ContactItem("first","last","123-456-7890","email@email.com");
        list.add(contact);
        list.get(0).editContact("first","last","123-456-7890","");
        assertEquals(list.get(0).getEmailAddress(),"");
    }

    @Test
    public void editingSucceedsWithNonBlankValues(){
        ContactList list = new ContactList();
        ContactItem contact = new ContactItem("first","last","123-456-7890","email@email.com");
        list.add(contact);
        list.get(0).editContact("Hank","Hill","707-566-2053","HankHill@StricklandPropane.com");
        assertEquals(list.get(0).getFirstName(),"Hank");
        assertEquals(list.get(0).getLastName(),"Hill");
        assertEquals(list.get(0).getPhoneNumber(),"707-566-2053");
        assertEquals(list.get(0).getEmailAddress(),"HankHill@StricklandPropane.com");
    }

    @Test
    public void newListIsEmpty(){
        ContactList list = new ContactList();
        assertEquals(list.size(),0);
    }

    @Test
    public void removingItemsDecreasesSize(){
        ContactList list = new ContactList();
        ContactItem contact = new ContactItem("first","last","123-456-7890","email@email.com");
        list.add(contact);
        assertEquals(list.size(),1);
        list.remove(0);
        assertEquals(list.size(),0);
    }

    @Test
    public void removingItemsFailsWithInvalidIndex(){
        ContactList list = new ContactList();
        ContactItem contact = new ContactItem("first","last","123-456-7890","email@email.com");
        list.add(contact);
        assertThrows(InvalidParameterException.class, () -> list.remove(2));
    }

    @Test
    public void savedContactListCanBeLoaded(){
        ContactList savedList = new ContactList();
        ContactItem contact = new ContactItem("Hank","Hill","707-566-2053","HankHill@StricklandPropane.com");
        savedList.add(contact);
        savedList.save("save8609.txt");
        ContactList loadedList = new ContactList();
        loadedList.load("save8609.txt");
        assertEquals(savedList.get(0).toString(), loadedList.get(0).toString());
    }

}
