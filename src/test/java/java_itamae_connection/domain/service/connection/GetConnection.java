package java_itamae_connection.domain.service.connection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import jakarta.inject.Inject;
import java.sql.Connection;
import java.util.Map;
import java_itamae.domain.component.properties.PropertiesComponentImpl;
import java_itamae.domain.model.contents.ContentsModel;
import java_itamae.domain.service.properties.PropertiesService;
import java_itamae.domain.service.properties.PropertiesServiceImpl;
import java_itamae_connection.domain.model.ConnectionInfo;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/** DB 接続のテスト */
public class GetConnection {
  @Inject private ConnectionService cs;
  @Inject private PropertiesService ps;
  private ConnectionInfo cnInfo;

  @Rule
  public WeldInitiator weld =
      WeldInitiator.from(
              PropertiesServiceImpl.class,
              PropertiesComponentImpl.class,
              ConnectionService.class,
              ConnectionServiceImpl.class)
          .inject(this)
          .build();

  @Before
  public void setUp() throws Exception {
    final ContentsModel model = new ContentsModel();
    model.setPath("src/test/resources/connection.properties");

    ps.init(model);
    final Map<String, String> properties = ps.getProperties();
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
