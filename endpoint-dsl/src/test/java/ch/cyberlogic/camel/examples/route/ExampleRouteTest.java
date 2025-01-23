package ch.cyberlogic.camel.examples.route;

import ch.cyberlogic.camel.examples.component.LocalSftp;
import java.util.Properties;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.FluentProducerTemplate;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.PropertiesComponent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

import static ch.cyberlogic.camel.examples.route.ExampleRoute.sftpEndpoint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@DisplayName("ExampleRouteTest needs docker client to be running")
public class ExampleRouteTest {

    @Container
    private final GenericContainer<?> sftpContainer = new GenericContainer<>("atmoz/sftp:alpine")
            .withExposedPorts(22)
            .withCopyFileToContainer(MountableFile.forClasspathResource("it/ssh_host_ed25519_key", 0777),
                    "/etc/ssh/ssh_host_ed25519_key")
            .withCommand("user:password:::tmp/files");

    private CamelContext camelContext;

    private FluentProducerTemplate producerTemplate;

    private ConsumerTemplate consumerTemplate;

    @BeforeEach
    void setUp() throws Exception {
        camelContext = new DefaultCamelContext();

        Properties properties = new Properties();
        properties.put("sftp.host", "localhost");
        properties.put("sftp.port", sftpContainer.getMappedPort(22));
        properties.put("sftp.username", "user");
        properties.put("sftp.password", "password");
        properties.put("sftp.directory", "tmp/files");
        properties.put("sftp.knownHosts", "src/test/resources/it/known_hosts");
        PropertiesComponent propertiesComponent = camelContext.getPropertiesComponent();
        propertiesComponent.setOverrideProperties(properties);
        camelContext.addRoutes(new ExampleRoute());
        camelContext.addComponent("localSftp", new LocalSftp().localSftp());

        producerTemplate = camelContext.createFluentProducerTemplate();
        consumerTemplate = camelContext.createConsumerTemplate();

        camelContext.start();
    }

    @AfterEach
    void tearDown() {
        camelContext.stop();
    }

    @Test
    void exampleRouteTest() {
        String contents = "Hello world!";
        String fileName = "hello.txt";

        producerTemplate
                .withBody(contents)
                .withHeader(Exchange.FILE_NAME, fileName)
                .to(sftpEndpoint())
                .send();

        Exchange actualDoneFileExchange = consumerTemplate.receive(
                sftpEndpoint().fileName("done.txt").getUri());

        assertNotNull(actualDoneFileExchange);
        Message actualDoneFile = actualDoneFileExchange.getMessage();
        assertNotNull(actualDoneFile);
        assertEquals("done.txt", actualDoneFile.getHeader(Exchange.FILE_NAME));
        assertEquals(contents, actualDoneFile.getBody(String.class));
    }
}
