package controllers;

import lombok.AllArgsConstructor;
import models.BankTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.BankTransactionRepository;

@RequestMapping("/api/transactions")
@AllArgsConstructor
public class TransactionController {
    private BankTransactionRepository bankTransactionRepository;

    @GetMapping("transaction/{transaction_id}")
    public ResponseEntity<BankTransaction> getTransaction(@PathVariable Long id)
    {
        BankTransaction bankTransaction = bankTransactionRepository.findBankTransactionById(id);

        if ( bankTransaction != null)
            return ResponseEntity.ok(bankTransaction);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BankTransaction> createTransaction(@RequestBody BankTransaction transaction)
    {
        BankTransaction bankTransaction = bankTransactionRepository.save(transaction);

        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }
}
