package PaymentsService.Models;

import javax.persistence.*;

/**
 * Created by Hare on 17.03.2017..
 */

@Entity
@Table(name = "payment_types")
public class PaymentTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_type_id", nullable = false)
    private long id;

    public PaymentTypeModel(PaymentTypeModel paymentType) {
        this.id = paymentType.id;
        this.paymentTypeName = paymentType.paymentTypeName;
    }

    public long getId() {
        return id;
    }

    @Column(name = "payment_type_name", nullable = false)
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
