package models;

import enums.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String code;

    private Double balance;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date creation_date;

    @Enumerated
    private Currency currency;

    @OneToMany(mappedBy = "bankAccount")
    List<BankTransaction> transactions;
}
