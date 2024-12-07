package dao;

import models.Product;
import models.User;

public interface UserDao {
    // registration
    void RegistrationNewUser(User user);

    // login
    User login(String email, String password);

    // add favorite product
    String AddFavoriteProduct(long id, User user);

    // get all favorite product
    Product[] getAllFavoriteProduct(long id);

    // get all user
    User[] getAllUser();
}
