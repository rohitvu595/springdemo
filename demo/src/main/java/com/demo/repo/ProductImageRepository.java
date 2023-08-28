package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.dao.ProductImage;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long>{

}
