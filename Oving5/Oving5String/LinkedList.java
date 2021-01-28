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

  public void setInnNode(Node nyNode){
    hale.setNesteNode(nyNode);
    hale = nyNode;
  }

public void fjernNode(String navn){
  Node current = hode;
  if(current.getNavn().equals(navn)){
    hode = hode.getNesteNode();
  }
  while(current.getNesteNode() != null){
    if(current.getNesteNode().getNavn().equals(navn)){
      current.fjernNesteNode();
    }
  }
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
