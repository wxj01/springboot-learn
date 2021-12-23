package com.wxj.springboot.multi.jap.datasource.multi.jpa.datasource;

import com.wxj.springboot.multi.jap.datasource.multi.jpa.datasource.model.Product;
import com.wxj.springboot.multi.jap.datasource.multi.jpa.datasource.product.dao.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/12/23 0023 9:04
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductDataSourcesTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional("productTransactionManager")
    public void selectProduct(){
        Product product = productRepository.getById(1);
        System.out.println(product);
    }


    @Test
    @Transactional("productTransactionManager")
    public void saveProduct(){
        Product product = new Product(2, "book",  20.0);
        product = productRepository.save(product);
        assertNotNull(productRepository.getById(2));
    }
}