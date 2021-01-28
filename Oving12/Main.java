public class Main {

    public static void main(String[] args) {
        Huffman hm = new Huffman();
        byte [] ut = hm.lesFraFil("test.txt");
        hm.komprimerBytes(ut);
        byte [] ut2 = hm.lesFraFil("KomprimertFil.txt");
        hm.dekomprimerBytes(ut2);
    }

}
