import java.util.List;

interface Bot {

    public void applyOppMove(List<Pair> moves);
    public List<Pair> getMove();
}