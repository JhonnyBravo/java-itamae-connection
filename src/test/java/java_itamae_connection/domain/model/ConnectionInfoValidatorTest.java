package java_itamae_connection.domain.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import jakarta.inject.Inject;
import java.util.Map;
import java_itamae.domain.component.properties.PropertiesComponent;
import java_itamae.domain.component.properties.PropertiesComponentImpl;
import java_itamae.domain.model.contents.ContentsModel;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class ConnectionInfoValidatorTest {
  @Inject private ConnectionInfoConverter converter;
  @Inject private ConnectionInfoValidator validator;
  @Inject private PropertiesComponent component;

  // dbName が指定されていない場合
  @DataPoint public static String DB_NAME = "src/test/resources/test3.properties";
  // userName が指定されていない場合
  @DataPoint public static String USER_NAME = "src/test/resources/test4.properties";
  // password が指定されていない場合
  @DataPoint public static String PASSWORD = "src/test/resources/test5.properties";

  @Rule
  public WeldInitiator weld =
      WeldInitiator.from(
              PropertiesComponent.class,
              PropertiesComponentImpl.class,
              ConnectionInfoValidator.class,
              ConnectionInfoConverter.class)
          .inject(this)
          .build();

  /** {@link ConnectionInfo} の必須項目に値が設定されていない場合に false が返されること。 */
  @Theory
  public void validation001(String resourceName) throws Exception {
    final ContentsModel model = new ContentsModel();
    model.setPath(resourceName);

    final Map<String, String> properties = component.getProperties(model);
    final ConnectionInfo cnInfo = converter.apply(properties);

    final boolean result = validator.test(cnInfo);
    assertThat(result, is(false));
  }

  /** {@link ConnectionInfo} の必須項目に値が設定されている場合に true が返されること。 */
  @Test
  public void validation002() throws Exception {
    final ContentsModel model = new ContentsModel();
    model.setPath("src/test/resources/connection.properties");

    final Map<String, String> properties = component.getProperties(model);
    final ConnectionInfo cnInfo = converter.apply(properties);

    final boolean result = validator.test(cnInfo);
    assertThat(result, is(true));
  }
}
