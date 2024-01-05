package data;

import business_package.Person;
import javax.swing.JOptionPane;
import java.io.*;

public class PersonDataHandler {
    private static final String FILE_NAME = "persons.dat";
    private static final int RECORD_SIZE = Integer.BYTES + // for recordNumber
            Person.MAX_FIRST_NAME_SIZE + Person.MAX_LAST_NAME_SIZE +
            Person.MAX_PHONE_SIZE + Integer.BYTES;

    public static void addPerson(Person person) {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            // Seek to the end of the file
            file.seek(file.length());

            // Write person data to the file
            file.writeInt(person.getRecordNumber());
            writeString(file, person.getFirstName(), Person.MAX_FIRST_NAME_SIZE);
            writeString(file, person.getLastName(), Person.MAX_LAST_NAME_SIZE);
            writeString(file, person.getPhone(), Person.MAX_PHONE_SIZE);
            file.writeInt(person.getAge());

            // Show a dialog to indicate that the person has been added
            JOptionPane.showMessageDialog(null, "Person added to file", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            // You may want to handle this exception in a more appropriate way, e.g., show an error message to the user
        }
    }

    public static Person findPerson(int recordNumber) {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
            long position = (recordNumber - 1) * RECORD_SIZE;

            // Check if the position is within the file bounds
            if (position >= 0 && position < file.length()) {
                file.seek(position);

                // Read person data from the file, including recordNumber
                int storedRecordNumber = file.readInt();
				/*
				 * String firstName = readString(file, Person.MAX_FIRST_NAME_SIZE); String
				 * lastName = readString(file, Person.MAX_LAST_NAME_SIZE); String phone =
				 * readString(file, Person.MAX_PHONE_SIZE);
				 * 
				 * // Check if the stored recordNumber matches the requested one if
				 * (storedRecordNumber == recordNumber) {
				 * 
				 * 
				 * return new Person(recordNumber, firstName, lastName, phone, age); }
				 */
                if (storedRecordNumber == recordNumber) {
                	String firstName = readString(file, Person.MAX_FIRST_NAME_SIZE);
                    String lastName = readString(file, Person.MAX_LAST_NAME_SIZE);
                    String phone = readString(file, Person.MAX_PHONE_SIZE);
                    
                    // Read the age as a string and convert it to an integer
                    int age = Integer.parseInt(readString(file, Integer.BYTES));
                    return new Person(recordNumber, firstName, lastName, phone, age);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle this exception in a more appropriate way if needed
        }

        // Return null if the person is not found
        return null;
    }



    private static void writeString(RandomAccessFile file, String value, int size) throws IOException {
        // Write a string to the file with a specified maximum size
        byte[] bytes = new byte[size];
        byte[] stringBytes = value.getBytes("UTF-8");  // Specify the encoding
        System.arraycopy(stringBytes, 0, bytes, 0, Math.min(stringBytes.length, size));
        file.write(bytes);
        // Add a delimiter or padding (e.g., space) between fields
        file.writeBytes(" ");
    }
    

  
    
    private static String readString(RandomAccessFile file, int size) throws IOException {
        // Read a string from the file with a specified maximum size
        byte[] bytes = new byte[size];
        file.readFully(bytes);
        
        // Skip the delimiter or padding (assuming it's a space)
        file.readByte();
        
        return new String(bytes, "UTF-8").trim();
    }


  

}
