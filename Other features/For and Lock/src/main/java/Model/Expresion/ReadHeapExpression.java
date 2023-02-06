package Model.Expresion;


import Exception.MyException;
import Model.ADTStack.MyIDictionary;
import Model.ADTStack.MyIHeap;
import Model.Type.ReferenceType;
import Model.Type.Type;
import Model.Value.ReferenceValue;
import Model.Value.Value;

public class ReadHeapExpression implements Expression{
    Expression expression;

    public ReadHeapExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symTable, MyIHeap<Integer, Value> heap) throws MyException {
        Value expressionValue = expression.evaluate(symTable, heap);

        if (expressionValue instanceof ReferenceValue) {


            int address = ((ReferenceValue) expressionValue).getAddress();
            //System.out.println(address);
            if (heap.isDefined(address)) {

                return heap.lookUp(address);

            }
            else{
                throw new MyException("address " + address +
                        " not defined on heap");
            }

        } else {
            throw new MyException("Expression " + expression.toString() +
                    " can not evaluated as reference value");
        }

    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnvironment) throws MyException {
        Type type = expression.typeCheck(typeEnvironment);

        if (!(type instanceof ReferenceType)){
            throw new MyException("the rH argument is not a Reference type ");
        }

        return ((ReferenceType) type).getInner();
    }

    @Override
    public String toString() {
        return "ReadHeap(" + expression.toString() +
                ')';
    }
}
