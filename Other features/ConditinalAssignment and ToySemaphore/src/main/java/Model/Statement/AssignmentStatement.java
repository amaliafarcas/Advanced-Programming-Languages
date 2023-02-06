package Model.Statement;

import Model.ADTStack.MyIDictionary;
import Exception.MyException;
import Model.Expresion.Expression;
import Model.ProgramState;
import Model.Type.Type;
import Model.Value.Value;

public class AssignmentStatement implements IStatement {

    String id;
    Expression expression;

    public AssignmentStatement(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

        if (symTbl.isDefined(id)) {

            //System.out.println(expression.toString());
            Value expressionValue = expression.evaluate(symTbl, state.getHeap());

            Type typeId = (symTbl.lookUp(id)).getType();

            if (expressionValue.getType().equals(typeId)) {

                symTbl.update(id, expressionValue);
            } else {
                throw new MyException("declared type of variable " + id +
                        " and type of the assigned expression do not match");
            }
        } else {
            throw new MyException("the used variable " + id +
                    " was not declared before");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        Type typeVariable = typeEnvironment.lookUp(id);
        Type typeExpression = expression.typeCheck(typeEnvironment);

        if (!typeVariable.equals(typeExpression)) {
            throw new MyException("Assignment: right hand side and left hand side have different types");
        }
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return id + "=" + expression.toString();
    }
}
