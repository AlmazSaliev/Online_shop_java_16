package dao;

import models.Product;

public interface ProductDao {
    //add bew product
    void AddNewProduct(Product product);
    //get All Product
    Product[] getAllProduct();
    //get one product by id
    Product getOneProductById(long id);
    //update product by id
    boolean updateProduct(long id, Product product);
    //delete product by id
    boolean deleteProductById(long id);
}
