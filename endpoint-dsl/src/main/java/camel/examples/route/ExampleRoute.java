package camel.examples.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.endpoint.dsl.SftpEndpointBuilderFactory.SftpEndpointBuilder;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.sftp;

public class ExampleRoute extends RouteBuilder {
    @Override
    public void configure() {
        from(sftpEndpoint()
                .moveFailed(".error")
                .fileName("hello.txt"))
                .log("Read document: ${header." + Exchange.FILE_NAME + "}")
                .to(sftpEndpoint().fileName("done.txt"));
    }

    private SftpEndpointBuilder sftpEndpoint() {
        return sftp("localSftp", "{{sftp.host}}:{{sftp.port}}/{{sftp.directory}}")
                .username("{{sftp.username}}")
                .password("{{sftp.password}}")
                .knownHostsFile("{{sftp.knownHosts}}");
    }
}
