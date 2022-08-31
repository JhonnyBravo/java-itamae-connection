package java_itamae_connection.app.validator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Map;
import java_itamae.domain.model.contents.ContentsModel;
import java_itamae.domain.service.properties.PropertiesService;
import java_itamae.domain.service.properties.PropertiesServiceImpl;
import java_itamae_connection.app.connection.ConnectionInfoValidator;
import java_itamae_connection.domain.model.ConnectionInfo;
import java_itamae_connection.domain.service.connection.ConnectionService;
import java_itamae_connection.domain.service.connection.ConnectionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class ConnectionInfoValidatorTest {
  private ContentsModel model;
  private ConnectionInfoValidator validator;
  private PropertiesService ps;
  private ConnectionService cs;

  // dbName が指定されていない場合
  @DataPoint public static String DB_NAME = "src/test/resources/test3.properties";
  // userName が指定されていない場合
  @DataPoint public static String USER_NAME = "src/test/resources/test4.properties";
  // password が指定されていない場合
  @DataPoint public static String PASSWORD = "src/test/resources/test5.properties";

  @Before
  public void setUp() throws Exception {
    model = new ContentsModel();
    ps = new PropertiesServiceImpl();
    cs = new ConnectionServiceImpl();
    validator = new ConnectionInfoValidator();
  }

  /** {@link ConnectionInfo} の必須項目に値が設定されていない場合に false が返されること。 */
  @Theory
  public void validation001(String resourceName) throws Exception {
    model.setPath(resourceName);
    ps.init(model);
    final Map<String, String> properties = ps.getProperties();
    final ConnectionInfo cnInfo = cs.convertToConnectionInfo(properties);

    final boolean result = validator.test(cnInfo);
    assertThat(result, is(false));
  }

  /** {@link ConnectionInfo} の必須項目に値が設定されている場合に true が返されること。 */
  @Test
  public void validation002() throws Exception {
    model.setPath("src/test/resources/connection.properties");
    ps.init(model);
    final Map<String, String> properties = ps.getProperties();
    final ConnectionInfo cnInfo = cs.convertToConnectionInfo(properties);

    final boolean result = validator.test(cnInfo);
    assertThat(result, is(true));
  }
}
