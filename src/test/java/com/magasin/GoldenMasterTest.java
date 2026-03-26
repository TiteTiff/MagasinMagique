package com.magasin;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GoldenMasterTest {

    String[] names = new String[]{"Kryptonite", "Comté", "Pass VIP Concert", "Chocolat", "Tomate", "Salade"};


    Item[] items = new Item[]{
            new Item("Kryptonite", 0, 80),
            new Item("Comté", 100, 4),
            new Item("Pass VIP Concert", 100, 4),
            new Item("Chocolat", 30, 50),
            new Item("Tomate", 10, 50),
            new Item("Salade", 5, 50)
    };

    @Test
    void items() {
        int[] sellins = new int[]{0, 100, 100, 30, 10, 5};
        int[] qualities = new int[]{80, 4, 4, 50, 50, 50};

        for (int j = 0; j < items.length; j++) {
            assertEquals(names[j], items[j].name);
            assertEquals(sellins[j], items[j].sellIn);
            assertEquals(qualities[j], items[j].quality);
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("GoldenMasterTest.txt"))) {
            for (int i = 0; i < items.length; i++) {
                bw.write(items[i].name + " " + items[i].sellIn + " " + items[i].quality);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    @Test
    void KryptoniteTest() {
        Magasin app = new Magasin(items);
        int qualityK = app.items[0].quality;
        app.updateQuality();
        assertEquals(qualityK, app.items[0].quality);

        //try (BufferedWriter bw = new BufferedWriter(new FileWriter("GoldenMasterTest.txt"))) {
        //    for (int i = 0; i < items.length; i++) {
        //        bw.write(items[i].name + " " + items[i].sellIn + " " + items[i].quality);
        //       bw.newLine();
        //    }
        //} catch (IOException e) {
        //    System.out.println("Error writing file.");
        //}
    }

    @Test
    void ComteTest() {
        Magasin app = new Magasin(items);
        int sellInC = app.items[1].sellIn;
        int qualityC = app.items[1].quality;
        app.updateQuality();
        assertEquals(sellInC, app.items[1].sellIn + 1);
        assertEquals(qualityC, app.items[1].quality - 1);
    }

    @Test
    void ComteTestQualityMax() {
        Magasin app = new Magasin(items);
        items[1].quality = 50;
        app.updateQuality();
        assertEquals(50, app.items[1].quality);
    }

    @Test
    void PassVipConcertTest() {
        Magasin app = new Magasin(items);
        items[2].sellIn = 12;
        items[2].quality = 4;
        app.updateQuality();
        assertEquals(11, app.items[2].sellIn);
        assertEquals(5, app.items[2].quality);
    }

    @Test
    void PassVipConcertTestQualityMax() {
        Magasin app = new Magasin(items);
        items[2].quality = 50;
        app.updateQuality();
        assertEquals(50, app.items[2].quality);
    }

    @Test
    void PassVipConcertTestSellIn5to10() {
        Magasin app = new Magasin(items);
        items[2].sellIn = 6;
        items[2].quality = 4;
        app.updateQuality();
        assertEquals(5, app.items[2].sellIn);
        assertEquals(6, app.items[2].quality);
    }

    @Test
    void PassVipConcertTestSellIn1to5() {
        Magasin app = new Magasin(items);
        items[2].sellIn = 2;
        items[2].quality = 4;
        app.updateQuality();
        assertEquals(1, app.items[2].sellIn);
        assertEquals(7, app.items[2].quality);
    }

    @Test
    void PassVipConcertTestSellIn0() {
        Magasin app = new Magasin(items);
        items[2].sellIn = 0;
        app.updateQuality();
        assertEquals(-1, app.items[2].sellIn);
        assertEquals(0, app.items[2].quality);
    }

    @Test
    void ChocolatTest() {
        Magasin app = new Magasin(items);
        items[3].sellIn = 2;
        items[3].quality = 4;
        app.updateQuality();
        assertEquals(1, app.items[3].sellIn);
        assertEquals(3, app.items[3].quality);
    }

    @Test
    void ChocolatTestSellInExpired() {
        Magasin app = new Magasin(items);
        items[3].sellIn = -1;
        items[3].quality = 4;
        app.updateQuality();
        assertEquals(-2, app.items[3].sellIn);
        assertEquals(2, app.items[3].quality);
    }

    @Test
    void ChocolatTestQualityMin() {
        Magasin app = new Magasin(items);
        items[3].sellIn = 2;
        items[3].quality = 0;
        app.updateQuality();
        assertEquals(1, app.items[3].sellIn);
        assertEquals(0, app.items[3].quality);
    }

    @Test
    void testQualityMax() {
        items[1].quality = 50;
        Magasin app = new Magasin(items);
        app.updateQuality();
        assertEquals(50, app.items[1].quality);
    }
}

//trouver solution pour les résultats de tests dans un même fichier


