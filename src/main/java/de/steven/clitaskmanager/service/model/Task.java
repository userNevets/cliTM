package de.steven.clitaskmanager.service.model;

import de.steven.clitaskmanager.utils.Status;

public class Task {

    private int id = 0;
    private String name;
    private String description;
    private Status status;

    public Task() {
        id++;
        this.status = Status.OPEN;
    }

    public Task(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.status = builder.status;
    }

    public Task(String name) {
        this.id++;
        this.name = name;
        this.description = "";
        this.status = Status.OPEN;
    }

    public Task(String name, String description) {
        this.id++;
        this.name = name;
        this.description = description;
        this.status = Status.OPEN;
    }

    public Task(int id, String name, String description, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static class Builder {
        private int id;
        private String name;
        private String description;
        private Status status;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }
}
