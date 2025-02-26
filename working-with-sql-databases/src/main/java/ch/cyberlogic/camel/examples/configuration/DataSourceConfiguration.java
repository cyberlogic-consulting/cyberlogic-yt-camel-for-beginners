package ch.cyberlogic.camel.examples.configuration;

import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;

public class DataSourceConfiguration {
    private static final PGSimpleDataSource dataSource;

    static {
        dataSource = new PGSimpleDataSource();
        dataSource.setServerNames(new String[]{"localhost"});
        dataSource.setPortNumbers(new int[]{5433});
        dataSource.setDatabaseName("postgres");
        dataSource.setCurrentSchema("public");
        dataSource.setUser("postgres");
        dataSource.setPassword("postgres");
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
