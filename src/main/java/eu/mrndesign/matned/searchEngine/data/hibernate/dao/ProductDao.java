package eu.mrndesign.matned.searchEngine.data.hibernate.dao;

import eu.mrndesign.matned.searchEngine.data.hibernate.entity.Product;

import java.util.Arrays;
import java.util.List;

public class ProductDao implements DaoInterface<Product> {
    @Override
    public List<Product> find() {
        return null;
    }

    @Override
    public List<String> listOfFields() {
        return Arrays.asList("NUMBER::Id","VARCHAR::Name","NUMBER::Value","NUMBER::Project details id");
    }
}
