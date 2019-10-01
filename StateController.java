class StateController {
    private State state;
    private StateView stateView;
    private Bot randomBot;
    private Bot minimaxBot;

    public StateController(State state, StateView stateView, Bot randomBot, Bot minimaxBot) {
        this.state = state;
        this.stateView = stateView;
        this.randomBot = randomBot;
        this.minimaxBot = minimaxBot;
    }

    public void initController(){
        System.out.println(this.state);
        this.stateView.updateButtonImage(this.state.getBoard());
    }
    public StateView getView(){
        return stateView;
    }
    
}