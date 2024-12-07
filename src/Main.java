import enums.Role;
import models.User;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;

import java.util.Scanner;

public class Main {
    static User user = null;
    static Scanner scanner = new Scanner(System.in);
    static UserServiceImpl userService = new UserServiceImpl();
    static ProductServiceImpl productService = new ProductServiceImpl();

    public static void main(String[] args) {
        userService.CreateDefAdmin();
        userService.DefUser();
        productService.AddDefProduct();
        while (true) {
            System.out.println("""
                    1. Registration
                    2. Login
                    3. Exit
                    """);
            switch (scanner.nextLine()) {
                case "1" -> {
                    user = userService.RegistrationNewUser();
                    AfterLoginOrReg();
                }
                case "2" -> {
                    user = userService.Login();
                    AfterLoginOrReg();
                }
                case "3" -> {
                    return;
                }
                default -> {
                    System.out.println("Invalid value");
                }
            }
        }
    }

    public static void AfterLoginOrReg() {
        if (user.getRole().equals(Role.USER)) {
            while (true) {
                System.out.println("""
                        1. Get all product
                        2. Filter product by category and size
                        3. Get my all favorite products
                        4. Add or remove product to favorite
                        5. Log out
                        """);
                switch (scanner.nextLine()) {
                    case "1" -> {
                        productService.GetAllProduct();
                    }
                    case "2" -> {
                        userService.FilterByCategoryAndSize();
                    }
                    case "3" -> {
                        userService.GetAllFavoriteProduct();
                    }
                    case "4" -> {
                        userService.AddFavoriteProduct();
                    }
                    case "5" -> {
                        user = null;
                        return;
                    }
                    default -> {
                        System.out.println("Invalid value");
                    }
                }
            }
        }else if (user.getRole().equals(Role.ADMIN)){
            while (true){
                System.out.println("""
                        1. Add new product
                        2. Get all product
                        3. Get product by id
                        4. Filter product by category and size
                        5. Update product by id
                        6. Log out
                        """);
                switch (scanner.nextLine()){
                    case "1"->{
                        productService.AddNewProduct();
                    }
                    case "2"->{
                        productService.GetAllProduct();
                    }
                    case "3"->{
                        productService.GetOneProductById();
                    }
                    case "4"->{
                        productService.FilterByCategoryAndSize();
                    }
                    case "5"->{
                        productService.UpdateProduct();
                    }
                    case "6"->{
                        user = null;
                        return;
                    }
                    default -> {
                        System.out.println("Invalid choice!");
                    }
                }
            }
        }else {
            System.out.println("Invalid user role!");
        }
    }
}