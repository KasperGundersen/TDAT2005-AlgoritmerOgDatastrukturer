class Node{
    private Node nesteNode;
    private int navn;

    public Node(int navn){
      this.navn = navn;
      this.nesteNode = null;
    }

    public int getNavn(){
        return navn;
    }

    public Node getNesteNode(){
        return nesteNode;
    }

    public void setNesteNode(Node nesteNode){
      this.nesteNode = nesteNode;
    }
}
