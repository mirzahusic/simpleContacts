package sample.editContact;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.contact.Contact;
import sample.contact.ContactData;

public class EditContactController {

    @FXML
    private TextField firstNameTextFiled;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField phoneNumberTextFiled;
    @FXML
    private TextField emailTextFiled;

    public void loadSelectedContact(Contact contact) {
        firstNameTextFiled.setText(contact.getFirstName());
        lastNameTextField.setText(contact.getLastName());
        phoneNumberTextFiled.setText(contact.getPhoneNumber());
        emailTextFiled.setText(contact.getEmail());
    }

    public void processResult(Contact contact) {

        contact.setFirstName(firstNameTextFiled.getText().trim());
        contact.setLastName(lastNameTextField.getText().trim());
        contact.setPhoneNumber(phoneNumberTextFiled.getText().trim());
        contact.setEmail(emailTextFiled.getText().trim());


    }


}
