package Model.Value;

import Model.Type.ReferenceType;
import Model.Type.Type;

public class ReferenceValue implements Value{
    int address;
    Type locationType;

    public ReferenceValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public Type getLocationType() {
        return locationType;
    }

    public void setLocationType(Type locationType) {
        this.locationType = locationType;
    }

    @Override
    public Type getType() {
        return new ReferenceType(locationType);
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof ReferenceValue;
    }

    @Override
    public String toString() {
        return "(" + address +
                ", " + locationType +
                ')';
    }
}
