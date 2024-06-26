package models;

import java.util.Objects;

public class Person {
private Name name;
private Authentication authentication;
private Address address;
private ContactInfo contactInfo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return person.authentication.getUsername().equals(authentication.getUsername()) &&
                person.contactInfo.getEmail().equals(contactInfo.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, authentication, address, contactInfo);
    }

    public Person(Name name, Authentication authentication, Address address, ContactInfo contactInfo) {
        setName(name);
        setAuthentication(authentication);
        setAddress(address);
        setContactInfo(contactInfo);
    }

    public Person() {

    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
}
