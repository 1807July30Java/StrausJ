package com.revature.bean;

import java.time.LocalDateTime;
import java.util.Objects;

public class Request {
    private int requestId;
    private int employeeId;
    private double amount;
    private int status;
    private String description;
    private LocalDateTime dateTime;

    public Request(int requestId, int employeeId, double amount, int status, String description, LocalDateTime dateTime) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.amount = amount;
        this.status = status;
        this.description = description;
        this.dateTime = dateTime;
    }

    public Request(int employeeId, double amount, int status, String description, LocalDateTime dateTime) {
        this.employeeId = employeeId;
        this.amount = amount;
        this.status = status;
        this.description = description;
        this.dateTime = dateTime;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return getRequestId() == request.getRequestId() &&
                getEmployeeId() == request.getEmployeeId() &&
                Double.compare(request.getAmount(), getAmount()) == 0 &&
                getStatus() == request.getStatus() &&
                Objects.equals(getDescription(), request.getDescription()) &&
                Objects.equals(getDateTime(), request.getDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequestId(), getEmployeeId(), getAmount(), getStatus(), getDescription(), getDateTime());
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", employeeId=" + employeeId +
                ", amount=" + amount +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
