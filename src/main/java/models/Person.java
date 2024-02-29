package models;

public class Person {
private Name name;
private Authentication authentication;
private Address address;
private ContactInfo contactInfo;

    public Person(Name name, Authentication authentication, Address address, ContactInfo contactInfo) {
        this.name = name;
        this.authentication = authentication;
        this.address = address;
        this.contactInfo = contactInfo;
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
