package top.ashman.demo.pattern.memento;

/**
 * @author sunzhaojie
 * @date 2018/10/29
 */
public class Originator {
    private String state;

    public void set(String state) {
        this.state = state;
        System.out.println("Originator: Setting state to " + state);
    }

    public Memento saveToMemento() {
        System.out.println("Originator: Saving to Memento.");
        return new Memento(this.state);
    }

    public void restoreFromMemento(Memento memento) {
        this.state = memento.getSavedState();
        System.out.println("Originator: State after restoring from Memento: " + state);
    }

    public static class Memento {
        private final String state;

        public Memento(String stateToSave) {
            this.state = stateToSave;
        }

        /**
         * accessible by outer class only
         *
         * @return State
         */
        private String getSavedState() {
            return state;
        }
    }
}
