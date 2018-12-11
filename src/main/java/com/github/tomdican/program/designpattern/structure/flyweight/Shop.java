package com.github.tomdican.program.designpattern.structure.flyweight;

import com.github.tomdican.program.Util;
import com.github.tomdican.program.designpattern.structure.flyweight.PotionFactory.PotionType;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<Potion> topShelf;
    private List<Potion> bottomShelf;

    /**
     * Constructor
     */
    public Shop() {
        topShelf = new ArrayList<>();
        bottomShelf = new ArrayList<>();
        fillShelves();
    }

    private void fillShelves() {

        PotionFactory factory = new PotionFactory();

        topShelf.add(factory.createPotion(PotionType.HEALING));
        topShelf.add(factory.createPotion(PotionType.HEALING));
        topShelf.add(factory.createPotion(PotionType.HEALING));

        bottomShelf.add(factory.createPotion(PotionType.POISON));
        bottomShelf.add(factory.createPotion(PotionType.POISON));
        bottomShelf.add(factory.createPotion(PotionType.POISON));
        bottomShelf.add(factory.createPotion(PotionType.HOLY_WATER));
        bottomShelf.add(factory.createPotion(PotionType.HOLY_WATER));
    }

    public void enumerate() {

        Util.println("Enumerating top shelf potions\n");

        for (Potion p : topShelf) {
            p.drink();
        }

        Util.println("Enumerating bottom shelf potions\n");

        for (Potion p : bottomShelf) {
            p.drink();
        }
    }


    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.enumerate();
    }
}
