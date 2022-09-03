package java_itamae_connection.domain.service.connection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import jakarta.inject.Inject;
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

/** オプション項目が設定されていない場合のテスト */
public class NotSetOptions {
  @Inject private ConnectionService cs;
  @Inject private PropertiesService ps;
  private Map<String, String> properties;

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
    model.setPath("src/test/resources/test1.properties");

    ps.init(model);
    properties = ps.getProperties();
  }

  /**
   * {@link ConnectionService#convertToConnectionInfo(Map)} の動作確認を実行する。 hostName
   * が設定されていない場合にデフォルト値が設定された {@link ConnectionInfo} が返されること。
   */
  @Test
  public void css001() {
    final ConnectionInfo cnInfo = cs.convertToConnectionInfo(properties);
    assertThat(cnInfo.getHostName(), is("localhost"));
  }

  /**
   * {@link ConnectionService#convertToConnectionInfo(Map)} の動作確認を実行する。 portNumber
   * が設定されていない場合にデフォルト値が設定された {@link ConnectionInfo} が返されること。
   */
  @Test
  public void css002() {
    final ConnectionInfo cnInfo = cs.convertToConnectionInfo(properties);
    assertThat(cnInfo.getPortNumber(), is("3306"));
  }

  /**
   * {@link ConnectionService#convertToConnectionInfo(Map)} の動作確認を実行する。 encoding
   * が設定されていない場合にデフォルト値が設定された {@link ConnectionInfo} が返されること。
   */
  @Test
  public void css003() {
    final ConnectionInfo cnInfo = cs.convertToConnectionInfo(properties);
    assertThat(cnInfo.getEncoding(), is("UTF-8"));
  }

  /**
   * {@link ConnectionService#convertToConnectionInfo(Map)} の動作確認を実行する。 timeZone
   * が設定されていない場合にデフォルト値が設定された {@link ConnectionInfo} が返されること。
   */
  @Test
  public void css004() {
    final ConnectionInfo cnInfo = cs.convertToConnectionInfo(properties);
    assertThat(cnInfo.getTimeZone(), is("SERVER"));
  }
}
