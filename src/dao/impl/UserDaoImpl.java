package dao.impl;

import config.Config;
import dao.UserDao;
import db.DataBase;
import models.Product;
import models.User;

import java.util.Arrays;

public class UserDaoImpl implements UserDao {
    ProductDaoImpl productDao = new ProductDaoImpl();

    @Override
    public void RegistrationNewUser(User user) {
        DataBase.users = Arrays.copyOf(DataBase.users, DataBase.users.length + 1);
        DataBase.users[DataBase.users.length - 1] = user;
    }

    @Override
    public User login(String email, String password) {
        User userLogin = null;
        for (User user : DataBase.users) {
            if (email.equals(user.getEmail())) {
                if (password.equals(user.getPassword())) {
                    userLogin = user;
                }
            }
        }
        if (userLogin == null) {
            throw new RuntimeException("Not found!");
        }
        System.out.println("Welcome " + userLogin.getName() + "!");
        return userLogin;
    }

    @Override
    public String AddFavoriteProduct(long id, User user) {
        Product[] products = user.getFavoriteProduct();
        Product product = Config.findProduct(DataBase.products, id);
        String is = null;
        if (product != null) {
            boolean findInArray = Config.isFindInArray(products, id);
            if (findInArray) {
                is = "del";
                user.setFavoriteProduct(Config.deleteProduct(products, id));
            } else {
                is = "add";
                if (products != null) {
                    products = Arrays.copyOf(products, products.length + 1);
                    products[products.length - 1] = product;
                    user.setFavoriteProduct(products);
                } else {
                    user.setFavoriteProduct(new Product[]{product});
                }
            }
        } else {
            is = "not";
        }
        return is;
    }

    @Override
    public Product[] getAllFavoriteProduct(long id) {
        Product[] favoriteProduct = new Product[0];
        for (User user : DataBase.users) {
            if (user.getId() == id) {
                if (user.getFavoriteProduct() != null) {
                    for (Product product : user.getFavoriteProduct()) {
                        favoriteProduct = Arrays.copyOf(favoriteProduct, favoriteProduct.length + 1);
                        favoriteProduct[favoriteProduct.length - 1] = product;
                    }
                }
            }
        }
        return favoriteProduct;
    }

    @Override
    public User[] getAllUser() {
        return DataBase.users;
    }
}
