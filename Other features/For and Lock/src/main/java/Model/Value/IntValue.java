package Model.Value;

import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;

public class IntValue implements Value {
    int value;

    public IntValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof IntValue;
    }
}
