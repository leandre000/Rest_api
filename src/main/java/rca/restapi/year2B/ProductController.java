package rca.restapi.year2B;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @PostMapping
    public void createProduct(@RequestBody Product product) {
        service.createProduct(product);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable int productId, @RequestBody Product updatedProduct) {
        return service.updateProduct(productId, updatedProduct);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable int productId) {
        service.deleteProduct(productId);
    }

    @GetMapping("/search/name/{productName}")
    public List<Product> getProductByName(@PathVariable String productName) {
        return service.getProductByName(productName);
    }

    @GetMapping("/search/price/{productPrice}")
    public List<Product> getProductByPrice(@PathVariable int productPrice) {
        return service.getProductByPrice(productPrice);
    }

    @GetMapping("/sort/name")
    public List<Product> getAllSortedByName(@RequestParam(defaultValue = "asc") String sortDir) {
        return service.getAllProductsSorted("name", sortDir);
    }

    @GetMapping("/sort/price")
    public List<Product> getAllSortedByPrice(@RequestParam(defaultValue = "asc") String sortDir) {
        return service.getAllProductsSorted("price", sortDir);
    }

    @GetMapping("/sorted")
    public List<Product> getAllSorted(
            @RequestParam(defaultValue = "name") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return service.getAllProductsSorted(sortField, sortDir);
    }

    @GetMapping("/paginated")
    public Page<Product> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.getAllProductsPaginated(pageable);
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable int productId) {
        return service.getProductById(productId);
    }
}
