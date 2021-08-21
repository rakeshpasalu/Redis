package com.redisExample.demo.repository;

import com.redisExample.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableRedisRepositories
public class ProductRepo {
    @Autowired
    private RedisTemplate template;

    public static final  String HASH_KEY = "Product";
    public Product save(Product product){
        template.opsForHash().put(HASH_KEY,product.getId(),product);
        return product;
    }

    public List<Product> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }
    public Product findProdctById(int id){
        System.out.println("called findProductById() from DB");
        return (Product) template.opsForHash().get(HASH_KEY,id);
    }
    public String deleteProduct(int id){
        template.opsForHash().delete(HASH_KEY,id);
        return "product id: "+id+ " removed";
    }
}
