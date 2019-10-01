import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class MainTest {
    public static void main(String[] args) {
        State internal = new State();

        Scanner scan = new Scanner(System.in);
        System.out.println("Hitam / Putih ? ");
        String turn = scan.nextLine();
        boolean isBlack;
        if(turn.compareTo("Hitam") == 0)
        {
            isBlack = true;
        }else{
            isBlack = false;
        }
        int input = 0;

        // RandomBot minimaxBotBlack = new RandomBot();
        // RandomBot minimaxBotWhite = new RandomBot();
        MinimaxBot minimaxBotBlack = new MinimaxBot();
        MinimaxBot minimaxBotWhite = new MinimaxBot();
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
            
        }
    }
}