package com.jatdev.qa.API.models;


import com.google.gson.annotations.SerializedName;

public class CreateUserResponse {
    private String id;
    private String name;
    private String job;

    @SerializedName("createdAt")
    private String createdAt;

    // Getters and setters...
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
