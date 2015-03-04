package sample.data.jpa.service;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.data.jpa.domain.Account;
import sample.data.jpa.domain.Card;
import sample.data.jpa.domain.Order;
import sample.data.jpa.domain.Transaction;

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
    private AccountRepository accountRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ApplicationContext ac;

    @Autowired
    private TaskExecutor taskExecutor;

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
        Order order = orderRepository.findOne(orderId);
        order.setLastUpdateTimestamp(new Date());
        order.setStatus("STARTED");
        Transaction transaction = new Transaction();
        transaction.setOrder(order);
        transaction.setPresetAmount(order.getOrderAmount());
        transaction.setDeviceId(order.getCard().getHashId());
        transaction.setStartTimestamp(new Date());
        orderRepository.save(order);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction stopTx(Long dispenseId, final BigDecimal txAmount) {
        final Transaction transaction = transactionRepository.findOne(dispenseId);
        transaction.setEndTimestamp(new Date());
        transaction.setTxAmount(txAmount);

        final Card card = cardRepository.findFirstByHashId(transaction.getDeviceId());

        taskExecutor.execute(ac.getBean(TxTask.class, card.getAccount().getId(), txAmount));

        transaction.getOrder().setStatus("COMPLETED");

        orderRepository.save(transaction.getOrder());
        return transactionRepository.save(transaction);
    }

    @Override
    public Order saveOrder(Order order) {
        Card card = cardRepository.findFirstByHashId(order.getHashId());
        Validate.notNull(card);
        order.setCard(card);
        return orderRepository.save(order);
    }

    @Override
    public Card addOrder(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Account load(Integer id) {
        return accountRepository.findOne(id);
    }

    interface TxTask extends Runnable{}

    @Transactional
    @Component
    @Scope("prototype")
    static class TxTaskImpl implements TxTask {
        Integer accountId;
        BigDecimal txAmount;
        @Autowired
        AccountRepository accountRepository;

        @Autowired
        TxTaskImpl(Integer accountId, BigDecimal txAmount) {
            this.accountId = accountId;
            this.txAmount = txAmount;
        }

        @Override
        public void run() {
            Account account = accountRepository.findOne(accountId);
            //substract tx amount from account balance
            BigDecimal currentVol = account.getCurrentVolume();
            account.setCurrentVolume(currentVol.subtract(txAmount));
            account.setCurrentVolumeTimestamp(new Date());
            accountRepository.save(account);
        }
    }

}
