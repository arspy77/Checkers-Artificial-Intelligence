import java.util.List;

interface Bot {

    public void applyMove(List<Pair> moves);
    public List<Pair> getMove();
}