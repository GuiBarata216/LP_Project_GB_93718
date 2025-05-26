public class ASTEqual implements ASTNode {
    ASTNode lhs, rhs;

    public ASTEqual(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue v1 = lhs.eval(e);
        IValue v2 = rhs.eval(e);

        if (v1 instanceof VInt && v2 instanceof VInt) {
            int i1 = ((VInt)v1).getval();
            int i2 = ((VInt)v2).getval();
            return new VBool(i1 == i2);
        }

        throw new InterpreterError("illegal types to == operator");
    }
}
