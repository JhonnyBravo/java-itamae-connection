package java_itamae_connection.app.validator;

import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import java_itamae_connection.domain.model.ConnectionInfo;
import java_itamae_connection.domain.service.connection_info.ConnectionInfoService;
import java_itamae_connection.domain.service.connection_info.ConnectionInfoServiceImpl;
import java_itamae_contents.domain.model.ContentsAttribute;

public class IsValidConnectionInfoTest {
    private ContentsAttribute attr;
    private ConnectionInfoService service;
    private IsValidConnectionInfo validator;

    @Before
    public void setUp() throws Exception {
        attr = new ContentsAttribute();
        service = new ConnectionInfoServiceImpl();
        validator = new IsValidConnectionInfo();
    }

    @Test
    public void dbNameが設定されていない場合にfalseが返されること() throws Exception {
        attr.setPath("src/test/resources/test3.properties");
        final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
        final boolean result = validator.test(cnInfo);
        assertThat(result, is(false));
    }

    @Test
    public void userNameが設定されていない場合にfalseが返されること() throws Exception {
        attr.setPath("src/test/resources/test4.properties");
        final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
        final boolean result = validator.test(cnInfo);
        assertThat(result, is(false));
    }

    @Test
    public void passwordが設定されていない場合にfalseが返されること() throws Exception {
        attr.setPath("src/test/resources/test5.properties");
        final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
        final boolean result = validator.test(cnInfo);
        assertThat(result, is(false));
    }

}
