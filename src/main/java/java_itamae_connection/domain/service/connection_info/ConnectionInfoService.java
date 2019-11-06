package java_itamae_connection.domain.service.connection_info;

import java_itamae_connection.domain.model.ConnectionInfo;
import java_itamae_contents.domain.model.ContentsAttribute;

public interface ConnectionInfoService {
    /**
     * DB 接続の設定情報を記載したプロパティファイルを読込み、 ConnectionInfo に変換して取得する。
     *
     * @param attr プロパティファイルの情報を納めた ContentsAttribute を指定する。
     * @return connectionInfo 変換された ConnectionInfo を返す。
     * @throws Exception {@link java.lang.Exception}
     */
    public ConnectionInfo getConnectionInfo(ContentsAttribute attr) throws Exception;
}
