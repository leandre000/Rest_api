package rca.restapi.year2B;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    @Query("SELECT p FROM Product p WHERE p.price = :productPrice")
    List<Product> findByProductPrice(@Param("productPrice") int productPrice);
    @Query("SELECT p FROM Product p WHERE p.name = :productName")
    List<Product> findByProductName(@Param("productName") String productName);
}
