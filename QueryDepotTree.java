
import lib.TreeIF;
import lib.Tree;
import lib.IteratorIF;
import lib.ListIF;
import lib.List;

/**
 * int x = Character.getNumericValue(element.charAt(2));
 */
public class QueryDepotTree extends QueryDepot {

    // Atributos
    private TreeIF<Character> treeOfQueries;// {T,QueueIF<TreeIF<T>> children}

    public QueryDepotTree() {
        // crear el nodo(arbol) raiz del arbol de caracteres
        treeOfQueries = new Tree<Character>();
    }

    /**
     * @param strTxt
     * @return frecuencia de las consultas con texto 'strTxt' Si no es
     *         encontrado devuelve cero. Invoca a la funcion recursiva
     *         'getQueryRec(..)'
     */
    @Override
    public int getFreqQuery(String strTxt) {
        int val = 0;
        Query qAux = getQuery(strTxt);
        if (qAux != null) {// encontrado
            val = qAux.getFreq();
        }
        return val;
    }

    @Override
    public void incFreqQuery(String strTxt) {
        TreeIF<Character> hoja = null;
        TreeIF<Character> nodo = null;
        // (nodoActual, textoBuscado, indice, bPrefijo)
        nodo = getQueryRec(this.treeOfQueries, strTxt, 0, false);
        if (nodo != null) {// nodo encontrado
            hoja = getHojaUnderCurrent(nodo);
            int val = hoja.getRoot();
            char chData = (char) (val + 1);
            hoja.setRoot(chData);
        } else {
            System.out.println("incFreqQuery(" + strTxt + ") no encontrado");
        }
    }

    @Override
    public void decFreqQuery(String strTxt) {
        TreeIF<Character> hoja = null;
        TreeIF<Character> nodo = null;

        // (nodoActual, textoBuscado, indice, bPrefijo)
        nodo = getQueryRec(this.treeOfQueries, strTxt, 0, false);
        if (nodo != null) {// nodo encontrado
            hoja = getHojaUnderCurrent(nodo);
            int val = hoja.getRoot();
            char chData = (char) (val - 1);
            hoja.setRoot(chData);
        } else {
            System.out.println("decFreqQuery(" + strTxt + ") no encontrado");
        }
    }

    /**
     * Busca en el arbol una consulta que tenga exactamente el mismo texto que
     * 'strText'.
     *
     * @param strTxt
     *               @return, la consulta, o null si no es encontrada. Nota: Lo que
     *               devuelve es una copia del dato en el arbol como objeto Query.
     */
    public Query getQuery(String strTxt) {
        TreeIF<Character> hoja = null;
        Query qRet = null;
        TreeIF<Character> nodo = null;
        // System.out.println(treeOfQueries);
        // (nodoActual, textoBuscado, indice, bPrefijo)
        nodo = getQueryRec(this.treeOfQueries, strTxt, 0, false);

        if (nodo != null) {// nodo encontrado
            hoja = getHojaUnderCurrent(nodo);
            qRet = new Query(strTxt);
            int val = hoja.getRoot() - 32;
            qRet.setFreq(val);
        }
        return qRet;
    }

    /**
     * Devuelve una cola con todas las consultas que empiezan con el prefijo
     *
     * @param prefix
     * @return
     */
    @Override
    public ListIF<Query> listOfQueriesByPrefix(String prefix) {
        TreeIF<Character> nodoBusca = null;
        ListIF<Query> listReturn = new List<Query>();

        // primero comprobar si la subcadena esta en el arbol
        nodoBusca = getQueryRec(this.treeOfQueries, prefix, 0, true);// getQueryRec(TreeIF,strTxt,index,bPrefix)
        if (nodoBusca != null) {// ahora tomar todas las consultas a partir de este nodo
            getAllQueriesBelowByFreq(nodoBusca, prefix, listReturn);
        }

        return listReturn;
    }

    /**
     * El numero de consultas es el numero de hojas del arbol.
     *
     * @return
     */
    @Override
    public int numQueries() {
        // uso metodo recursivo para contar los nodos sin hijos
        int cuantas = cuentaHojas(this.treeOfQueries);
        return cuantas;
    }

    /**
     * Inserta los caracteres del texto en Query de la forma:
     * (raiz)->(c)->(a)->(s)->(o)->(2) Donde el nodo raiz no tiene informacion,
     * y el nodo hoja guarda la frecuencia de la consulta (como numero en dato
     * Character).
     *
     * @param strTxt
     */
    public void insert(String strTxt) {
        // invocar metodo recursivo
        insertRec(treeOfQueries, strTxt, 0);// (currentNode,qElem,index)
    }

    /**
     * Las nuevas consultas solo necesitan el texto
     *
     * @param query
     */
    public void insert(Query query) {
        insert(query.getText());
    }

