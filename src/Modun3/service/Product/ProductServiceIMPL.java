package Modun3.service.Product;

import Modun3.config.Config;
import Modun3.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceIMPL implements iProductService {
    List<Product> productList = new Config<Product>().readFromFile(Config.PATH_PRODUCT);

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public void save(Product product) {
        if (findById(product.getProductId()) == null) {
            productList.add(product);
        }else {
            productList.set(productList.indexOf(findById(product.getProductId())),product);
        }
        new Config<Product>().writeFromFile(Config.PATH_PRODUCT, productList);
    }

    @Override
    public Product findById(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId() == id) {
                return productList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId() == id) {
                productList.remove(productList.get(i));
            }
        }
        new Config<Product>().writeFromFile(Config.PATH_PRODUCT, productList);
    }

    @Override
    public List<Product> searchProduct(String name) {
        List<Product> productListSearch = new ArrayList<>();
        for (Product product : productList) {
            if (product.getProductName().toLowerCase().contains(name.toLowerCase())) {
                productListSearch.add(product);
            }
        }
        return productListSearch;
    }

    @Override
    public void editStatusProductById(int id) {
        System.out.println("Enter status you want to change");
        boolean newStatus = Config.scanner().nextBoolean();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId() == id) {
                productList.get(i).setProductStatus(newStatus);
                break;
            }
        }
        new Config<Product>().writeFromFile(Config.PATH_PRODUCT, productList);
    }


}
