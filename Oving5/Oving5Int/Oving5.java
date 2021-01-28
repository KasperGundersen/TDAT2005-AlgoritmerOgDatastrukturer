import java.util.ArrayList;
import java.io.*;
import java.util.Random;
import java.util.HashMap;
import java.util.Hashtable;

class Oving5 {

  public static int[] fyllTabell(int length) {
      Random rand = new Random();
      int[] array = new int[length];

      for (int i = 0; i < array.length; i++) {
          array[i] = rand.nextInt(1000000000) +1;
      }
      return array;
  }

    public static void main(String[] args){
      HashTable tabell = new HashTable(5000011);
      int[] tab = fyllTabell(5000000);

      long startTid = System.nanoTime();
      for(int i = 0; i < tab.length; i++){
        tabell.setInnTall(tab[i]);
      }
      long sluttTid = System.nanoTime();

      long totTid = sluttTid - startTid;

      System.out.println("Totaltid ms(min metode): " + totTid/1000000);

      HashMap<Integer, Integer> test = new HashMap<Integer, Integer>();
      long startTidny = System.nanoTime();
      for(int i = 0; i<tab.length;i++){
        test.put(tab[i], tab[i]);
      }
      long sluttTidny = System.nanoTime();
      long totTidNy = sluttTidny - startTidny;
      System.out.println("Java HashMap: " + totTidNy/1000000);
    }
}
