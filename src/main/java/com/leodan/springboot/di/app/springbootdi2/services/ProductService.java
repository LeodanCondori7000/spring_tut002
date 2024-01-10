package com.leodan.springboot.di.app.springbootdi2.services;

import java.util.List;
import java.util.stream.Collectors;

import com.leodan.springboot.di.app.springbootdi2.models.Product;
import com.leodan.springboot.di.app.springbootdi2.repositories.ProductRepository;

public class ProductService {

  ProductRepository repository = new ProductRepository();

  public List<Product> findAll() {
    // return repository.findAll();

    // commented out code(better performance and memory usage)
    // return repository.findAll().stream().map(p -> {
    //   Double priceImp = p.getPrice() * 1.25d;
    //   p.setPrice(priceImp.longValue());
    //   return p;
    // }).collect(Collectors.toList());

    // improved version for the previous commented out code(complies with inmutability)
    // return repository.findAll().stream().map(p -> {
    //   Double priceImp = p.getPrice() * 1.25d;
    //   Product newProd = new Product(p.getId(),p.getName(),priceImp.longValue());
    //   return newProd;
    // }).collect(Collectors.toList());

    // a third option
    return repository.findAll().stream().map(p -> {
      Double priceTax = p.getPrice() * 1.25d;
      Product newProd = (Product) p.clone();
      newProd.setPrice(priceTax.longValue());
      return newProd;
    }).collect(Collectors.toList());
  }

  public Product findById(Long id) {
    return repository.findById(id);
  }
}
