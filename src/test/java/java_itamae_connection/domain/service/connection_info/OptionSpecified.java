package java_itamae_connection.domain.service.connection_info;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java_itamae_connection.domain.model.ConnectionInfo;
import java_itamae_contents.domain.model.ContentsAttribute;
import javax.inject.Inject;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * オプション項目が設定されている場合のテスト。
 */
@RunWith(CdiRunner.class)
@AdditionalClasses(ConnectionInfoServiceImpl.class)
public class OptionSpecified {
  @Inject
  private ConnectionInfoService service;
  private ContentsAttribute attr;

  @Before
  public void setUp() throws Exception {
    attr = new ContentsAttribute();
    attr.setPath("src/test/resources/test2.properties");
  }

  /**
   * hostName の値をプロパティファイルから取得できること。
   */
  @Test
  public void test1() throws Exception {
    final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
    assertThat(cnInfo.getHostName(), is("192.168.2.30"));
  }

  /**
   * portNumber の値をプロパティファイルから取得できること。
   */
  @Test
  public void test2() throws Exception {
    final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
    assertThat(cnInfo.getPortNumber(), is("3333"));
  }

  /**
   * encoding の値をプロパティファイルから取得できること。
   */
  @Test
  public void test3() throws Exception {
    final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
    assertThat(cnInfo.getEncoding(), is("MS932"));
  }

  /**
   * timeZone の値をプロパティファイルから取得できること。
   */
  @Test
  public void test4() throws Exception {
    final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
    assertThat(cnInfo.getTimeZone(), is("UTC"));
  }

  /**
   * dbName の値をプロパティファイルから取得できること。
   */
  @Test
  public void test5() throws Exception {
    final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
    assertThat(cnInfo.getDbName(), is("test_db"));
  }

  /**
   * userName の値をプロパティファイルから取得できること。
   */
  @Test
  public void test6() throws Exception {
    final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
    assertThat(cnInfo.getUserName(), is("test_user"));
  }

  /**
   * password の値をプロパティファイルから取得できること。
   */
  @Test
  public void test7() throws Exception {
    final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
    assertThat(cnInfo.getPassword(), is("p@ssword"));
  }
}
