package java_itamae_connection.domain.service.connection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

import java_itamae_connection.domain.model.ConnectionInfo;
import java_itamae_connection.domain.service.connection_info.ConnectionInfoService;
import java_itamae_connection.domain.service.connection_info.ConnectionInfoServiceImpl;
import java_itamae_contents.domain.model.ContentsAttribute;

public class ConnectionServiceTest {
    private ConnectionInfo cnInfo;
    private ConnectionService cs;

    @Before
    public void setUp() throws Exception {
        final ContentsAttribute attr = new ContentsAttribute();
        attr.setPath("src/test/resources/connection.properties");

        final ConnectionInfoService cis = new ConnectionInfoServiceImpl();
        cnInfo = cis.getConnectionInfo(attr);

        cs = new ConnectionServiceImpl();
    }

    @Test
    public void getConnection実行時にDB接続を確立できること() throws Exception {
        try (Connection connection = cs.getConnection(cnInfo)) {
            assertThat(connection.isClosed(), is(false));
        } catch (final Exception e) {
            System.err.println(e);
        }
    }
}
