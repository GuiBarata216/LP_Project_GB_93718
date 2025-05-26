import java.util.List;

public class ASTFn implements ASTNode {
    private final List<String> args;
    private final ASTNode body;

    public ASTFn(List<String> args, ASTNode body) {
        this.args = args;
        this.body = body;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        return new VClosure(args, body, env);
    }
}
