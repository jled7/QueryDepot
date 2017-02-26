
import lib.ComparatorIF;

/**
 * Las operaciones de creación se definirán mediante constructores para cada una
 * de las
 * implementaciones posibles de la interfaz (dado que no es posible definir un
 * constructor en una
 * interfaz en Java).
 * La interfaz 'QueryDepot' utiliza la clase Query
 */
public class Query implements Comparable<Query>, ComparatorIF<Query> {

    private String text = "";
    private int freq = 0;

    /** Construye una nueva query con el texto pasado como parametro */
    public Query(String text) {
        this.text = text;
        this.freq = 1;
    }

    Query() {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

    /** Modifica la frecuencia de la query */
    public void setFreq(int freqParam) {
        if (freqParam >= 0) {// no aceptar negativos
            this.freq = freqParam;
        }
    }

    /**
     * Incrementa la frecuencia en uno.
     * 
     * @return la frecuencia actualizada
     */
    public int incFreq() {
        this.freq++;
        return this.freq;
    }

    /**
     * Decrementa la frecuencia en uno.
     * 
     * @return la frecuencia actualizada
     */
    public int decFreq() {
        this.freq--;
        return this.freq;
    }

    /** Devuelve el texto de una query */
    public String getText() {
        return this.text;
    }

    /**
     * Devuelve la frecuencia de una query
     * 
     * @return Frecuencia
     */
    public int getFreq() {
        return this.freq;
    }

    /**
     * Se compara el texto para que la ordenación por defecto sea alfabética.
     * 
     * @param other
     * @return
     */
    @Override
    public int compareTo(Query other) {
        return this.text.compareTo(other.text);
    }

    /**
     * texto y frecuencia como string
     * 
     * @return
     */
    @Override
    public String toString() {
        String str = this.text + " (" + this.freq + ")";
        return str;
    }

    @Override
    public int compare(Query e1, Query e2) {
        return e1.compareTo(e2);
    }

    @Override
    public boolean lt(Query e1, Query e2) {
        return e1.compareTo(e2) < 0;
    }

    @Override
    public boolean eq(Query e1, Query e2) {
        return e1.compareTo(e2) == 0;
    }

    @Override
    public boolean gt(Query e1, Query e2) {
        return e1.compareTo(e2) > 0;
    }
}
