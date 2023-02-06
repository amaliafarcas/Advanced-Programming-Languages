package Model.Statement;

import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIFileTable;
import Model.Expresion.Expression;
import Exception.MyException;
import Model.ProgramState;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseReadFileStatement implements IStatement{

    Expression expression;

    public CloseReadFileStatement(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "closeReadFile{" +
                "expression=" + expression +
                '}';
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {

        MyIFileTable<String, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String, Value> symTable = state.getSymTable();

        Value value = expression.evaluate(symTable, state.getHeap());

        if (value.getType().equals(new StringType())){
            StringValue stringValue = (StringValue) value;
            String file = stringValue.getValue();
            BufferedReader bfReader = fileTable.lookUp(file);

            try{
                bfReader.close();
                fileTable.delete(file);
            }
            catch (IOException e){
                throw new MyException(e.getMessage());
            }
        }
        else
            throw new MyException("Invalid type. Not a string");

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        Type expressionType = expression.typeCheck(typeEnvironment);
        if(!expressionType.equals(new StringType())){
            throw  new MyException("Expression" + expression.toString() + "can not be evaluated to a string");
        }
        return typeEnvironment;
    }
}
