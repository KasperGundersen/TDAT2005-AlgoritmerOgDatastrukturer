class Node{
  private String navn;
  private Node nesteNode;

  public Node(String navn){
    this.navn = navn;
  }

  public void setNesteNode(Node node){
    this.nesteNode = node;
  }

  public Node getNesteNode(){
    return nesteNode;
  }

  public String getNavn(){
    return this.navn;
  }

  public void fjernNesteNode(){
    if(this.nesteNode != null){
      this.nesteNode = this.nesteNode.getNesteNode();
    }
  }
}
