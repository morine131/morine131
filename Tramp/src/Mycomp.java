import java.util.Comparator;

class MyComp implements Comparator<Tramp> {
    public int compare(Tramp c1, Tramp c2) {
        if(c1.getCardStr() < c2.getCardStr()) {
            return -1;
        } else if(c1.getCardStr() > c2.getCardStr()) {
            return 1;
        } else {
            return c1.getCardName().compareTo(c2.getCardName());
        }
    }
}