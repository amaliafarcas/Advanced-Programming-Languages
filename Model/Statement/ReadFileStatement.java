package Model.Statement;

import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIFileTable;
import Model.Expresion.Expression;
import Exception.MyException;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;
import java.io.BufferedReader;
import java.io.IOException;


public class ReadFileStatement implements IStatement{
    Expression expression;
    String variable_name;

    public ReadFileStatement(Expression expression, String variable_name) {
        this.expression = expression;
        this.variable_name = variable_name;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public String getVariable_name() {
        return variable_name;
    }

    public void setVariable_name(String variable_name) {
        this.variable_name = variable_name;
    }

    @Override
    public String toString() {
        return "readFile{" +
                "expression=" + expression +
                ", variable_name='" + variable_name + '\'' +
                '}';
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {

        MyIFileTable<String, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String, Value> symTable = state.getSymTable();

        if (symTable.isDefined(variable_name) && symTable.lookUp(variable_name).getType().equals(new IntType())) {

            Value value = expression.evaluate(symTable, state.getHeap());
            if (value.getType().equals(new StringType())) {

                StringValue stringValue = (StringValue) value;
                String file = stringValue.getValue();
                BufferedReader bfReader = fileTable.lookUp(file);

                try{
                    String readLine = bfReader.readLine();
                    Value newValue;
                    if (readLine == null){
                        newValue = new IntValue(0);
                    }
                    else{
                        newValue = new IntValue(Integer.parseInt(readLine));
                    }
                    symTable.update(variable_name, newValue);
                }
                catch (IOException e){
                    throw new MyException(e.getMessage());
                }
            }
            else
                throw new MyException("Invalid type. Not a string");
        }
        else
            throw new MyException("Invalid variable");

        return null;
    }


    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        Type typeVariable  = typeEnvironment.lookUp(variable_name);
        Type typeExpression = expression.typeCheck(typeEnvironment);

        if(!typeVariable.equals(new IntType())){
            throw new MyException("Variable" + expression.toString() + "can not be evaluated to an int");
        }

        if(!typeExpression.equals(new StringType())){
            throw new MyException("Variable " + variable_name + "can not be evaluated to a string");
        }

        return typeEnvironment;
    }
}
