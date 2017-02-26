
/**
 Una operacion posible despues de haber sido realizadas varias consultas. 'F'
 <consult> conocer la frecuencia de una consulta. 'S' <pref> consultas las
 sugerencias a partir del prefijo. Ejemplo: F�caso F�casos F�coco�rallado S�ca
 
 El constructor comprueba posibles errores, y devuelve una excepcion si la
 operacion no es correcta.
 */
public class QOperacion
{

    public char f_s = ' ';
    public String strQ = "";

    public QOperacion(char f_s, String strQ) throws Exception
    {
        char aux = Character.toUpperCase(f_s);
        if (aux != 'F' && aux != 'S')
        {
            throw new Exception("tipo no es 'F' ni 'S'. Se ha recibido: " + f_s);
        }

        String strAux = strQ.trim();

        if (strAux.isEmpty())
        {
            throw new Exception("Consulta en la operacion esta en blanco");
        }

        //datos aceptados.
        this.f_s = aux;
        this.strQ = strAux;
    }

    /**
     La cadena 'strOperacion' contiene toda la informacion como
     "F coco rallado". 
     Se extraen ambos campos y se invoca al constructor de dos
     parametros.     
     * @param strOperacion
     * @throws java.lang.Exception
     */
    public QOperacion(String strOperacion) throws Exception
    {
        if (strOperacion == null)
        {
            throw new Exception("Operacion es null");
        }

        String strAux = strOperacion.trim();

        if (strAux.isEmpty())
        {
            throw new Exception("Operacion esta en blanco");
        }

        if (strAux.length() < 3)
        {//lo minimo es algo como "F c" con un espacio
            throw new Exception("Operacion no es correcta: " + strAux);
        }

        //"F coco rallado"        
        char chAux = strAux.charAt(0);
        this.f_s = Character.toUpperCase(chAux);//"F"
        this.strQ = strAux.substring(2).trim();//saltar espacio "coco rallado"

    }

    /**
     @return
     */
    @Override
    public String toString()
    {
        String str = "\"" + f_s + "\" " + strQ;
        return str;
    }
}
