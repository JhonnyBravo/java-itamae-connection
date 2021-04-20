package java_itamae_connection.domain.service.connection_info;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import java_itamae_connection.domain.model.ConnectionInfo;
import java_itamae_contents.domain.model.ContentsAttribute;
import java_itamae_contents.domain.repository.stream.StreamRepository;
import java_itamae_contents.domain.repository.stream.StreamRepositoryImpl;
import java_itamae_properties.domain.repository.properties.PropertiesRepository;
import java_itamae_properties.domain.repository.properties.PropertiesRepositoryImpl;

public class ConnectionInfoServiceImpl implements ConnectionInfoService {

    @Override
    public ConnectionInfo getConnectionInfo(ContentsAttribute attr) throws Exception {
        final StreamRepository sr = new StreamRepositoryImpl();
        final PropertiesRepository pr = new PropertiesRepositoryImpl();
        Map<String, String> properties = new HashMap<>();

        try (Reader reader = sr.getReader(attr)) {
            properties = pr.getProperties(reader);
        }

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
