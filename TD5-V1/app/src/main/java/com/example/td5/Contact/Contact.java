package com.example.td5.Contact;

public class Contact {
    private String lastName;
    private String firstName;
    private String imageUrl;

    public Contact(String firstName,String lastName,String imageUrl){
        this.lastName = lastName;
        this.firstName = firstName;
        this.imageUrl = imageUrl;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
