package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.dao.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
