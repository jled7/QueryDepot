
import lib.QueueIF;
import lib.Queue;
import lib.IteratorIF;
import lib.ListIF;
import java.io.*;

/**
 * La clase QueryMain contiene la función principal main()
 */
class QueryMain {

    private final static int REP = 10000;// repeticiones para medidas de tiempo
    //
    private QueryDepotList listaDepotConsultas = null;
    private QueryDepotTree treeDepotConsultas = null;
    private Configuracion config;
    private QueueIF<QOperacion> colaOperaciones = null;
    // variables para medidas de tiempo
    private long msTardados = 0;// nanoSgFin - nanoSgInicio;

    public QueryMain(Configuracion config) throws Exception {
        // crear lista ordenada de consultas segun el texto
        this.listaDepotConsultas = new QueryDepotList();
        // crear arbol de consultas
        this.treeDepotConsultas = new QueryDepotTree();
        //
        // configuracion segun los parametros recibidos
        this.config = config;
        //
        // Las operaciones se guardan por comodidad en una cola
        this.colaOperaciones = new Queue<QOperacion>();
        // cargar fichero de consultas y crea una lista o una cola según parámetro (L/T)
        cargarConsultas(config.rutaConsultas);
        // cargar fichero de operaciones
        cargarOperaciones(config.rutaOperaciones);
    }

