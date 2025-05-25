import java.util.*;

public class Environment <E>{
    Environment<E> anc;
    Map<String, E> bindings;

    Environment(){
        anc = null;
        bindings = new HashMap<String,E>();
    }
    
    Environment(Environment<E> ancestor){
	    // Initialize new environment with reference to outer scope
        anc = ancestor;
        bindings = new HashMap<>();
    }

    Environment<E> beginScope(){
        return new Environment<E>(this);
    }
    
    Environment<E> endScope(){
        return anc;
    }

    void assoc(String id, E bind) throws InterpreterError {
	    // Add new binding to the current scope
        bindings.put(id, bind);
    }


    E find(String id) throws InterpreterError {
        // Look up variable recursively in current and outer scopes
        if (bindings.containsKey(id)) {
            return bindings.get(id);
        } else if (anc != null) {
            return anc.find(id);
        } else {
            throw new InterpreterError("Unbound variable: " + id);
        }
    }

}
