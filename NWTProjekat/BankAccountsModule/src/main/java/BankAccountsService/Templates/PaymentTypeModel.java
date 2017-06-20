package BankAccountsService.Templates;

/**
 * Created by Hare on 20.06.2017..
 */
public class PaymentTypeModel {
    private long id;

    public PaymentTypeModel(PaymentTypeModel paymentType) {
        this.id = paymentType.id;
        this.paymentTypeName = paymentType.paymentTypeName;
    }

    public long getId() {
        return id;
    }

    private String paymentTypeName;

    public PaymentTypeModel() {
    }

    public PaymentTypeModel(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }
}
