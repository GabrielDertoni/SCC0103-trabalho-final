package interpreter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Environment {
    private Map<String, Object> variables;
    private Map<String, Function<List<Object>, Object>> functions;

    public OutputDevice outputDevice;

    public Environment(OutputDevice outputDevice) {
        this.variables = new HashMap<String, Object>();
        this.functions = new HashMap<String, Function<List<Object>, Object>>();
        this.outputDevice = outputDevice;
    }

    public Object lookupVariable(String name) {
        return variables.get(name);
    }

    public Function<List<Object>, Object> lookupFunction(String name) {
        return functions.get(name);
    }

    public boolean isVariableDefined(String name) {
        return variables.containsKey(name);
    }

    public boolean isFunctionDefined(String name) {
        return functions.containsKey(name);
    }

    public void declareVariable(String name, Object value) {
        variables.put(name, value);
    }

    public void addFunction(String name, Function<List<Object>, Object> func) {
        functions.put(name, func);
    }

    public Object callFunction(String name, List<Object> args) {
        Function func = functions.get(name);

        if (func == null) {
            throw new RuntimeException("função chamada mas não definida");
        }

        return func.call(this, args);
    }
}
