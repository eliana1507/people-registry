package com.example.peopleregistry.model;

public class OldestChildDto {
    String parentPnr;
    String childName;

    public OldestChildDto(String parentPnr, String childName) {
        this.parentPnr = parentPnr;
        this.childName = childName;
    }
}
