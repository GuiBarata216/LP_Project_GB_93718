import java.util.List;

public class VClosure implements IValue {
    private final List<String> args;
    private final ASTNode body;
    private final Environment<IValue> env;

    public VClosure(List<String> args, ASTNode body, Environment<IValue> env) {
        this.args = args;
        this.body = body;
        this.env = env;
    }

    public List<String> getArgs() {
        return args;
    }

    public ASTNode getBody() {
        return body;
    }

    public Environment<IValue> getEnv() {
        return env;
    }

    public String toStr() {
        return "<closure>";
    }
}

