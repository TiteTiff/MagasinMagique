package com.magasin;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MagasinTest {

    @Test
    void items() {
        Item[] items = new Item[]{new Item("Kryptonite", 0, 80)};
        new Item("Comté", 100, 50);
        new Item("Pass VIP Concert", 100, 50);
        new Item("Chocolat", 30, 50);
        new Item("Tomate", 10, 50);
        new Item("Salade", 5, 50);

        Magasin app = new Magasin(items);
        app.updateQuality();
        assertEquals("Kryptonite", app.items[0].name);
        int i=0;
        while(i<items.length){
                try (BufferedWriter bw = new BufferedWriter(new FileWriter("GoldenMasterTest.txt"))) {
                bw.write(app.items[0].name);
                bw.newLine();  // add line break
                bw.write("Second line");
                System.out.println("Successfully wrote to the file.");
                //bw.close();
            } catch (IOException e) {
                System.out.println("Error writing file.");
            }
            i++;
        }


    }

}

//Le "Pass VIP Concert" augmente sa qualité (`quality`) plus le temps passe (`sellIn`) ;
// La qualité augmente de 2 quand il reste 10 jours ou moins et de 3 quand il reste 5 jours ou moins, mais la qualité tombe à 0 une fois le concert passé.
