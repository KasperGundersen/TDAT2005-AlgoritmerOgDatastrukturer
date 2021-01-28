class HashTable{
  private int[] list;
  private int size;

  public HashTable(int size){
    this.list = new int[size];
    this.size = size;
  }

  public double getSize(){
    return (double)this.size;
  }

  public int hashOne(int number){
    int index = 0;
    return index = number%this.size;
  }

  public int hashTwo(int number){
    int index = 0;
    return index = (number%(this.size -1) +1);
  }

  public void setInnTall(int number){
        int index = hashOne(number);
        if(list[index] == 0){
          list[index] = number;
        } else {
          int newIndex = hashTwo(index);
          int temp = index;
          int teller = 1;
          while(list[temp] != 0 && teller != getSize()){
            temp += newIndex;
            temp = temp%list.length;
            if(list[temp] == 0){
              list[temp] = number;
              break;
            }
            teller++;
          }
        }
  }
}
