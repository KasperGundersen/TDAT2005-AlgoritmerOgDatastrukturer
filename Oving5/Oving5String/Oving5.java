import java.util.ArrayList;
import java.io.*;

class Oving5 {


  public static ArrayList<String> fyllTabell(String filnavn){
    ArrayList<String> array = new ArrayList<String>();
    FileReader fr = null;
    BufferedReader br = null;
    try {

      fr = new FileReader(new File(filnavn));
      br = new BufferedReader(fr);
      String st = br.readLine();
      while (st != null){
        array.add(st);
        st = br.readLine();
      }
    } catch (IOException e){
      e.printStackTrace();
    } finally {
      try {
        if(fr != null){
          fr.close();
        }
        if(br != null){
          br.close();
        }
      } catch (IOException e){
        e.printStackTrace();
      }
    }
    return array;
  }



    public static void main(String[] args){
      HashTable tabell = new HashTable(128);
      int antKollisjon = 0;
      //tabell.setInnNavn("Kasper");
      ArrayList<String> arr = fyllTabell("navneliste.txt");
      for(int i=0;i<arr.size();i++){
        boolean kollisjon = tabell.setInnNavn(arr.get(i));
        if(kollisjon) antKollisjon++;
      }
      System.out.println("Antall kollisjoner: " + antKollisjon);
      // System.out.println(tabell.getUnicode("Kasper"));
      // System.out.println(tabell.hentNavn(21));
      System.out.println("Er Kasper i tabellen? "+tabell.sokPerson("Gundersen,Kasper Vedal"));
      System.out.println("Lastfaktor: " + arr.size()/tabell.getSize());
      System.out.println("Gjennomsnitlig kollisjon per pers: " + (double)antKollisjon/arr.size());
    }
}
