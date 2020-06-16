package cn.enjoy.controller;


import cn.enjoy.pojo.Product;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProductController {

    @RequestMapping("/getProduct/{id}")
    public  Object  getProdcut(HttpServletRequest request, @PathVariable("id") String id) {
        Product product = new Product();
        product.setId(id);
        product.setName("product-"+request.getLocalPort());
        return product;
    }
}
