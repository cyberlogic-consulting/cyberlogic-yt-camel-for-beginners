package ch.cyberlogic.camel.examples.route;

import java.io.File;
import java.util.List;
import java.util.Properties;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.infra.core.CamelContextExtension;
import org.apache.camel.test.infra.core.DefaultCamelContextExtension;
import org.apache.camel.test.infra.core.annotations.ContextFixture;
import org.apache.camel.test.infra.core.annotations.RouteFixture;
import org.apache.camel.test.infra.kafka.services.KafkaService;
import org.apache.camel.test.infra.kafka.services.KafkaServiceFactory;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;
import org.testcontainers.shaded.org.awaitility.Awaitility;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("KafkaExampleRouteIntegrationTest needs docker client to be running")
public class KafkaExampleRouteIntegrationTest {

    @Order(1)
    @RegisterExtension
    protected static KafkaService kafkaService = KafkaServiceFactory.createSingletonService();

    @Order(2)
    @RegisterExtension
    protected static CamelContextExtension camelContextExtension = new DefaultCamelContextExtension();

    private static AdminClient kafkaAdminClient;

    private static String topic;

    private static String filePath;

    private static String fileName;

    @ContextFixture
    public void setUpProperties(CamelContext camelContext) throws Exception {
        camelContext.getPropertiesComponent().setLocation("classpath:application-it.properties");

        Properties props = new Properties();
        props.put("camel.kafka-route.brokers-string", kafkaService.getBootstrapServers());
        camelContext.getPropertiesComponent().setOverrideProperties(props);
    }

    @RouteFixture
    public void createRoute(CamelContext camelContext) throws Exception {
        camelContext.addRoutes(new KafkaExampleRoute());
    }

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        FileUtils.deleteDirectory(new File("./src/test/resources/files"));
        Properties props = new Properties();
        props.load(KafkaExampleRouteIntegrationTest.class.getClassLoader().getResourceAsStream("application-it.properties"));
        topic = props.getProperty("camel.kafka-route.topic");
        filePath = props.getProperty("camel.kafka-route.file-path");
        fileName = props.getProperty("camel.kafka-route.file-name");
    }

    @BeforeAll
    public static void createKafkaAdminClient() {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaService.getBootstrapServers());
        kafkaAdminClient = KafkaAdminClient.create(props);
    }

    @BeforeEach
    public void cleanTopics() {
        kafkaAdminClient.deleteTopics(List.of(topic));
    }

    @Test
    void testKafkaExampleRoute() throws Exception {
        String testBody = "Bye World";
        ProducerTemplate producerTemplate = camelContextExtension.getProducerTemplate();
        ConsumerTemplate consumerTemplate = camelContextExtension.getConsumerTemplate();

        Awaitility
                .await()
                .until(
                        () -> camelContextExtension.getContext().isStarted());
        producerTemplate.sendBody("kafka:" + topic + "?brokers=" + kafkaService.getBootstrapServers(), testBody);
        String result = consumerTemplate.receiveBody("file:" + filePath + "?fileName=" + fileName, 3000, String.class);

        assertEquals(testBody.toLowerCase(), result);
    }
}
