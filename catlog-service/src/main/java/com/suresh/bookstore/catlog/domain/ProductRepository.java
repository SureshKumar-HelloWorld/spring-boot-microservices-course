package com.suresh.bookstore.catlog.domain;

import org.springframework.data.jpa.repository.JpaRepository;


 interface ProductRepository extends JpaRepository<ProductEntity,Long>{

}

