import java.util.List;
import java.util.ArrayList;
import java.util.Random;

class RandomBot implements Bot {

    State internal = new State();
    Random rand = new Random(System.currentTimeMillis());

    public void applyMove(List<Pair> moves) {
        internal.move(moves);
    }

    public List<Pair> getMove() {
        List<List<Pair>> allMoves = internal.generateAllMoves();
        List<Pair> randMove = allMoves.get(rand.nextInt(allMoves.size()));
        internal.move(randMove);
        return randMove;
    }
}