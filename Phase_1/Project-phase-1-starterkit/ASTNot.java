public class ASTNot implements ASTNode {
    ASTNode exp;

    public ASTNot(ASTNode e) {
        exp = e;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue v = exp.eval(env);
        if (!(v instanceof VBool)) {
            throw new InterpreterError("operand of ~ is not boolean");
        }
        return new VBool(!((VBool) v).getval());
    }
}
