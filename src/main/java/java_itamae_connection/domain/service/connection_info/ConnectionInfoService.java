package java_itamae_connection.domain.service.connection_info;

import java_itamae_connection.domain.model.ConnectionInfo;
import java_itamae_contents.domain.model.ContentsAttribute;

/**
 * {@link ConnectionInfo} の生成を管理する。
 */
public interface ConnectionInfoService {
  /**
   * DB 接続の設定情報を記載したプロパティファイルを読込み、 {@link ConnectionInfo} を生成して返す。
   *
   * @param attr プロパティファイルの情報を納めた {@link ContentsAttribute} を指定する。
   * @return connectionInfo {@link ConnectionInfo} を返す。
   * @throws Exception {@link java.lang.Exception}
   */
  ConnectionInfo getConnectionInfo(ContentsAttribute attr) throws Exception;
}
