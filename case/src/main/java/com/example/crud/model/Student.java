package com.example.crud.model;

public class Student {
    private int id;
    private String name;
    private String email;
    private int clazz_id;
    private double point;

    public Student() {

    }

    public Student(String name, String email, int clazz_id, double point) {
        this.name = name;
        this.email = email;
        this.clazz_id = clazz_id;
        this.point = point;
    }

    public Student(int id, String name, String email, int clazz_id, double point) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.clazz_id = clazz_id;
        this.point = point;
    }

    // Getters and setters...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getClazz_id() {
        return clazz_id;
    }

    public void setClazz_id(int clazz_id) {
        this.clazz_id = clazz_id;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }
}
