package sample.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.data.jpa.domain.Order;

/**
 * @author eg
 * @version 4.0
 * @since 2015-03-03
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
