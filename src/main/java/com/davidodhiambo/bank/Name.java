package bank;


/* File:    Name.java
 * Class:   Name
 * Author:  Instructor X
 * Date:    dd mm yyyy
 * Purpose: Implements a Name, consisting of a first name and
 *          a last name.
 */
import java.io.Serializable;

public class Name implements Comparable, Serializable {
    private final String _firstName;
    private final String _lastName;

    public Name(String firstName, String lastName) {
        _firstName = firstName;
        _lastName = lastName;
    }

    public String getFirstName() {
        return _firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    @Override
    public String toString() {
        return _firstName + " " + _lastName;
    }

    @Override
    public boolean equals(Object o) {
        String fullName = _firstName + " " + _lastName;
        Name n = (Name) o;
        String compFullName = n._firstName + " " + n._lastName;
        return fullName.equals(compFullName);
    }

    @Override
    public int hashCode() {
        String fullName = _firstName + " " + _lastName;
        return fullName.hashCode();
    }

    public int compareTo(Object o) {
        String fullName = _firstName + " " + _lastName;
        Name n = (Name) o;
        String compFullName = n._firstName + " " + n._lastName;
        return fullName.compareTo(compFullName);
    }
}