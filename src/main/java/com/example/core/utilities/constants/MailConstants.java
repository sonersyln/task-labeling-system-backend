package com.example.core.utilities.constants;

public enum MailConstants {

    SENDER_EMAIL("noreply@rentogo.com.tr"),
    SENDER_NAME("Rent2Go Şirketi"),
    SUBJECT("Yeni Görev Eklendi"),

    RECEIVER_EMAIL("contact.rent2go@gmail.com");

    private final String value;

    MailConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
