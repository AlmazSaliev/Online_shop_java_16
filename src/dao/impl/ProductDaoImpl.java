package dao.impl;

import dao.ProductDao;
import db.DataBase;
import models.Product;

import java.util.Arrays;

public class ProductDaoImpl implements ProductDao {
    @Override
    public void AddNewProduct(Product product) {
        DataBase.products = Arrays.copyOf(DataBase.products, DataBase.products.length + 1);
        DataBase.products[DataBase.products.length - 1] = product;
    }

    @Override
    public Product[] getAllProduct() {
        return DataBase.products;
    }

    @Override
    public Product getOneProductById(long id) {
        Product[] allProduct = getAllProduct();
        Product findProduct = null;
        for (Product product : allProduct) {
            if (product.getId() == id) {
                findProduct = product;
            }
        }
        return findProduct;
    }

    @Override
    public boolean updateProduct(long id, Product product) {
        boolean isUpdate = false;
        for (Product prod : getAllProduct()) {
            if (prod.getId() == id) {
                prod.setCategory(product.getCategory());
                prod.setQuantity(product.getQuantity());
                prod.setColor(product.getColor());
                prod.setName(product.getName());
                prod.setPrice(product.getPrice());
                prod.setSize(product.getSize());
                prod.setImageUrl(product.getImageUrl());
                isUpdate = true;
            } else {
                isUpdate = false;
            }
        }
        return isUpdate;
    }

    @Override
    public boolean deleteProductById(long id) {
        boolean isDelete = false;
        Product[] products = new Product[0];
        for (Product product : DataBase.products) {
            if (product.getId() == id) {
                isDelete = true;
                if (product.getId() != id) {
                    products = Arrays.copyOf(products, products.length + 1);
                    products[products.length - 1] = product;
                }
            } else {
                isDelete = false;
            }
        }
        DataBase.products = products;
        return isDelete;
    }
}
