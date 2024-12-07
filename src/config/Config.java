package config;

import enums.Size;
import models.Product;

import java.util.Arrays;
import java.util.Scanner;


public class Config {
    public static String[] CheckValue(String label, String regex) {
        try {
            System.out.print("Write the " + label + ": ");
            boolean isBool = false;
            String text;
            do {
                text = new Scanner(System.in).nextLine();
                if (!text.matches(regex)) {
                    isBool = false;
                    System.out.printf("Invalid %s. Write the valid %s: ", label, label);
                } else {
                    isBool = true;
                }
            } while (!text.matches(regex));
            String[] value = new String[2];
            value[0] = text;
            value[1] = String.valueOf(isBool);
            return value;
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid value!");
        }
    }

    public static Size[] getSizeArr() {
        Size[] sizes = new Size[0];
        Size[] values = Size.values();
        boolean loop = true;
        System.out.println("Choice size (one or more): ");
        for (int i = 0; i < values.length; i++) {
            System.out.println((i + 1) + ". " + values[i].toString().toLowerCase());
        }
        System.out.println("8. Done");
        while (loop) {
            String[] choice = CheckValue("choice", "\\d+");
            if (Integer.valueOf(choice[0]) < (values.length + 1)) {
                sizes = Arrays.copyOf(sizes, sizes.length + 1);
                sizes[sizes.length - 1] = values[Integer.valueOf(choice[0])];
                loop = true;
            } else {
                loop = false;
                break;
            }
        }
        return sizes;
    }

    public static int ReturnValue(String... values) {
        try {
            for (int i = 0; i < values.length; i++) {
                System.out.println((i + 1) + ". " + values[i]);
            }
            String value = new Scanner(System.in).nextLine();
            int index = -1;
            for (int i = 0; i < values.length; i++) {
                if (value.equalsIgnoreCase(values[i]) || String.valueOf(i + 1).equals(value)) {
                    index = i;
                }
            }
            return index;
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid value");
        }
    }

    public static Product findProduct(Product[] products, long id) {
        Product product = null;
        if (products != null) {
            for (Product product1 : products) {
                if (product1.getId() == id) {
                    product = product1;
                    break;
                }
            }
        }
        return product;
    }

    public static Product[] deleteProduct(Product[] products, long id) {
        Product[] deleteProd = new Product[0];
        if (products != null) {
            for (Product product : products) {
                if (product.getId() != id) {
                    deleteProd = Arrays.copyOf(deleteProd, deleteProd.length + 1);
                    deleteProd[deleteProd.length - 1] = product;
                    break;
                }
            }
        }
        return deleteProd;
    }

    public static boolean isFindInArray(Product[] products, long id) {
        boolean isFind = false;
        if (products != null) {
            for (Product product : products) {
                if (product.getId() == id) {
                    isFind = true;
                    break;
                }
            }
        }
        return isFind;
    }
}
