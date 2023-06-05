
public class StackUnderflowException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public StackUnderflowException() {
        super("Stack underflow occurred.");
    }

    public StackUnderflowException(String message) {
        super(message);
    }
}
