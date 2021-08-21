package com.redisExample.demo.controller;

import com.redisExample.demo.entity.Product;
import com.redisExample.demo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class RedisController {


    @Autowired
    ProductRepo productRepo;
    @PostMapping
    public Product save(@RequestBody Product product){
        return productRepo.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", value="Product",unless = "#result.price > 1000")
    public Product findProduct(@PathVariable int id){
        return productRepo.findProdctById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id){
        return productRepo.deleteProduct(id);
    }
}
