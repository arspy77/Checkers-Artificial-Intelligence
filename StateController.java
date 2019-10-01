import java.util.List;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class StateController {
    public static final char PLAYER_MODE = 'p';
    public static final char RANDOM_MODE = 'r';
    public static final char MINIMAX_MODE = 'm';
    public static final int WHITE = 1;
    public static final int BLACK = 0;

    private State state;
    private StateView stateView;
    private Bot randomBot;
    private Bot minimaxBot;
    private char mode[];

    public StateController(State state, StateView stateView, Bot randomBot, Bot minimaxBot) {
        this.state = state;
        this.stateView = stateView;
        this.randomBot = randomBot;
        this.minimaxBot = minimaxBot;
        mode = new char[2];
        mode[BLACK] = mode[WHITE] = PLAYER_MODE;

        initViewer();
        initListenerShowMoves();
        run();
    }

    public void initViewer(){
        this.stateView.updateButtonImage(this.state.getBoard());
        stateView.getHumanOneButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mode[BLACK] = PLAYER_MODE;
            }
        });
        stateView.getRandomOneButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mode[BLACK] = RANDOM_MODE;
            }
        });
        stateView.getMinimaxOneButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mode[BLACK] = MINIMAX_MODE;
            }
        });
        stateView.getHumanTwoButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mode[WHITE] = PLAYER_MODE;
            }
        });
        stateView.getRandomTwoButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mode[WHITE] = RANDOM_MODE;
            }
        });
        stateView.getMinimaxTwoButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mode[WHITE] = MINIMAX_MODE;
            }
        });
    }

    public void run() {
        // while (!state.end()) {

        // }
    }

    public void moveToThere(List<Pair> lp) {
        Pair source = lp.get(0);
        Pair nextMove = lp.get(1);
        List<Pair> temp = new ArrayList<Pair>();
        temp.add(source);
        temp.add(nextMove);
        state.move(temp);
        char[] boardNext = state.getBoard().clone();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
        stateView.updateButtonImage(boardNext);
        if(lp.size() > 2){
            showAvailableMove(nextMove);
        }else{
            initListenerShowMoves();
        }
    }

    public void showAvailableMove(Pair p) {
        List<List<Pair>> availMove = state.generateAllMoves();
  
        char[] board = state.getBoard().clone();
        for (List<Pair> lp : availMove) {
            if(lp.size() > 0){
                if(lp.get(0).equals(p)) {
                    Pair nextMove = lp.get(1);
                    //Add event listener
                    stateView.getButton(nextMove.row, nextMove.col).addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            moveToThere(lp);
                        }
                    });
                    //Change view
                    char curSel = board[Pair.convertCoorToNum(p)];
                    switch (curSel) {
                        case State.BLACK_PAWN:
                            board[Pair.convertCoorToNum(p)] = State.CURRENTLY_SELECTED_BLACK_PAWN;
                            break;
                        case State.BLACK_KING:
                            board[Pair.convertCoorToNum(p)] = State.CURRENTLY_SELECTED_BLACK_KING;
                            break;
                        case State.WHITE_PAWN:
                            board[Pair.convertCoorToNum(p)] = State.CURRENTLY_SELECTED_WHITE_PAWN;
                            break;
                        case State.WHITE_KING:
                            board[Pair.convertCoorToNum(p)] = State.CURRENTLY_SELECTED_WHITE_KING;
                            break;
                        default:
                            break;
                    }
                    board[Pair.convertCoorToNum(nextMove)] = State.POSSIBLE_MOVES;
                }
            }
        }
        stateView.updateButtonImage(board);
    }

    public void initListenerShowMoves() {
        if(mode[BLACK] == PLAYER_MODE) {
            for(int i = 1; i <= State.CELL_CNT; i++) {
                Pair p = Pair.convertNumToCoor(i);
                if(state.getBoard(p) != State.EMPTY) {
                    stateView.getButton(p.row, p.col).addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            showAvailableMove(p);
                        }
                    });
                }
            }
        }
    }

    public StateView getView() {
        return stateView;
    }
}