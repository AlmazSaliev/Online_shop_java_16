package service;

import models.Product;

public interface ProductService extends GeneralService {
    // add
    void AddNewProduct();

    // get
    void GetAllProduct();

    //get by id
    void GetOneProductById();

    // update
    void UpdateProduct();

    // delete
    void DeleteProductById();
}
