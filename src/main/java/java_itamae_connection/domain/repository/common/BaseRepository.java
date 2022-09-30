package java_itamae_connection.domain.repository.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java_itamae_connection.domain.model.ConnectionInfo;

/** Repository クラスの土台となるクラス。 */
public interface BaseRepository {
  /**
   * DB 接続を確立する。
   *
   * @param cnInfo 設定ファイルの情報を納めた {@link ConnectionInfo} を指定する。
   * @return connection {@link Connection} を取得する。
   * @throws Exception {@link Exception}
   */
  default Connection getConnection(final ConnectionInfo cnInfo) throws Exception {
    final StringBuilder buffer = new StringBuilder();

    buffer
        .append("jdbc:mysql://")
        .append(cnInfo.getHostName() + ":")
        .append(cnInfo.getPortNumber() + "/")
        .append(cnInfo.getDbName())
        .append("?characterEncoding=" + cnInfo.getEncoding())
        .append("&serverTimezone=" + cnInfo.getTimeZone())
        .append("&verifyServerCertificate=false")
        .append("&useSSL=false")
        .append("&allowPublicKeyRetrieval=true");

    final String cnStr = new String(buffer);

    Class.forName("com.mysql.cj.jdbc.Driver");
    return DriverManager.getConnection(cnStr, cnInfo.getUserName(), cnInfo.getPassword());
  }
}
