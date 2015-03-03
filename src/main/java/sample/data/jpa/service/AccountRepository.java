package sample.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import sample.data.jpa.domain.Account;

import javax.persistence.LockModeType;

/**
 * @author EvgeniGordeev
 * @since 3/2/2015.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Account findOne(Integer integer);
}
