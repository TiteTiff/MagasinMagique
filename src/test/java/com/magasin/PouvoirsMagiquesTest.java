package com.magasin;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PouvoirsMagiquesTest {

    Item [] items = new Item [] {new Item ("Pouvoirs magiques", 10, 50)};
    @Test
    void pouvoirsMagiquesTestLowQuality() {
        Magasin app = new Magasin(items);
        app.updateQuality();
        assertEquals(48, items[0].quality);
    }

    @Test
    void pouvoirsMagiquesTestExpiredSellIn() {
        Magasin app = new Magasin(items);
        items[0].sellIn=0;
        app.updateQuality();
        assertEquals(46, items[0].quality);
    }

    @Test
    void pouvoirsMagiquesTestQualitymin() {
        Magasin app = new Magasin(items);
        items[0].sellIn=0;
        items[0].quality=0;
        app.updateQuality();
        assertEquals(0, items[0].quality);
    }
}
