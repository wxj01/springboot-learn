package com.wxj.springboot.multi.jap.datasource.multi.jpa.datasource.product.dao;

import com.wxj.springboot.multi.jap.datasource.multi.jpa.datasource.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/12/23 0023 8:53
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
