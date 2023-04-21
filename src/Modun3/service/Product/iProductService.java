package Modun3.service.Product;

import Modun3.model.product.Product;
import Modun3.service.iGenericService;

import java.util.List;

public interface iProductService extends iGenericService<Product> {
     List<Product> searchProduct(String name);

    void editStatusProductById(int id);
}