    /**
     * Metodo recursivo Inserta los caracteres del texto en Query de la forma:
     * (raiz)->(c)->(a)->(s)->(o)->(2) Donde el nodo raiz no tiene informacion,
     * y el nodo hoja guarda la frecuencia de la consulta (como numero en dato
     * Character).
     *
     * @param currentNode
     * @param qElem
     * @param index
     */
    private void insertRec(TreeIF<Character> currentNode, String strTxt, int index) {
        TreeIF<Character> hoja = null;
        int len = strTxt.length();

        // *Caso base:falta grabar la frecuencia como hoja
        if (index == len) { // comprobar si es una consulta en este punto (estamos en la ultima letra)
            hoja = getHojaUnderCurrent(currentNode);
            if (hoja == null) {
                hoja = new Tree<>();
                char chData = 33;
                hoja.setRoot(chData);
                currentNode.addChild(hoja);
            } else {
                // int val = hoja.getRoot();
                // char chData = (char) (val + 1);
                // hoja.setRoot(chData);
                incFreqQuery(strTxt);
            }

            // A IMLEMENTAR POR CADA UNO/
            // A IMLEMENTAR POR CADA UNO/A
            return;
        }

        // *Caso recursivo: insertar caracter como hijo y continuar hacia abajo
        // siguiente caracter a guardar
        Character chData = strTxt.charAt(index);

        // buscar letra 'chData' como descendiente de 'currentNode'
        ListIF<TreeIF<Character>> childrenOfCur = currentNode.getChildren();
        TreeIF<Character> childBuscado = getVertice(chData, childrenOfCur);// busqueda lineal
        if (childBuscado == null) { // crear nuevo nodo con{chData, cola<Tree> vacia}
            childBuscado = new Tree<Character>(chData);
            currentNode.addChild(childBuscado);
        }
        // ahora el actual es el nuevo nodo
        currentNode = childBuscado;
        // ==Llamada recursiva=\\
        insertRec(currentNode, strTxt, index + 1);
        // ====================//
    }

    /**
     * Metodo recursivo Busca una consulta siguiendo los caracteres hasta el
     * fondo del arbol. Es similar al de insercion recursivo, pero esta vez solo
     * lo recorre hasta llegar a un camino sin salida o la hoja buscada.
     *
     * @param currentNode
     * @param strTxt,     texto de la consulta
     * @param index,      indice de cada caracter en 'strTxt'
     * @param bPrefix,    si es true se devuelve el nodo con la ultima letra sin
     *                    comprobar que es consulta completa.
     *                    @return, el nodo justo sobre la hoja con la frecuencia de
     *                    la consulta, o
     *                    null si no es encontrada.
     */
    private TreeIF<Character> getQueryRec(TreeIF<Character> currentNode, String strTxt, int index, boolean bPrefix) {
        TreeIF<Character> hoja = null;
        int len = strTxt.length();
        // *Casos base, la frecuencia como hoja
        if (index == len) {
            if (bPrefix) {// solo se busca una parte de la consulta
                return currentNode;
            }

            // comprobar si es una consulta en este punto (estamos en la ultima letra)
            hoja = getHojaUnderCurrent(currentNode);
            if (hoja != null) { // La consulta existe

                return currentNode;
            } else {// no encontrada
                return null;
            }
        }

        // *Caso recursivo: insertar caracter como hijo y continuar hacia abajo
        // siguiente caracter a guardar
        Character chData = strTxt.charAt(index);

        // buscar letra 'chData' como descendiente de 'currentNode'
        ListIF<TreeIF<Character>> childrenOfCur = currentNode.getChildren();
        TreeIF<Character> childBuscado = getVertice(chData, childrenOfCur);// busqueda lineal
        if (childBuscado == null) { // crear nuevo nodo con{chData, cola<Tree> vacia}
            return null;
        }

        // ahora el actual es el nuevo nodo
        currentNode = childBuscado;
        // ==Llamada recursiva=\\
        return getQueryRec(currentNode, strTxt, index + 1, bPrefix);
        // ====================//
    }

