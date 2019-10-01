import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class MainTest {
    public static void main(String[] args) {
        State internal = new State();

        Scanner scan = new Scanner(System.in);
        int input = 0;
        while(input != -1){
            if(internal.getIsBlackMove()) {
                System.out.println("Black Turn");
            }else{
                System.out.println("White Turn");
            }
            System.out.println("Board : ");
            System.out.println(internal);
            System.out.println("===========================");
            System.out.println("Available Move : ");
            List<List<Pair>> solution = internal.generateAllMoves();
            int i = 1;
            for (List<Pair> lp : solution) {
                System.out.println(i + ". Moves : " + lp);
                i++;
            }
            input = scan.nextInt();
            if(input != -1) {
                internal.move(solution.get(input-1));
            }
            internal.setIsBlackMove(!internal.getIsBlackMove());
        }
    }
}