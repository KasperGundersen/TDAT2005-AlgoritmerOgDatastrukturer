class LinkedList {
  Node hode;
  Node hale;

  public LinkedList(Node node){
    this.hode = node;
    this.hale = node;
  }

  public Node getHode(){
    return hode;
  }
  public void setHode(Node node){
    this.hode = node;
  }
  public void setHale(Node node){
    this.hale = node;
  }

  public void settInnNode(Node nyNode){
    hale.setNesteNode(nyNode);
    hale = nyNode;
  }



  public String toString(){
    String print = "";
    Node current = hode;
    while(current != null){
      print += current.getNavn() +"\n";
      current = current.getNesteNode();
    }
    return print;
  }
}
