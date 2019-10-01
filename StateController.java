import java.util.List;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;

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
        List<Pair> temp = new ArrayList<Pair>();
        temp.add(Pair.convertNumToCoor(11));
        temp.add(Pair.convertNumToCoor(15));
        state.move(temp);
        temp = new ArrayList<Pair>();
        temp.add(Pair.convertNumToCoor(10));
        temp.add(Pair.convertNumToCoor(14));
        state.move(temp);
        temp = new ArrayList<Pair>();
        temp.add(Pair.convertNumToCoor(23));
        temp.add(Pair.convertNumToCoor(19));
        state.move(temp);
        temp = new ArrayList<Pair>();
        temp.add(Pair.convertNumToCoor(8));
        temp.add(Pair.convertNumToCoor(11));
        state.move(temp);
        temp = new ArrayList<Pair>();
        temp.add(Pair.convertNumToCoor(3));
        temp.add(Pair.convertNumToCoor(8));
        state.move(temp);
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

    public void moveToThere(Pair source, Pair dest, boolean multipleJump) {
        List<Pair> temp = new ArrayList<Pair>();
        temp.add(source);
        temp.add(dest);
        state.move(temp);
        System.out.println(state);
        char[] boardNext = state.getBoard().clone();
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            //TODO: handle exception
        }
        stateView.updateButtonImage(boardNext);
        if(multipleJump){
            state.setIsBlackMove(!state.getIsBlackMove());
            showAvailableMove(dest);
        }else{
            initListenerShowMoves();
        }
    }

    public void showAvailableMove(Pair p) {
        List<List<Pair>> availMove = state.generateAllMoves();
        System.out.println("Pair: " + p + ", Moves : " + availMove);
  
        char[] board = state.getBoard().clone();
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
        for(int i = 1; i <= State.CELL_CNT; i++) {
            Pair _p = Pair.convertNumToCoor(i);
            JButton button = stateView.getButton(_p.row, _p.col);
            for( ActionListener al : button.getActionListeners()){
                button.removeActionListener(al);
            }
        }
        for (List<Pair> lp : availMove) {
            if(lp.size() > 0){
                if(lp.get(0).equals(p)) {
                    Pair nextMove = lp.get(1);
                    //Add event listener
                    JButton button = stateView.getButton(nextMove.row, nextMove.col);
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            moveToThere(p, nextMove, lp.size() > 2);
                        }
                    });
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
                    JButton button = stateView.getButton(p.row, p.col);
                    for( ActionListener al : button.getActionListeners()){
                        button.removeActionListener(al);
                    }
                    button.addActionListener(new ActionListener() {
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