package sample.data.jpa.service;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.data.jpa.domain.Transaction;
import sample.data.jpa.domain.Card;
import sample.data.jpa.domain.Account;
import sample.data.jpa.domain.Order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author EvgeniGordeev
 * @since 2/27/2015.
 */
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Order getOrderByHashId(String hashId) {
        List<Order> orders = transactionRepository.findOrderByHashId(hashId);
        if (orders.isEmpty()) {
            return null;
        } else {
            return orders.iterator().next();
        }
    }

    @Override
    public Transaction startTx(Integer orderId) {
        Order order = transactionRepository.findTxById(orderId);
        order.setLastUpdateTimestamp(new Date());
        order.setStatus("STARTED");
        Transaction transaction = new Transaction();
        transaction.setOrder(order);
        transaction.setPresetAmount(order.getOrderAmount());
        transaction.setDeviceId(order.getCard().getHashId());
        transaction.setStartTimestamp(new Date());
        transactionRepository.save(order);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction stopTx(Long dispenseId, BigDecimal txAmount) {
        Transaction transaction = transactionRepository.findById(dispenseId);
        transaction.setEndTimestamp(new Date());
        transaction.setTxAmount(txAmount);
        Card card = transactionRepository.findCardByHashId(transaction.getDeviceId());
        Account account = accountRepository.findOne(card.getAccount().getId());
        BigDecimal currentVol = account.getCurrentVolume();
        account.setCurrentVolume(currentVol.subtract(txAmount));
        account.setCurrentVolumeTimestamp(transaction.getEndTimestamp());
        transaction.getOrder().setStatus("COMPLETED");

        transactionRepository.save(account);
        transactionRepository.save(transaction.getOrder());
        return transactionRepository.save(transaction);
    }

    @Override
    public Order saveOrder(Order order) {
        Card card = transactionRepository.findCardByHashId(order.getHashId());
        Validate.notNull(card);
        order.setCard(card);
        return transactionRepository.save(order);
    }

    @Override
    public Card addOrder(Card card) {
        return transactionRepository.save(card);
    }

    @Override
    public List<Order> getOrders() {
        return transactionRepository.retrieveOrders();
    }
}
