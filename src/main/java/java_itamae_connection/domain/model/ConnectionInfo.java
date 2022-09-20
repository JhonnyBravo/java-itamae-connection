package java_itamae_connection.domain.model;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Optional;

/** DB への接続情報を管理する。 */
public class ConnectionInfo implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 接続先 DB のホスト名 */
  private String hostName;
  /** DB 接続時に使用するポート番号 */
  private String portNumber;
  /** 接続先 DB の文字エンコーディング */
  private String encoding;
  /** 接続先 DB のタイムゾーン */
  private String timeZone;
  /** 接続対象とする DB の名前 */
  @NotNull private String dbName;
  /** DB 接続時に使用するユーザ名 */
  @NotNull private String userName;
  /** DB 接続時に使用するパスワード */
  @NotNull private String password;

  /**
   * DB サーバのホスト名を返す。(既定値: localhost)
   *
   * @return hostName DB サーバのホスト名
   */
  public String getHostName() {
    final Optional<String> value = Optional.ofNullable(hostName);
    return value.orElse("localhost");
  }

  /**
   * DB サーバのホスト名を設定する。
   *
   * @param hostName DB サーバのホスト
   */
  public void setHostName(final String hostName) {
    this.hostName = hostName;
  }

  /**
   * DB 接続時に使用するポート番号を返す。(既定値: 3306)
   *
   * @return portNumber DB 接続時に使用するポート番号
   */
  public String getPortNumber() {
    final Optional<String> value = Optional.ofNullable(portNumber);
    return value.orElse("3306");
  }

  /**
   * DB 接続時に使用するポート番号を設定する。
   *
   * @param portNumber DB 接続時に使用するポート番号
   */
  public void setPortNumber(final String portNumber) {
    this.portNumber = portNumber;
  }

  /**
   * DB サーバの文字エンコーディングを返す。(既定値: UTF-8)
   *
   * @return encoding DB サーバの文字エンコーディング
   */
  public String getEncoding() {
    final Optional<String> value = Optional.ofNullable(encoding);
    return value.orElse("UTF-8");
  }

  /**
   * DB サーバの文字エンコーディングを設定する。
   *
   * @param encoding DB サーバの文字エンコーディング
   */
  public void setEncoding(final String encoding) {
    this.encoding = encoding;
  }

  /**
   * DB サーバのタイムゾーンを返す。(既定値: SERVER)
   *
   * @return timeZone DB サーバのタイムゾーン
   */
  public String getTimeZone() {
    final Optional<String> value = Optional.ofNullable(timeZone);
    return value.orElse("SERVER");
  }

  /**
   * DB サーバのタイムゾーンを設定する。
   *
   * @param timeZone DB サーバのタイムゾーン
   */
  public void setTimeZone(final String timeZone) {
    this.timeZone = timeZone;
  }

  /**
   * DB 接続時に使用する DB 名を返す。
   *
   * @return dbName DB 接続時に使用する DB の名前
   */
  public String getDbName() {
    return dbName;
  }

  /**
   * DB 接続時に使用する DB 名を設定する。
   *
   * @param dbName DB 接続時に使用する DB の名前
   */
  public void setDbName(final String dbName) {
    this.dbName = dbName;
  }

  /**
   * DB 接続時に使用するユーザ名を返す。
   *
   * @return userName DB 接続時に使用するユーザ名
   */
  public String getUserName() {
    return userName;
  }

  /**
   * DB 接続時に使用する使用するユーザ名を設定する。
   *
   * @param userName DB 接続時に使用するユーザ名
   */
  public void setUserName(final String userName) {
    this.userName = userName;
  }

  /**
   * DB 接続時に使用するパスワードを返す。
   *
   * @return password DB 接続時に使用するパスワード
   */
  public String getPassword() {
    return password;
  }

  /**
   * DB 接続時に使用するパスワードを設定する。
   *
   * @param password DB 接続時に使用するパスワード
   */
  public void setPassword(final String password) {
    this.password = password;
  }
}
