package ch.cyberlogic.camel.examples.bean;

import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;

public class ExampleFileFilter implements GenericFileFilter {
    @Override
    public boolean accept(GenericFile file) {
        return file.getFileName().endsWith(".txt");
    }
}
