
import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** StateView adalah kelas yang menggambar State */
@SuppressWarnings("serial")
public class StateView extends JFrame {
    private LinkedList<LinkedList<JButton>> buttons = new LinkedList<>();

    private JButton randomButton, minimaxButton, undoButton;

    /** Ctor untuk class StateView */
    public StateView() {
        setFocusable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel board = new JPanel();
        board.setLayout(new GridLayout(8, 8));

        for (int i = 0; i < 8; i++) {
            LinkedList<JButton> rowButtons = new LinkedList<>();
            for (int j = 0; j < 8; j++) {
                JButton button = new JButton();
                if ((i + j) % 2 == 0){
                    button.setBackground(new Color(240, 217, 181));
                }
                else{
                    button.setBackground(new Color(181, 136, 99));
                }
                rowButtons.add(button);
                board.add(button);
            }
            this.buttons.add(rowButtons);
        }
        this.add(board,BorderLayout.CENTER);

        this.setSize(900, 800);
		this.setResizable(false);
        this.setVisible(true);
        
        JPanel playerBar = new JPanel();
        playerBar.setLayout(new BoxLayout(playerBar, BoxLayout.Y_AXIS));
		
        randomButton = new JButton(" Random ");
        randomButton.setPreferredSize(new Dimension(100,350));
		playerBar.add(randomButton);
        
        undoButton = new JButton("  Undo  ");
        undoButton.setPreferredSize(new Dimension(100,100));
		playerBar.add(undoButton);
        
        minimaxButton = new JButton(" Minimax ");
        minimaxButton.setPreferredSize(new Dimension(100,350));
        playerBar.add(minimaxButton);

        this.add(playerBar,BorderLayout.EAST);
    }

    /**
     * Updates the gui This will iterate through updating all the buttons
     */
    public void update() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

            }
        }
    }

    /**
     * @return LinkedList<LinkedList<JButton>> return the buttons
     */
    public LinkedList<LinkedList<JButton>> getButtons() {
        return buttons;
    }

    /**
     * @return JButton return the button
     */
    public JButton getButton(int row, int col) {
        return buttons.get(row).get(col);
    }

    /**
     * @param buttons the buttons to set
     */
    public void setButtons(LinkedList<LinkedList<JButton>> buttons) {
        this.buttons = buttons;
    }

    public void updateButtonImage(char[] board) {
        // System.out.println(board);
        for (int i = 1; i <= State.CELL_CNT; i++) {
            Pair p = Pair.convertNumToCoor(i);
            switch (board[i]) {
            case State.EMPTY:
                buttons.get(p.row).get(p.col).setIcon(new ImageIcon());
                break;
            case State.POSSIBLE_MOVES:
                buttons.get(p.row).get(p.col).setIcon(new ImageIcon("img/possible-moves.png"));
                break;
            case State.CURRENTLY_SELECTED_BLACK_PAWN:
                buttons.get(p.row).get(p.col).setIcon(new ImageIcon("img/currently-selected-black-pawn.png"));
                break;
            case State.CURRENTLY_SELECTED_BLACK_KING:
                buttons.get(p.row).get(p.col).setIcon(new ImageIcon("img/currently-selected-black-king.png"));
                break;
            case State.CURRENTLY_SELECTED_WHITE_PAWN:
                buttons.get(p.row).get(p.col).setIcon(new ImageIcon("img/currently-selected-white-pawn.png"));
                break;
            case State.CURRENTLY_SELECTED_WHITE_KING:
                buttons.get(p.row).get(p.col).setIcon(new ImageIcon("img/currently-selected-white-king.png"));
                break;
            case State.BLACK_PAWN:
                buttons.get(p.row).get(p.col).setIcon(new ImageIcon("img/black-pawn.png"));
                break;
            case State.BLACK_KING:
                buttons.get(p.row).get(p.col).setIcon(new ImageIcon("img/black-king.png"));
                break;
            case State.WHITE_PAWN:
                buttons.get(p.row).get(p.col).setIcon(new ImageIcon("img/white-pawn.png"));
                break;
            case State.WHITE_KING:
                buttons.get(p.row).get(p.col).setIcon(new ImageIcon("img/white-king.png"));
                break;
            }
        }
    }

    public JButton getRandomButton(){
        return randomButton;
    }
    public JButton getMinimaxButton(){
        return minimaxButton;
    }
    public JButton getUndoButton(){
        return undoButton;
    }
}