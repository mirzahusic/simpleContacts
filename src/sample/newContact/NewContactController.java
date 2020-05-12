package sample.newContact;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.contact.Contact;
import sample.contact.ContactData;


public class NewContactController {
    @FXML
    private TextField firstNameTextFiled;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField phoneNumberTextFiled;
    @FXML
    private TextField emailTextFiled;

    public void processResult(){
        String firstName = firstNameTextFiled.getText().trim();
        String lastName = lastNameTextField.getText().trim();
        String phoneNumber = phoneNumberTextFiled.getText().trim();
        String email = emailTextFiled.getText().trim();

        Contact contact = new Contact(firstName, lastName, phoneNumber, email);
        ContactData.getInstance().addContactItem(contact);

    }
}