import java.util.List;
import java.util.ArrayList;

class MinimaxBot implements Bot {
    private static int MAX_DEPTH = 7;

    State internal;

    public MinimaxBot(State s) {
        internal = s;
    }

    public void applyMove(List<Pair> moves) {
        internal.move(moves);
    }

    private double heuristic1() {
        // Heuristic untuk awal game, menghitung banyak bidak putih - hitam
        // + bagus buat hitam, - bagus buat putih
        // return rand.nextInt();
        double score = 0;
        // Hitung skor tiap bidak
        for(int i = 1; i <= State.CELL_CNT; i++) {
            Pair p = Pair.convertNumToCoor(i);
            if(internal.isWhite(p)) {
               if(internal.isWhiteKing(p)) {
                   score -= 2;
               } else {
                   score -= 1;
               }
            } else {
                //Hitam
                if(internal.isBlackKing(p)) {
                    score += 2;
                } else {
                    score += 1;
                }
            }
        }
        return score;
    }

    private double heuristic2() {
        // Heuristic untuk mid game dengan harapan bot akan membuat sebanyak mungkin bidak menjadi king
        // + bagus buat hitam, - bagus buat putih
        // return rand.nextInt();
        double score = 0;
        // Hitung skor tiap bidak
        for(int i = 1; i <= State.CELL_CNT; i++) {
            Pair p = Pair.convertNumToCoor(i);
            if(internal.isWhite(p)) {
               if(internal.isWhiteKing(p)) {
                   score -= 10;
               } else {
                   // Beri nilai sesuai kedekatan dengan ujung
                   score -= (10 - (i / 4));
               }
            } else {
                //Hitam
                if(internal.isBlackKing(p)) {
                    score += 2;
                } else {
                    // Beri nilai sesuai kedekatan dengan ujung
                   score += (10 - (i / 4));
                }
            }
        }
        return score;
    }

    private double heuristic3() {
        // Heuristic untuk late game dengan harapan bot akan menghabisi sebanyak mungkin musuh
        // + bagus buat hitam, - bagus buat putih
        // return rand.nextInt();
        // Idenya dengan menormalisasikan nilai heuristik 2 yaitu membaginya dengan total bidak
        int totalPieces = 0;
        for(int i = 1; i <= State.CELL_CNT; i++) {
            if(internal.getBoard(i) != State.EMPTY)
            {
                totalPieces++;
            }
        }
        return heuristic2() / totalPieces;
    }

    private double heuristic() {
        //Fungsi general heuristic
        //Link paper : https://cs.huji.ac.il/~ai/projects/old/English-Draughts.pdf
        int totalPieces = 0;
        for(int i = 1; i <= State.CELL_CNT; i++) {
            if(internal.getBoard(i) != State.EMPTY)
            {
                totalPieces++;
            }
        }
        //Awal 24 bidak

        int minEarlyGame = 20;
        int minMidGame = 6; 

        if(totalPieces >= minEarlyGame){
            return heuristic1();
        }else if(totalPieces >= minMidGame) {
            return heuristic2();
        }else {
            return heuristic3();
        }
    }

    private double minimax(int depth, double alpha, double beta) {
        if (depth == MAX_DEPTH) {
            return heuristic();
        }
        if (internal.getIsBlackMove()) {
            double bestVal = Double.NEGATIVE_INFINITY;
            List<List<Pair>> allMoves = internal.generateAllMoves();
            for (List<Pair> move : allMoves) {
                internal.move(move);
                double value = minimax(depth + 1, alpha, beta);
                internal.revertMove();
                bestVal = Math.max(bestVal, value);
                alpha = Math.max(alpha, bestVal);
                if (beta <= alpha) {
                    break;
                }
            }
            // System.out.println("IsBlackMove: "+depth+" " + bestVal);
            return bestVal;
        } else {
            double bestVal = Double.POSITIVE_INFINITY;
            List<List<Pair>> allMoves = internal.generateAllMoves();
            for (List<Pair> move : allMoves) {
                internal.move(move);
                double value = minimax(depth + 1, alpha, beta);
                internal.revertMove();
                bestVal = Math.min(bestVal, value);
                beta = Math.min(beta, bestVal);
                if (beta <= alpha) {
                    break;
                }
            }
            // System.out.println("IsWhiteMove: "+depth+" " + bestVal);
            return bestVal;
        }
    }

    public List<Pair> getMove() {
        if (internal.getIsBlackMove()) {
            double bestVal = Double.NEGATIVE_INFINITY;
            List<List<Pair>> allMoves = internal.generateAllMoves();
            List<Pair> nextMove = new ArrayList<Pair>();
            //System.out.println(allMoves);
            for (List<Pair> move : allMoves) {
                internal.move(move);
                double value = minimax(0, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
                internal.revertMove();
                if (value > bestVal) {
                    bestVal = value;
                    nextMove = move;
                }
            }
            return nextMove;
        } else {
            double bestVal = Double.POSITIVE_INFINITY;
            List<List<Pair>> allMoves = internal.generateAllMoves();
            List<Pair> nextMove = new ArrayList<Pair>();
            //System.out.println(allMoves);
            for (List<Pair> move : allMoves) {
                internal.move(move);
                double value = minimax(0, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
                internal.revertMove();
                if (value < bestVal) {
                    bestVal = value;
                    nextMove = move;
                }
            }
            return nextMove;
        }
    }
}