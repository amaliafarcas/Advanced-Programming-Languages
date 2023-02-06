package Model.Value;

import Model.Type.BoolType;
import Model.Type.StringType;
import Model.Type.Type;

public class StringValue implements Value{
    String value;

    public StringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof StringValue;
    }
}
