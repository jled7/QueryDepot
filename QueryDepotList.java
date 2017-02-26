
import lib.ComparatorIF;
import lib.IteratorIF;
import java.util.Collections;
//import java.util.List;
import lib.ListIF;
import lib.List;

/**
 */
class QueryDepotList extends QueryDepot {

    // Atributos
    private ListIF<Query> listaQueriesSortedAlpha;// ordenada alfabeticamente
    private int numElementos = 0;

    public QueryDepotList() {
        listaQueriesSortedAlpha = new List<Query>();
    }

    /**
     * Inserta el elemento en orden de modo que la lista
     * interna esta siempre ordenada alfabeticamente.
     * 
     * @param element
     */
    public void insert(Query element) {
        Query elementFromTheList = getQuery(element.getText());

        if (elementFromTheList == null)
            listaQueriesSortedAlpha.insert(element);
        else {
            elementFromTheList.incFreq();
        }
    }

    /**
     * Busca en la lista una consulta que tenga exactamente el mismo
     * texto que 'strText'
     * 
     * @param strText
     *                @return, la consulta no null si no es encontrada.
     */
    public Query getQuery(String strText) {
        int r = 0;

        IteratorIF<Query> itQ = this.listaQueriesSortedAlpha.getIterator();
        Query qDummy = new Query(strText);// query falsa para consultar

        while (itQ.hasNext()) {
            Query qNext = itQ.getNext();

            r = qNext.compareTo(qDummy);
            if (r == 0) {// encontrada
                return qNext;
            }
        }

        // no encontrada
        return null;
    }

    /**
     * Devuelve el numero de consultas diferentes ignorando la frecuencia
     * 
     * @return
     */
    @Override
    public int numQueries() {
        return this.listaQueriesSortedAlpha.getLength();
    }

    /**
     * Devuelve la frecuencia de la consulta con el texto 'strText'
     * 
     * @param strText
     * @return la frecuencia o cero si no es encontrada.
     */
    @Override
    public int getFreqQuery(String strText) {
        Query qAux = getQuery(strText);
        if (qAux == null) {
            return 0;
        }

        // devolver frecuencia
        return qAux.getFreq();
    }

    /**
     * Devuelve todas las consultas que empiecen por 'prefix'
     * 
     * @param prefix
     * @return
     */
    @Override
    public ListIF<Query> listOfQueriesByPrefix(String prefix) {
        // crear una lista nueva vacia
        ListIF<Query> listReturn = new List<>();
        IteratorIF<Query> itQ = this.listaQueriesSortedAlpha.getIterator();

        while (itQ.hasNext()) {
            Query qNext = itQ.getNext();
            String strText = qNext.getText();
            if (strText.startsWith(prefix)) {// comienza por 'prefix'
                                             // uso metodo auxiliar para insertar por orden de frecuencia
                insertByFrec(qNext, listReturn);

                // No se porque no me cambiaba el parametro listReturn
                // Y cree otra funcion auxiliar insertByFrec2 que devolvia
                // la lista ya ordenada
                // listReturn = insertByFrec2(qNext, listReturn);

            }
        }

        return listReturn;
    }

    /**
     * Busca la consulta con el texto 'strText' e incrementa su
     * frecuencia en uno.
     * 
     * @param q
     */
    @Override
    public void incFreqQuery(String strText) {
        Query qAux = getQuery(strText);
        if (qAux == null) {// no encontrada
            return;
        }
        // incrementar frecuencia en uno
        qAux.incFreq();
    }

    /*
     * lista.dinamica.ListaDinamica.getLength
     */
    public int getLength() {
        return this.numElementos;
    }

    /**
     * Busca la consulta con el texto 'strText' y decrementa su
     * frecuencia en uno.
     * 
     * @param q
     */
    @Override
    public void decFreqQuery(String strText) {
        Query qAux = getQuery(strText);
        if (qAux == null) {// no encontrada
            return;
        }
        // decrementar frecuencia en uno
        qAux.decFreq();
    }

    /**
     * Devuelve cadena con la informacion de cada Query en la lista
     * 
     * @return
     */
    @Override
    public String toString() {
        String str = "QueryDepotList content alphabetically: \n";

        IteratorIF<Query> itQ = this.listaQueriesSortedAlpha.getIterator();
        while (itQ.hasNext()) {
            Query qNext = itQ.getNext();
            str += "  " + qNext.toString() + "\n";
        }

        return str;
    }

}// class
