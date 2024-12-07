package models;

import enums.Role;

import java.util.Arrays;

public class User {
    private long id;
    private String email;
    private String password;
    private String name;
    private Role role;
    private Product[] favoriteProduct;

    private static long startId = 0;

    public User() {
        this.id = startId++;
    }

    public User(String email, String password, String name, Role role, Product[] favoriteProduct) {
        this.id = startId++;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.favoriteProduct = favoriteProduct;
    }

    public User(String email, String password, String name, Role role) {
        this.id = startId++;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Product[] getFavoriteProduct() {
        return favoriteProduct;
    }

    public void setFavoriteProduct(Product[] favoriteProduct) {
        this.favoriteProduct = favoriteProduct;
    }

    public long getId() {
        return id;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", favoriteProduct=" + Arrays.toString(favoriteProduct) +
                '}';
    }
}
