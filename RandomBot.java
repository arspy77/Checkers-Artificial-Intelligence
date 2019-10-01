import java.util.List;
import java.util.ArrayList;
import java.util.Random;

class RandomBot implements Bot {
    State internal;
    Random rand = new Random(System.currentTimeMillis());

    public RandomBot(State s) {
        internal = s;
    }

    public void applyMove(List<Pair> moves) {
        internal.move(moves);
    }

    public List<Pair> getMove() {
        List<List<Pair>> allMoves = internal.generateAllMoves();
        List<Pair> randMove = allMoves.get(rand.nextInt(allMoves.size()));
        return randMove;
    }
}