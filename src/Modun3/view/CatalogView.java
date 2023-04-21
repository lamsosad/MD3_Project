package Modun3.view;

import Modun3.config.Config;
import Modun3.controller.CatalogController;
import Modun3.model.product.Catalog;

import java.util.List;

public class CatalogView {
    public void CatalogViewNavBar() {
        System.out.println("**************************** CATEGORY MANAGE *******************************");
        String alignFormat = "| %-4d | %-15s | %-4d | %-15s | %-4d | %-15s |%n";
        System.out.format("+------+-----------------+------+-----------------+------+-----------------+%n");
        System.out.format(alignFormat, 1, "Show Category", 2, "Create Category", 3, "Update Category");
         System.out.format("+------+-----------------+------+-----------------+------+-----------------+%n");
        System.out.println("| 4    | Delete Category | 5    |   Home          |      |                  |");
        System.out.format("+------+-----------------+------+-----------------+------+-----------------+%n");
        System.out.println("****************************************************************************");

        int chooseMenu = Config.scanner().nextInt();
        switch (chooseMenu) {
            case 1:
                showFormCatalogList();
                System.out.println("Enter 'back' to return Menu: ");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    CatalogViewNavBar();
                }
                break;
            case 2:
                formCreateCatalog();
                break;
            case 3:
                showFormCatalogList();
                formUpdateCatalog();
                break;
            case 4:
                formDeleteCatalog();
                break;
            case 5:
                new ProfileView();
        }
    }

    CatalogController catalogController = new CatalogController();
    List<Catalog> catalogList = catalogController.getListCatalog();

    public void showFormCatalogList() {
        String alignFormat = "| %-15s | %-4d |%n";
        System.out.format("+------------------------+%n");
        System.out.format("| Catalog name    | ID   |%n");
        System.out.format("+-----------------+------+%n");
        for (int i = 0; i < catalogList.size(); i++) {

            System.out.format(alignFormat, catalogList.get(i).getName(), catalogList.get(i).getId());

        }
        System.out.format("+------------------------+%n");

    }

    private void backMenu() {
        System.out.println("Enter the any key to continue or Enter 'back' to return Menu: ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            CatalogViewNavBar();
        }
    }

    public void formCreateCatalog() {
        while (true) {
            int id = 0;
            if (catalogList.size() == 0) {
                id = 1;
            } else {
                id = catalogList.get(catalogList.size() - 1).getId() + 1;
            }
            System.out.println("Enter the name: ");
            String name = Config.scanner().nextLine();
            Catalog catalog = new Catalog(id, name);
            catalogController.createCatalogToDB(catalog);
            System.out.println("Create Success!");
            backMenu();
        }
    }

    public void formUpdateCatalog() {

        while (true) {
            System.out.println("Enter the id to update: ");
            int id = Config.scanner().nextInt();
            if (catalogController.detailCatalog(id) == null) {
                System.err.println("Id not found! Please try again!");
//                id = Config.scanner().nextInt();
            } else {
                System.out.println("Enter the name: ");
                String name = Config.scanner().nextLine();
                Catalog catalog = new Catalog(id, name);
                catalogController.updateCatalog(catalog);
                System.out.println("Update success!");
            }
            backMenu();
        }
    }

    public void formDeleteCatalog() {
        while (true) {
            System.out.println("Enter the id to delete: ");
            int id = Config.scanner().nextInt();
            if (catalogController.detailCatalog(id) == null) {
                System.err.println("Id not found! Please try again!");
            } else {
                catalogController.deleteCatalog(id);
                System.out.println("Delete success!");
            }
            backMenu();
        }
    }
}
