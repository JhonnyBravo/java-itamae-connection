package java_itamae_connection.domain.service.connection;

import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.sql.Connection;
import java_itamae_connection.domain.model.ConnectionInfo;
import java_itamae_connection.domain.service.connection_info.ConnectionInfoService;
import java_itamae_connection.domain.service.connection_info.ConnectionInfoServiceImpl;
import java_itamae_contents.domain.model.ContentsAttribute;
import javax.inject.Inject;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * {@link ConnectionService} のテスト
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({ConnectionServiceImpl.class, ConnectionInfoServiceImpl.class})
public class TestConnectionService {
  @Inject
  private ConnectionService cs;
  @Inject
  private ConnectionInfoService cis;
  private ConnectionInfo cnInfo;

  @Before
  public void setUp() throws Exception {
    final ContentsAttribute attr = new ContentsAttribute();
    attr.setPath("src/test/resources/connection.properties");
    cnInfo = cis.getConnectionInfo(attr);
  }

  /**
   * {@link ConnectionService#getConnection(ConnectionInfo)} 実行時に DB 接続を確立できること。
   */
  @Test
  public void test1() throws Exception {
    try (Connection connection = cs.getConnection(cnInfo)) {
      assertThat(connection.isClosed(), is(false));
    } catch (final Exception e) {
      System.err.println(e);
      fail();
    }
  }
}
