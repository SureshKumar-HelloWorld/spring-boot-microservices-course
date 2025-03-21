package com.suresh.bookstore.catlog.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
// import org.testcontainers.utility.TestcontainersConfiguration;

@DataJpaTest(
        properties = {
            "spring.test.database.replace=none",
            "spring.datasource.url=jdbc:tc:postgresql:latest:///db",
        })
// @Import(TestcontainersConfiguration.class)
@Sql("/test-data.sql")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldGetAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        assertThat(products).hasSize(14);
    }

    @Test
    void shouldGetProductByCode(){
        ProductEntity product= productRepository.findByCode("P100").orElseThrow();
        assertThat(product.getCode()).isEqualTo("P100");
        assertThat(product.getName()).isEqualTo("Confabulations");
        assertThat(product.getDescription()).isEqualTo("'Language is a body, a living creature ... and this creature's home is the inarticulate as well as the articulate'. John Berger's work has revolutionized the way we understand visual language. In this new book he writes about language itself, and how it relates to thought, art, song, storytelling and political discourse today. Also containing Berger's own drawings, notes, memories and reflections on everything from Albert Camus to global capitalism, Confabulations takes us to what is 'true, essential and urgent'.");
        assertThat(product.getPrice()).isEqualTo(new BigDecimal("25.00"));

    }

    @Test
    void shouldReturnEmptyWhenProductCodeNotExists(){
        assertThat(productRepository.findByCode("Invalid_product_code")).isEmpty();
    }
}
