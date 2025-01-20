package nox;

import java.util.List;

public interface NoxCallable {

    int arity();
    Object call(Interpreter interpreter, List<Object> arguments);
}
