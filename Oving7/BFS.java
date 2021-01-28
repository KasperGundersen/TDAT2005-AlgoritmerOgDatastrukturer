import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.LinkedList;

class BFS{

  // prints BFS traversal from a given source s
  public static int[][] BFS(int s, Graf graf){
    boolean visited[] = new boolean[graf.getAntallNoder()];
    LinkedList<Integer> queue = new LinkedList<Integer>();

    int[][] info = new int[graf.getAntallNoder()][3];

    // lAG EN TO-DIM int TABELL SOM LAGRER Node-nr, forgjenger, og distanse til startnode

    // Mark the current node as visited and enqueue it
    visited[s]=true;
    queue.add(s);
    info[s][0]=s;
    info[s][1]=-1;
    info[s][2]=0;

    while (queue.size() != 0){
      // Dequeue a vertex from queue and print it
      s = queue.poll();
      Node current = graf.getNode(s);

      // HER LA JEG INN SHIT
      // HER ENDRET JEG KONDISJONEN I WHILE LØKKEN
      while (current.getNesteNode() != null){
        int n = current.getNesteNode().getNavn();
        if (!visited[n])
        {
          info[n][0]=n;
          info[n][1]=s;
          info[n][2]=info[s][2] + 1;

          visited[n] = true;
          queue.add(n);
        }
        current = current.getNesteNode();
      }
    }
    return info;
  }

  public static void topologicalSortUtil(int v, boolean visited[], ArrayList<Integer> arr, Graf graf){
    visited[v] = true;
    Node current = graf.getNode(v);

    while (current.getNesteNode() != null){
      int neste = current.getNesteNode().getNavn();
      if (!visited[neste]){
        topologicalSortUtil(neste, visited, arr, graf);
      }
      current = current.getNesteNode();
    }

    arr.add(v);
  }

  // The function to do Topological Sort. It uses
  // recursive topologicalSortUtil()
  public static void topologicalSort(Graf graf){
    ArrayList<Integer> liste = new ArrayList<Integer>();

    // Mark all the vertices as not visited
    int lengde = graf.getAntallNoder();
    boolean visited[] = new boolean[lengde];

    for (int i = 0; i < lengde; i++)
    if (visited[i] == false){
      topologicalSortUtil(i, visited, liste, graf);
    }

    // Print contents of stack
    while (liste.size() !=0){
      int i = (liste.get(liste.size()-1));
      System.out.print(i + " ");
      liste.remove(liste.size()-1);
    }
  }

  public static void main(String[] args) {
    Graf graf = new Graf("graf1.txt");
    int[][] liste = BFS(0, graf);
    String print = "";
    print = "Node: Forgj: Dist: ";
    for(int i = 0; i<liste.length;i++){
      if(liste[i][1]==-1){
        print += "\n" + liste[i][0] + "            " + liste[i][2];

      } else {
        print += "\n" + liste[i][0] + "     " + liste[i][1] + "      " + liste[i][2];
      }
    } 
    System.out.println(print);

    Graf graf2 = new Graf("graf2.txt");
    System.out.println("\n" + "Topologisk Sortering: " + "\n");
    topologicalSort(graf2);
  }
}
