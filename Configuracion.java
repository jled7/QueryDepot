
/**
 El programa recibirá tres parámetros de entrada que determinarán su comportamiento. 
 El orden y significado de los parámetros será el siguiente:
 1. Selección de la estructura de datos que se desea utilizar, 
 que tendrá únicamente dos valores válidos:
 'L' para utilizar la implementación mediante una lista.
 'T' para utilizar la implementación mediante un árbol.
 2. Fichero de consultas, que contendrá el nombre del fichero de consultas con el que se
 construirá el depósito.
 3. Fichero de operaciones, que contendrá el nombre del fichero de operaciones que se desean
 realizar sobre el contenido del depósito.
 *
 java ­jar eped2015.jar <estructura> <consultas> <operaciones>
 */
import java.io.*;

/**
 Esta clase lee y analiza los datos de entrada.
 */
public class Configuracion
{

    public char chEstructura = ' ';
    public String rutaConsultas = ""; //"consultas.txt"
    public String rutaOperaciones = "";//"operaciones.txt"

    /**
     Comprobar los argumentos (son tres obligatorios)
     java QueryMain fichero_consultas fichero_operaciones
     Ej: $> java QueryMain L consultas.txt operaciones.txt
     */
    public void argumentosPrograma(String args[])
    {
        File fileCheck;

        if (args.length < 3)
        {
            System.out.println("'\nFaltan argumentos");
            ayuda();
            System.exit(1);
        }

        try
        {
            chEstructura = args[0].toUpperCase().charAt(0); //es un solo caracter
            switch (chEstructura)
            {
                case 'L':
                    //System.out.println("'" + chEstructura + "' QueryDepotList");
                    break;
                case 'T':
                    //System.out.println("'" + chEstructura + "' QueryDepotTree");
                    break;
                default:
                    throw new Exception("La estructura debe ser 'L' / 'T'");
            }            

            //fichero_entrada consultas
            rutaConsultas = args[1];
            fileCheck = new File(rutaConsultas);
            if (!fileCheck.exists())
            {
                throw new Exception("El fichero de consultas: " + rutaConsultas + " no existe");
            }

            //fichero_entrada operaciones
            rutaOperaciones = args[2];
            fileCheck = new File(rutaOperaciones);
            if (!fileCheck.exists())
            {
                throw new Exception("El fichero de operaciones: " + rutaOperaciones + " no existe");
            }

        }
        catch (Exception ex)
        {
            System.out.println("\nFallo leyendo argumentos: " + ex.getMessage());
            ayuda();
            System.exit(1);
        }

    }

    /**
     Muestra la sintaxis para invocar al programa
     Comprobar los argumentos
     java ­jar eped2015.jar <estructura> <consultas> <operaciones>
     Ej: $> java QueryMain L consultas.txt operaciones.txt
     */
    static void ayuda()
    {
        String strhelp = "\nQueryMain.jar/class \n"
                + "(Estrategias de Programacion y Estructuras de Datos. Curso 2014-2015).\n"
                + "Sintaxis:\n JAVA QueryMain L/T fichero_consultas fichero_operaciones\n"
                + "  L (emplear implementacion de Lista), T(emplear implementacion de arbol)\n"
                + "  <fichero_consultas> listado de consultas (obligatorio).\n"
                + "  <fichero_operaciones> listado de operaciones (obligatorio).\n";

        System.out.println(strhelp);

    }
}
