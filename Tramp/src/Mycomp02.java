import java.util.Comparator;

class MyComp02 implements Comparator<Player> {
    public int compare(Player c1, Player c2) {
        if(c1.getPlayerPoint() < c2.getPlayerPoint()) {
            return -1;
        } else if(c1.getPlayerPoint() > c2.getPlayerPoint()) {
            return 1;
        } else {
            return c1.getPlayerName().compareTo(c2.getPlayerName());
        }
    }
}