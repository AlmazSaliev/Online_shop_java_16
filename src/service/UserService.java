package service;

import models.User;

public interface UserService extends GeneralService {
    // registration
    User RegistrationNewUser();

    // login
    User Login();

    // add favorite product
    void AddFavoriteProduct();
}
