
public interface Stack {

    // Pushes an item onto the top of this stack.
    public default void push(Object o) {
    }

    // Removes the object at the top of this stack and returns that object as the value of this function.
    public default void pop() throws StackUnderflowException {
    }

    // Looks at the object at the top of this stack without removing it from the stack.
    public default Object top() {
        return null;
    }

    // Tests if this stack is empty.
    public default boolean isEmpty() {
        return false;
    }

    // ...
    public default void empty() {
    }

    // ...
    public String toString();
}