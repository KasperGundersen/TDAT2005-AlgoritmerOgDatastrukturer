class Node {
    private int plass;
    private Node nesteNode;

    public Node(int plass, Node nesteNode) {
        this.plass = plass;
        this.nesteNode = nesteNode;
    }

    public void setNesteNode(Node node) {
        this.nesteNode = node;
    }

    public Node getNesteNode() {
        return nesteNode;
    }

    public int getPlass() {
        return plass;
    }
}

class LinkedList {

    Node hode;
    int lengde;

    public LinkedList(Node node) {
        this.hode = node;
        this.lengde = 1;
    }

    public Node getHode() {
        return hode;
    }

    public void setHode(Node hode) {
        this.hode = hode;
    }
}

class oving4_1 {
    public static void main(String[] args) {
        int antPersoner = 41;
        int intervall = 3;
        Node forrigeNode = new Node(1, null);
        LinkedList list = new LinkedList(forrigeNode);

        for (int i = 0; i < antPersoner-1; i++) {
            Node tempNode = new Node(i+2, list.getHode());
            forrigeNode.setNesteNode(tempNode);
            forrigeNode = tempNode;
        }

        int teller = 1;
        while (list.getHode() != list.getHode().getNesteNode()) {
            if (teller == intervall) {
                teller = 1;
                if (forrigeNode.getNesteNode() == list.getHode()) {
                    list.setHode(list.getHode().getNesteNode());
                }
                forrigeNode.setNesteNode(forrigeNode.getNesteNode().getNesteNode());
            } else {
                forrigeNode = forrigeNode.getNesteNode();
                teller++;
            }
        }
        System.out.println(list.getHode().getPlass());
        // Kompleksiteten ble n
    }
}
