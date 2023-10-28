package controllers;

import lombok.AllArgsConstructor;
import models.BankAccount;
import models.BankTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.BankAccountRepository;

import java.util.List;

@RequestMapping("/api/accounts")
@AllArgsConstructor
public class BankAccountController {
    private BankAccountRepository bankAccountRepository;

    @GetMapping("/account/{account_code}")
    public ResponseEntity<BankAccount> getAccountByCode(@PathVariable String code)
    {
        BankAccount bankAccount = bankAccountRepository.findBankAccountByCode(code);
        if (bankAccount != null)
            return ResponseEntity.ok(bankAccount);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BankAccount> createAccount(@RequestBody BankAccount bankAccount)
    {
        BankAccount newAccount = bankAccountRepository.save(bankAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(bankAccount);
    }

    @PutMapping("/account/{account_code}")
    public ResponseEntity<BankAccount> updateAccount(@PathVariable String code, @RequestBody BankAccount bankAccount)
    {
        BankAccount updatedAccount = bankAccountRepository.findBankAccountByCode(code);

        if (updatedAccount != null) {
            updatedAccount.setBalance(bankAccount.getBalance());
            updatedAccount.setCurrency(bankAccount.getCurrency());
            updatedAccount.setTransactions(bankAccount.getTransactions());

            return ResponseEntity.ok(bankAccount);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{account_code}")
    public ResponseEntity<String> deleteAccount(@PathVariable String code)
    {
        BankAccount toDelete = bankAccountRepository.findBankAccountByCode(code);

        if (toDelete != null)
            bankAccountRepository.delete(toDelete);
        return ResponseEntity.ok("Account Deleted");
    }

    @GetMapping("/account/transactions")
    public ResponseEntity<List<BankTransaction>> getTransactionsByBankAccount(@RequestBody BankAccount account)
    {
        List<BankTransaction> transactions = account.getTransactions();

        return ResponseEntity.ok(transactions);
    }
}
