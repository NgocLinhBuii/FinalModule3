package com.example.ss9_java_web1;

public class Student {
    private String name;
    private String email;
    private String className;

    public Student(String name, String email, String className) {
        this.name = name;
        this.email = email;
        this.className = className;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
