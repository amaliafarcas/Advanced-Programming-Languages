package Model.ADTStack;

import Model.Statement.IStatement;

import java.util.Stack;

public interface MyIStack<T> {

    T pop();

    void push(T v);

    boolean isEmpty();

    Stack<T> getStack();
}
