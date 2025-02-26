import java.util.List;
import java.util.ArrayList;

class Pair {
    public int row;
    public int col;

    public Pair(int r, int c) {
        row = r;
        col = c;
    }

    public Pair add(Pair p) {
        return new Pair(row + p.row, col + p.col);
    }

    public Pair mid(Pair p) {
        return new Pair((row + p.row) / 2, (col + p.col) / 2);
    }

    public Pair copy() {
        return new Pair(row, col);
    }

    public static Pair convertNumToCoor(int x) {
        --x;
        Pair ans = new Pair(x / 4, (x % 4) * 2);
        if (ans.row % 2 == 0) {
            ++ans.col;
        }
        return ans;
    }

    public static int convertCoorToNum(Pair p) {
        if ((p.row + p.col) % 2 == 1) {
            return 4 * p.row + (p.col / 2) + 1;
        } else {
            return 0;
        }
    }
    public int dist(Pair p) {
        //Manhattan Distance
        return Math.abs(row-p.row) + Math.abs(col - p.col);
    }

    @Override
    public String toString() {
        return Pair.convertCoorToNum(this) + "(" + row + ", " + col + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Pair)) return false;
        Pair p = (Pair) o;
        return row == p.row && col == p.col;
    }
}

class State {
    public static final int CELL_CNT = 32;
    public static final int START_PAWN_CNT = 12;
    public static final int CELL_LEN = 8;
    public static final char OUT_OF_BOUND = '~';
    public static final char EMPTY = '_';
    public static final char POSSIBLE_MOVES = 'P';
    public static final char CURRENTLY_SELECTED_BLACK_PAWN = 'x';
    public static final char CURRENTLY_SELECTED_BLACK_KING = 'X';
    public static final char CURRENTLY_SELECTED_WHITE_PAWN = 'y';
    public static final char CURRENTLY_SELECTED_WHITE_KING = 'Y';
    public static final char BLACK_PAWN = 'b';
    public static final char BLACK_KING = 'B';
    public static final char WHITE_PAWN = 'w';
    public static final char WHITE_KING = 'W';
    
    private char board[];
    private boolean isBlackMove;
    private List<char[]> boardHistory;

    public State() {
        isBlackMove = true;
        reset();
    }

    public void reset() {
        board = new char[CELL_CNT + 1];
        board[0] = EMPTY;
        for (int i = 1; i <= CELL_CNT; ++i) {
            if (i <= START_PAWN_CNT){ 
                board[i] = BLACK_PAWN;
            } else if (i > CELL_CNT - START_PAWN_CNT) {
                board[i] = WHITE_PAWN;
            } else {
                board[i] = EMPTY;
            }
        }

        isBlackMove = true;
        boardHistory = new ArrayList<char[]>();
    }

    public char getBoard(int x) {
        if (x < 0 || x > CELL_CNT) return OUT_OF_BOUND;
        return board[x];
    }

    public char getBoard(Pair p) {
        if (p.row < 0 || p.row >= CELL_LEN || p.col < 0 || p.col >= CELL_LEN) return OUT_OF_BOUND;
        return board[Pair.convertCoorToNum(p)];
    }

    public char[] getBoard() {
        return board;
    }

    public boolean getIsBlackMove() {
        return isBlackMove;
    }

    public void setIsBlackMove(boolean b) {
        isBlackMove = b;
    }

    public boolean isEmpty(Pair p) {
        return getBoard(p) == EMPTY;
    }

    public boolean isBlackPawn(Pair p) {
        return getBoard(p) == BLACK_PAWN;
    }

    public boolean isBlackKing(Pair p) {
        return getBoard(p) == BLACK_KING;
    }

    public boolean isWhitePawn(Pair p) {
        return getBoard(p) == WHITE_PAWN;
    }

    public boolean isWhiteKing(Pair p) {
        return getBoard(p) == WHITE_KING;
    }
    
    public boolean isBlack(Pair p) {
        return isBlackPawn(p) || isBlackKing(p);
    }

    public boolean isWhite(Pair p) {
        return isWhitePawn(p) || isWhiteKing(p);
    }

    public boolean isDiff(Pair p, Pair q) {
        return (isWhite(p) && isBlack(q)) || (isBlack(p) && isWhite(q));
    }

    /*
        y 00 01 02 03 04 05 06 07
      x--------------------------
     00 | -- 01 -- 02 -- 03 -- 04
     01 | 05 -- 06 -- 07 -- 08 --
     02 | -- 09 -- 10 -- 11 -- 12
     03 | 13 -- 14 -- 15 -- 16 --
     04 | -- 17 -- 18 -- 19 -- 20
     05 | 21 -- 22 -- 23 -- 24 --
     06 | -- 25 -- 26 -- 27 -- 28
     07 | 29 -- 30 -- 31 -- 32 --
     */

    public void setCell(Pair p, char c) {
        board[Pair.convertCoorToNum(p)] = c;
    }

