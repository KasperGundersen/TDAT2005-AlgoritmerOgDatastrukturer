class Oving2{
  public static double regne(double grunntall, int eksponent){ // tidskompleksitet = n
    if(eksponent == 0){return 1;}
    else if(eksponent == 1){return grunntall;}
    else{
      return grunntall*regne(grunntall,eksponent-1);
    }
  }

  public static double regne2(double grunntall, int eksponent){ // tidskompleksitet = log(n)
    if(eksponent==0){
      return 1;
    }else if(eksponent%2==0){
        return regne2(grunntall*grunntall,eksponent/2);
      }
      else {
        return grunntall*regne2(grunntall*grunntall, (eksponent-1)/2);
      }
  }

  public static double regne3(double grunntall, int eksponent){
    return Math.pow(grunntall,eksponent);
  }

  public static void main(String args[]){
    double grunntall = 200000000;
    int eksponent = 2000;
    double resultat = 0;

    long startTime = 0;
    long stopTime = 0;
    long totTime = 0;


    for(int i = 0; i<10000;i++){
      startTime = System.nanoTime();
      resultat = regne(grunntall, eksponent);
      stopTime = System.nanoTime();
      totTime += stopTime-startTime;
    }
    totTime/=10000;

    System.out.println("Metode 1: Grunntall: " + grunntall+ " eksponent: " + eksponent + " Resultat= " + resultat);
    System.out.println("Tid(ns): " + totTime);

    for(int i = 0; i<10000;i++){
      startTime = System.nanoTime();
      resultat = regne2(grunntall, eksponent);
      stopTime = System.nanoTime();
      totTime += stopTime-startTime;
    }
    totTime/=10000;
    System.out.println("Metode 2: Grunntall: " + grunntall + " eksponent: " + eksponent + " Resultat= "+ resultat);
    System.out.println("Tid(ns): " + totTime);

    for(int i = 0; i<10000;i++){
      startTime = System.nanoTime();
      resultat = regne3(grunntall, eksponent);
      stopTime = System.nanoTime();
      totTime += stopTime-startTime;
    }
    totTime/=10000;
    System.out.println("Metode 1: Grunntall: " + grunntall+ " eksponent: " + eksponent + " Resultat= " + resultat);
    System.out.println("Tid(ns): " + totTime);
  }
} // regne: a=1, b=1, cn^k = 0
//
// regne2: a=!, b=2, k=0, cn^k = 1;
// regne2:
