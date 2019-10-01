import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class RandomBot {

    public State internal = new State();
    Random rand = new Random(13517013);
    boolean isBlack;

    public void applyOppMove(List<Pair> moves) {
        internal.move(moves);
    }

    public void applyOwnMove(List<Pair> moves) {
        internal.move(moves);
    }

    public List<Pair> getMove() {
        List<List<Pair>> allMoves = internal.generateAllMoves();
        List<Pair> randMove = allMoves.get(rand.nextInt(allMoves.size()));
        return randMove;
    }
}