    public List<List<Pair>> generateSingleMotion(Pair p) {
        List<List<Pair>> ans = new ArrayList<List<Pair>>();
        Pair pMotion = p;
        if (isWhite(p) || isBlackKing(p)) {
            pMotion = p.add(new Pair(-1, -1));
            if (isEmpty(pMotion)) {
                List<Pair> temp = new ArrayList<Pair>();
                temp.add(p);
                temp.add(pMotion);
                ans.add(temp);
            }
            pMotion = p.add(new Pair(-1, 1));
            if (isEmpty(pMotion)) {
                List<Pair> temp = new ArrayList<Pair>();
                temp.add(p);
                temp.add(pMotion);
                ans.add(temp);
            }
        }
        if (isBlack(p) || isWhiteKing(p)) {
            pMotion = p.add(new Pair(1, -1));
            if (isEmpty(pMotion)) {
                List<Pair> temp = new ArrayList<Pair>();
                temp.add(p);
                temp.add(pMotion);
                ans.add(temp);
            }
            pMotion = p.add(new Pair(1, 1));
            if (isEmpty(pMotion)) {
                List<Pair> temp = new ArrayList<Pair>();
                temp.add(p);
                temp.add(pMotion);
                ans.add(temp);
            }
        }
        return ans;
    }

    public List<Pair> generateSingleCapture(Pair p) {
        List<Pair> ans = new ArrayList<Pair>();
        Pair pMotion = p, pCaptured = p;
        if (isWhite(p) || isBlackKing(p)) {
            pMotion = p.add(new Pair(-2, -2));
            pCaptured = p.add(new Pair(-1, -1));
            if (isEmpty(pMotion) && isDiff(p, pCaptured)) {
                ans.add(pMotion);
            }
            pMotion = p.add(new Pair(-2, 2));
            pCaptured = p.add(new Pair(-1, 1));
            if (isEmpty(pMotion) && isDiff(p, pCaptured)) {
                ans.add(pMotion);
            }
        }
        if (isBlack(p) || isWhiteKing(p)) {
            pMotion = p.add(new Pair(2, -2));
            pCaptured = p.add(new Pair(1, -1));
            if (isEmpty(pMotion) && isDiff(p, pCaptured)) {
                ans.add(pMotion);
            }
            pMotion = p.add(new Pair(2, 2));
            pCaptured = p.add(new Pair(1, 1));
            if (isEmpty(pMotion) && isDiff(p, pCaptured)) {
                ans.add(pMotion);
            }
        }
        return ans;
    }

    private void DFSAllCapture(Pair p, List<Pair> curPath, List<List<Pair>> solution) {
        List<Pair> singleCapture = generateSingleCapture(p);
        if (singleCapture.size() != 0) {
            for (Pair pNew : singleCapture) {
                Pair pCapt = p.mid(pNew);
                char cur = getBoard(p), capt = getBoard(pCapt);
                boardHistory.add(board.clone());
                setCell(p, EMPTY);
                setCell(pCapt, EMPTY);
                if (pNew.row == 7 && cur == BLACK_PAWN) {
                    setCell(pNew, BLACK_KING);
                } else if (pNew.row == 0 && cur == WHITE_PAWN) {
                    setCell(pNew, WHITE_KING);
                } else {
                    setCell(pNew, cur);
                }
                setCell(pNew, cur);
                curPath.add(pNew);
                DFSAllCapture(pNew, curPath, solution);
                curPath.remove(curPath.size() - 1);
                isBlackMove = !isBlackMove;
                revertMove();
            }
        } else {
            if(curPath.size() > 1){
                List<Pair> temp = new ArrayList<Pair>();
                curPath.stream().forEach(_p -> {
                    Pair np = _p.copy();
                    temp.add(np);
                });
                solution.add(temp);
            }  
        }
    }

    public List<List<Pair>> generateAllCaptures(Pair p) {
        List<List<Pair>> solution = new ArrayList<List<Pair>>();
        List<Pair> currentPath = new ArrayList<Pair>();
        currentPath.add(p);
        DFSAllCapture(p, currentPath, solution);
        return solution;
    }

    public List<List<Pair>> generateAllMoves() {
        List<List<Pair>> solution = new ArrayList<List<Pair>>();
        for (int i = 1; i <= CELL_CNT; ++i) {
            Pair p = Pair.convertNumToCoor(i);
            if ((isBlackMove && isBlack(p)) || (!isBlackMove && isWhite(p))) {
                solution.addAll(generateAllCaptures(p));
            } 
        }
        if (solution.size() == 0) {
            for (int i = 1; i <= CELL_CNT; ++i) {
                Pair p = Pair.convertNumToCoor(i);
                if ((isBlackMove && isBlack(p)) || (!isBlackMove && isWhite(p))) {
                    solution.addAll(generateSingleMotion(p));
                } 
            }
        }

        return solution;
    }

    public void move(List<Pair> moves) {
        boardHistory.add(board.clone());
        Pair p = moves.get(0);
        Pair pNew = p.copy();
        //Apply move
        for(int i = 1; i < moves.size(); i++) {
            pNew = moves.get(i);
            char cur = getBoard(p);
            setCell(p, EMPTY);
            if (pNew.row == 7 && cur == BLACK_PAWN) {
                setCell(pNew, BLACK_KING);
            } else if (pNew.row == 0 && cur == WHITE_PAWN) {
                setCell(pNew, WHITE_KING);
            } else {
                setCell(pNew, cur);
            }
            if(p.dist(pNew) == 4) {
                //Capture
                setCell(p.mid(pNew), EMPTY);
            }
            p = pNew.copy();
        }
        isBlackMove = !isBlackMove;
    }

    public void revertMove() {
        board = boardHistory.remove(boardHistory.size() - 1);
        isBlackMove = !isBlackMove;
    }

    @Override
    public String toString() {
        String s = "_________________\n";
        for (int i = 0; i < 8; i++) {
            s += '|';
            for (int j = 0; j < 8; j++) {
                s += getBoard(new Pair(i,j)) + "|";
            }
            s += '\n';
        }
        return s;
    }

    public boolean isFinished(){
        return (generateAllMoves().size() == 0);
    }
}