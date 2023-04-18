package com.apex.eqp.inventory.helpers;

import com.apex.eqp.inventory.entities.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ProductFilterTest {

    @Test
    void removeRecalledFrom() {
        Set<String> recalledNames = new HashSet<>();
        recalledNames.add("recalled1");
        recalledNames.add("recalled2");
        recalledNames.add("recalled3");

        List<Product> products = new ArrayList<>();
        products.add(createTestProduct("Good Product 1"));
        products.add(createTestProduct("Good Product 2"));
        products.add(createTestProduct("Good Product 3"));
        products.add(createTestProduct("recalled1"));

        ProductFilter tested = new ProductFilter(recalledNames);
        List<Product> filteredProducts = tested.removeRecalledFrom(products);
        Set<String> resultSet = filteredProducts.stream().map(Product::getName).collect(Collectors.toSet());

        assertEquals(3, filteredProducts.size());
        assertFalse(resultSet.containsAll(recalledNames));
    }

    private Product createTestProduct(String productName) {
        return Product.builder()
                .name(productName)
                .build();
    }
}