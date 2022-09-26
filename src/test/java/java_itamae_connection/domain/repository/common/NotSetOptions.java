package java_itamae_connection.domain.repository.common;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Map;
import java_itamae.domain.component.properties.PropertiesComponent;
import java_itamae.domain.component.properties.PropertiesComponentImpl;
import java_itamae.domain.model.contents.ContentsModel;
import java_itamae_connection.domain.model.ConnectionInfo;
import org.junit.Before;
import org.junit.Test;

/** オプション項目が設定されていない場合のテスト */
public class NotSetOptions {
  private BaseRepository repository;
  private Map<String, String> properties;

  @Before
  public void setUp() throws Exception {
    final ContentsModel model = new ContentsModel();
    model.setPath("src/test/resources/test1.properties");

    final PropertiesComponent component = new PropertiesComponentImpl();
    properties = component.getProperties(model);

    repository = new BaseRepositoryImpl();
  }

  /**
   * {@link BaseRepository#convertToConnectionInfo(Map)} の動作確認を実行する。 hostName
   * が設定されていない場合にデフォルト値が設定された {@link ConnectionInfo} が返されること。
   */
  @Test
  public void css001() {
    final ConnectionInfo cnInfo = repository.convertToConnectionInfo(properties);
    assertThat(cnInfo.getHostName(), is("localhost"));
  }

  /**
   * {@link BaseRepository#convertToConnectionInfo(Map)} の動作確認を実行する。 portNumber
   * が設定されていない場合にデフォルト値が設定された {@link ConnectionInfo} が返されること。
   */
  @Test
  public void css002() {
    final ConnectionInfo cnInfo = repository.convertToConnectionInfo(properties);
    assertThat(cnInfo.getPortNumber(), is("3306"));
  }

  /**
   * {@link BaseRepository#convertToConnectionInfo(Map)} の動作確認を実行する。 encoding
   * が設定されていない場合にデフォルト値が設定された {@link ConnectionInfo} が返されること。
   */
  @Test
  public void css003() {
    final ConnectionInfo cnInfo = repository.convertToConnectionInfo(properties);
    assertThat(cnInfo.getEncoding(), is("UTF-8"));
  }

  /**
   * {@link BaseRepository#convertToConnectionInfo(Map)} の動作確認を実行する。 timeZone
   * が設定されていない場合にデフォルト値が設定された {@link ConnectionInfo} が返されること。
   */
  @Test
  public void css004() {
    final ConnectionInfo cnInfo = repository.convertToConnectionInfo(properties);
    assertThat(cnInfo.getTimeZone(), is("SERVER"));
  }
}
