package ru.geekbrains.boot.controllers;

import ru.geekbrains.boot.model.Product;
import ru.geekbrains.boot.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping // GET http://localhost:8189/market/products
    public String showAll(Model model,
                          @RequestParam(required = false, name = "min_cost") Integer minCost,
                          @RequestParam(required = false, name = "max_cost") Integer maxCost
    ) {
        model.addAttribute("products", productService.findAll(minCost, maxCost));
        return "products";
    }

    @GetMapping("/saveOrUpdate")
    public String saveOrUpdate(Model model,
                               @RequestParam(required = false, name = "id") Long id,
                               @RequestParam(required = false, name = "title") String title,
                               @RequestParam(required = false, name = "cost") Integer cost
    ) {
        if (id == null || title.equals("") || cost == 0) {
            return "redirect:/products";
        }
        productService.saveOrUpdate(id, title, cost);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id, HttpServletResponse response) {
        productService.deleteBydId(id);
        return "redirect:/products"; // [http://localhost:8189/app]/products
    }
}
