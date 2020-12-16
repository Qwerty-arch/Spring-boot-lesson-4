package ru.geekbrains.boot.repositories;



import ru.geekbrains.boot.model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>(Arrays.asList(
                new Product(1L,"American bobtail cat",1010),
                new Product(2L,"British shorthair cat",7020),
                new Product(3L,"Persian cat",3030),
                new Product(4L,"Norwegian forest cat",4040),
                new Product(5L,"Savanna cat",5550 )
        ));

    }

    public Product saveOrUpdate(Product product) {
        if (product.getId() != null) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(product.getId())) {
                    products.set(i, product);
                    return product;
                }
            }
        }

        Long newId = products.stream().mapToLong(Product::getId).max().orElseGet(() -> 0L) + 1L;
        product.setId(newId);
        products.add(product);
        return product;
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> findById(Long id) {
        return products.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    public void deleteById(Long id) {
        products.removeIf(s -> s.getId().equals(id));
    }
}
