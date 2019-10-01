
import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/** StateView adalah kelas yang menggambar State */
@SuppressWarnings("serial")
public class StateView extends JFrame{
    private LinkedList<LinkedList<JButton>> buttons = new LinkedList<>();
    
    
    /** Ctor untuk class StateView */
    public StateView() {
        setFocusable(true);

        JPanel board = new JPanel();
		board.setLayout(new GridLayout(8,8));


        for (int i = 0; i < 8; i++){
            LinkedList<JButton> rowButtons = new LinkedList<>();
            for (int j = 0; j < 8; j++){
				JButton button = new JButton();
				
                board.add(button);
			}
			buttons.add(rowButtons);
		}

    }

    /**
	 * Updates the gui
	 * This will iterate through updating all the buttons
	 */
	public void update() {
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				
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

	public void updateButtons(char[] board){
		for (int i = 0; i < board.length; i++) {
				
		}
	}
}