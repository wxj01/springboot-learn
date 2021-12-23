package com.wxj.springboot.multi.jap.datasource.multi.jpa.datasource;

import com.wxj.springboot.multi.jap.datasource.multi.jpa.datasource.customer.dao.CustomerRepository;
import com.wxj.springboot.multi.jap.datasource.multi.jpa.datasource.model.Customer;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/12/23 0023 9:49
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerDataSourcesTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Transactional("customerTransactionManager")
    public void selectCustomer(){
        Customer customer = customerRepository.getById(1);
        System.out.println(customer);
    }
}