package Modun3.service.catalog;

import Modun3.config.Config;
import Modun3.model.product.Catalog;

import java.util.List;

public class CatalogServiceIMPL implements iCatalogService {
    List<Catalog> catalogList = new Config<Catalog>().readFromFile(Config.PATH_CATALOG);


    @Override
    public List<Catalog> findAll() {
        return catalogList;
    }

    @Override
    public void save(Catalog catalog) {
        if (findById(catalog.getId()) == null) {
            catalogList.add(catalog);
        } else {
            catalogList.set(catalogList.indexOf(findById(catalog.getId())), catalog);
        }
        new Config<Catalog>().writeFromFile(Config.PATH_CATALOG, catalogList);
    }

    @Override
    public Catalog findById(int id) {
        for (int i = 0; i < catalogList.size(); i++) {
            if (catalogList.get(i).getId() == id) {
                return catalogList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < catalogList.size(); i++) {
            if (catalogList.get(i).getId() == id) {
                catalogList.remove(i);
                new Config<Catalog>().writeFromFile(Config.PATH_CATALOG, catalogList);
                return;
            }
        }
    }
}
