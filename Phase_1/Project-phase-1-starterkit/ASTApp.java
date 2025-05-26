import java.util.List;

public class ASTApp implements ASTNode {
    private final ASTNode fn;
    private final List<ASTNode> args;

    public ASTApp(ASTNode fn, List<ASTNode> args) {
        this.fn = fn;
        this.args = args;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue f = fn.eval(env);
        if (!(f instanceof VClosure)) {
            throw new InterpreterError("Attempt to apply non-function value");
        }

        VClosure clos = (VClosure) f;
        List<String> params = clos.getArgs();
        ASTNode body = clos.getBody();

        if (params.size() != args.size()) {
            throw new InterpreterError("Wrong number of arguments in function application");
        }

        Environment<IValue> local = clos.getEnv().beginScope();
        for (int i = 0; i < params.size(); i++) {
            IValue v = args.get(i).eval(env);
            local.assoc(params.get(i), v);
        }

        return body.eval(local);
    }
}

