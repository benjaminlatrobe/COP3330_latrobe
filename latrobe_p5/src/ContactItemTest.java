import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContactItemTest {

    @Test
    public void creationFailsWithAllBlankValues(){
        assertThrows(InvalidContactException.class, () -> new ContactItem("","","", ""));
    }

    @Test
    public void creationSucceedsWithBlankEmail(){
        ContactItem contact = new ContactItem("first","last","1234567890","");
        assertEquals(contact.getFirstName(), "first");
    }

    @Test
    public void creationSucceedsWithBlankFirstName(){
        ContactItem contact = new ContactItem("","last","1234567890","email@email.com");
        assertEquals(contact.getEmailAddress(), "email@email.com");
    }

    @Test
    public void creationSucceedsWithBlankLastName(){
        ContactItem contact = new ContactItem("first","","1234567890","email@email.com");
        assertEquals(contact.getPhoneNumber(), "1234567890");
    }

    @Test
    public void creationSucceedsWithBlankPhone(){
        ContactItem contact = new ContactItem("first","last","","email@email.com");
        assertEquals(contact.getLastName(), "last");
    }

    @Test
    public void creationSucceedsWithNonBlankValues(){
        ContactItem contact = new ContactItem("a","b","c","d");
        assertEquals(contact.getFirstName(),"a");
        assertEquals(contact.getLastName(),"b");
        assertEquals(contact.getPhoneNumber(), "c");
        assertEquals(contact.getEmailAddress(),"d");
    }

    @Test
    public void editingFailsWithAllBlankValues(){
        ContactItem contact = new ContactItem("a","b","c","d");
        assertThrows(InvalidContactException.class, () -> contact.editContact("","","", ""));
    }

    @Test
    public void editingSucceedsWithBlankEmail(){
        ContactItem contact = new ContactItem("a","b","c","d");
        contact.editContact("newa","newb","newc","");
        assertEquals(contact.getFirstName(),"newa");
        assertEquals(contact.getLastName(),"newb");
        assertEquals(contact.getPhoneNumber(), "newc");
        assertEquals(contact.getEmailAddress(),"");
    }

    @Test
    public void editingSucceedsWithBlankFirstName(){
        ContactItem contact = new ContactItem("a","b","c","d");
        contact.editContact("","newb","newc","newd");
        assertEquals(contact.getFirstName(),"");
        assertEquals(contact.getLastName(),"newb");
        assertEquals(contact.getPhoneNumber(), "newc");
        assertEquals(contact.getEmailAddress(),"newd");
    }

    @Test
    public void editingSucceedsWithBlankLastName(){
        ContactItem contact = new ContactItem("a","b","c","d");
        contact.editContact("newa","","newc","newd");
        assertEquals(contact.getFirstName(),"newa");
        assertEquals(contact.getLastName(),"");
        assertEquals(contact.getPhoneNumber(), "newc");
        assertEquals(contact.getEmailAddress(),"newd");
    }

    @Test
    public void editingSucceedsWithBlankPhone(){
        ContactItem contact = new ContactItem("a","b","c","d");
        contact.editContact("newa","newb","","newd");
        assertEquals(contact.getFirstName(),"newa");
        assertEquals(contact.getLastName(),"newb");
        assertEquals(contact.getPhoneNumber(), "");
        assertEquals(contact.getEmailAddress(),"newd");
    }

    @Test
    public void editingSucceedsWithNonBlankValues(){
        ContactItem contact = new ContactItem("a","b","c","d");
        contact.editContact("newa","newb","newc","newd");
        assertEquals(contact.getFirstName(),"newa");
        assertEquals(contact.getLastName(),"newb");
        assertEquals(contact.getPhoneNumber(), "newc");
        assertEquals(contact.getEmailAddress(),"newd");
    }

    @Test
    public void testToString(){
        ContactItem contact = new ContactItem("first","last","123-456-7890","email@gmail.com");
        assertEquals(contact.toString(),"Name: first last\nPhone: 123-456-7890\nEmail: email@gmail.com\n");
    }

}
