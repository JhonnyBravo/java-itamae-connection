package java_itamae_connection.domain.model;

import java.util.Map;
import java.util.function.Function;

/** DB の接続情報を収めた {@link Map} を {@link ConnectionInfo} に変換する。 */
public class ConnectionInfoConverter implements Function<Map<String, String>, ConnectionInfo> {
  /**
   * DB の接続情報を収めた {@link Map} を {@link ConnectionInfo} に変換する。
   *
   * @param properties DB の接続情報を収めた {@link Map} を指定する。
   * @return connectionInfo 変換された {@link ConnectionInfo} を返す。
   */
  @Override
  public ConnectionInfo apply(final Map<String, String> properties) {
    final ConnectionInfo cnInfo = new ConnectionInfo();

    cnInfo.setHostName(properties.get("hostName"));
    cnInfo.setPortNumber(properties.get("portNumber"));
    cnInfo.setEncoding(properties.get("encoding"));
    cnInfo.setTimeZone(properties.get("timeZone"));
    cnInfo.setDbName(properties.get("dbName"));
    cnInfo.setUserName(properties.get("userName"));
    cnInfo.setPassword(properties.get("password"));

    return cnInfo;
  }
}
