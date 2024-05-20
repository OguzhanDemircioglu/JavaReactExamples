package helper;

public class Person {

    public String name;
    public String surName;
    public int age;
    public String birthPlace;

    public Person() {
    }

    public Person(String name, String surName, int age, String birthPlace) {
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.birthPlace = birthPlace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String toString(){
        return this.name + " " + this.surName + " " + this.age +" yaşındadır, memleketi " + this.birthPlace;
    }
}
