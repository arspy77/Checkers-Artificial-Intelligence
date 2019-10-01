import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends{

    /** Fungsi program utama */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            Main m = new Main();
            m.setVisible(true);
        });
    }

    /** Nilai x dari selisih frame dan panel background */
    private final int xOffset = 18;
    /** Nilai y dari selisih frame dan panel background */
    private final int yOffset = 47;

    /** Constructor Main */
    private Main(){
        State s = new State(20, 22);
        StateController sc = new StateController(s);
        StateView sv = sc.getStateView();
        add(sv);
        setTitle("Checkers");
        Thread t1 = new Thread(sc,"T1");
        t1.start();
        setSize((1 + s.getNCol() + 1 + sv.InventoryTabLength/2 + 2 + sv.mesQueueLength/2 + 1) * sv.SPACE + xOffset, (s.getNRow() + 2) * sv.SPACE + yOffset);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}