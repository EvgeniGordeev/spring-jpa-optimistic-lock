package sample.data.jpa.service;

import org.springframework.stereotype.Repository;
import sample.data.jpa.domain.Transaction;
import sample.data.jpa.domain.Card;
import sample.data.jpa.domain.Account;
import sample.data.jpa.domain.Order;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
class JpaTransactionRepository implements TransactionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Transaction> findAll() {
        return this.entityManager.createQuery("SELECT n FROM Dispense n", Transaction.class).getResultList();
    }

    @Override
    public Order findTxById(Integer txId) {
        return this.entityManager.find(Order.class, txId);
    }

    @Override
    public Order save(Order order) {
        return this.entityManager.merge(order);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return this.entityManager.merge(transaction);
    }

    @Override
    public Account save(Account account) {
        return this.entityManager.merge(account);
    }

    @Override
    public Transaction findById(Long txId) {
        return this.entityManager.find(Transaction.class, txId);
    }

    @Override
    public List<Order> findOrderByHashId(String hashId) {
        return this.entityManager.createQuery("SELECT m FROM Order m " +
                "WHERE card.hashId = ?1 AND status IS NULL", Order.class).
                setParameter(1, hashId).getResultList();
    }

    @Override
    public Card findCardByHashId(String hashId) {
        return this.entityManager.createQuery("SELECT m from Card m where hashId = ?1", Card.class)
                .setParameter(1, hashId).getSingleResult();
    }

    @Override
    public Card save(Card card) {
        return this.entityManager.merge(card);
    }

    @Override
    public List<Order> retrieveOrders() {
        return entityManager.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }
}
