/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.data.jpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import sample.data.jpa.domain.Account;
import sample.data.jpa.domain.Card;
import sample.data.jpa.domain.Order;
import sample.data.jpa.domain.Transaction;
import sample.data.jpa.service.TransactionService;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class SampleController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/order/{hashId}", method = RequestMethod.GET)
    public Order getFirstAvailableOrder(@PathVariable String hashId) {
        return transactionService.getOrderByHashId(hashId);
    }

    @JsonView(View.Summary.class)
    @RequestMapping(value = "/tx/{orderId}/start", method = RequestMethod.POST)
    public Transaction startTx(@PathVariable Integer orderId) {
        return transactionService.startTx(orderId);
    }

    @JsonView(View.Summary.class)
    @RequestMapping(value = "/tx/{txId}/stop/{amount}", method = RequestMethod.POST)
    public Transaction stopTx(@PathVariable Long txId, @PathVariable BigDecimal amount) {
        return transactionService.stopTx(txId, amount);
    }

    @RequestMapping(value = "/order/add", method = RequestMethod.POST)
    public String addOrder(@RequestBody Order order) {
        Order result = transactionService.saveOrder(order);
        if (result.getId() > 0) {
            return "SUCCESS";
        } else {
            return "FAILED";
        }
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public List<Order> getOrders() {
        return transactionService.getOrders();
    }


    @RequestMapping(value = "/card/add", method = RequestMethod.POST)
    public String addMeter(@RequestBody Card card) {
        Card result = transactionService.addOrder(card);
        if (result.getId() > 0) {
            return "SUCCESS";
        } else {
            return "FAILED";
        }
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable Integer id) {
        return transactionService.load(id);
    }


}
