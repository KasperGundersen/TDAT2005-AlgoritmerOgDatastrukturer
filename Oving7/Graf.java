import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;


class Graf{
  private int antall;
  private LinkedList[] graf;


  public Graf(String filnavn){
    FileReader fr = null;
    BufferedReader br = null;
    try {
      fr = new FileReader(new File(filnavn));
      br = new BufferedReader(fr);
      String line = br.readLine();
      String[] values = line.split(" ");
      int antNoder = Integer.parseInt(values[0]);
      this.graf = new LinkedList[antNoder];

      for(int i = 0; i<antNoder;i++){
        this.graf[i] = new LinkedList(new Node(i));
      }

      line = br.readLine();
      while (line != null){
        values = line.split(" ");
        int fraIndex = Integer.parseInt(values[0]);
        int tilIndex = Integer.parseInt(values[1]);

        Node tilNode = new Node(tilIndex);

        this.graf[fraIndex].settInnNode(tilNode);

        line = br.readLine();
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
  }

  public int getAntallNoder() {
    return graf.length;
  }

  public Node getNode(int i) {
    return graf[i].getHode();
  }

  public static void printGraf(Graf graf){
    for(int i = 0; i < graf.getAntallNoder(); i++){
      String print = i + ": ";
      Node currentNode = graf.getNode(i);
      while (currentNode.getNesteNode() != null) {
        currentNode = currentNode.getNesteNode();
        print += currentNode.getNavn() + " ";
      }
      System.out.println(print);
    }
  }


  public static void main(String[] args) {
    Graf test = new Graf("graf1.txt");
    printGraf(test);
  }
}
