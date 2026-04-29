package com.unpam.pos.service;

import com.unpam.pos.model.Transaction;
import com.unpam.pos.model.TransactionDetail;
import com.unpam.pos.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction save(Transaction transaction) {
        // Set tanggal otomatis
        transaction.setTransactionDate(LocalDateTime.now());

        // Hitung total otomatis dari details
        if (transaction.getDetails() != null) {
            double total = 0;
            for (TransactionDetail detail : transaction.getDetails()) {
                detail.setTransaction(transaction);
                double subtotal = detail.getProduct().getPrice() * detail.getQuantity();
                detail.setSubtotal(subtotal);
                total += subtotal;
            }
            transaction.setTotalAmount(total);
        }

        return transactionRepository.save(transaction);
    }

    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> getByCustomer(Long customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }
}