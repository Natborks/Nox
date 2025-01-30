package nox;

import java.util.List;
import java.util.Map;

public class NoxClass implements NoxCallable{
    final String name;
    private final Map<String, NoxFunction> methods;

    final NoxClass superclass;

    NoxClass(String name, NoxClass superClass, Map<String, NoxFunction> methods) {
        this.superclass = superClass;
        this.name = name;
        this.methods = methods;
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public int arity() {
        NoxFunction initializer = findMethod("init");
        if (initializer == null) return 0;
        return initializer.arity();
    }

    @Override
    public Object call(Interpreter interpreter,
                       List<Object> arguments) {
        NoxInstance instance = new NoxInstance(this);
        NoxFunction initializer = findMethod("init");
        if (initializer != null) {
            initializer.bind(instance).call(interpreter, arguments);
        }
        return instance;
    }

    NoxFunction findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }

        if (superclass != null) {
            return superclass.findMethod(name);
        }

        return null;
    }
}
