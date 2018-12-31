package ch8.item52;

import java.util.Arrays;
import java.util.List;

// Classification using method overrriding (Page 239)
public class Overriding {
    public static void main(String[] args) {
        List<Wine> wineList = Arrays.asList(
                new Wine(), new SparklingWine(), new Champagne());

        for (Wine wine : wineList)
            System.out.println(wine.name()); // 多态
    }
}
