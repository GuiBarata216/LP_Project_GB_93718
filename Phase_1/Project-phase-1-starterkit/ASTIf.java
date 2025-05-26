public class ASTIf implements ASTNode {

    ASTNode cond, thenBranch, elseBranch;

    public ASTIf(ASTNode cond, ASTNode thenBranch, ASTNode elseBranch) {
        this.cond = cond;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue vcond = cond.eval(e);

        if (!(vcond instanceof VBool)) {
            throw new InterpreterError("Condition in 'if' must be boolean");
        }

        if (((VBool) vcond).getval()) {
            return thenBranch.eval(e);
        } else {
            return elseBranch.eval(e);
        }
    }
}
