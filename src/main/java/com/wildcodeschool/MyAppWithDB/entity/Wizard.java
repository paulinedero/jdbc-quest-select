package com.wildcodeschool.MyAppWithDB.entity;

public class Wizard {

    private int id;
    private String firstName;
    private String lastName;
    private String birthday;
    private String birthPlace;
    private String biography;
    private boolean isMuggle;

    public Wizard (int id, String firstName, String lastName, String birthday, String birthPlace, String biography, boolean isMuggle) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.birthPlace = birthPlace;
        this.biography = biography;
        this.isMuggle = isMuggle;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName () {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName () {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday () {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthPlace () {
        return this.birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getBiography () {
        return this.biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public boolean isMuggle () {
        return this.isMuggle;
    }

    public void setIsMuggle(boolean isMuggle) {
        this.isMuggle = isMuggle;
    }
}
