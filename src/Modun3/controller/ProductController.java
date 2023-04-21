package Modun3.controller;

import Modun3.config.Config;
import Modun3.model.product.Product;
import Modun3.service.Product.ProductServiceIMPL;
import Modun3.service.Product.iProductService;


import java.util.List;

public class ProductController {
    public iProductService productService = new ProductServiceIMPL();

    public List<Product> getListProduct(){
        return productService.findAll();
    }
    public void createProduct(Product product) {
        productService.save(product);
    }
    public List<Product> searchProductByName() {
        System.out.println("Enter the ProductName you want to search");
        String searchName = Config.scanner().nextLine();
        return productService.searchProduct(searchName);
    }
    public void editStatusProductById(){
        System.out.println("Enter ID status you want to change");
        int idStatus = Config.scanner().nextInt();
        productService.editStatusProductById(idStatus);
    }
    public void deleteProductById(){
        System.out.println("Enter ID you want to delete");
        int idDelete = Config.scanner().nextInt();
        productService.deleteById(idDelete);
    }
    public void updateProduct(Product product){
        productService.save(product);
    }
    public Product detailProduct(int id){
       return productService.findById(id);
    }
}
