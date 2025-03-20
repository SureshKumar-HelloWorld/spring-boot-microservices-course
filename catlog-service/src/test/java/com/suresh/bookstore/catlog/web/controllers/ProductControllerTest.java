package com.suresh.bookstore.catlog.web.controllers;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import com.suresh.bookstore.catlog.AbstractIT;
import com.suresh.bookstore.catlog.domain.Product;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

@Sql("/test-data.sql")
class ProductControllerTest extends AbstractIT {

    @Test
    void shouldReturnProducts() {
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("data", hasSize(10))
                .body("totalElements", is(14))
                .body("pageNumber", is(1))
                .body("totalPages", is(2))
                .body("isFirst", is(true))
                .body("isLast", is(false))
                .body("hasNext", is(true))
                .body("hasPrevious", is(false));
    }

    @Test
    void shouldGetProductByCode(){
        Product product = given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}","P100")
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(Product.class);

        assertThat(product.code()).isEqualTo("P100");
        assertThat(product.name()).isEqualTo("Confabulations");
        assertThat(product.description()).isEqualTo("'Language is a body, a living creature ... and this creature's home is the inarticulate as well as the articulate'. John Berger's work has revolutionized the way we understand visual language. In this new book he writes about language itself, and how it relates to thought, art, song, storytelling and political discourse today. Also containing Berger's own drawings, notes, memories and reflections on everything from Albert Camus to global capitalism, Confabulations takes us to what is 'true, essential and urgent'.");
        assertThat(product.price()).isEqualTo(new BigDecimal("25.00"));

    }

    @Test
    void shouldReturnNotFoundProductCodeNotExits(){
        String code ="invalid_product_code";
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}",code)
                .then()
                .statusCode(404)
                .body("status", is(404))
                .body("title", is("Product Not Found"))
                .body("detail", is("Product with code " + code + " not found"));

    }
}
