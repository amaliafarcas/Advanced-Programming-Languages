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

public class NewStatement implements IStatement {
    String variableName;
    Expression expression;

    public NewStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        if (symTbl.isDefined(variableName)) {
            Type typeId = (symTbl.lookUp(variableName)).getType();

            if(typeId instanceof ReferenceType) {

                Value expressionValue = expression.evaluate(symTbl, heap);

                if (expressionValue.getType().equals(((ReferenceType) typeId).getInner())) {

                    int address = heap.getNextFreeAddress();
                    heap.insert(address, expressionValue);

                    // update in the symbol table the reference value associated to variableName
                    ReferenceValue newReferenceValue = new ReferenceValue(address, expressionValue.getType());
                    symTbl.update(variableName, newReferenceValue);

                } else {
                    throw new MyException("declared type of variable " + variableName +
                            " and type of the assigned expression do not match");
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

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        Type typeVariable  = typeEnvironment.lookUp(variableName);
        Type typeExpression = expression.typeCheck(typeEnvironment);

        if(!typeVariable.equals(new ReferenceType(typeExpression))){
            throw new MyException("NEW Statement: right hand side and left hand side have different types");
        }

        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "new(" + variableName + ", " + expression.toString() +
                ")";
    }
}
