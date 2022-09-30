package java_itamae_connection.domain.repository.common;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import jakarta.inject.Inject;
import java.sql.Connection;
import java.util.Map;
import java_itamae.domain.component.properties.PropertiesComponent;
import java_itamae.domain.component.properties.PropertiesComponentImpl;
import java_itamae.domain.model.contents.ContentsModel;
import java_itamae_connection.domain.model.ConnectionInfo;
import java_itamae_connection.domain.model.ConnectionInfoConverter;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/** DB 接続のテスト */
public class BaseRepositoryTest {
  @Inject private BaseRepository repository;
  @Inject private PropertiesComponent component;
  @Inject private ConnectionInfoConverter converter;
  private ConnectionInfo cnInfo;

  @Rule
  public WeldInitiator weld =
      WeldInitiator.from(
              BaseRepository.class,
              BaseRepositoryImpl.class,
              PropertiesComponent.class,
              PropertiesComponentImpl.class,
              ConnectionInfoConverter.class)
          .inject(this)
          .build();

  @Before
  public void setUp() throws Exception {
    final ContentsModel model = new ContentsModel();
    model.setPath("src/test/resources/connection.properties");

    final Map<String, String> properties = component.getProperties(model);
    cnInfo = converter.apply(properties);
  }

  /** {@link BaseRepository#getConnection(ConnectionInfo)} 実行時に DB 接続を確立できること。 */
  @Test
  public void css001() throws Exception {
    try (Connection connection = repository.getConnection(cnInfo)) {
      assertThat(connection.isClosed(), is(false));
    } catch (final Exception e) {
      System.err.println(e);
      throw e;
    }
  }
}
