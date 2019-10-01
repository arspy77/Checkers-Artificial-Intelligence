import java.util.List;

interface Bot {

    public void applyOppMove(List<Pair> moves);
    public void applyOwnMove(List<Pair> moves);
    public List<Pair> getMove();
}