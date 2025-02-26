package ch.cyberlogic.camel.examples.route;

import org.apache.camel.builder.RouteBuilder;

public class SqlExampleRoute extends RouteBuilder {

    public static String ROUTE_ID_SELECT = "SqlExampleRouteSelect";
    public static String ROUTE_ID_INSERT = "SqlExampleRouteInsert";

    @Override
    public void configure() {
        from("timer:tick?period=5s")
                .routeId(ROUTE_ID_INSERT)
                .setHeader("petName", constant("doggie"))
                .setHeader("petType", constant("Dog"))
                .setHeader("petStatus", constant("Available"))
                .setHeader("petDateOfBirth", constant("2020-05-15"))
                .to("sql:insert into pets (name, status, type, birth_date)" +
                        " values (:#petName, :#petStatus, :#petType, to_date(:#petDateOfBirth,'YYYY-MM-DD'))");

        from("timer:tick?period=10s")
                .routeId(ROUTE_ID_SELECT)
                .setHeader("petName", constant("doggie"))
                .setBody(simple(""))
                .to("sql:select * from pets where name=:#petName")
                .log("${body}");
    }

}
