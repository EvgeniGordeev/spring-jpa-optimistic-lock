package sample.data.jpa.service;

import sample.data.jpa.domain.Account;

/**
 * @author NikolaiSinyakevich
 * @version 4.0
 * @since 2015-04-23
 */
public interface AccountRepositoryCustom {

    void refreshWithPessimisticLock(Account account);

}
