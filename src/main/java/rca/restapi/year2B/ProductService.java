package rca.restapi.year2B;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public void createProduct(Product product) {
        repository.save(product);
    }

    public Product updateProduct(int id, Product updatedProduct) {
        Optional<Product> existing = repository.findById(id);
        if (existing.isPresent()) {
            Product product = existing.get();
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            return repository.save(product);
        }
        return null;
    }

    public void deleteProduct(int id) {
        repository.deleteById(id);
    }

    public Product getProductById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Product> getProductByPrice(int price) {
        return repository.findByProductPrice(price);
    }

    public List<Product> getProductByName(String name) {
        return repository.findByProductName(name);
    }

    public List<Product> getAllProductsSorted(String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();
        return repository.findAll(sort);
    }

    public Page<Product> getAllProductsPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
