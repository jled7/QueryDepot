
import comun.ComparatorIF;
import comun.IteratorIF;
import lista.ListIF;
import lista.dinamica.ListaDinamica;

/**
 * Nos interesa conocer el texto de la consulta (una cadena de caracteres) junto
 * con su frecuencia (el número de veces que ese texto aparece en el registro de
 * búsquedas). Por simplicidad, para esta práctica se considerará que dos
 * consultas son la misma si las cadenas de caracteres de ambas son idénticas.
 * Por ejemplo, las consultas "camion rojo" y "camión rojo" se considerarán
 * diferentes. --------- Se emplea clase abstracta en lugar de interface para
 * poder incluir metodos comunes para las clases descendientes.
 */
public abstract class QueryDepot {

    private static ListIF<Query> listaQueriesSortedFrec;//(axiliar)ordenada por frecuencia 

    /**
     * Devuelve el número de consultas diferentes (sin contar repeticiones) que
     * hay almacenadas en el depósito
     *
     * @returns el número de consultas diferentes almacenadas
     */
    abstract public int numQueries();

    /**
     * Consulta la frecuencia de una consulta en el depósito
     *
     * @returns la frecuencia de la consulta. Si no está, devolverá 0
     * @param el texto de la consulta
     */
    abstract public int getFreqQuery(String q);

    /**
     * Dado un prefijo de consulta, devuelve una lista, ordenada por frecuencias
     * de mayor a menor, de todas las consultas almacenadas en el depósito que
     * comiencen por dicho prefijo
     *
     * @returns la lista de consultas ordenada por frecuencias y orden
     * lexicográfico en caso de coincidencia de frecuencia
     * @param el prefijo
     */
    abstract public ListIF<Query> listOfQueriesByPrefix(String prefix);

    /**
     * Incrementa en uno la frecuencia de una consulta en el depósito Si la
     * consulta no existía en la estructura, la deberá añadir
     *
     * @param el texto de la consulta
     */
    abstract public void incFreqQuery(String q);

    /**
     * Decrementa en uno la frecuencia de una consulta en el depósito Si la
     * frecuencia decrementada resultase ser 0, deberá eliminar la información
     * referente a la consulta del depósito
     *
     * @precondición la consulta debe estar ya en el depósito
     * @param el texto de la consulta
     */
    abstract public void decFreqQuery(String q);

    /**
     * Inserta el elemento en orden por frecuencia Decreciente (de mayor a
     * menor). El parametro 'listaQF' estara siempre ordenado.
     *
     * @param element
     * @param listaQF
     */
    public static void insertByFrec(Query element, ListIF<Query> listaQF) {

        ComparatorIF comparator = new ComparatorQuery();
        boolean inserted = false;
        ListIF<Query> aux = listaQF;
        while (!aux.isEmpty()) {

            if (comparator.isGreater(aux.getFirst(), element)) {
                aux.insert(element);
                inserted = true;
                break;
            }
            aux = aux.getTail();
        }
        if(!inserted)
            aux.insert(element);
    }

}
