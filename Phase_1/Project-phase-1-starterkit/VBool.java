public class VBool implements IValue {

    private final boolean val;

    public VBool(boolean v) {
        this.val = v;
    }

    public boolean getval() {
        return val;
    }

    public String toStr() {
        return Boolean.toString(val);
    }
}
