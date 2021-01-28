import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.LinkedList;


class Oving8{

  public static int[][] Graf(String filnavn){
    int[][] graf= null;

    FileReader fr = null;
    BufferedReader br = null;
    try {
      fr = new FileReader(new File(filnavn));
      br = new BufferedReader(fr);
      String line = br.readLine();
      String[] values = line.split(" ");
      int antNoder = Integer.parseInt(values[1]);
      graf = new int[antNoder][antNoder];

      for(int i = 0; i<antNoder;i++){
        line = br.readLine();
        String[] verdier = line.split(" ");
        int fraIndex = Integer.parseInt(verdier[0]);
        int tilIndex = Integer.parseInt(verdier[1]);
        int kapasitet = Integer.parseInt(verdier[2]);
        graf[fraIndex][tilIndex] = kapasitet;
      }
      return graf;
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
    return graf;
  }

  public static boolean bfs(int rGraph[][], int s, int t, int parent[])
  {
    int V = rGraph[0].length;
    // Create a visited array and mark all vertices as not
    // visited
    boolean visited[] = new boolean[V];

    LinkedList<Integer> queue = new LinkedList<Integer>();
    queue.add(s);
    visited[s] = true;
    parent[s]=-1;

    // Standard BFS Loop
    while (queue.size()!=0)
    {
      int u = queue.poll();

      for (int v=0; v<V; v++)
      {
        if (visited[v]==false && rGraph[u][v] > 0)
        {
          queue.add(v);
          parent[v] = u;
          visited[v] = true;
        }
      }
    }

    return (visited[t] == true);
  }

  // Returns tne maximum flow from s to t in the given graph
  public static int fordFulkerson(int graph[][], int s, int t)
  {
    int V = graph.length;
    int u, v;

    int rGraph[][] = new int[V][V];

    for (u = 0; u < V; u++) {
      for (v = 0; v < V; v++) {
        rGraph[u][v] = graph[u][v];
      }
    }

    // This array is filled by BFS and to store path
    int parent[] = new int[V];

    int max_flow = 0;  // There is no flow initially

    while (bfs(rGraph, s, t, parent))
    {
      int path_flow = Integer.MAX_VALUE;
      for (v=t; v!=s; v=parent[v])
      {
        u = parent[v];
        path_flow = Math.min(path_flow, rGraph[u][v]);
      }

      // update residual capacities of the edges and
      // reverse edges along the path

      String path = "";
      for (v=t; v != s; v=parent[v])
      {
        path += v +  " ";
        u = parent[v];
        rGraph[u][v] -= path_flow;
        rGraph[v][u] += path_flow;
      }
      System.out.println(path_flow + " --> "  + path + s);
      // Add path flow to overall flow
      max_flow += path_flow;
    }

    return max_flow;
  }

  public static void main(String[] args) {
    int[][] graf = Graf("graf3.txt");
    System.out.println("Maksimal flyt: " + fordFulkerson(graf,0,1));

  }

}
