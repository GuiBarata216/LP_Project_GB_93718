public class ASTAnd implements ASTNode {
    ASTNode lhs, rhs;

    public ASTAnd(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue v1 = lhs.eval(e);
        if (!(v1 instanceof VBool)) {
            throw new InterpreterError("left operand of && is not boolean");
        }

        // short-circuit: se v1 for false, devolve logo false
        if (!((VBool) v1).getval()) {
            return new VBool(false);
        }

        IValue v2 = rhs.eval(e);
        if (!(v2 instanceof VBool)) {
            throw new InterpreterError("right operand of && is not boolean");
        }

        return new VBool(((VBool) v2).getval());
    }
}
