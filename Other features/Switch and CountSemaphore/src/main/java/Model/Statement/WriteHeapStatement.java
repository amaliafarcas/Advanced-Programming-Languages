package Model.Statement;

import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIHeap;
import Model.Expresion.Expression;
import Model.ProgramState;
import Exception.MyException;
import Model.Type.ReferenceType;
import Model.Type.Type;
import Model.Value.ReferenceValue;
import Model.Value.Value;

public class WriteHeapStatement implements IStatement{
    Expression variableName;
    Expression newValue;

    public WriteHeapStatement(Expression variableName, Expression newValue) {
        this.variableName = variableName;
        this.newValue = newValue;
    }

    public Expression getVariableName() {
        return variableName;
    }

    public void setVariableName(Expression variableName) {
        this.variableName = variableName;
    }

    public Expression getNewValue() {
        return newValue;
    }

    public void setNewValue(Expression newValue) {
        this.newValue = newValue;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();


        if (symTbl.isDefined(variableName.toString())) {

            Type typeId = (symTbl.lookUp(variableName.toString())).getType();

            if (typeId instanceof ReferenceType) {

                Value varValue = variableName.evaluate(symTbl, heap);

                int address = ((ReferenceValue) varValue).getAddress();

                if (heap.isDefined(address)) {

                    Value expressionValue = newValue.evaluate(symTbl, heap);

                    if (expressionValue.getType().equals(((ReferenceType) typeId).getInner())) {

                        heap.update(address, expressionValue);

                    } else {
                        throw new MyException("declared type of variable " + variableName +
                                " and type of the assigned expression do not match");
                    }
                } else {
                    throw new MyException("Address " + address +
                            " not defined on heap table");

                }
            }
            else{
                throw new MyException("the used variable " + variableName +
                        " does not have Reference type");
            }
        } else {
            throw new MyException("the used variable " + variableName +
                    " was not declared before");
        }


        return null;
    }

    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        Type variableType = variableName.typeCheck(typeEnvironment);
        Type newType = newValue.typeCheck(typeEnvironment);

        if(!variableType.equals(new ReferenceType(newType))){
            throw new MyException("Parameters type mismatch: current - " + variableName.toString()+ " of type " + variableType.toString() + " | new - " + newValue.toString()+ " of type " + newType.toString());
        }

        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "WrSt = (" + variableName.toString() +
                ", " + newValue.toString() +
                ')';
    }
}
