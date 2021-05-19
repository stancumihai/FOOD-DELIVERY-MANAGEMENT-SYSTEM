package org.stancu.service;

import org.stancu.model.BaseProduct;
import org.stancu.model.MenuItem;
import org.stancu.model.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class DeliveryService implements IDeliveryServiceProcessing {

    private static Set<MenuItem> itemsSet = new HashSet<>();
    private static List<MenuItem> items;
    private static Map<Integer, Order> orders = new HashMap<>();
    private static final List<MenuItem> menuItems = new ArrayList<>();

    public static Map<Integer, Order> graspListOfOrder() {
        OrderService orderService = OrderService.getInstance();
        orders = orderService.selectAll();
        return orders;
    }

    public static List<MenuItem> getMenuItems() {
        ProductService productService = ProductService.getInstance();
        return productService.selectAll();
    }

    public static List<MenuItem> getItems() {
        return items;
    }

    /**
     * @param items items to put in DeliveryService
     * @post return != null
     */
    public static void setItems(List<MenuItem> items) {
        DeliveryService.items = items;
    }

    public static void setInitialItemsFromFile() {
        try {
            List<String[]> lines = Files.lines(Paths.get("D:\\Munca\\Facultate\\Anul 2\\Semestrul2\\Tehnici de Programare Fundamentale\\Teme\\Tema4" +
                    "\\Impl\\PT2021_30221_Stancu_Mihai_Cristian_Assignment_4\\products.csv"))
                    .filter(line -> !line.startsWith("Title"))
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());

            itemsSet = lines.stream()
                    .map(a -> new BaseProduct(a[0], Double.valueOf(a[1]), Integer.valueOf(a[2]), Integer.valueOf(a[3])
                            , Integer.valueOf(a[4]), Integer.valueOf(a[5]), Integer.valueOf(a[6])))
                    .collect(Collectors.toSet());
            setItems(new ArrayList<>(itemsSet));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @pre id != null
     * @pre isWellFormed()
     */
    public static boolean isWellFormed() {
        for (MenuItem o : itemsSet)
            if (o == null)
                return false;
        for (Map.Entry<Integer, Order> o : graspListOfOrder().entrySet())
            if (o == null)
                return false;
        for (MenuItem o : getMenuItems())
            if (o == null)
                return false;
        return true;
    }
}


