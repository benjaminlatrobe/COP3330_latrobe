public class ContactItem {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    public ContactItem(String firstName, String lastName, String phoneNumber, String emailAddress) {
        if(isValidContact(firstName,lastName,phoneNumber,emailAddress)){
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.emailAddress = emailAddress;
        } else {
            throw new InvalidContactException("A contact item shall contain at least one of " +
                    "[first name], [last name], [phone number], or [email address]\n");
        }
    }

    private boolean isValidContact(String firstName, String lastName, String phoneNumber, String emailAddress) {
        return (!firstName.isEmpty() || !lastName.isEmpty() || !phoneNumber.isEmpty() || !emailAddress.isEmpty());
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void editContact(String firstName, String lastName, String phoneNumber, String emailAddress) {
        if(isValidContact(firstName,lastName,phoneNumber,emailAddress)){
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.emailAddress = emailAddress;
        } else {
            throw new InvalidContactException("A contact item shall contain at least one of " +
                    "[first name], [last name], [phone number], or [email address]");
        }
    }

    @Override
    public String toString(){
        return "Name: " + firstName + " " + lastName + "\nPhone: " + phoneNumber + "\nEmail: " + emailAddress + "\n";
    }

}

class InvalidContactException extends IllegalArgumentException {
    public InvalidContactException(String msg){
        super(msg);
    }
}
