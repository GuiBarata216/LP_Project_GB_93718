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
           throw new InterpreterError("Attempt to apply non-function value: " + f.toStr());
        }

        VClosure clos = (VClosure) f;
        List<String> params = clos.getArgs();
        ASTNode body = clos.getBody();

        if (args.size() < params.size()) {
            // Currying: returns new function with remaining arguments
            Environment<IValue> newEnv = clos.getEnv().beginScope();
            for (int i = 0; i < args.size(); i++) {
                IValue v = args.get(i).eval(env);
                newEnv.assoc(params.get(i), v);
            }

            List<String> remainingParams = params.subList(args.size(), params.size());
            return new VClosure(remainingParams, body, newEnv);
        }

        if (args.size() > params.size()) {
            throw new InterpreterError(
                "Too many arguments: expected " + params.size() + ", got " + args.size());
        }

        Environment<IValue> local = clos.getEnv().beginScope();
        for (int i = 0; i < params.size(); i++) {
            IValue v = args.get(i).eval(env);
            local.assoc(params.get(i), v);
        }

        return body.eval(local);
    }
}

