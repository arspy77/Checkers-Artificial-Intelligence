public class Test{
    public static void main(String[] args){
        StateView sv = new StateView();
        State s = new State();
        StateController sc = new StateController(s, sv, null, null);
    }
}