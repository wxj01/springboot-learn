package com.wxj.springboot.multi.jap.datasource.multi.jpa.datasource.customer.dao;

import com.wxj.springboot.multi.jap.datasource.multi.jpa.datasource.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/12/22 0022 20:53
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
