package business_package;

import data.RandomIO;
import data.PersonDataHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonGui extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField recordNumberTextField, firstNameTextField, lastNameTextField, ageTextField, phoneTextField;

    public PersonGui() {
        // Initialize components, set layout, etc.
        recordNumberTextField = new JTextField(10);
        firstNameTextField = new JTextField(20);
        lastNameTextField = new JTextField(25);
        ageTextField = new JTextField(5);
        phoneTextField = new JTextField(10);

        // Add Button
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPerson();
            }
        });

        // Find Button
        JButton findButton = new JButton("Find");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findPerson();
            }
        });

        // Add components to the GUI
        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Record Number:"));
        panel.add(recordNumberTextField);
        panel.add(new JLabel("First Name:"));
        panel.add(firstNameTextField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameTextField);
        panel.add(new JLabel("Age:"));
        panel.add(ageTextField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneTextField);
        panel.add(addButton);
        panel.add(findButton);

        // Set up JFrame
        setTitle("Person GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to add a person
    private void addPerson() {
        try {
            int recordNumber = Integer.parseInt(recordNumberTextField.getText());
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String phone = phoneTextField.getText();
            int age = Integer.parseInt(ageTextField.getText());

            Person person = new Person(recordNumber, firstName, lastName, phone, age);
            RandomIO.addPerson(person);

            // You may want to add some feedback to the user here
            JOptionPane.showMessageDialog(this, "Person added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid record number or age", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void findPerson() {
        try {
            int recordNumber = Integer.parseInt(recordNumberTextField.getText());
            Person foundPerson = PersonDataHandler.findPerson(recordNumber);

            if (foundPerson != null) {
                // Display the information of the found person in a dialog box
                showPersonInfoMessage(foundPerson);
            } else {
                JOptionPane.showMessageDialog(this, "Person not found", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid record number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showPersonInfoMessage(Person person) {
        String message = "Record Number: " + person.getRecordNumber() + "\n" +
                "First Name: " + person.getFirstName() + "\n" +
                "Last Name: " + person.getLastName() + "\n" +
                "Age: " + person.getAge() + "\n" +
                "Phone: " + person.getPhone();

        JOptionPane.showMessageDialog(this, message, "Person Information", JOptionPane.INFORMATION_MESSAGE);
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PersonGui());
    }
}
