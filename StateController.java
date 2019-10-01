class StateController {
    private State state;
    private StateView stateView;

    public StateController(State state, StateView stateView) {
        this.state = state;
        this.stateView = stateView;
    }

    public void initController(){
        this.stateView.updateButtons(this.state.getBoard());

    }
}