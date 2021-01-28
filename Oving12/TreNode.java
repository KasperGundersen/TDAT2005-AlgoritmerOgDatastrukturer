class TreNode implements Comparable<TreNode>{
    Integer verdi;
    int frekvens;
    TreNode venstre;
    TreNode hoyre;

    public TreNode(int frekvens, TreNode v, TreNode h){
        this.frekvens = frekvens;
        this.venstre = v;
        this.hoyre = h;
    }

    public TreNode(Integer verdi, int frekvens){
        this.verdi = verdi;
        this.frekvens = frekvens;
        this.venstre = null;
        this.hoyre = null;
    }

    public TreNode getVenstre(){
        return this.venstre;
    }

    public void setVenstre(TreNode v){
        this.venstre = v;
    }

    public TreNode getHoyre(){
        return this.hoyre;
    }

    public void setHoyre(TreNode h){
        this.hoyre = h;
    }

    public Integer getVerdi(){
        return this.verdi;
    }

    public void setVerdi(char c){
        this.frekvens = c;
    }

    public int getFrekvens() {
        return this.frekvens;
    }

    public void setFrekvens(int f) {
        this.frekvens = f;
    }

    public int compareTo(TreNode x) {
        return this.frekvens - x.getFrekvens();
    }
}