    /**
     * Método principal (main) -crea un objeto de la clase QueryMain (de ésta).
     * -establece el modo de trabajo según los argumentos -ejecuta las
     * consultas.
     */
    public static void main(String args[]) {
        QueryMain qm = null;

        // Comprobar los argumentos
        Configuracion config = new Configuracion();
        config.argumentosPrograma(args);
        try {
            // Crear instancia QueryMain
            qm = new QueryMain(config);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        // ==========Procesar las operaciones==========//
        String strResult = qm.procesarOperaciones();
        System.out.print(strResult);
        // =============================================//
    }

    /**
     * Hace uso de la estructura QueryDepotList para guardar las consultas a
     * partir de la información del fichero ---------- Resultado similar a:
     * Consultas almacenadas: 4. La frecuencia de "caso" es 2. ­Tiempo: 6 La
     * frecuencia de "casos" es 1. ­Tiempo: 8 La frecuencia de "coco rallado" es
     * 0. ­Tiempo: 4 La lista de sugerencias para "ca" es: "casa" con frecuencia
     * 2. "caso" con frecuencia 2. "casos" con frecuencia 1. ­Tiempo: 11
     */
    public String procesarOperaciones() {
        String str = "";
        Query qAux = null;
        int frec = 0;
        int count = 0;
        ListIF<Query> listPrefx = null;

        str += "Consultas almacenadas: ";
        count = numQueries();
        str += count + "\n";

        // operaciones a emular
        IteratorIF<QOperacion> itQ = this.colaOperaciones.getIterator();
        while (itQ.hasNext()) {
            QOperacion operation = itQ.getNext();
            if (operation.f_s == 'F') // FRECUENCIA
            {
                frec = getFreqQuery(operation.strQ);
                str += "La frecuencia de \"" + operation.strQ + "\" es " + frec + ".\n";

            } else // SUGERENCIA operation.f_s == 'S'
            {
                str += "La lista de sugerencias de mayor a menor frecuencia para \"" + operation.strQ + "\" es:\n";
                listPrefx = this.listOfQueries(operation.strQ);
                while (!listPrefx.isEmpty()) {
                    qAux = listPrefx.getFirst();
                    str += "\"" + qAux.getText() + "\" con frecuencia " + qAux.getFreq() + "\n";
                    listPrefx = listPrefx.getTail();

                }

            }
            double duration = (float) this.msTardados / (float) REP;

            str += "-Tiempo: " + String.format("%.4f", duration) + " milisegundos\n";
        } // while

        return str;
    }

    /**
     * Método común para facilitar las llamadas y las pruebas empíricas. Obtiene
     * la frecuencia de la consulta 'strTxtQuery' según la estructura de datos
     * actual, y también mide el tiempo transcurrido en milisegundos, dejándolo
     * como valor en el atributo 'nanoSgTardados'.
     *
     * @param strTxtQuery
     * @return
     */
    public int getFreqQuery(String strTxtQuery) {
        int frec = 0;
        long msInicio = 0;// tiempo inicial
        long msFin = 0;// tiempo final

        // --tiempo begin--
        msInicio = System.currentTimeMillis();
        //
        for (int i = 0; i < REP; i++) {
            if (config.chEstructura == 'L') {
                frec = this.listaDepotConsultas.getFreqQuery(strTxtQuery);
            } else// (config.chEstructura=='T')
            {
                frec = this.treeDepotConsultas.getFreqQuery(strTxtQuery);
            }
        }
        // --tiempo end--
        msFin = System.currentTimeMillis();
        this.msTardados = (msFin - msInicio);
        //
        return frec;
    }

    /**
     * /**
     * Método común para facilitar las llamadas y las pruebas empíricas. Obtiene
     * la lista de sugerencias para el prefijo 'strPrefix' según la estructura
     * de datos actual, y también mide el tiempo transcurrido en milisegundos,
     * dejándolo como valor en el atributo 'nanoSgTardados'.
     *
     * @param strPrefix
     * @return
     */
    public ListIF<Query> listOfQueries(String strPrefix) {
        long msInicio = 0;// tiempo inicial
        long msFin = 0;// tiempo final
        ListIF<Query> listPrefx = null;

        // --tiempo begin--
        msInicio = System.currentTimeMillis();
        //
        for (int i = 0; i < REP; i++) {
            if (config.chEstructura == 'L') {
                listPrefx = this.listaDepotConsultas.listOfQueriesByPrefix(strPrefix);
            } else// (config.chEstructura=='T')
            {
                listPrefx = this.treeDepotConsultas.listOfQueriesByPrefix(strPrefix);
            }

        }
        // --tiempo end--
        msFin = System.currentTimeMillis();
        this.msTardados = msFin - msInicio;
        //
        return listPrefx;
    }

    /**
     * Método común para facilitar las llamadas y las pruebas empíricas. Obtiene
     * el numero de elementos contenidos por la lista o el árbol según el
     * parámetro de entrada.
     *
     * @param strTxtQuery
     * @return
     */
    public int numQueries() {
        int count = 0;

        if (config.chEstructura == 'L') {
            count = this.listaDepotConsultas.numQueries();
            this.listaDepotConsultas.getQuery("");// cacheo
            // System.out.println(listaDepotConsultas.toString());//-debug-
        } else {
            count = this.treeDepotConsultas.numQueries();
            this.treeDepotConsultas.getQuery("");// cacheo
            // System.out.println(treeDepotConsultas.toString());//-debug-
        }

        return count;
    }

    /**
     * Lee las lineas de las consultas y las guarda en la lista de cadenas
     * 'listaConsultas'.
     *
     * @param rutaFichero
     * @throws java.lang.Exception
     */
    public final void cargarConsultas(String rutaFichero) throws Exception {

        BufferedReader br;
        FileReader fr = new FileReader(rutaFichero);
        br = new BufferedReader(fr);
        int nLinea = 0;// contador de linea del fichero

        // asegurar contenedores estan vacios
        this.listaDepotConsultas = new QueryDepotList();
        this.treeDepotConsultas = new QueryDepotTree();

        // bucle de lectura
        String str = br.readLine();
        while (str != null) {
            nLinea++;
            str = str.trim();

            if (str.startsWith("#") || str.isEmpty()) {// ignorar comentarios o lineas en blanco
                str = br.readLine();
                continue;
            }

            // los datos son todos lineas de caracteres
            Query qNew = new Query(str);
            if (config.chEstructura == 'L') {
                this.listaDepotConsultas.insert(qNew);
            } else {// config.chEstructura == 'T'
                this.treeDepotConsultas.insert(qNew);
            }

            // leer otra linea del fichero
            str = br.readLine();
        } // while

        // cerrar fichero
        br.close();

    }

    /**
     * Lee las lineas de las operaciones y las guarda en la lista de cadenas
     * 'listaOperaciones'.
     *
     * @param rutaFichero
     * @throws java.lang.Exception ---- Nota: The unicode character \u0160 is
     *                             not a non-breaking space. After the "\\u" there
     *                             must be the hexadecimal
     *                             representation of the character not the decimal,
     *                             so the unicode for
     *                             non-breaking space is \u00A0. Try using: string =
     *                             string.replace("\u00A0","");
     */
    public final void cargarOperaciones(String rutaFichero) throws Exception {
        BufferedReader br;
        // Cambio este codigo porque FileReader coge el encoding base del sistema que es
        // mi caso es UTF-8
        // Y Deberia ser ISO-8859
        // FileReader fr = new FileReader(rutaFichero);
        InputStreamReader is = new InputStreamReader(new FileInputStream(rutaFichero), "ISO-8859-15");
        br = new BufferedReader(is);
        int nLinea = 0;// contador de linea del fichero

        // asegurar lista es nueva y vacia
        this.colaOperaciones = new Queue<QOperacion>();

        // bucle de lectura
        String str = br.readLine();
        while (str != null) {
            if (str.startsWith("#"))
                // ignore
                ;
            else {
                // System.out.println("Operation: " + str.charAt(0) + "\n" + str.substring(2));
                this.colaOperaciones.add(new QOperacion(str.charAt(0), str.substring(2)));
            }
            str = br.readLine();
        } // while

        // cerrar fichero
        br.close();
    }

}
