package java_itamae_connection.domain.repository.common;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import jakarta.inject.Inject;
import java.util.Map;
import java_itamae.domain.component.properties.PropertiesComponent;
import java_itamae.domain.component.properties.PropertiesComponentImpl;
import java_itamae.domain.model.contents.ContentsModel;
import java_itamae_connection.domain.model.ConnectionInfo;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/** オプション項目が設定されている場合のテスト */
public class SetOptions {
  @Inject private BaseRepository repository;
  @Inject private PropertiesComponent component;
  private Map<String, String> properties;

  @Rule
  public WeldInitiator weld =
      WeldInitiator.from(
              PropertiesComponent.class,
              PropertiesComponentImpl.class,
              BaseRepository.class,
              BaseRepositoryImpl.class)
          .inject(this)
          .build();

  @Before
  public void setUp() throws Exception {
    final ContentsModel model = new ContentsModel();
    model.setPath("src/test/resources/test2.properties");
    properties = component.getProperties(model);
  }

  /**
   * {@link BaseRepository#convertToConnectionInfo(Map)} の動作確認を実行する。 hostName の値が設定された {@link
   * ConnectionInfo} が返されること。
   */
  @Test
  public void css001() {
    final ConnectionInfo cnInfo = repository.convertToConnectionInfo(properties);
    assertThat(cnInfo.getHostName(), is("192.168.2.30"));
  }

  /**
   * {@link BaseRepository#convertToConnectionInfo(Map)} の動作確認を実行する。 portNumber の値が設定された {@link
   * ConnectionInfo} が返されること。
   */
  @Test
  public void css002() {
    final ConnectionInfo cnInfo = repository.convertToConnectionInfo(properties);
    assertThat(cnInfo.getPortNumber(), is("3333"));
  }

  /**
   * {@link BaseRepository#convertToConnectionInfo(Map)} の動作確認を実行する。 encoding の値が設定された {@link
   * ConnectionInfo} が返されること。
   */
  @Test
  public void css003() {
    final ConnectionInfo cnInfo = repository.convertToConnectionInfo(properties);
    assertThat(cnInfo.getEncoding(), is("MS932"));
  }

  /**
   * {@link BaseRepository#convertToConnectionInfo(Map)} の動作確認を実行する。 timeZone の値が設定された {@link
   * ConnectionInfo} が返されること。
   */
  @Test
  public void css004() {
    final ConnectionInfo cnInfo = repository.convertToConnectionInfo(properties);
    assertThat(cnInfo.getTimeZone(), is("UTC"));
  }

  /**
   * {@link BaseRepository#convertToConnectionInfo(Map)} の動作確認を実行する。 dbName の値が設定された {@link
   * ConnectionInfo} が返されること。
   */
  @Test
  public void css005() {
    final ConnectionInfo cnInfo = repository.convertToConnectionInfo(properties);
    assertThat(cnInfo.getDbName(), is("test_db"));
  }

  /**
   * {@link BaseRepository#convertToConnectionInfo(Map)} の動作確認を実行する。 userName の値が設定された {@link
   * ConnectionInfo} が返されること。
   */
  @Test
  public void css006() {
    final ConnectionInfo cnInfo = repository.convertToConnectionInfo(properties);
    assertThat(cnInfo.getUserName(), is("test_user"));
  }

  /**
   * {@link BaseRepository#convertToConnectionInfo(Map)} の動作確認を実行する。 password の値が設定された {@link
   * ConnectionInfo} が返されること。
   */
  @Test
  public void css007() {
    final ConnectionInfo cnInfo = repository.convertToConnectionInfo(properties);
    assertThat(cnInfo.getPassword(), is("p@ssword"));
  }
}
