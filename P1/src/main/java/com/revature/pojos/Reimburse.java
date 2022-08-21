package com.revature.pojos;

import java.util.Objects;

public class Reimburse {
    private Integer reimburseId;
    private Integer userId;
    private Integer ticket;
    private String reason;
    private double amount;
    private boolean pending;

    public Reimburse(){

    }

    public Reimburse(Integer reimburseId, Integer userId, Integer ticket, String reason, Double amount, Boolean pending) {
        this.reimburseId = reimburseId;
        this.userId = userId;
        this.ticket = ticket;
        this.reason = reason;
        this.amount = amount;
        this.pending = pending;
    }

    public Integer getReimburseId() {
        return reimburseId;
    }

    public void setReimburseId(Integer reimburseId) {
        this.reimburseId = reimburseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason
    ) {
        this.reason = reason;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getPending() {
        return pending;
    }

    public void setPending(Boolean pending) {
        this.pending = pending;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimburse reimburse = (Reimburse) o;
        return Objects.equals(reimburseId, reimburse.reimburseId) && Objects.equals(userId, reimburse.userId) && Objects.equals(ticket, reimburse.ticket) && Objects.equals(reason, reimburse.reason) && Objects.equals(amount, reimburse.amount) && Objects.equals(pending, reimburse.pending);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimburseId, userId, ticket, reason, amount, pending);
    }

    @Override
    public String toString() {
        return "Reimburse{" +
                "reimburseId=" + reimburseId +
                ", userId=" + userId +
                ", ticket=" + ticket +
                ", reason='" + reason + '\'' +
                ", amount=" + amount +
                ", pending=" + pending +
                '}';
    }
}
