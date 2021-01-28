class HashTable{
  private LinkedList[] list;
  private int size;

  public HashTable(int size){
    this.list = new LinkedList[size];
    this.size = size;
  }

  public double getSize(){
    return (double)this.size;
  }

  public String hentNavn(int index){
    return list[index].getHode().getNavn();
  }

  public int getUnicode(String word){
    int total = 0;
    for(int i =0; i<word.length();i++){
      char a = word.charAt(i);
      int number = (int) a;
      total = ((total + (number*i))% list.length);
    }
    return total;
  }

  public boolean setInnNavn(String navn){
    int index = getUnicode(navn);
    if(list[index] == null){
      list[index] = new LinkedList(new Node(navn));
      return false;
    } else {
      list[index].setInnNode(new Node(navn));
      return true;
    }
  }

  public boolean sokPerson(String navn){
    int index = getUnicode(navn);
    if(list[index] == null){
      return false;
    } else {
      Node current = list[index].getHode();
      if(current.getNavn().equals(navn)){
        return true;
      }
      while(current.getNesteNode() != null){
        if(current.getNesteNode().getNavn().equals(navn)){
          return true;
        }
        current = current.getNesteNode();
      }
      return false;
    }
  }
}
