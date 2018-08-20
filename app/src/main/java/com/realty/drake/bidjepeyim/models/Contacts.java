package com.realty.drake.bidjepeyim.models;

/**
 * Created by drake on 8/20/18
 */
public class Contacts {
    String contactName,
            contactFunction,
            contactPhone,
            contactEmail,
            contactImage;

    public Contacts(String contactName,
                    String contactFunction,
                    String contactPhone,
                    String contactEmail,
                    String contactImage) {
        this.contactName = contactName;
        this.contactFunction = contactFunction;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.contactImage = contactImage;
    }
}
