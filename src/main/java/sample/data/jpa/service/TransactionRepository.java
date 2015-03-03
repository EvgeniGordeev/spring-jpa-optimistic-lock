package sample.data.jpa.service;

import sample.data.jpa.domain.Transaction;
import sample.data.jpa.domain.Card;
import sample.data.jpa.domain.Account;
import sample.data.jpa.domain.Order;

import java.util.List;

/**
 * No separation of concerns here, sorry, Design.
 */
public interface TransactionRepository {

    List<Transaction> findAll();

    Order findTxById(Integer txId);

    Card findCardByHashId(String hashId);

    List<Order> findOrderByHashId(String hashId);

    Transaction findById(Long txId);

    Order save(Order order);

    Card save(Card card);

    Transaction save(Transaction tx);

    Account save(Account account);

    List<Order> retrieveOrders();
}