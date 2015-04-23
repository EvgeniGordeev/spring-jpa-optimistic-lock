package sample.data.jpa.service;

import sample.data.jpa.domain.Account;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

/**
 * Spring data JPA custom repository
 * http://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#repositories.custom-implementations
 *
 * @author NikolaiSinyakevich
 * @version 4.0
 * @since 2015-04-23
 */
public class AccountRepositoryImpl implements AccountRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void refreshWithPessimisticLock(Account account) {
        entityManager.refresh(account, LockModeType.PESSIMISTIC_WRITE);
    }

}