    /**
     * Metodo recursivo Similar a 'getQueryRec(..)' pero esta vez guarda todos
     * las consultas encontradas en cualquier nivel bajo 'currentNode'. El nodo
     * 'currentNode' seria el que cumple con la cadena 'strPrefix'
     *
     * @param currentNode
     * @param strTxtGrow, el texto va creciendo con cada letra encontrada hasta
     *                    llegar a una hoja
     * @param listParam,  lista con las consultas encontradas
     */
    private void getAllQueriesBelowByFreq(TreeIF<Character> currentNode, String strTxtGrow, ListIF<Query> listParam) {
        // *Casos base, llegado a hoja
        if (currentNode.isLeaf()) { // guardar la consulta como objeto 'Query'
            Query qNew = new Query(strTxtGrow);
            int val = currentNode.getRoot() - 32; // OJO!!
            qNew.setFreq(val);
            // uso metodo auxiliar para insertar por orden de frecuencia
            insertByFrec(qNew, listParam);
            return;// fin de la rama
        }

        // *Caso recursivo: insertar caracter como hijo y continuar hacia abajo
        // buscar letra 'chData' como descendiente de 'currentNode'
        ListIF<TreeIF<Character>> childrenOfCur = currentNode.getChildren();
        IteratorIF<TreeIF<Character>> itChildren = childrenOfCur.getIterator();
        while (itChildren.hasNext()) {// explorar todas las ramass
            TreeIF<Character> child = itChildren.getNext();
            String strChild = strTxtGrow;
            // siguiente caracter a guardar
            if (!child.isLeaf()) {
                Character chData = child.getRoot();
                strChild += chData;// "cas"+'o' -> "caso"
            }
            // ==Llamada recursiva=\\
            getAllQueriesBelowByFreq(child, strChild, listParam);
            // ====================//
        }
    }

    /**
     * Si el nodo 'currentNode' es una consulta tendra un hijo inmediatamente
     * debajo con el numero que indica la frecuencia como hoja.
     *
     * @param currentNode
     *                    @return, el nodo hoja con la frecuencia o null si no
     *                    existe.
     */
    private TreeIF<Character> getHojaUnderCurrent(TreeIF<Character> currentNode) {
        TreeIF<Character> hoja;
        ListIF<TreeIF<Character>> childrenOfCur = currentNode.getChildren();
        IteratorIF<TreeIF<Character>> itChildren = childrenOfCur.getIterator();
        while (itChildren.hasNext()) {
            hoja = itChildren.getNext();
            if (hoja.isLeaf()) {
                return hoja;
            }
        }
        // A IMLEMENTAR POR CADA UNO/A
        // A IMLEMENTAR POR CADA UNO/A
        // A IMLEMENTAR POR CADA UNO/A
        // A IMLEMENTAR POR CADA UNO/A
        // A IMLEMENTAR POR CADA UNO/A
        // A IMLEMENTAR POR CADA UNO/
        // A IMLEMENTAR POR CADA UNO/A
        // A IMLEMENTAR POR CADA UNO/A
        // A IMLEMENTAR POR CADA UNO/
        // A IMLEMENTAR POR CADA UNO/A

        return null;
    }

    /**
     * Metodo recursivo Cuenta las hojas del arbol sabiendo que cada una es una
     * consulta (raiz)->(c)->(a)->(s)->(o)->(2) Donde el nodo raiz no tiene
     * informacion, y el nodo hoja guarda la frecuencia de la consulta (como
     * numero en dato Character).
     *
     * @param currentNode
     * @param qElem
     * @param index
     */
    private int cuentaHojas(TreeIF<Character> currentNode) {
        int accValue = 0;// sera la suma de los hijos hoja

        // *Caso base: ultimo caracter ya fue guardado
        if (currentNode.isLeaf()) {// hoja (nodo con la frecuencia de la consulta)
            return 1;
        }

        // *Caso recursivo: insertar caracter como hijo y continuar hacia abajo
        ListIF<TreeIF<Character>> childrenOfCur = currentNode.getChildren();
        IteratorIF<TreeIF<Character>> itChildren = childrenOfCur.getIterator();
        while (itChildren.hasNext()) {
            TreeIF<Character> child = itChildren.getNext();
            // ==Llamada recursiva=\\
            accValue += cuentaHojas(child);// acumular hojas bajo este nodo
            // ====================//
        }

        return accValue;
    }

    /**
     * Busca el nodo (arbol/vertice) con el dato 'info' en la lista 'listParam'
     *
     * @param info
     * @param listParam
     *                  @return, elemento Tree, o null si no es encontrado
     */
    private TreeIF<Character> getVertice(Character info, ListIF<TreeIF<Character>> listParam) {

        IteratorIF<TreeIF<Character>> itArbol = listParam.getIterator();

        while (itArbol.hasNext()) {
            TreeIF<Character> arbolAux = itArbol.getNext();
            if (arbolAux.getRoot().equals(info)) {
                return arbolAux;
            }

        }

        return null;
    }

    /**
     * @return, Devuelve cadena con la informacion de cada Query en el arbol
     */
    @Override
    public String toString() {
        String str = "QueryDepotTree content by freq: \n";

        ListIF<Query> listAllQ = new ListaDinamica<Query>();

        // Tomar todas las consultas a partir de la raiz
        getAllQueriesBelowByFreq(this.treeOfQueries, "", listAllQ);

        IteratorIF<Query> itQ = listAllQ.getIterator();
        while (itQ.hasNext()) {
            Query qNext = itQ.getNext();
            str += "  " + qNext.toString() + "\n";
        }

        return str;
    }

}
