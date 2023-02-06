package Model.ADTStack;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    //CollectionType<T> stack;
    Stack<T> stack;


    public MyStack(Stack<T> stack) {
        this.stack = stack;
    }

    public MyStack() {
        this.stack = new Stack<>();
    }

    public Stack<T> getStack() {
        return stack;
    }

    public void setStack(Stack<T> stack) {
        this.stack = stack;
    }

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return "ExeStack = [" + stack.toString() + ']';
    }
}
