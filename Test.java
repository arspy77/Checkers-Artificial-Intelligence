public class Test{
    public static void main(String[] args){
        StateView sv = new StateView();
        State s = new State();
        Bot randomBot = new RandomBot(s);
        Bot minimaxBot = new MinimaxBot(s);
        StateController sc = new StateController(s, sv, randomBot, minimaxBot);
    }
}