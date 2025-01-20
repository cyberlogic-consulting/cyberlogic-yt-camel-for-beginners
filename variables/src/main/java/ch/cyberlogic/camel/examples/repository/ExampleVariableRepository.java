package ch.cyberlogic.camel.examples.repository;

import java.util.HashMap;
import java.util.Map;
import org.apache.camel.spi.VariableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleVariableRepository implements VariableRepository {

    public static final String ID = "exampleVariableRepository";

    private final Map<String, Object> store = new HashMap<String, Object>();

    private static final Logger log = LoggerFactory.getLogger(ExampleVariableRepository.class);

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public Object removeVariable(String name) {
        log.info("Removing variable: {}", name);
        return store.remove(name);
    }

    @Override
    public Object getVariable(String name) {
        log.info("Fetching variable: {}", name);
        return store.get(name);
    }

    @Override
    public void setVariable(String name, Object value) {
        log.info("Storing variable: {} with value {}", name, value);
        store.put(name, value);
    }


    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
