package java_itamae_connection.domain.service.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import java_itamae_connection.domain.model.ConnectionInfo;

public abstract class ConnectionService {
    /**
     * @param cnInfo 設定ファイルの情報を納めた ConnectionInfo を指定する。
     * @return connection DB 接続を返す。
     * @throws Exception {@link java.lang.Exception}
     */
    public Connection getConnection(ConnectionInfo cnInfo) throws Exception {
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
        final Connection connection = DriverManager.getConnection(cnStr, cnInfo.getUserName(), cnInfo.getPassword());

        return connection;
    }
}
