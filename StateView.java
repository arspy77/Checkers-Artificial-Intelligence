
import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;


/** StateView adalah kelas yang menggambar State */
@SuppressWarnings("serial")
public class StateView extends JFrame implements Observer {
    private State state;
    private LinkedList<LinkedList<JButton>> buttons = new LinkedList<>();
    
    
    /** Ctor untuk class StateView */
    public StateView(State state) {
        setFocusable(true);
        this.state = state;
        state.addObserver(this);

        JPanel board = new JPanel();
		board.setLayout(new GridLayout(8,8));


        for (int i = 0; i < 8; i++){
            LinkedList<JButton> rowButtons = new LinkedList<>();
            for (int j = 0; j < 8; j++){
				JButton button = new JButton();
                board.add(button);
                
			}
		}

    }

    /**
	 * Updates the gui when the observable object notifies its observers.
	 * This will iterate through updating all the buttons, the status, and 
	 * check if the game has been won.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		for (int r = 0; r < 8; r++){
			for (int c = 0; c < 8; c++){
				getButton(r,c).update();
			}
		}
		if ((this.model.hasWon() != 0)){
			this.setEnabled(false);
			new GameOver(this,this.model);
			this.setEnabled(true);
		}
		else if (this.model.clicked.size() == 0){
			this.status.setText("Status: Select the piece you want to move");
		}
		else{
			this.status.setText("Status: Select where to move this piece to");
		}
		
	}
}