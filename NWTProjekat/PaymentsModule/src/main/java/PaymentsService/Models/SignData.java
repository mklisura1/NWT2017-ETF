package PaymentsService.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Hare on 20.06.2017..
 */
//    @Entity
public class SignData {
    public Integer getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Integer accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Integer getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Integer accountTo) {
        this.accountTo = accountTo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @JsonProperty("accountFrom")
    private Integer accountFrom;

    @JsonProperty("accountTo")
    private Integer accountTo;

    @JsonProperty("amount")
    private Double amount;
}