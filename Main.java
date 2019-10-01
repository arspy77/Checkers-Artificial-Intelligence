class Main {
    public static void main(String args[]) {
        State state = new State();
        StateView stateView = new StateView();
        Bot randomBot = new RandomBot(state);
        Bot minimaxBot = new MinimaxBot(state);
        StateController stateController = new StateController(state, stateView, randomBot, minimaxBot);
    }
}