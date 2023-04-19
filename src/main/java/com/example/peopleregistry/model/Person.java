package com.example.peopleregistry.model;

import java.util.List;

public class Person {
    private String pnr;

    private String name;

    private String spouseName;

    private int age;

    private List<Person> children;

    public Person(String pnr, String name, String spouseName, int age, List<Person> children) {
        this.pnr = pnr;
        this.name = name;
        this.spouseName = spouseName;
        this.age = age;
        this.children = children;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpouseName() {
        return spouseName != null ? spouseName : "";
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

    public void addChild(Person child) {
        children.add(child);
    }

}


