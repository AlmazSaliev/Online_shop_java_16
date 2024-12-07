package service.impl;

import config.Config;
import dao.impl.ProductDaoImpl;
import enums.Category;
import enums.Size;
import models.Product;
import service.ProductService;

import java.math.BigDecimal;

public class ProductServiceImpl implements ProductService {
    ProductDaoImpl productDao = new ProductDaoImpl();
    UserServiceImpl userService = new UserServiceImpl();
    private String strPattern = "^[a-zA-Z\\s]+";
    private String numPattern = "\\d+";

    @Override
    public void AddNewProduct() {
        try {
            productDao.AddNewProduct(addNewProductF());
            System.out.println("Successfully add new product!");
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid value!");
        }
    }

    @Override
    public void GetAllProduct() {
        Product[] allProduct = productDao.getAllProduct();
        if (allProduct.length != 0) {
            System.out.println("All products: ");
            for (Product product : allProduct) {
                System.out.println(product);
            }
        } else {
            System.out.println("Product is empty!");
        }
    }

    @Override
    public void GetOneProductById() {
        try {
            String[] productId = Config.CheckValue("product id", numPattern);
            if (!Boolean.parseBoolean(productId[1])) {
                throw new RuntimeException("Invalid id!");
            } else {
                System.out.println(productDao.getOneProductById(Long.parseLong(productId[0])));
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid value!");
        }
    }

    @Override
    public void UpdateProduct() {
        try {
            String[] productId = Config.CheckValue("product id", numPattern);
            if (!Boolean.parseBoolean(productId[1])) {
                throw new RuntimeException("Invalid id!");
            } else {
                boolean isUpdate = productDao.updateProduct(Long.parseLong(productId[0]), addNewProductF());
                if (isUpdate) {
                    System.out.println("Product successfully updated!");
                } else {
                    System.out.println("Product is not update!");
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid value!");
        }
    }

    @Override
    public void DeleteProductById() {
        try {
            String[] productId = Config.CheckValue("product id", numPattern);
            if (!Boolean.parseBoolean(productId[1])) {
                throw new RuntimeException("Invalid id!");
            } else {
                boolean isDelete = productDao.deleteProductById(Long.parseLong(productId[0]));
                if (isDelete) {
                    System.out.println("Product successfully deleted!");
                } else {
                    System.out.println("Error on delete product!");
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid value!");
        }
    }

    @Override
    public void FilterByCategoryAndSize() {
        userService.FilterByCategoryAndSize();
    }

    public Product addNewProductF() {
        try {
            Product product = new Product();
            //name
            String[] name = Config.CheckValue("product name", strPattern);
            if (!Boolean.parseBoolean(name[1])) {
                throw new RuntimeException("Invalid value!");
            } else {
                product.setName(name[0]);
            }
            //price
            String[] price = Config.CheckValue("product price", numPattern);
            if (!Boolean.parseBoolean(price[1])) {
                throw new RuntimeException("Invalid value!");
            } else {
                product.setPrice(new BigDecimal(price[0]));
            }
            //color
            String[] color = Config.CheckValue("product color", strPattern);
            if (!Boolean.parseBoolean(color[1])) {
                throw new RuntimeException("Invalid value!");
            } else {
                product.setColor(color[0]);
            }
            //quantity
            String[] quantity = Config.CheckValue("product quantity", numPattern);
            if (!Boolean.parseBoolean(quantity[1])) {
                throw new RuntimeException("Invalid value!");
            } else {
                product.setQuantity(Integer.parseInt(quantity[0]));
            }
            //img
            String[] img = Config.CheckValue("product image", strPattern);
            if (!Boolean.parseBoolean(img[1])) {
                throw new RuntimeException("Invalid value!");
            } else {
                product.setImageUrl(img[0]);
            }
            //category
            System.out.println("Add category: ");
            int indexCategory = Config.ReturnValue("Man", "Woman", "Children");
            switch (indexCategory) {
                case 0 -> {
                    product.setCategory(Category.MAN);
                }
                case 1 -> {
                    product.setCategory(Category.WOMAN);
                }
                case 2 -> {
                    product.setCategory(Category.CHILDREN);
                }
                default -> {
                    System.out.println("Invalid value");
                }
            }
            //size
            Size[] sizes = Config.getSizeArr();
            product.setSize(sizes);
            return product;
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid value");
        }
    }

    public void AddDefProduct() {
        Product product1 = new Product(Category.MAN, "Test1", new BigDecimal(230), new Size[]{Size.XS, Size.S, Size.XL}, "black", "img", 23);
        Product product2 = new Product(Category.WOMAN, "Test2", new BigDecimal(240), new Size[]{Size.XS, Size.S, Size.XXS}, "red", "img", 20);
        Product product3 = new Product(Category.CHILDREN, "Test3", new BigDecimal(250), new Size[]{Size.XS, Size.S, Size.XXS, Size.L, Size.M}, "blue", "img", 40);
        Product product4 = new Product(Category.MAN, "Test4", new BigDecimal(260), new Size[]{Size.XS, Size.S, Size.XL}, "grey", "img", 25);
        productDao.AddNewProduct(product1);
        productDao.AddNewProduct(product2);
        productDao.AddNewProduct(product3);
        productDao.AddNewProduct(product4);
    }
}
