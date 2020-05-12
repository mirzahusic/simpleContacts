package sample.contact;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class ContactData {

    private static ContactData instance = new ContactData();
    private static String filename = "ContactDataItems.txt";
    private ObservableList<Contact> contactItemList = FXCollections.observableArrayList();

    public static ContactData getInstance() {
        return instance;
    }


    public ObservableList<Contact> getContactItemList() {
        return contactItemList;
    }

    public void setContactItemList(ObservableList<Contact> contactItemList) {
        this.contactItemList = contactItemList;
    }

    public void addContactItem(Contact contact) {
        contactItemList.add(contact);
    }

    public void deleteContactItem(Contact contact) {
        contactItemList.remove(contact);
    }

    public void loadContactData() throws IOException {
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;
        try {
            while ((input = br.readLine()) != null) {

                String[] dataItems = input.split("\t");

                String firstName = dataItems[0];
                String lastName = dataItems[1];
                String phoneNumber = dataItems[2];
                String email = dataItems[3];

                Contact contact = new Contact(firstName, lastName, phoneNumber, email);
                contactItemList.add(contact);

            }

        } finally {
            if (br != null)
                br.close();
        }


    }

    public void saveContactData() throws IOException {
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<Contact> iterator = contactItemList.iterator();
            while (iterator.hasNext()) {
                Contact contact = iterator.next();
                bw.write(String.format("%s\t%s\t%s\t%s",
                        contact.getFirstName(), contact.getLastName(),
                        contact.getPhoneNumber(), contact.getEmail()));
                bw.newLine();
            }
        } finally {
            if (bw != null)
                bw.close();
        }

    }
}
