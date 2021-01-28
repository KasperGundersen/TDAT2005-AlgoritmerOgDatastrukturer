import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.PriorityQueue;

public class Huffman {

    int [] frekvenser = new int[256];

    public byte[] lesFraFil(String startFil) {
        try {
            byte[] bytesFromFile = Files.readAllBytes(Paths.get(startFil));
            return bytesFromFile;
        }
        catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void skrivTilFil(byte[] data, String sluttFil) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(sluttFil)));
            dataOutputStream.write(data);
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void komprimerBytes(byte[] fraFil) {
        TreNode rot = huffmanTre(finnFrekvens(fraFil));
        BitSet[] tabell = lagKodeTabell(rot);

        BitSet data = new BitSet();

        int lengde = 0;
        for (int i = 0; i < fraFil.length; i++) {
            BitSet kode = tabell[fraFil[i] + 128];
            for (int j = 0; j < kode.length() - 1; j++) {
                data.set(lengde, kode.get(j));
                lengde++;
            }
        }
        data.set(lengde, true);
        byte[] tilFil = data.toByteArray();
        byte[] ut = new byte[tilFil.length+1024];
        for(int i = 0; i < frekvenser.length; ++i) {
            ut[4*i+3] = (byte)(frekvenser[i] & 0xFF);
            ut[4*i+2] = (byte)((frekvenser[i] >> 8) & 0xFF);
            ut[4*i+1] = (byte)((frekvenser[i] >> 16) & 0xFF);
            ut[4*i] = (byte)((frekvenser[i] >> 24) & 0xFF);
        }
        for(int i = 0; i < tilFil.length;i++){
            ut[i+1024] = tilFil[i];
        }
        skrivTilFil(ut, "KomprimertFil.txt");
    }

    public void dekomprimerBytes(byte[] fraFil) {
        int[] frekvenstabell = new int[256];
        for (int i = 0; i<256; i++) {
            byte[] buffer = new byte[]{fraFil[i*4],fraFil[i*4+1],fraFil[i*4+2],fraFil[i*4+3]};
            frekvenstabell[i] = ByteBuffer.wrap(buffer).getInt();
        }
        TreNode rot = huffmanTre(frekvenstabell);

        byte[] data = new byte[fraFil.length-1024];
        for (int i = 0; i<data.length;i++) {
            data[i] = fraFil[i+1024];
        }
        BitSet bs = BitSet.valueOf(data);
        ArrayList<Byte> byteList = new ArrayList<>();
        TreNode tn = rot;
        for (int i = 0; i < bs.length() - 1; i++) {
            if (bs.get(i)) {
                tn = tn.getVenstre();
            } else {
                tn = tn.getHoyre();
            }
            if (tn.getVerdi() != null) {
                byteList.add((byte) (tn.getVerdi() - 128));
                tn = rot;
            }
        }
        byte[] tilFil = new byte[byteList.size()];
        for (int i = 0; i < tilFil.length; i++) {
            tilFil[i] = byteList.get(i);
        }
        skrivTilFil(tilFil, "DekomprimertFil.txt");
    }


    public int[] finnFrekvens(byte[] fraFil) {
        for (byte b: fraFil) {
            frekvenser[b+128]++;
        }
        return frekvenser;
    }

    public TreNode huffmanTre(int[] frekvensTabell) {
        PriorityQueue<TreNode> pq = new PriorityQueue<>();
        for (int i = 0; i < 256; i++) {
            pq.add(new TreNode(i,frekvensTabell[i]));
        }
        for (int i = 0; i < 255; i++) {
            TreNode t1 = pq.remove();
            TreNode t2 = pq.remove();
            TreNode t = new TreNode(t1.getFrekvens() + t2.getFrekvens(),t1,t2);
            pq.add(t);
        }
        return pq.remove();
    }

    public BitSet[] lagKodeTabell(TreNode rot) {
        BitSet[] tabell = new BitSet[256];
        fyllTabell(rot, "", tabell);

        return tabell;
    }

    private void fyllTabell(TreNode tn, String path, BitSet[] tabell) {
        if (tn.getVerdi() != null) {

            BitSet b = new BitSet();
            for (int j = 0; j < path.length(); j++) {
                b.set(j, path.charAt(j) == '1');
            }
            b.set(path.length(), true);
            tabell[tn.getVerdi()] = b;

        } else {
            fyllTabell(tn.getVenstre(), path.concat("1"), tabell);
            fyllTabell(tn.getHoyre(), path.concat("0"), tabell);
        }
    }

}
