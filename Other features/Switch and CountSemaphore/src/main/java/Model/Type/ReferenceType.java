package Model.Type;

import Model.Value.ReferenceValue;
import Model.Value.Value;

import java.util.Objects;

public class ReferenceType implements Type{
    Type inner;

    public ReferenceType(Type inner) {
        this.inner = inner;
    }

    public Type getInner() {
        return inner;
    }

    @Override
    public Value defaultValue() {
        return new ReferenceValue(0, inner);
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof ReferenceType)
            return inner.equals(((ReferenceType) another).getInner());
        else
            return false;
    }

    @Override
    public String toString() {
        return " Reference " + inner.toString();
    }

}
