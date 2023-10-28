package repositories;

import models.BankAccount;
import models.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    BankAccount save(BankAccount bankAccount);
    void delete(BankAccount bankAccount);
    BankAccount findBankAccountByCode(String code);
    List<BankAccount> findAll();
}
