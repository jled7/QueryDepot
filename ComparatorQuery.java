import lib.ComparatorIF;

public class ComparatorQuery implements ComparatorIF<Query> {

    @Override
    public int compare(Query e1, Query e2) {
        if (lt(e1, e2)) {
            return LT;
        } else if (gt(e1, e2)) {
            return GT;

        } else {
            return e1.getText().compareTo(e2.getText());
        }

    }

    @Override
    public boolean lt(Query e1, Query e2) {
        if (e1.getFreq() == e2.getFreq()) {
            return e1.compareTo(e2) < 0;
        } else if (e1.getFreq() > e2.getFreq()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean eq(Query e1, Query e2) {
        if (e1.getFreq() == e2.getFreq()) {
            return e1.compareTo(e2) == 0;
        }
        return false;
    }

    @Override
    public boolean gt(Query e1, Query e2) {
        if (e1.getFreq() == e2.getFreq()) {
            return e1.compareTo(e2) > 0;
        } else if (e1.getFreq() < e2.getFreq()) {
            return true;
        }
        return false;
    }

}
