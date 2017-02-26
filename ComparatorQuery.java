
import comun.ComparatorIF;
import static comun.ComparatorIF.GREATER;
import static comun.ComparatorIF.LESS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public class ComparatorQuery implements ComparatorIF<Query> {

    @Override
    public int compare(Query e1, Query e2) {
        System.out.println("Comparing...");
        if (isLess(e1, e2)) {
            return LESS;
        } else if (isGreater(e1, e2)) {
            return GREATER;

        } else {
            return e1.getText().compareTo(e2.getText());
        }

    }

    @Override
    public boolean isLess(Query e1, Query e2) {
        //return e1.getFreq() > e2.getFreq();
        if (e1.getFreq() == e2.getFreq()) {
            return e1.compareTo(e2) < 0;
        } else if (e1.getFreq() > e2.getFreq()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isEqual(Query e1, Query e2) {
        //return e1.getFreq() == e2.getFreq();
        if (e1.getFreq() == e2.getFreq()) {
            return e1.compareTo(e2) == 0;
        }
        return false;
    }

    @Override
    public boolean isGreater(Query e1, Query e2) {
        //return e1.getFreq() < e2.getFreq();
        if(e1.getFreq() == e2.getFreq()) {
            return e1.compareTo(e2) > 0;    
        }else if(e1.getFreq() < e2.getFreq()) {
            return true;
        }
        return false;
    }

}
