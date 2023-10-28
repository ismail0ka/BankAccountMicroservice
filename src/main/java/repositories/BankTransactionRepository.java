package repositories;

import models.BankAccount;
import models.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long> {
    BankTransaction save(BankTransaction bankTransaction);
    void delete(BankTransaction bankTransaction);
    BankTransaction findBankTransactionById(Long id);
}
