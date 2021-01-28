import java.util.Random;

class Oving1 {
  public static void beregnProfitt(int[] kursEndring) {
    long startTime = System.nanoTime();

    int max = 0;
    int fortjeneste = 0;
    int dagKjop = 0;
    int dagSelg = 0;

    for (int i = 0; i < kursEndring.length; i++) {
      fortjeneste = 0;
      for (int k = i; k < kursEndring.length; k++) {
        fortjeneste += kursEndring[k];
        if (fortjeneste > max) {
          max = fortjeneste;
          dagKjop = i;
          dagSelg = k + 1;
        }
      }
    }
    long stopTime = System.nanoTime();
    long elapsedTime = stopTime - startTime;
    System.out.println("Millisekunder: " + (elapsedTime / 1000000));
  }

  public static void main(String[] args) {
    Random rand = new Random();
    int n = 100000;
    int[] verdier = new int[n];
    for (int i = 0; i < n; i++) {
      verdier[i] = rand.nextInt(20) - 10;
    }
    beregnProfitt(verdier);
  }
}
// Tidskompleksitet: Θ (n^2)

// 10 000 verdier: 55 ms
// 100 000 verider: 3777 ms
// 100 000 000 verdier:

/*
{-1, +3, -9, +2, +2, -1, +2, -1, -5} // expected buy day 3, sell day 7, profit 5
{-2, -4, 5, 2, -5, -2} // expected buy day 2, sell day 4, profit 7
*/
