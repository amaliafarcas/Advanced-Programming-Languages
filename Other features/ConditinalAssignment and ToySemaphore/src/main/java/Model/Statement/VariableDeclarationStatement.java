package Model.Statement;

import Model.ADTStack.MyIDictionary;
import Exception.MyException;
import Model.ProgramState;
import Model.Type.Type;
import Model.Value.Value;

public class VariableDeclarationStatement implements IStatement {
    Type type;
    String string;

    public VariableDeclarationStatement(Type type, String string) {
        this.type = type;
        this.string = string;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        if (symTbl.isDefined(string)) {
            throw new MyException("already defined");
        } else {
            symTbl.insert(string, type.defaultValue());
        }

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        typeEnvironment.insert(string, type);
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return type.toString() + " " + string;
    }
}
