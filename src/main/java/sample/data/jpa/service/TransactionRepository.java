package sample.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sample.data.jpa.domain.Transaction;
import sample.data.jpa.domain.Card;
import sample.data.jpa.domain.Account;
import sample.data.jpa.domain.Order;

import java.util.List;
import java.util.LongSummaryStatistics;

/**
 * No separation of concerns here, sorry, Design.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT m FROM Order m WHERE card.hashId = ?1 AND status IS NULL")
    List<Order> findOrderByHashId(String hashId);
}