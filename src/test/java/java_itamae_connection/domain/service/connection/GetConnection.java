package java_itamae_connection.domain.service.connection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.Connection;
import java.util.Map;
import java_itamae.domain.model.contents.ContentsModel;
import java_itamae.domain.service.properties.PropertiesService;
import java_itamae.domain.service.properties.PropertiesServiceImpl;
import java_itamae_connection.domain.model.ConnectionInfo;
import org.junit.Before;
import org.junit.Test;

/** DB 接続のテスト */
public class GetConnection {
  private ConnectionService cs;
  private ConnectionInfo cnInfo;

  @Before
  public void setUp() throws Exception {
    final ContentsModel model = new ContentsModel();
    model.setPath("src/test/resources/connection.properties");

    final PropertiesService ps = new PropertiesServiceImpl();
    ps.init(model);
    final Map<String, String> properties = ps.getProperties();

    cs = new ConnectionServiceImpl();
    cnInfo = cs.convertToConnectionInfo(properties);
  }

  /** {@link ConnectionService#getConnection(ConnectionInfo)} 実行時に DB 接続を確立できること。 */
  @Test
  public void css001() throws Exception {
    try (Connection connection = cs.getConnection(cnInfo)) {
      assertThat(connection.isClosed(), is(false));
    } catch (final Exception e) {
      System.err.println(e);
      throw e;
    }
  }
}
