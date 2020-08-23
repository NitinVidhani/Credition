package application.example.credition.model;

public class Transaction {

    private int transactionId;
    private int fromId;
    private int toId;
    private int amount;

    public Transaction() {
    }

    public Transaction(int transactionId, int fromId, int toId, int amount) {
        this.transactionId = transactionId;
        this.fromId = fromId;
        this.toId = toId;
        this.amount = amount;
    }

    public Transaction(int fromId, int toId, int amount) {
        this.fromId = fromId;
        this.toId = toId;
        this.amount = amount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
