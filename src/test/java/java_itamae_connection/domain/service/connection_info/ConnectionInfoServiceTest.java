package java_itamae_connection.domain.service.connection_info;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java_itamae_connection.domain.model.ConnectionInfo;
import java_itamae_contents.domain.model.ContentsAttribute;

@RunWith(Enclosed.class)
public class ConnectionInfoServiceTest {
    /**
     * オプション項目が設定されていない場合のテスト
     */
    public static class Test1 {
        private ContentsAttribute attr;
        private ConnectionInfoService service;

        @Before
        public void setUp() throws Exception {
            attr = new ContentsAttribute();
            attr.setPath("src/test/resources/test1.properties");
            service = new ConnectionInfoServiceImpl();
        }

        @Test
        public void hostNameが設定されていない場合にデフォルト値が設定されること() throws Exception {
            final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
            assertThat(cnInfo.getHostName(), is("localhost"));
        }

        @Test
        public void portNumberが設定されていない場合にデフォルト値が設定されること() throws Exception {
            final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
            assertThat(cnInfo.getPortNumber(), is("3306"));
        }

        @Test
        public void encodingが設定されていない場合にデフォルト値が設定されること() throws Exception {
            final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
            assertThat(cnInfo.getEncoding(), is("UTF-8"));
        }

        @Test
        public void timeZoneが設定されていない場合にデフォルト値が設定されること() throws Exception {
            final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
            assertThat(cnInfo.getTimeZone(), is("JST"));
        }
    }

    /**
     * オプション項目が設定されている場合のテスト
     */
    public static class Test2 {
        private ContentsAttribute attr;
        private ConnectionInfoService service;

        @Before
        public void setUp() throws Exception {
            attr = new ContentsAttribute();
            attr.setPath("src/test/resources/test2.properties");
            service = new ConnectionInfoServiceImpl();
        }

        @Test
        public void hostNameの値をプロパティファイルから取得できること() throws Exception {
            final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
            assertThat(cnInfo.getHostName(), is("192.168.2.30"));
        }

        @Test
        public void portNumberの値をプロパティファイルから取得できること() throws Exception {
            final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
            assertThat(cnInfo.getPortNumber(), is("3333"));
        }

        @Test
        public void encodingの値をプロパティファイルから取得できること() throws Exception {
            final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
            assertThat(cnInfo.getEncoding(), is("MS932"));
        }

        @Test
        public void timeZoneの値をプロパティファイルから取得できること() throws Exception {
            final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
            assertThat(cnInfo.getTimeZone(), is("UTC"));
        }

        @Test
        public void dbNameの値をプロパティファイルから取得できること() throws Exception {
            final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
            assertThat(cnInfo.getDbName(), is("test_db"));
        }

        @Test
        public void userNameの値をプロパティファイルから取得できること() throws Exception {
            final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
            assertThat(cnInfo.getUserName(), is("test_user"));
        }

        @Test
        public void passwordの値をプロパティファイルから取得できること() throws Exception {
            final ConnectionInfo cnInfo = service.getConnectionInfo(attr);
            assertThat(cnInfo.getPassword(), is("p@ssword"));
        }
    }

    /**
     * 必須項目が設定されていない場合のテスト
     */
    public static class Test3 {
        private ContentsAttribute attr;
        private ConnectionInfoService service;

        @Before
        public void setUp() throws Exception {
            attr = new ContentsAttribute();
            service = new ConnectionInfoServiceImpl();
        }

        @Test(expected = Exception.class)
        public void dbNameが設定されていない場合にExceptionが発生すること() throws Exception {
            try {
                attr.setPath("src/test/resources/test3.properties");
                service.getConnectionInfo(attr);
            } catch (final Exception e) {
                System.err.println(e);
                throw e;
            }
        }

        @Test(expected = Exception.class)
        public void userNameが設定されていない場合にExceptionが発生すること() throws Exception {
            try {
                attr.setPath("src/test/resources/test4.properties");
                service.getConnectionInfo(attr);
            } catch (final Exception e) {
                System.err.println(e);
                throw e;
            }
        }

        @Test(expected = Exception.class)
        public void passwordが設定されていない場合にExceptionが発生すること() throws Exception {
            try {
                attr.setPath("src/test/resources/test5.properties");
                service.getConnectionInfo(attr);
            } catch (final Exception e) {
                System.err.println(e);
                throw e;
            }
        }
    }
}
