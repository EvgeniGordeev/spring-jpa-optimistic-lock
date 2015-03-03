package sample.data.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sample.data.jpa.domain.Account;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

/**
 * @author EvgeniGordeev
 * @since 3/2/2015.
 */
@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    private EntityManager em;

    @Override
    public Account findOne(Integer id) {
        return em.find(Account.class, id);
    }

    @Override
    public Account getLockedAccount(Integer id) {
        final Account account = findOne(id);
        em.lock(account, LockModeType.PESSIMISTIC_WRITE);
        return account;
    }

    @Override
    public Account save(Account account) {
        return em.merge(account);
    }
}
