package com.jatdev.qa.API.models;

import com.google.gson.annotations.SerializedName;

public class User {
    private String name;
    private String job;
    private String id;

    @SerializedName("createdAt")
    private String createdAt;

    public User() {}

    public User(String name, String job) {
        this.name = name;
        this.job = job;
    }

    // Getters and setters...
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}