package sample.data.jpa.service;

import sample.data.jpa.domain.Transaction;
import sample.data.jpa.domain.Card;
import sample.data.jpa.domain.Order;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author EvgeniGordeev
 * @since 2/27/2015.
 */
public interface TransactionService {

    Order getOrderByHashId(String hashId);

    Transaction startTx(Integer orderId);

    Transaction stopTx(Long txId, BigDecimal txAmount);

    Order saveOrder(Order order);

    Card addOrder(Card card);

    List<Order> getOrders();

}
