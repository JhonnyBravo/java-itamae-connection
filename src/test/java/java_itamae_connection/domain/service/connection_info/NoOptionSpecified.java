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
 * オプション項目が設定されていない場合のテスト。
 */
@RunWith(CdiRunner.class)
@AdditionalClasses(ConnectionInfoServiceImpl.class)
public class NoOptionSpecified {
  @Inject
  private ConnectionInfoService service;
  private ContentsAttribute attr;

  @Before
  public void setUp() throws Exception {
    attr = new ContentsAttribute();
    attr.setPath("src/test/resources/test1.properties");
  }

  /**
   * hostName が設定されていない場合にデフォルト値が設定されること。
   */
  @Test
  public void test1() throws Exception {
    final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
    assertThat(cnInfo.getHostName(), is("localhost"));
  }

  /**
   * portNumber が設定されていない場合にデフォルト値が設定されること。
   */
  @Test
  public void test2() throws Exception {
    final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
    assertThat(cnInfo.getPortNumber(), is("3306"));
  }

  /**
   * encoding が設定されていない場合にデフォルト値が設定されること。
   */
  @Test
  public void test3() throws Exception {
    final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
    assertThat(cnInfo.getEncoding(), is("UTF-8"));
  }

  /**
   * timeZone が設定されていない場合にデフォルト値が設定されること。
   */
  @Test
  public void test4() throws Exception {
    final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
    assertThat(cnInfo.getTimeZone(), is("JST"));
  }
}
