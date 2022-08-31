package java_itamae_connection.domain.service.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import java_itamae.domain.service.common.BaseService;
import java_itamae_connection.domain.model.ConnectionInfo;

/** DB への接続を管理する。 */
public interface ConnectionService extends BaseService {
  /**
   * DB 接続の設定情報を格納した {@link Map} を {@link ConnectionInfo} に変換する。
   *
   * @param properties DB 接続の情報を納めた {@link Map} を指定する。
   * @return connectionInfo {@link ConnectionInfo} を返す。
   */
  public default ConnectionInfo convertToConnectionInfo(Map<String, String> properties) {
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

  /**
   * DB 接続を確立する。
   *
   * @param cnInfo 設定ファイルの情報を納めた {@link ConnectionInfo} を指定する。
   * @return connection {@link Connection} を取得する。
   * @throws Exception {@link Exception}
   */
  public default Connection getConnection(ConnectionInfo cnInfo) throws Exception {
    final StringBuilder buffer = new StringBuilder();

    buffer.append("jdbc:mysql://");
    buffer.append(cnInfo.getHostName() + ":");
    buffer.append(cnInfo.getPortNumber() + "/");
    buffer.append(cnInfo.getDbName());
    buffer.append("?characterEncoding=" + cnInfo.getEncoding());
    buffer.append("&serverTimezone=" + cnInfo.getTimeZone());
    buffer.append("&verifyServerCertificate=false");
    buffer.append("&useSSL=false");
    buffer.append("&allowPublicKeyRetrieval=true");

    final String cnStr = new String(buffer);

    Class.forName("com.mysql.cj.jdbc.Driver");
    return DriverManager.getConnection(cnStr, cnInfo.getUserName(), cnInfo.getPassword());
  }
}
