package Modun3.view;

import Modun3.config.Config;
import Modun3.controller.CatalogController;
import Modun3.controller.ProductController;
import Modun3.model.product.Catalog;
import Modun3.model.product.Product;

import java.util.List;

public class ProductView {
    CatalogController catalogController = new CatalogController();
    ProductController productController = new ProductController();
    List<Product> productList = productController.getListProduct();

    public void ProductNavbar() {
        System.out.println("\n******************************** PRODUCT ***********************************");
        String alignFormat = "| %-4d | %-15s | %-4d | %-15s | %-4d | %-15s |%n";
        System.out.format("+------+-----------------+------+-----------------+------+-----------------+%n");
        System.out.format(alignFormat, 1, "Show product", 2, "Create product", 3, "Search by name");
        System.out.format("+------+-----------------+------+-----------------+------+-----------------+%n");
        System.out.format(alignFormat, 4, "Edit status", 5, "Delete product", 6, "Update product");
        System.out.format("+------+-----------------+------+-----------------+------+-----------------+%n");
        System.out.println("| 7    | Home            |                                                 |");
        System.out.format("+------+-----------------+------+-----------------+------+-----------------+%n");
        System.out.println("****************************************************************************");
        int chooseMenu = Config.scanner().nextInt();
        switch (chooseMenu) {
            case 1:
                showProduct();
                ProductNavbar();
                break;
            case 2:
                createProduct();
                break;
            case 3:
                searchProductByName();
                ProductNavbar();
                break;
            case 4:
                editStatusProduct();
                break;
            case 5:
                deleteProduct();
                break;
            case 6:
                updateProduct();
                break;
            case 7:
                new ProfileView();
                break;

        }
    }

    private void deleteProduct() {
        showProduct();
        productController.deleteProductById();
        ProductNavbar();
    }

    public void showProduct() {
        System.out.println("************************************ SHOW LIST PRODUCT ***********************************");
        String alignFormat = "| %-4d | %-15s | %-15s | %-15s | %-7s | %-15s |%n";
        System.out.format("+------+-----------------+-----------------+-----------------+---------+-----------------+%n");
        System.out.format("|  ID  |   PRODUCT NAME  |      COLOR      |      STATUS     |  PRICE  |      CATALOG    |%n");
        System.out.format("+------+-----------------+-----------------+-----------------+---------+-----------------+%n");
        for (Product product : productList) {
            System.out.format(alignFormat,
                    product.getProductId(), product.getProductName(), product.getColor(),
                    (product.isProductStatus() ? "Stocking" : "Sold out"),
                    product.getPrice(), (product.getCatalog().getId() + ". " + product.getCatalog().getName()));
        }
        System.out.format("+------+-----------------+-----------------+-----------------+---------+-----------------+%n");
        System.out.println("******************************************************************************************");
    }

    public void createProduct() {
        while (true) {
            int id = 0;
            if (productList.size() == 0) {
                id = 1;
            } else {
                id = productList.get(productList.size() - 1).getProductId() + 1;
            }
            boolean status = true;
            System.out.println("Enter the product name");
            String productName = Config.scanner().nextLine();
            System.out.println("Enter the product title");
            String title = Config.scanner().nextLine();
            System.out.println("Enter the product color");
            String color = Config.scanner().nextLine();
            System.out.println("Enter the product price");
            double price = Config.scanner().nextDouble();
            Catalog catalog = selectCatalog();
            Product product = new Product(id, productName, title, color, price, catalog, status);
            productController.createProduct(product);
            System.out.println("Enter the any key to continue or Enter 'back' to return Menu: ");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                ProductNavbar();
                break;
            }
        }
    }

    public Catalog selectCatalog() {
        new CatalogView().showFormCatalogList();
        System.out.println("Enter the catalog");
        int idCatalog = Config.scanner().nextInt();
        Catalog catalog = catalogController.detailCatalog(idCatalog);
        if (catalog == null) {
            System.err.println("Invalid Id! Please try again!");
            selectCatalog();
        }
        return catalog;
    }

    public void searchProductByName() {
        System.out.println(productController.searchProductByName());

    }

    public void editStatusProduct() {
        productController.editStatusProductById();
        ProductNavbar();
    }

    public void updateProduct() {
        while (true) {
            System.out.println("Enter ID you want to update");
            int id = Config.scanner().nextInt();
            if (productController.detailProduct(id) == null) {
                System.err.println("ID not found! try again!");
            } else {
                boolean status = true;
                System.out.println("Enter the product name");
                String productName = Config.scanner().nextLine();
                System.out.println("Enter the product title");
                String title = Config.scanner().nextLine();
                System.out.println("Enter the product color");
                String color = Config.scanner().nextLine();
                System.out.println("Enter the product price");
                double price = Config.scanner().nextDouble();
                Catalog catalog = selectCatalog();
                Product product = new Product(id, productName, title, color, price, catalog, status);
                productController.updateProduct(product);
                System.out.println("Update success");
                System.out.println("Enter the any key to continue or Enter 'back' to return Menu: ");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    ProductNavbar();
                    break;
                }
            }
        }

    }

}
