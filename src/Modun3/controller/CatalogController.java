package Modun3.controller;

import Modun3.model.product.Catalog;
import Modun3.service.catalog.CatalogServiceIMPL;
import Modun3.service.catalog.iCatalogService;

import java.util.List;

public class CatalogController {
    public iCatalogService catalogService=new CatalogServiceIMPL();
    public List<Catalog> getListCatalog(){
        return catalogService.findAll();
    }
    public void createCatalogToDB(Catalog catalog){
        catalogService.save(catalog);
    }
    public void updateCatalog(Catalog catalog){
        catalogService.save(catalog);
    }
    public Catalog detailCatalog(int id){
        return catalogService.findById(id);
    }
    public void deleteCatalog(int id){catalogService.deleteById(id);}

}
