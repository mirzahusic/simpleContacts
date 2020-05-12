package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import sample.editContact.EditContactController;
import sample.newContact.NewContactController;
import sample.contact.Contact;
import sample.contact.ContactData;

import java.io.IOException;
import java.util.Optional;


public class Controller {
    @FXML
    private TableView<Contact> contactTableView;
    @FXML
    private TableColumn firstNameTableColumn;
    @FXML
    private TableColumn lastNameTableColumn;
    @FXML
    private TableColumn phoneNumberTableColumn;
    @FXML
    private TableColumn emailTableColumn;
    @FXML
    private BorderPane mainSampleContact;


    public void initialize() {

        firstNameTableColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("firstName"));
        lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("lastName"));
        phoneNumberTableColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("phoneNumber"));
        emailTableColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("email"));

        contactTableView.setItems(ContactData.getInstance().getContactItemList());


    }

    @FXML
    public void editContactItem() {
        Contact selectedContact = contactTableView.getSelectionModel().getSelectedItem();

        if (selectedContact != null) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Edit Contact");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("editContact/editContactDialog.fxml"));

            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());

            } catch (IOException e) {

            }

            EditContactController controller = fxmlLoader.getController();
            controller.loadSelectedContact(selectedContact);

            dialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);


            Optional<ButtonType> result = dialog.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.APPLY) {

                controller.processResult(selectedContact);
            }
        }

    }

    @FXML
    public void deleteContactItem() {
        Contact selectedContact = contactTableView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete contact");
            alert.setHeaderText("Delete contact: " + selectedContact.getFirstName() + " " + selectedContact.getLastName() + " ?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                ContactData.getInstance().deleteContactItem(selectedContact);
            }
        }

    }

    @FXML
    public void addNewContact() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add New Contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("newContact/newContactDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e) {

        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            NewContactController controller = fxmlLoader.getController();
            controller.processResult();
        }
    }


}
