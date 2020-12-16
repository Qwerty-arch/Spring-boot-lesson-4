package ru.geekbrains.boot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.boot.model.Product;
import ru.geekbrains.boot.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAll(Integer minCost, Integer maxCost) {
        List<Product> out = findAll();
        if (minCost != null) {
            out = out.stream().filter(s -> s.getCost() >= minCost).collect(Collectors.toList());
        }
        if (maxCost != null) {
            out = out.stream().filter(s -> s.getCost() <= maxCost).collect(Collectors.toList());
        }
        return out;
    }

  /*  public Product saveOrUpdate(Product product) {

        return productRepository.saveOrUpdate(product);
    }*/

    public Product saveOrUpdate(Long id, String title, int cost) {
        Product product = new Product(id,title,cost);
        return productRepository.saveOrUpdate(product);
    }

    public void deleteBydId(Long id) {
        productRepository.deleteById(id);
    }
}

