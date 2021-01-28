import java.util.Random;
import java.util.Arrays;
class oving3{

  public static void quicksort(int[] tabell, int f, int l){
    if((l-f)>40){
      int delepos = splitt(tabell, f, l);
      quicksort(tabell, f, delepos-1);
      quicksort(tabell, delepos+1, l);
    } else{
      bubblesort(tabell, f, l);
    }
  }

  public static void bubblesort(int[] tabell, int f, int l){
    for(int i = l; i>f; i--){
      for(int k = f; k<i; k++){
        if(tabell[k]>tabell[k+1]){
          bytt(tabell, k, k+1);
        }
      }
    }
  }


  private static int median3sort(int []t, int v, int h) {
  int m = (v + h) / 2;
  if (t[v] > t[m]) bytt(t, v, m);
  if (t[m] > t[h]) {
    bytt(t, m, h);
    if (t[v] > t[m]) bytt(t, v, m);
  }
  return m;
}

  public static void bytt(int[] t, int v, int h){
    int temp = t[v];
    t[v] = t[h];
    t[h] = temp;
  }

  public static int[] randArray(int a){
    Random rd = new Random();
      int[] arr = new int[a];
      for (int i = 0; i < arr.length; i++) {
         arr[i] = rd.nextInt();
       }
       return arr;
  }

  public static boolean sjekk(int[] t){
    for(int i = 0; i<t.length-1;i++){
      if(t[i]>t[i+1]){
        return false;
      }
    }
    return true;
  }

  private static int splitt(int []t, int v, int h) {
  int iv, ih;
  int m = median3sort(t, v, h);
  int dv = t[m];
  bytt(t, m, h - 1);
  for (iv = v, ih = h - 1;;) {
    while (t[++iv] < dv) ;
    while (t[--ih] > dv) ;
    if (iv >= ih) break;
    bytt(t, iv, ih);
  }
  bytt(t, iv, h-1);
  return iv;
}

public static int sjekkSum(int[]t){
  int sum =0;
  for(int i = 0; i<t.length;i++){
    sum+=t[i];
  }
  return sum;
}

  public static void main(String args[]){

    int lengde = 1000000;
    int[] tabell = randArray(lengde);
    int sjekkSumFor = sjekkSum(tabell);

    long startTime = 0;
    long stopTime = 0;
    long totTime = 0;
    long ms = 0;

    startTime = System.nanoTime();
    quicksort(tabell, 0, tabell.length-1);
    stopTime = System.nanoTime();
    totTime += stopTime-startTime;
    ms = totTime/1000000;
    int sjekkSumEtter = sjekkSum(tabell);
    if(sjekkSumEtter==sjekkSumFor){
      System.out.println("Sum er ok");
    }
    if(sjekk(tabell)){
      System.out.println("Rekkefølge er ok");
    }
    System.out.println("Total tid: " + ms);



  }
}
