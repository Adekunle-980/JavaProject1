package business_package;

public class Person {
    private int recordNumber;
    private String firstName;
    private String lastName;
    private String phone;
    private int age;

    public static final int MAX_FIRST_NAME_SIZE = 20;
    public static final int MAX_LAST_NAME_SIZE = 25;
    public static final int MAX_PHONE_SIZE = 10;

    public Person(int recordNumber, String firstName, String lastName, String phone, int age) {
        this.recordNumber = recordNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
    }

    public Person(String firstName, String lastName, String phone, int age) {
        this(0, firstName, lastName, phone, age); // Assuming 0 as a default value for recordNumber
    }

    // Getters
    public int getRecordNumber() {
        return recordNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
