
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import lib.ListIF;

/**
 *
 * @author User
 */
public class WebApplet extends JApplet {

    private JTextField textArea = new JTextField(20);
    private JButton button = new JButton("Search");
    private QueryDepotTree treeDepotConsultas = null;

    public final void cargarConsultas(String rutaFichero)
            throws FileNotFoundException, IOException, URISyntaxException {
        // URL url = new
        // URL("http://www.ietf.org/id/draft-hoehrmann-cp-collation-01.txt");

        // File archivo = new File(url.toURI());
        BufferedReader br;

        // FileReader fr = new FileReader(archivo);
        br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(consulta.getBytes())));
        int nLinea = 0;// contador de linea del fichero

        // asegurar contenedores estan vacios
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
            this.treeDepotConsultas.insert(qNew);

            // leer otra linea del fichero
            str = br.readLine();
        } // while
        JOptionPane.showMessageDialog(this, "Consultas Cargadas!");
        // cerrar fichero
        br.close();

    }

    public void init() {

        // AccessController.doPrivileged(new PrivilegedAction() {
        // public Object run() {
        try {
            cargarConsultas("JdP-consultas.txt");
        } catch (Exception ex) {
            Logger.getLogger(WebApplet.class.getName()).log(Level.SEVERE, null, ex);
        }
        // return null;
        // }
        // });

        // constructs the GUI
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(textArea);
        // getContentPane().add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    // buttonActionPerformed(evt);
                    getAppletContext().showDocument(
                            new URL("https://www.google.com/search?q=" + textArea.getText() + "&ie=utf-8&oe=utf-8"));
                } catch (MalformedURLException ex) {
                    Logger.getLogger(WebApplet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        textArea.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change
                // body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change
                // body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                String text = textArea.getText();
                if ((keyCode != 27 && keyCode != 10)) {
                    buttonActionPerformed(null);
                }
            }
        });
    }

    public void buttonActionPerformed(ActionEvent ev) {
        String texto = textArea.getText();
        if (texto.isEmpty())
            texto = " ";
        ListIF<Query> queries = this.treeDepotConsultas.listOfQueriesByPrefix(texto);
        String querys = queries.toString();
        querys = querys.substring(17, querys.length() - 1);
        String[] arrayTheQuerys = querys.split(",");

        try {
            getAppletContext().showDocument(new URL("javascript:clear();"));
            for (int i = 0; i < arrayTheQuerys.length; i++) {
                String tmp = arrayTheQuerys[i];
                getAppletContext().showDocument(new URL("javascript:add(\"" + tmp + "\");"));
            }
            // getAppletContext().showDocument(new
            // URL("javascript:show("+arrayTheQuerys+");"));
            // JSObject jsObj = JSObject.getWindow(this);
            // calls Javascript method and gets return value
            // jsObj.call("show",null);
            // Object result = jsObj.call("show", new Integer[] {1,2});
            // JOptionPane.showMessageDialog(this, "Sum is " + texto);
        } catch (Exception ex) {
            Logger.getLogger(WebApplet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    String consulta = "pre0\n" +
            "prea\n" +
            "pre4\n" +
            "preb\n" +
            "prec\n" +
            "pre4\n" +
            "pre4\n" +
            "pre01\n" +
            "pre02\n" +
            "pre03\n" +
            "pre04\n" +
            "pre05\n" +
            "pre06\n" +
            "pre07\n" +
            "pre08\n" +
            "pre09\n" +
            "pre10\n" +
            "pre11\n" +
            "pre12\n" +
            "pre13\n" +
            "pre14\n" +
            "pre15\n" +
            "pre16\n" +
            "pre17\n" +
            "pre18\n" +
            "pre19\n" +
            "pre20\n" +
            "pre21\n" +
            "pre22\n" +
            "pre23\n" +
            "pre24\n" +
            "pre25\n" +
            "pre26\n" +
            "pre27\n" +
            "pre28\n" +
            "car tree\n" +
            "car over tree\n" +
            "car tree sculture\n" +
            "car tree horses\n" +
            "car tree horses\n" +
            "car on tree\n" +
            "car on tree\n" +
            "car tree\n" +
            "car tree\n" +
            "car tree\n" +
            "car tree\n" +
            "auto baum\n" +
            "auto baum pferd\n" +
            "auto baum\n" +
            "auto baum\n" +
            "auto baum\n" +
            "auto\n" +
            "glasses book\n" +
            "glasses book\n" +
            "glasses book\n" +
            "glasses book\n" +
            "gafas libro\n" +
            "gafas libro\n" +
            "tree\n" +
            "esel\n" +
            "esel winter\n" +
            "esel winter schnee\n" +
            "esel winter schnee liebe\n" +
            "esel winter schnee landschaft\n" +
            "esel winter schnee landschaft\n" +
            "esel winter schnee landschaft\n" +
            "esel winter schnee\n" +
            "esel winter schnee\n" +
            "esel winter schnee tier\n" +
            "esel winter schnee tier\n" +
            "esel winter schnee tier wiese\n" +
            "esel schnee\n" +
            "esel schnee\n" +
            "esel schnee\n" +
            "esel schnee\n" +
            "tier schnee\n" +
            "tier schnee esel\n" +
            "schnee esel\n" +
            "schnee esel\n" +
            "schnee esel\n" +
            "schnee esel\n" +
            "schnee esel\n" +
            "landschaft baum luftballon\n" +
            "landschaft baum luftballon\n" +
            "wiese baum luftballon\n" +
            "wiese baum luftballon\n" +
            "foglio punizione\n" +
            "foglio punizione\n" +
            "foglio punizione\n" +
            "foglio punizione\n" +
            "foglio punizione\n" +
            "foglio punizione\n" +
            "foglio punizione\n" +
            "fiche de la peine\n" +
            "fiche peine\n" +
            "fiche peine\n" +
            "fiche peine\n" +
            "fiche\n" +
            "fiche\n" +
            "fiche\n" +
            "touch sreen\n" +
            "cat basket cats miao\n" +
            "cat basket cats miao 2875619565149c6e26ca\n" +
            "cat basket cats miao black splash spot\n" +
            "cat basket cats miao black splash spot\n" +
            "cat basket cats\n" +
            "cat basket cats black\n" +
            "cat basket cats black spot\n" +
            "cat basket cats black stain\n" +
            "cat basket cats black stain\n" +
            "cat basket black stain\n" +
            "cat in basket black stain\n" +
            "cat in basket black stain\n" +
            "cat in basket black\n" +
            "pencil red\n" +
            "cat in basket black fleck\n" +
            "cat basket black fleck\n" +
            "cat basket\n" +
            "cat basket\n" +
            "pencil red front\n" +
            "pencil red front\n" +
            "cat basket\n" +
            "cat basket\n" +
            "cat basket\n" +
            "rot buntstift schatten\n" +
            "gatta cestino\n" +
            "gatta cestino\n" +
            "rot holzfarbstift stilleben\n" +
            "rot holzfarbstift stillleben\n" +
            "gatta cestino\n" +
            "rot holzfarbstift\n" +
            "rot holzmalstift\n" +
            "rot holzstift\n" +
            "gatta cestino nero nera macchia macchiato\n" +
            "red pencil front view\n" +
            "gatta cestino nero nera macchia macchiato\n" +
            "red pencil\n" +
            "gatta cestino nero nera macchia macchiato\n" +
            "red pencil\n" +
            "red pencil\n" +
            "red pencil\n" +
            "red pencil\n" +
            "red pencil shadow white\n" +
            "red pencil shadow white\n" +
            "cat white black basket\n" +
            "red pencil shadow white\n" +
            "cat whiteblack basket\n" +
            "cat white black basket\n" +
            "red pencil\n" +
            "cat white black basket\n" +
            "cat white black basket\n" +
            "red pencil\n" +
            "cat white black basket\n" +
            "red pencil\n" +
            "red pencil\n" +
            "cat white black splash basket\n" +
            "cat splash basket\n" +
            "whitecat basket\n" +
            "roter buntstift\n" +
            "roter holzstift\n" +
            "whitecat basket\n" +
            "roter malstift\n" +
            "rot stift schatten\n" +
            "whitecat basket\n" +
            "rot stift schatten lapiz\n" +
            "rot lapiz\n" +
            "rot lapiz\n" +
            "rot lapiz\n" +
            "rot lapiz\n" +
            "whitecat basket eye\n" +
            "rot lapiz\n" +
            "rot lapiz\n" +
            "gatto macchia occhio\n" +
            "gatto cestino macchia occhio\n" +
            "lapiz rosa\n" +
            "lapiz rosa\n" +
            "gatto bianco macchia occhio\n" +
            "lapiz rosa schatten\n" +
            "gatto bianco macchia occhio\n" +
            "lapiz rosa weiss\n" +
            "gatto bianco macchia occhio\n" +
            "lapiz rosa vida\n" +
            "gatto bianco macchia occhio\n" +
            "whitecat cat basket\n" +
            "whitecat basket spotted\n" +
            "whitecat basket spotted\n" +
            "cat basket spotted\n" +
            "cat white basket spotted\n" +
            "cat white basket spotted\n" +
            "cat white basket\n" +
            "cat white basket\n" +
            "cat white basket\n" +
            "cat white basket\n" +
            "cat white basket\n" +
            "cat white basket\n" +
            "cat white basket\n" +
            "cat white basket\n" +
            "cat white basket\n" +
            "cat white basket\n" +
            "cat white wicker basket\n" +
            "cat white wicker basket\n" +
            "cat white wicker basket\n" +
            "cat white wicker basket\n" +
            "cat\n" +
            "cat white wicker basket\n" +
            "cat\n" +
            "cat basket\n" +
            "cat white wicker basket\n" +
            "cat white wicker basket\n" +
            "cat white wicker basket\n" +
            "cat basket\n" +
            "cat white wicker basket\n" +
            "cat basket\n" +
            "cat white wicker basket alone nice sleeping\n" +
            "cat white wicker basket alone\n" +
            "cat basket\n" +
            "cat wicker basket\n" +
            "cat basket\n" +
            "cat wicker basket\n" +
            "korb katze\n" +
            "cat wicker basket\n" +
            "korb katze\n" +
            "korb katze\n" +
            "korb katze\n" +
            "cat basket\n" +
            "sea beach woman smile racket\n" +
            "gatto cesta gatto cesta\n" +
            "sea beach woman smile\n" +
            "gatto cesta gatto cesta\n" +
            "sea beach woman smile\n" +
            "sea beach woman smile\n" +
            "sea beach woman smile\n" +
            "sea beach woman play playing\n" +
            "sea beach woman play racket\n" +
            "sea beach woman play tennis\n" +
            "sea beach woman play tennis\n" +
            "sea beach woman play match\n" +
            "sea beach woman man play match\n" +
            "sea beach woman man double play match\n" +
            "sea beach woman man double play match matkot\n" +
            "matkot\n" +
            "matkot\n" +
            "matkot\n" +
            "matkot girl woman\n" +
            "matkot girl woman beach paddle ball tennis\n" +
            "matkot girl woman beach ball tennis\n" +
            "girl woman beach ball tennis\n" +
            "matkot girl woman beach ball tennis\n" +
            "matkot girl woman beach tennis\n" +
            "matkot girl woman beach\n" +
            "matkot girl woman\n" +
            "matkot girl\n" +
            "matkot girl\n" +
            "matkot girl\n" +
            "matkot girl\n" +
            "matkot girl\n" +
            "matkot\n" +
            "matkot girl\n" +
            "matkot girl racchettoni\n" +
            "matkot girl racchettoni\n" +
            "matkot girl racchettoni\n" +
            "matkot girl racchettoni\n" +
            "controluce racchettoni\n" +
            "controluce racchettoni\n" +
            "racchettoni\n" +
            "racchettoni\n" +
            "racchettoni\n" +
            "racchettoni\n" +
            "racchettoni ragazza spiaggia\n" +
            "racchettoni ragazza spiaggia\n" +
            "racchettoni ragazza spiaggia tennis\n" +
            "girl women beach tennis ball\n" +
            "girl women beach ball\n" +
            "girl women beach ball racket racquet\n" +
            "girl women beach racket racquet\n" +
            "girl women beach racket\n" +
            "girl women beach racquet\n" +
            "girl women beach\n" +
            "girl women beach\n" +
            "girl women beach\n" +
            "girl woman beach\n" +
            "girl woman beach racket\n" +
            "sneeze\n" +
            "sneeze\n" +
            "sneeze\n" +
            "girl woman beach racket ball squash\n" +
            "sneeze\n" +
            "girl woman beach racket ball squash\n" +
            "girl woman beach racket squash\n" +
            "sneeze\n" +
            "girl woman beach racket\n" +
            "cold\n" +
            "cold\n" +
            "girl woman beach racket clouds\n" +
            "sick girl\n" +
            "ill girl\n" +
            "double beach girl woman\n" +
            "ill girl\n" +
            "ill girl\n" +
            "double beach girl woman\n" +
            "ill girl\n" +
            "ill girl\n" +
            "double beach girl woman\n" +
            "double beach girl woman\n" +
            "ill girl selfportrait\n" +
            "ill girl selfportrait\n" +
            "ill girl selfportrait\n" +
            "ill girl selfportrait\n" +
            "fille\n" +
            "ill girl selfportrait\n" +
            "fille plage\n" +
            "ill girl selfportrait\n" +
            "fille plage raquette\n" +
            "ill girl selfportrait\n" +
            "ill girl selfportrait\n" +
            "ill girl selfportrait\n" +
            "fille plage contrer\n" +
            "fille plage double\n" +
            "fille plage\n" +
            "mouchoir\n" +
            "fille plage sport\n" +
            "mouchoir\n" +
            "mouchoir\n" +
            "mouchoir\n" +
            "mouchoir\n" +
            "mouchoir\n" +
            "mouchoir\n" +
            "mouchoir\n" +
            "mouchoir\n" +
            "fille plage beach tennis\n" +
            "mouchoir\n" +
            "mouchoir\n" +
            "fille plage beach ball\n" +
            "donkey\n" +
            "fille plage beach ball\n" +
            "fille plage beach ball\n" +
            "donkey snow\n" +
            "fille plage beach ball\n" +
            "fille plage\n" +
            "mouchoir\n" +
            "fille plage ball tennis\n" +
            "mouchoir\n" +
            "plage ball tennis\n" +
            "plage\n" +
            "plage tennis\n" +
            "thorns wall\n" +
            "plage tennis de\n" +
            "thorns wall\n" +
            "plage tennis de\n" +
            "thorns wall\n" +
            "plage tennis de\n" +
            "thorns wall\n" +
            "plage tennis\n" +
            "thorns wall\n" +
            "plage tennis mer\n" +
            "plage tennis mer\n" +
            "thorns wall\n" +
            "plage tennis mer\n" +
            "thorns wall\n" +
            "thorns wall\n" +
            "thorns wall\n" +
            "plage tennis mer jouer\n" +
            "plage tennis jouer\n" +
            "thorns wall\n" +
            "plage jouer\n" +
            "plage jouer\n" +
            "donkey snow\n" +
            "plage jouer\n" +
            "plage jouer\n" +
            "donkey snow\n" +
            "plage jouer\n" +
            "donkey snow\n" +
            "plage jouer\n" +
            "plage jouer\n" +
            "plage jouer\n" +
            "plage jouer\n" +
            "plage jouer raquette\n" +
            "plage raquette\n" +
            "esel\n" +
            "esel\n" +
            "esel\n" +
            "esel schnee\n" +
            "esel schnee\n" +
            "snow white chair\n" +
            "esel schnee\n" +
            "esel schnee\n" +
            "esel schnee\n" +
            "esel schnee\n" +
            "snow white chair\n" +
            "esel schnee\n" +
            "snow white chair nice strange\n" +
            "snow white chair strange\n" +
            "esel schnee liebe\n" +
            "snow white chair strange\n" +
            "snow white chair strange\n" +
            "snow white chair\n" +
            "burro\n" +
            "snow white chair snowy\n" +
            "burro\n" +
            "burro\n" +
            "snow white chair snowy\n" +
            "snow white chair snowy\n" +
            "donkey\n" +
            "snow white chair snowy\n" +
            "donkey\n" +
            "donkey\n" +
            "donkey\n" +
            "snow white chair snowy\n" +
            "donkey\n" +
            "snow white chair snowy outdoor\n" +
            "donkey winter\n" +
            "white chair snowy outdoor\n" +
            "donkey winter\n" +
            "chair snowy\n" +
            "donkey winter\n" +
            "chair snowy outdoor\n" +
            "donkey winter\n" +
            "donkey winter\n" +
            "donkey winter\n" +
            "chair snowy outdoor\n" +
            "chair snowy outdoor\n" +
            "chair snowy outdoor\n" +
            "chair snowy\n" +
            "donkey winter\n" +
            "donkey winter\n" +
            "stuhl schneebedeckt\n" +
            "schneebedeckt\n" +
            "schneebedeckt\n" +
            "donkeys winter\n" +
            "schneebedeckt\n" +
            "donkeys snow\n" +
            "schneebedeckt\n" +
            "donkeys snow\n" +
            "schneebedeckt\n" +
            "donkeys snow\n" +
            "schneebedeckt\n" +
            "donkeys snow\n" +
            "schneebedeckt\n" +
            "schneebedeckt\n" +
            "donkeys snow\n" +
            "donkeys snow\n" +
            "schnee\n" +
            "donkeys snow\n" +
            "donkeys snow\n" +
            "donkeys snow\n" +
            "schnee stuhl\n" +
            "donkeys snow\n" +
            "donkeys snow\n" +
            "donkeys snow\n" +
            "donkeys snow\n" +
            "schnee\n" +
            "donkeys toghether\n" +
            "schnee sofa\n" +
            "donkeys together\n" +
            "donkeys together\n" +
            "donkeys together\n" +
            "neve poltrona divano innevato\n" +
            "neve poltrona divano\n" +
            "neve poltrona divano blackandwhite\n" +
            "donkeys together\n" +
            "neve poltrona divano bianco e nero\n" +
            "donkeys together\n" +
            "sofa schnee\n" +
            "sofa schnee\n" +
            "donkeys together\n" +
            "sofa schnee\n" +
            "donkeys together\n" +
            "donkeys together\n" +
            "donkeys together\n" +
            "donkeys together\n" +
            "donkeys together\n" +
            "castagno\n" +
            "donkeys together snow\n" +
            "castagno riccio frutti ricci\n" +
            "esel zusammen schnee\n" +
            "castagno riccio\n" +
            "esel zusammen schnee\n" +
            "esel zusammen schnee\n" +
            "castagno riccio\n" +
            "esel zusammen schnee\n" +
            "two donkeys\n" +
            "castagno riccio\n" +
            "two donkeys\n" +
            "castagno riccio\n" +
            "two donkeys\n" +
            "two donkeys\n" +
            "castagno riccio\n" +
            "donkey love winter\n" +
            "castagno riccio\n" +
            "donkey love winter\n" +
            "donkey love winter\n" +
            "castagno riccio\n" +
            "donkey love winter\n" +
            "castagno riccio\n" +
            "mule\n" +
            "castagno riccio\n" +
            "castagno riccio\n" +
            "castagno riccio\n" +
            "castagno riccio\n" +
            "castagno riccio\n" +
            "castagno riccio frutto\n" +
            "castagno riccio frutto\n" +
            "castagno frutto\n" +
            "riccio frutto\n" +
            "malboro\n" +
            "malboro rossetto\n" +
            "malboro mozzicone\n" +
            "goat\n" +
            "mozzicone\n" +
            "mule\n" +
            "mozzicone\n" +
            "mule\n" +
            "mule\n" +
            "mule love snow\n" +
            "mozzicone\n" +
            "mule love snow\n" +
            "mozzicone\n" +
            "donkeys farm\n" +
            "mozzicone rossetto\n" +
            "donkeys farm\n" +
            "mozzicone rossetto ghiaccio\n" +
            "donkeys farm\n" +
            "donkeys farm\n" +
            "mozzicone rossetto\n" +
            "donkeys farm\n" +
            "mozzicone rossetto\n" +
            "donkeys winter snow\n" +
            "mozzicone rossetto\n" +
            "mozzicone rossetto\n" +
            "donkeys winter snow\n" +
            "mozzicone rossetto\n" +
            "donkeys winter snow\n" +
            "mozzicone rossetto sangue\n" +
            "mozzicone sangue\n" +
            "donkeys winter snow\n" +
            "donkeys winter snow\n" +
            "mozzicone sangue\n" +
            "mozzicone sangue malboro\n" +
            "mozzicone malboro\n" +
            "donkeys winter snow\n" +
            "mozzicone marlboro\n" +
            "mozzicone marlboro sangue\n" +
            "mozzicone sangue\n" +
            "mozzicone sangue rossetto\n" +
            "mozzicone sangue sigaretta\n" +
            "donkeys winter snow\n" +
            "mozzicone sangue sigaretta rossetto\n" +
            "sigaretta rossetto\n" +
            "donkeys winter snow\n" +
            "donkeys winter snow\n" +
            "mozzicone rossetto\n" +
            "rossetto\n" +
            "donkeys winter snow\n" +
            "rossetto\n" +
            "rossetto\n" +
            "donkeys winter snow\n" +
            "rossetto ghiaccio\n" +
            "donkeys winter snow\n" +
            "mozzicone ghiaccio\n" +
            "sigaretta ghiaccio\n" +
            "snow donkeys loving\n" +
            "sigaretta blu\n" +
            "sigaretta blu\n" +
            "donkey love\n" +
            "sigaretta blu\n" +
            "donkey love\n" +
            "sigaretta blu\n" +
            "donkey warm\n" +
            "mozzicone blu\n" +
            "mozzicone blu\n" +
            "two donkey warm\n" +
            "two donkeys warm\n" +
            "mozzicone blu cigaretta\n" +
            "two donkeys snow\n" +
            "blu cigaretta\n" +
            "cigaretta\n" +
            "cigaretta\n" +
            "monet\n" +
            "monet time\n" +
            "monet time clock\n" +
            "salvador\n" +
            "salvador orologi sciolti\n" +
            "salvador dali\n" +
            "snow globe\n" +
            "salvador dali\n" +
            "snow globe santa\n" +
            "salvador dali\n" +
            "salvador dali clock\n" +
            "snow globe santa\n" +
            "snow globe santa down\n" +
            "salvador dali clock\n" +
            "snow globe santa down\n" +
            "salvador dali clock\n" +
            "snow globe santa down\n" +
            "salvador dali clock\n" +
            "snow globe red\n" +
            "salvador dali clock art\n" +
            "salvador dali clock art\n" +
            "snow globe red\n" +
            "salvador dali clock art\n" +
            "snow globe red\n" +
            "salvador dali clock art\n" +
            "snow globe red\n" +
            "salvador dali clock art\n" +
            "snow globe red\n" +
            "snow globe red\n" +
            "salvador dali clock art\n" +
            "salvador dali clock art\n" +
            "red santa snow globe\n" +
            "red santa snow globe\n" +
            "salvador dali clock art\n" +
            "red santa snow globe\n" +
            "salvador dali clock art\n" +
            "red santa snow globe\n" +
            "information visualization\n" +
            "red santa snow globe\n" +
            "salvador dali clock art\n" +
            "salvador dali clock art\n" +
            "salvador dali clock art\n" +
            "visualizacion de informacion\n" +
            "christmass snow globe\n" +
            "snow globe\n" +
            "dali clock art\n" +
            "snow globe\n" +
            "snow globe\n" +
            "dali clock art\n" +
            "airport\n" +
            "snow globe\n" +
            "dali clock art\n" +
            "snow globe\n" +
            "dali clock art\n" +
            "hall\n" +
            "water globe\n" +
            "dali clock art\n" +
            "hall phone\n" +
            "dali clock art\n" +
            "water globe santa\n" +
            "dali clock art\n" +
            "water globe santa\n" +
            "hall phone public\n" +
            "dali clock art\n" +
            "snowball globe santa\n" +
            "snow globe santa\n" +
            "dali montre clock art\n" +
            "snow globe santa tree\n" +
            "dali montre\n" +
            "snow globe santa tree\n" +
            "hall phone public red brown\n" +
            "snow globe santa tree\n" +
            "snow globe santa\n" +
            "hall light phone\n" +
            "snow globe santa claus\n" +
            "snow globe santa claus\n" +
            "hall light phone\n" +
            "snow globe santa claus\n" +
            "red snow globe santa claus\n" +
            "hall light telefon\n" +
            "schnee ball klausel\n" +
            "schnee ball klausel\n" +
            "schnee ball santa claus\n" +
            "schnee ball santa claus\n" +
            "hall light telefon public\n" +
            "snow ball santa claus\n" +
            "pasillo telefono publico\n" +
            "red\n" +
            "pasillo telefono publico luces\n" +
            "neige ball perte\n" +
            "pasillo telefono rojo publico luces\n" +
            "pasillo telefono publico luces personas\n" +
            "globe de neige rouge\n" +
            "globe de neige rouge\n" +
            "red snow globe\n" +
            "telefon rot\n" +
            "red snow globe\n" +
            "telefon rot hall\n" +
            "red snow globe\n" +
            "telefon rot hall\n" +
            "red snow globe\n" +
            "red snow globe\n" +
            "telefon rot hall light\n" +
            "red snow globe\n" +
            "red snow globe\n" +
            "telefon rot gleis\n" +
            "red snow globe\n" +
            "red snow globe\n" +
            "red snow globe\n" +
            "red snow globe\n" +
            "red snow globe santa fall\n" +
            "sillon gris chimenea\n" +
            "car horse\n" +
            "red snowglobe santa fall\n" +
            "sofa gris chimenea\n" +
            "red snowglobe\n" +
            "junk car horse\n" +
            "red snowglobe\n" +
            "junk car horse\n" +
            "sofa grigio ciminiera\n" +
            "junk car horse\n" +
            "red snowglobe\n" +
            "sofa gris chimenea\n" +
            "junk car horse\n" +
            "junk car horse tree\n" +
            "sofa gris chimenea\n" +
            "red snowglobe\n" +
            "junk car horse tree\n" +
            "junk car horse tree\n" +
            "sofa gris\n" +
            "red snowglobe\n" +
            "junk car horse tree\n" +
            "junk car\n" +
            "red snowglobe\n" +
            "junk car in tree\n" +
            "sofa gris divano\n" +
            "snata snowglobe\n" +
            "junk car in tree\n" +
            "christmass snowglobe\n" +
            "sofa grigio divano\n" +
            "christmas snowglobe\n" +
            "sofa grigio divano\n" +
            "junk car in tree\n" +
            "sofa grigio divano\n" +
            "junk car in tree horses\n" +
            "junk car tree horses\n" +
            "santa snowglobe\n" +
            "sofa grigio divano\n" +
            "car tree horses\n" +
            "sofa grigio divano\n" +
            "sofa grigio divano\n" +
            "car tree horses\n" +
            "santa snowglobe\n" +
            "sofa grigio divano\n" +
            "car tree horses\n" +
            "santa snowglobe\n" +
            "sofa grigio divano\n" +
            "sofa grigio divano\n" +
            "santa snowglobe\n" +
            "sofa grigio divano\n" +
            "sofa grigio divano\n" +
            "car tree horses\n" +
            "sofa grigio divano\n" +
            "used car horses\n" +
            "sofa grigio divano\n" +
            "red waterglobe\n" +
            "waterglobe\n" +
            "sofa grigio\n" +
            "used car tree horses\n" +
            "sofa grigio\n" +
            "waterglobe\n" +
            "sofa grigio\n" +
            "used car tree horses\n" +
            "used car tree horses\n" +
            "sofa grigio\n" +
            "santa waterglobe\n" +
            "sofa grigio\n" +
            "christmas waterglobe\n" +
            "car tree horses\n" +
            "sofa grigio\n" +
            "car tree horses\n" +
            "sofa grigio\n" +
            "car tree horses\n" +
            "fire car tree horses\n" +
            "fire car tree horses\n" +
            "fire car tree horses\n" +
            "burnt car tree horses\n" +
            "burnt car horses\n" +
            "africa car horses\n" +
            "sofa gris parque\n" +
            "africa car horses exposed\n" +
            "sofa gris parque\n" +
            "sofa gris parque\n" +
            "sofa grigio parco\n" +
            "santa falling down\n" +
            "santa falling down\n" +
            "falling down snowglobe\n" +
            "snowglobe tree santa\n" +
            "snowglobe tree santa\n" +
            "snowglobe tree santa\n" +
            "africa car horses exposed\n" +
            "africa car horses exposed\n" +
            "simple snowglobe\n" +
            "santa claus\n" +
            "auto pferd\n" +
            "auto pferd\n" +
            "santa claus falling\n" +
            "auto pferd\n" +
            "santa claus falling\n" +
            "auto pferd\n" +
            "auto pferd\n" +
            "auto pferd kamel\n" +
            "trinker bell\n" +
            "snowbell\n" +
            "water snowbell\n" +
            "christmass snow globe\n" +
            "christmass snowglobe\n" +
            "stone\n" +
            "snowglobe santa\n" +
            "stone site\n" +
            "red snowglobe santa\n" +
            "apeduct\n" +
            "snowglobe red santa\n" +
            "apeduct\n" +
            "apeduct\n" +
            "snowglobe santa\n" +
            "stone walls\n" +
            "snowglobe santa\n" +
            "stone walls\n" +
            "italian stone walls\n" +
            "rupe muro\n" +
            "italian stone walls\n" +
            "stone walls\n" +
            "stone walls\n" +
            "stone walls\n" +
            "reflection\n" +
            "snowglobe santa christmas tree\n" +
            "riflessione\n" +
            "riflessione aqua\n" +
            "snowglobe santa christmas tree\n" +
            "snowglobe santa christmas tree\n" +
            "reflection water\n" +
            "snowglobe santa christmas tree\n" +
            "riflessione orina\n" +
            "snowglobe santa christmas tree\n" +
            "riflessione acqua\n" +
            "snowglobe santa christmas tree\n" +
            "riflessione acqua\n" +
            "snowglobe santa christmas tree\n" +
            "riflessione acqua\n" +
            "riflessione acqua\n" +
            "snowglobe santa christmas tree\n" +
            "riflessione acqua\n" +
            "riflessione acqua\n" +
            "snowglobe santa\n" +
            "riflessione acqua\n" +
            "riflessione acqua\n" +
            "snowglobe santa\n" +
            "riflessione acqua\n" +
            "snowglobe santa\n" +
            "riflessione acqua\n" +
            "riflessione acqua\n" +
            "riflessione acqua\n" +
            "riflessione acqua\n" +
            "riflessione acqua\n" +
            "waterglobe\n" +
            "riflessione acqua sicily\n" +
            "riflessione column sicily\n" +
            "chiesa riflessione column sicily\n" +
            "chiesa riflessione column\n" +
            "chiesa riflessione\n" +
            "chiesa riflessione\n" +
            "chiesa riflessione\n" +
            "chiesa\n" +
            "chiesa\n" +
            "chiesa\n" +
            "chiesa\n" +
            "chiesa napoli\n" +
            "waterglobe\n" +
            "tree\n" +
            "parc\n" +
            "parc chaises\n" +
            "moon\n" +
            "lune\n" +
            "lune avion\n" +
            "addidas\n" +
            "adidas\n" +
            "adidas spiaggia\n" +
            "adidas sabbia\n" +
            "tree field\n" +
            "tree field red\n" +
            "tree field red\n" +
            "cat\n" +
            "chat\n" +
            "chat ciel\n" +
            "rote luftballon\n" +
            "chat ciel\n" +
            "rote luftballon baum\n" +
            "cat\n" +
            "head taste\n" +
            "head taste mind\n" +
            "cat panier\n" +
            "cat eye\n" +
            "libro occhiali\n" +
            "libro occhiali\n" +
            "libro occhiali\n" +
            "blue\n" +
            "blue fenetre\n" +
            "libro occhiali\n" +
            "blue fenetre\n" +
            "libro occhiali\n" +
            "chat yeu\n" +
            "libro occhiali\n" +
            "chat yeux\n" +
            "blue fenetre\n" +
            "chat yeux\n" +
            "libro occhiali\n" +
            "chat yeux\n" +
            "libro occhiali\n" +
            "libro occhiali\n" +
            "chat yeux\n" +
            "libro occhiali\n" +
            "blue fenetre\n" +
            "chat yeux\n" +
            "libro occhiali\n" +
            "chat vert yeux\n" +
            "libro occhiali\n" +
            "chat vert yeux\n" +
            "libro occhiali\n" +
            "chat vert yeux\n" +
            "libro occhiali\n" +
            "libro occhiali\n" +
            "eme\n" +
            "caramelo\n" +
            "blue fenetre\n" +
            "caramelo helado\n" +
            "blue fenetre\n" +
            "caramelo helado dibujos animados\n" +
            "blue fenetre\n" +
            "caramelo helado\n" +
            "caramelo helado manzana\n" +
            "blue fenetre\n" +
            "cat\n" +
            "blue fenetre\n" +
            "chat\n" +
            "blue fenetre\n" +
            "chat ciel\n" +
            "chat ciel\n" +
            "blue fenetre fleur\n" +
            "blue fenetre fleur\n" +
            "blue fenetre fleur\n" +
            "bombons\n" +
            "bleu fenetre fleur\n" +
            "snoepjes\n" +
            "red old car\n" +
            "red old car\n" +
            "lapiz\n" +
            "red old car\n" +
            "lapiz malva\n" +
            "lapiz malva\n" +
            "rose\n" +
            "lapiz color de malva\n" +
            "rose voiture\n" +
            "lapiz rosa\n" +
            "lapiz rosa\n" +
            "lapiz rosa\n" +
            "bike\n" +
            "fahrrad\n" +
            "fahrrad rot\n" +
            "fahrrad rot\n" +
            "fahrrad rot\n" +
            "rouge voiture\n" +
            "fahrrad rot\n" +
            "fahrrad rot\n" +
            "fahrrad rot\n" +
            "fahrrad rot\n" +
            "fahrrad rot\n" +
            "fahrrad rot\n" +
            "fahrrad rot\n" +
            "fahrrad rot\n" +
            "fahrrad rot\n" +
            "fahrrad rot\n" +
            "fahrrad blau\n" +
            "wire snow\n" +
            "fahrrad blau\n" +
            "fahrrad blau\n" +
            "fahrrad blau\n" +
            "wire snow\n" +
            "wire snow ice\n" +
            "fahrrad blau\n" +
            "wire snow ice\n" +
            "fahrrad blau\n" +
            "wire snow ice\n" +
            "fahrrad blau\n" +
            "fahrrad blau\n" +
            "draht schnee\n" +
            "fahrrad wolle\n" +
            "draht sneeuwvlok\n" +
            "sneeuwvlok\n" +
            "tree sun\n" +
            "tree sun\n" +
            "tree sun\n" +
            "cat\n" +
            "tree sun\n" +
            "tree sun\n" +
            "tree sun\n" +
            "katze ordner\n" +
            "tree sun\n" +
            "tree sun\n" +
            "tree sun\n" +
            "tree sun\n" +
            "tree sun\n" +
            "yellow flower\n" +
            "tree sun\n" +
            "tree sun\n" +
            "caracol flor\n" +
            "tree sun sky\n" +
            "caracol flor\n" +
            "tree sun sky\n" +
            "tree sun sky\n" +
            "caracol flor\n" +
            "tree sun sky\n" +
            "tree sun sky\n" +
            "tree sun sky\n" +
            "blumen fahrrad\n" +
            "tree sun nature\n" +
            "caracol flores\n" +
            "tree sun nature lucht\n" +
            "blumen fahrrad\n" +
            "tree sun nature lucht\n" +
            "blumen fahrrad\n" +
            "eme\n" +
            "bomen zon natuur lucht\n" +
            "blumen fahrrad\n" +
            "chess light lirk\n" +
            "eme\n" +
            "chess light dark\n" +
            "eme carrello spesa\n" +
            "eme spesa\n" +
            "chess light dark\n" +
            "eme spesa\n" +
            "blumen fahrrad\n" +
            "carrello spesa\n" +
            "blumen fahrrad\n" +
            "red atm guy\n" +
            "carrello spesa\n" +
            "carrello spesa\n" +
            "red guy\n" +
            "sky tree sun nature\n" +
            "carrello spesa\n" +
            "red atm\n" +
            "carrello spesa\n" +
            "sky tree sun nature\n" +
            "red atm\n" +
            "carrello spesa\n" +
            "sky tree sun nature\n" +
            "carrello spesa\n" +
            "red atm\n" +
            "carrello spesa\n" +
            "bomen zon natuur lucht\n" +
            "carrello spesa\n" +
            "red atm bancomat\n" +
            "schach\n" +
            "pubblicita eme\n" +
            "bancomat\n" +
            "red bancomat\n" +
            "schaakspel\n" +
            "perdita bancomat\n" +
            "pubblicita eme\n" +
            "rosa bancomat\n" +
            "pubblicita eme\n" +
            "rosso bancomat\n" +
            "stone walls\n" +
            "edificio\n" +
            "closed white bunny\n" +
            "purse sand\n" +
            "wasche\n" +
            "purse sand\n" +
            "trapped white bunny\n" +
            "peter pan carnival\n" +
            "purse sand\n" +
            "wasche\n" +
            "trapped white bunny\n" +
            "purse sand\n" +
            "peter pan carnival\n" +
            "trapped white bunny\n" +
            "architecture church old water sky\n" +
            "afgrond leegte\n" +
            "wasche tuch\n" +
            "peter pan carnaval\n" +
            "eme pubblicita\n" +
            "afgrond leegte rabbit\n" +
            "purse sand\n" +
            "peter pan ship carnaval\n" +
            "handtasche sand\n" +
            "eme pubblicita\n" +
            "peter pan ship\n" +
            "leegte konijn\n" +
            "architecture church old water arches\n" +
            "eme pubblicita\n" +
            "tasche sand\n" +
            "konijn\n" +
            "tasche braun\n" +
            "architecture church old water arches\n" +
            "architecture church old water arches\n" +
            "bear sailor cartoon\n" +
            "cat\n" +
            "architecture church old water arches\n" +
            "red nefertiti egypt\n" +
            "architecture church old water arches\n" +
            "nefertiti egypt\n" +
            "eme pubblicita\n" +
            "architecture church old water arches\n" +
            "nefertiti egypt\n" +
            "architecture church old water arches green\n" +
            "gatto\n" +
            "nefertiti egypt\n" +
            "architecture church old water arches green\n" +
            "nefertiti egypt\n" +
            "gatto foglia\n" +
            "eme pubblicita\n" +
            "architecture church old water arches green\n" +
            "nefertiti egypt\n" +
            "eme\n" +
            "gatto foglia\n" +
            "nefertiti egypt\n" +
            "eme\n" +
            "gatto foglia\n" +
            "architecture church old water arch green\n" +
            "eme\n" +
            "gatto foglia\n" +
            "nefertiti egypt\n" +
            "bar\n" +
            "eme\n" +
            "architecture church old water arch green\n" +
            "nefertiti egypt\n" +
            "bar\n" +
            "eme\n" +
            "architecture church old water arch green\n" +
            "nefertiti egypt\n" +
            "eme\n" +
            "gatto autunno\n" +
            "eme\n" +
            "bar wasser\n" +
            "nefertiti egypt bust\n" +
            "nefertiti egypt bust\n" +
            "eme\n" +
            "architecture gothic sky water\n" +
            "nefertiti egypt bust\n" +
            "gatto autunno\n" +
            "eme spesa\n" +
            "nefertiti egypt bust\n" +
            "gatto autunno\n" +
            "nefertiti egypt bust\n" +
            "eme supermercato\n" +
            "gatto autunno\n" +
            "nefertiti bust\n" +
            "church gothic sky water\n" +
            "gatto autunno\n" +
            "nefertiti bust\n" +
            "gatto autunno\n" +
            "ball wembley sport\n" +
            "nefertiti pleite\n" +
            "peter pan ship\n" +
            "church gothic sky water\n" +
            "nefertiti\n" +
            "gatto autunno\n" +
            "balls wembley sport\n" +
            "nefertiti rot\n" +
            "gatto autunno\n" +
            "peter pan ship\n" +
            "wasserhahn rot\n" +
            "gatto autunno\n" +
            "eme supermercato\n" +
            "balls wembley\n" +
            "church gothic sky water\n" +
            "peter pan ship\n" +
            "balls wembley\n" +
            "fruits table\n" +
            "gatto autunno foglie\n" +
            "balls wembley rugby\n" +
            "church gothic sky water\n" +
            "fruits table\n" +
            "glass\n" +
            "frucht tabelle\n" +
            "balls wembley rugby\n" +
            "glasses\n" +
            "schrank\n" +
            "pubblicita supermercato\n" +
            "balls wembley rugby\n" +
            "pubblicita supermercato\n" +
            "schrank vasen\n" +
            "balon\n" +
            "pubblicita supermercato\n" +
            "pubblicita supermercato\n" +
            "glaser tisch brille\n" +
            "pubblicita supermercato spesa\n" +
            "tisch brille\n" +
            "balones\n" +
            "couch forest winter\n" +
            "caramelo\n" +
            "couch forest winter\n" +
            "sofa forest winter\n" +
            "caramelo eme\n" +
            "dovan forest winter\n" +
            "foresta inverno couch\n" +
            "condom\n" +
            "foresta inverno sofa\n" +
            "foresta inverno divano\n" +
            "inverno divano\n" +
            "condom\n" +
            "tisch glaser\n" +
            "cane mare materassino\n" +
            "pirate ship\n" +
            "cane mare materassino\n" +
            "tisch glaser\n" +
            "cane mare materassino\n" +
            "cane mare materassino\n" +
            "tisch glaser\n" +
            "condomi\n" +
            "white cat in basket\n" +
            "tisch brille\n" +
            "brille tisch\n" +
            "white cat in basket\n" +
            "blanc chat dans panier\n" +
            "sand ocean\n" +
            "blanc chat panier\n" +
            "brille\n" +
            "cane mare materassino\n" +
            "pirate carnival ship\n" +
            "cafetera\n" +
            "brille\n" +
            "cane mare materassino\n" +
            "lipstick\n" +
            "brille\n" +
            "pirate carnival ship\n" +
            "cafetera\n" +
            "cafetera aqua\n" +
            "brille\n" +
            "cigaretta blue\n" +
            "brille\n" +
            "cigaretta blue\n" +
            "cafetera playa\n" +
            "cigaretta blu\n" +
            "pirate carnival ship\n" +
            "cane mare materassino\n" +
            "cigaretta rossetto\n" +
            "cane mare materassino\n" +
            "runde brille\n" +
            "ocean mountains dogs\n" +
            "cat blue sky\n" +
            "rund brille\n" +
            "cat blue sky\n" +
            "animaux\n" +
            "brille tisch\n" +
            "cat blue sky\n" +
            "animaux lac\n" +
            "chat bleu ciel\n" +
            "animaux lac\n" +
            "cane mare materassino\n" +
            "dop\n" +
            "cane mare materassino\n" +
            "cork\n" +
            "cane mare\n" +
            "animaux lac enfant\n" +
            "cane mare\n" +
            "seism write\n" +
            "liege\n" +
            "liege champagne\n" +
            "liege champagne\n" +
            "punition ecrite\n" +
            "montages italia\n" +
            "montagnes italia\n" +
            "bouchon champagne\n" +
            "cane mare materassino\n" +
            "green field yellow flower\n" +
            "bandiera cielo\n" +
            "cane mare materassino\n" +
            "green field yellow flower\n" +
            "cane mare materassino\n" +
            "cane mare materassino\n" +
            "verde area temeroso flor\n" +
            "cane mare materassino\n" +
            "bandiera cielo italia\n" +
            "verde temeroso flor\n" +
            "cane mare materassino\n" +
            "temeroso flor\n" +
            "cane mare materassino\n" +
            "caracol flor\n" +
            "cane mare materassino\n" +
            "bandiera cielo nev\n" +
            "cane mare materassino\n" +
            "bandiera cielo neve\n" +
            "caracol flores\n" +
            "cane mare materassino\n" +
            "cane mare materassino\n" +
            "cane mare materassino\n" +
            "cup bottle kitchen table\n" +
            "fantasy\n" +
            "cane materassino rosso\n" +
            "cup bottle kitchen table\n" +
            "cane cuscino rosso\n" +
            "cane mare\n" +
            "nebbia ponte luce\n" +
            "flasche tasse\n" +
            "cane mare naufrago\n" +
            "trees car house ranch\n" +
            "book flower blue\n" +
            "bolla di neve\n" +
            "book flower blue\n" +
            "trees car house ranch\n" +
            "book flower blue\n" +
            "buch blute blaue\n" +
            "frau blaue\n" +
            "frau tulpe\n" +
            "eschetrees car house ranch\n" +
            "esche\n" +
            "esche\n" +
            "palla acqua neve natale rosso\n" +
            "palla acqua neve natale rosso\n" +
            "esche\n" +
            "blue window flower\n" +
            "blue window flower\n" +
            "palla di vetro natale\n" +
            "bleu vitre fleur\n" +
            "palla di vetro natale\n" +
            "bleu vitre\n" +
            "lemons\n" +
            "palla di vetro natale\n" +
            "bleu fenetre fleur\n" +
            "lemons\n" +
            "palla vetro natale\n" +
            "palla vetro natale\n" +
            "citron doux\n" +
            "palla vetro natale\n" +
            "citron doux\n" +
            "palla vetro natale\n" +
            "citron doux\n" +
            "citron doux\n" +
            "two donkeys winter\n" +
            "palla vetro neve babbo natale\n" +
            "citron\n" +
            "two donkeys winter\n" +
            "citron\n" +
            "palla vetro neve santa clause\n" +
            "two donkeys winter\n" +
            "citron\n" +
            "palla vetro neve santa claus\n" +
            "deux ane hiver\n" +
            "palla vetro santa claus\n" +
            "melon jamon\n" +
            "melon\n" +
            "melon plate\n" +
            "melon plato\n" +
            "melon plato\n" +
            "melon plato\n" +
            "melon plato\n" +
            "melon prosciuto\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon\n" +
            "melon jamon\n" +
            "melon jamon sorbete\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon jamon\n" +
            "melon\n" +
            "melon jambon\n" +
            "melon jambon\n" +
            "melon jambon\n" +
            "melon jamon\n" +
            "melon\n" +
            "melon salade\n" +
            "melon salade\n" +
            "melonensalat\n" +
            "melonen\n" +
            "melon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon yellow\n" +
            "melon yellow\n" +
            "melon yellow\n" +
            "melon yellow\n" +
            "melon yellow piece\n" +
            "melon yellow piece\n" +
            "melon yellow\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon sorbete\n" +
            "melon\n" +
            "melon half\n" +
            "melon half\n" +
            "melon half filled\n" +
            "melon half filled\n" +
            "melon half filled\n" +
            "melon half filled\n" +
            "melon half\n" +
            "melon half\n" +
            "melon half\n" +
            "melon miel\n" +
            "melon miel\n" +
            "melon yogurt miel\n" +
            "melon yogurt miel\n" +
            "melon yogurt miel\n" +
            "melon yogurt miel\n" +
            "melon yogurt miel\n" +
            "melon yogurt miel\n" +
            "melon yogurt miel\n" +
            "melon yogurt honey\n" +
            "melon yogurt honey\n" +
            "melon yogurt honey\n" +
            "melon yogurt honey\n" +
            "melon yogurt honey\n" +
            "melon yogurt honey\n" +
            "melon yogurt honey\n" +
            "melon yogurt honey\n" +
            "melon yogurt honey\n" +
            "melon yogurt honey fruit\n" +
            "melon yogurt honey fruit\n" +
            "melon\n" +
            "melone\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon iberico\n" +
            "melon iberico\n" +
            "melon pata\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon\n" +
            "melon jamon sorbete\n" +
            "melon\n" +
            "melon half\n" +
            "melon half cream\n" +
            "melon half cream\n" +
            "melon half stuffed\n" +
            "melon half stuffed\n" +
            "melon half\n" +
            "melon half\n" +
            "melon half\n" +
            "melon half\n" +
            "melon half\n" +
            "melon half\n" +
            "melon slice\n" +
            "melon half slice\n" +
            "melon hat\n" +
            "melon hat\n" +
            "melone\n" +
            "melon honey\n" +
            "melon honey\n" +
            "melon honey\n" +
            "melon honey\n" +
            "melon honey";
}
