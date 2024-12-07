package service.impl;

import config.Config;
import dao.impl.ProductDaoImpl;
import dao.impl.UserDaoImpl;
import enums.Category;
import enums.Role;
import enums.Size;
import models.Product;
import models.User;
import service.UserService;

import java.util.Arrays;
import java.util.Scanner;

public class UserServiceImpl implements UserService {
    UserDaoImpl userDao = new UserDaoImpl();
    ProductDaoImpl productDao = new ProductDaoImpl();
    private String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private String numPattern = "\\d+";

    User findUser = null;

    public void CreateDefAdmin() {
        User admin = new User("Admin1@gmail.com", "Admin123@", "Admin", Role.ADMIN);
        userDao.RegistrationNewUser(admin);
    }

    public void DefUser() {
        User user = new User("Almaz23@gmail.com", "Almaz123@", "Almaz", Role.USER);
        userDao.RegistrationNewUser(user);
    }

    @Override
    public User RegistrationNewUser() {
        User user = new User();
        System.out.println("Welcome to registration form");
        String[] name = Config.CheckValue("name", "^[a-zA-Z\\s]+");
        if (!Boolean.parseBoolean(name[1])) {
            throw new RuntimeException("Invalid value!");
        } else {
            user.setName(name[0]);
        }
        String[] email = Config.CheckValue("email", emailPattern);
        if (!Boolean.parseBoolean(email[1])) {
            throw new RuntimeException("Invalid value!");
        } else {
            if (userDao.getAllUser() != null && userDao.getAllUser().length != 0) {
                for (User u : userDao.getAllUser()) {
                    if (u.getEmail().equals(email[0])) {
                        System.out.println("Email is already exist!, please try again.");
                        email = Config.CheckValue("email", emailPattern);
                    } else {
                        user.setEmail(email[0]);
                    }
                }
            } else {
                user.setEmail(email[0]);
            }
        }
        String[] password = Config.CheckValue("password", passwordPattern);
        if (!Boolean.parseBoolean(password[1])) {
            throw new RuntimeException("Invalid value!");
        } else {
            user.setPassword(password[0]);
        }
        user.setRole(Role.USER);
        userDao.RegistrationNewUser(user);
        System.out.println("Successfully registration!, welcome " + user.getName());
        findUser = user;
        return user;
    }

    @Override
    public User Login() {
        String[] email = Config.CheckValue("email", emailPattern);
        String[] password = Config.CheckValue("password", passwordPattern);
        User user = userDao.login(email[0], password[0]);
        findUser = user;
        return user;
    }

    @Override
    public void AddFavoriteProduct() {
        String[] id = Config.CheckValue("product ID", numPattern);
        if (Boolean.parseBoolean(id[1])) {
            String is = userDao.AddFavoriteProduct(Long.parseLong(id[0]), findUser);
            if (is.equalsIgnoreCase("del")) {
                System.out.println("Product remove on favorite!");
            } else if (is.equalsIgnoreCase("add")) {
                System.out.println("Product add to favorite!");
            } else if (is.equalsIgnoreCase("not")) {
                System.out.println("Product not found!");
            } else {
                System.out.println("Error!");
            }
        } else {
            throw new RuntimeException("Not found!");
        }
    }

    public void GetAllFavoriteProduct() {
        Product[] allFavoriteProduct = userDao.getAllFavoriteProduct(findUser.getId());
        if (allFavoriteProduct.length != 0) {
            System.out.println("All favorite product: ");
            for (Product product : allFavoriteProduct) {
                System.out.println(product);
            }
        } else {
            System.out.println("Products is empty!");
        }
    }

    @Override
    public void FilterByCategoryAndSize() {
        while (true) {
            System.out.println("""
                    Choice under value:
                    1. Man
                    2. Woman
                    3. Children
                    4. Exit
                    """);
            String text = new Scanner(System.in).nextLine();
            switch (text) {
                case "1", "man", "Man", "MAN" -> {
                    String[] size = new String[0];
                    size = getSize(size);
                    RenderValueByFilter(Category.MAN, size);
                }
                case "2", "woman", "Woman", "WOMAN" -> {
                    String[] size = new String[0];
                    size = getSize(size);
                    RenderValueByFilter(Category.WOMAN, size);
                }
                case "3", "children", "Children", "CHILDREN" -> {
                    String[] size = new String[0];
                    size = getSize(size);
                    RenderValueByFilter(Category.CHILDREN, size);
                }
                case "4" -> {
                    return;
                }
                default -> {
                    System.out.println("Invalid value");
                }
            }

        }
    }

    public String[] getSize(String[] sizes) {
        System.out.println("""
                Can you choice one or more:
                1. XXS
                2. XS
                3. S
                4. XXL
                5. XL
                6. L
                7. M
                8. Exit
                """);
        String size = new Scanner(System.in).nextLine();
        switch (size) {
            case "1", "XXS", "Xxs", "xxs" -> {
                sizes = getSize(AddOnArrValue(Size.XXS, sizes));
            }
            case "2", "XS", "Xs", "xs" -> {
                sizes = getSize(AddOnArrValue(Size.XS, sizes));
            }
            case "3", "S", "s" -> {
                sizes = getSize(AddOnArrValue(Size.S, sizes));
            }
            case "4", "XXL", "Xxl", "xxl" -> {
                sizes = getSize(AddOnArrValue(Size.XXL, sizes));
            }
            case "5", "XL", "Xl", "xl" -> {
                sizes = getSize(AddOnArrValue(Size.XL, sizes));
            }
            case "6", "L", "l" -> {
                sizes = getSize(AddOnArrValue(Size.L, sizes));
            }
            case "7", "M", "m" -> {
                sizes = getSize(AddOnArrValue(Size.M, sizes));
            }
            case "8" -> {
                return sizes;
            }
            default -> {
                System.out.println("Invalid value");
                return sizes;
            }
        }
        return sizes;
    }

    public String[] AddOnArrValue(Size size, String[] array) {
        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = String.valueOf(size);
        return array;
    }

    public void RenderValueByFilter(Category category, String[] size) {
        System.out.println("Filter by:\nCategory: " + category + "\nSizes: " + Arrays.toString(size));
        Product[] allProduct = productDao.getAllProduct();
        if (allProduct.length != 0) {
            Product[] products = new Product[0];
            for (Product product : allProduct) {
                if (product.getCategory() == category) {
                    if (size != null && size.length != 0) {
                        for (Size size1 : product.getSize()) {
                            for (String s : size) {
                                if (s.equalsIgnoreCase(size1.toString())) {
                                    products = Arrays.copyOf(products, products.length + 1);
                                    products[products.length - 1] = product;
                                }
                            }
                        }
                    } else {
                        products = Arrays.copyOf(products, products.length + 1);
                        products[products.length - 1] = product;
                    }
                }
            }
            if (products.length != 0) {
                Product newProd = products[0];
                boolean found = false;
                for (int i = 0; i < products.length; i++) {
                    if (newProd == products[i] && !found) {
                        found = true;
                    } else if (newProd != products[i]) {
                        System.out.print(" " + newProd);
                        newProd = products[i];
                        found = false;
                    }
                }
                System.out.println("\n" + newProd + " ");
            } else {
                System.out.println("Not found!");
            }
        } else {
            System.out.println("Products is empty!");
        }
    }


}
