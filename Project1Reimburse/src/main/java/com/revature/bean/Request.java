package com.revature.bean;

import java.util.Objects;

public class Request {
    private int requestId;
    private int employeeId;
    private int status;
    private String description;

    public Request(int requestId, int employeeId, int status, String description) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.status = status;
        this.description = description;
    }

    public Request(int employeeId, int status, String description) {
        this.employeeId = employeeId;
        this.status = status;
        this.description = description;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return getRequestId() == request.getRequestId() &&
                getEmployeeId() == request.getEmployeeId() &&
                getStatus() == request.getStatus() &&
                Objects.equals(getDescription(), request.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequestId(), getEmployeeId(), getStatus(), getDescription());
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", employeeId=" + employeeId +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
