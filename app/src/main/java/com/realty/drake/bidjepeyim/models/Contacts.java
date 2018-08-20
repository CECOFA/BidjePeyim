package com.realty.drake.bidjepeyim.models;

/**
 * Created by drake on 8/20/18
 */
public class Contacts {
    String contactName,
            contactFunction,
            contactPhone,
            contactEmail;

    public String getContactName() {
        return contactName;
    }

    public String getContactFunction() {
        return contactFunction;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public Contacts(String contactName,
                    String contactFunction,
                    String contactPhone,
                    String contactEmail) {
        this.contactName = contactName;
        this.contactFunction = contactFunction;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
    }
}
