package UsersService.Templates;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Hare on 21.03.2017..
 */
@Entity
@Table(name = "payments")
public class PaymentModel {

    @Id
    @GeneratedValue
    @Column(name = "payment_id", unique = true, nullable = false)
    private long id;

    @Column(name = "amount", nullable = false)
    private double amount;
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Column(name = "payment_date", nullable = false)
    private Date date;
    @Column(name = "sender_name", nullable = false)
    private String senderName;
    @Column(name = "sneder_bank_acc_num", nullable = false)
    private String senderBankAccNumber;
    @Column(name = "receiver_name", nullable = false)
    private String receiverName;
    @Column(name = "receiver_bank_acc_num", nullable = false)
    private String receiverBankAccNumber;
    @Column(name = "payment_purpose", nullable = false)
    private String purpose;
    @Column(name = "payment_type", nullable = false)
    private String type;

    private String typeDescription;

    public PaymentModel() {
    }

    public PaymentModel(double amount) {
        this.amount = amount;
    }

    public PaymentModel(PaymentModel payment) {
        this.amount = payment.amount;
        this.date = payment.date;
        this.userId = payment.userId;
        this.senderName = payment.senderName;
        this.senderBankAccNumber = payment.senderBankAccNumber;
        this.receiverName = payment.receiverName;
        this.receiverBankAccNumber = payment.receiverBankAccNumber;
        this.purpose = payment.purpose;
        this.type = payment.type;
        this.typeDescription = payment.typeDescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderBankAccNumber() {
        return senderBankAccNumber;
    }

    public void setSenderBankAccNumber(String senderBankAccNumber) {
        this.senderBankAccNumber = senderBankAccNumber;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverBankAccNumber() {
        return receiverBankAccNumber;
    }

    public void setReceiverBankAccNumber(String receiverBankAccNumber) {
        this.receiverBankAccNumber = receiverBankAccNumber;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
