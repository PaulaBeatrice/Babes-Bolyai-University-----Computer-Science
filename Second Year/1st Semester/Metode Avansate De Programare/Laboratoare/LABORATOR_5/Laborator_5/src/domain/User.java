package domain;

/**
 * User class extends Entity<ID> where ID is Long type
 */
public class User extends Entity<Long> {
    private String firstName;
    private String lastName;

    /**
     * Constructor for  User type Object
     *
     * @param firstName -string
     * @param lastName  -string
     */
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Getter for First Name
     *
     * @return firstName - string
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for Last Name
     *
     * @return lastName - string
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for FirstName
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Setter for LastName
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User that = (User) o;
        return getFirstName().equals(that.getFirstName()) &&
                getLastName().equals(that.getLastName());
    }
}
