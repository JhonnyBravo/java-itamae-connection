package java_itamae_connection.app.validator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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
 * {@link IsValidConnectionInfo} のテスト
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({IsValidConnectionInfo.class, ConnectionInfoServiceImpl.class})
public class TestIsValidConnectionInfo {
  @Inject
  private IsValidConnectionInfo validator;
  @Inject
  private ConnectionInfoService service;
  private ContentsAttribute attr;

  @Before
  public void setUp() throws Exception {
    attr = new ContentsAttribute();
  }

  /**
   * dbName が設定されていない場合に false が返されること
   */
  @Test
  public void test1() throws Exception {
    attr.setPath("src/test/resources/test3.properties");
    final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
    final boolean result = validator.test(cnInfo);
    assertThat(result, is(false));
  }

  /**
   * userName が設定されていない場合に false が返されること
   */
  @Test
  public void test2() throws Exception {
    attr.setPath("src/test/resources/test4.properties");
    final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
    final boolean result = validator.test(cnInfo);
    assertThat(result, is(false));
  }

  /**
   * password が設定されていない場合に false が返されること
   */
  @Test
  public void test3() throws Exception {
    attr.setPath("src/test/resources/test5.properties");
    final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
    final boolean result = validator.test(cnInfo);
    assertThat(result, is(false));
  }

}
