package sample.data.jpa.service;

import sample.data.jpa.domain.Account;

import java.math.BigDecimal;

/**
 * @author eg
 * @version 4.0
 * @since 2015-03-03
 */
public interface AccountRepository {
    Account findOne(Integer integer);

    Account getLockedAccount(Integer id);

    Account save(Account account);
}
