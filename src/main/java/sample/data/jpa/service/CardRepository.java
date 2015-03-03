package sample.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.data.jpa.domain.Card;

/**
 * @author eg
 * @version 4.0
 * @since 2015-03-03
 */
public interface CardRepository extends JpaRepository<Card, Integer> {
    Card findFirstByHashId(String hashId);
}
