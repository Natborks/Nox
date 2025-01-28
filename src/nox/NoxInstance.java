package nox;

import java.util.HashMap;
import java.util.Map;

public class NoxInstance {
    private NoxClass klass;
    private final Map<String, Object> fields = new HashMap<>();


    NoxInstance(NoxClass noxClass) {
        this.klass = noxClass;
    }

    @Override
    public String toString() {
        return klass.name + " instance";
    }

    Object get(Token name) {
        if (fields.containsKey(name.lexeme)) {
            return fields.get(name.lexeme);
        }

        NoxFunction method = klass.findMethod(name.lexeme);
        if (method != null) return method.bind(this);
        if (method != null) return method;

        throw new RuntimeError(name,
                "Undefined property '" + name.lexeme + "'.");
    }



    Object set(Token name, Object value) {
        return fields.put(name.lexeme, value);
    }
}
