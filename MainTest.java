import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

class MainTest {
    public static void main(String[] args) {
        State internal = new State();

        Scanner scan = new Scanner(System.in);
        System.out.println("Hitam / Putih ? ");
        String turn = getTurnDialog();
        boolean isBlack;
        if(turn.compareTo("Hitam") == 0)
        {
            isBlack = true;
        }else{
            isBlack = false;
        }
        int input = 0;

        // RandomBot randomBot = new RandomBot(!isBlack);
        MinimaxBot minimaxBot = new MinimaxBot();
        while(input != -1){
            if(internal.getIsBlackMove()) {
                System.out.println("Black Turn");
            }else{
                System.out.println("White Turn");
            }
            if(minimaxBotBlack.internal.getIsBlackMove()) {
                System.out.println("Bot Black Black Turn");
            }else{
                System.out.println("Bot Black White Turn");
            }
            if(minimaxBotWhite.internal.getIsBlackMove()) {
                System.out.println("Bot White Black Turn");
            }else{
                System.out.println("Bot White White Turn");
            }
            System.out.println("Board : ");
            System.out.println(internal);
            System.out.println("===========================");
            //development starts here
            // System.out.println("Available Move : ");
            // List<List<Pair>> solution = internal.generateAllMoves();
            // int i = 1;
            // for (List<Pair> lp : solution) {
            //     System.out.println(i + ". Moves : " + lp);
            //     i++;
            // }
            if(internal.getIsBlackMove()){
                // input = scan.nextInt();
                // if(input != -1) {
                //     internal.move(solution.get(input-1));
                //     minimaxBotBlack.applyOppMove(solution.get(input-1));
                // }
                List<Pair> move = minimaxBotBlack.getMove();
                System.out.println("Black Move : " + move);
                minimaxBotBlack.applyOwnMove(move);
                minimaxBotWhite.applyOppMove(move);
                internal.move(move);
            }else{
                List<Pair> move = minimaxBotWhite.getMove();
                System.out.println("White Move : " + move);
                minimaxBotWhite.applyOwnMove(move);
                minimaxBotBlack.applyOppMove(move);
                internal.move(move);
                // internal.move(minimaxBotBlack.getMove());
            }
            // try {
            //     Thread.sleep(1000);
            // } catch (Exception e) {
            //     //TODO: handle exception
            // }
            
            // master starts here
            System.out.println("Available Move : ");
            List<List<Pair>> solution = internal.generateAllMoves();
            int i = 1;
            for (List<Pair> lp : solution) {
                System.out.println(i + ". Moves : " + lp);
                i++;
            }
            if(isBlack == internal.getIsBlackMove()){
                input = scan.nextInt();
                if(input != -1) {
                    internal.move(solution.get(input-1));
                    minimaxBot.applyOppMove(solution.get(input-1));
                }
            }else{
                internal.move(minimaxBot.getMove());
            }
//ends here
        }
    }

    public static String getTurnDialog(){
        //Custom button text
        Object[] options = {"Hitam",
        "Putih"};
        int n = JOptionPane.showOptionDialog(null,
        ""
        + "with that ham?",
        "A Silly Question",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[2]);
    }
}