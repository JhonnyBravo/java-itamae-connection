package java_itamae_connection.domain.service.connection_info;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Function;
import java_itamae_connection.domain.model.ConnectionInfo;
import java_itamae_contents.domain.model.ContentsAttribute;

public class ConnectionInfoServiceImpl implements ConnectionInfoService {
  @Override
  public ConnectionInfo getConnectionInfo(ContentsAttribute attr) throws Exception {
    /**
     * 拡張子無しのファイル名を返す。
     */
    final Function<String, String> getFileName = path -> {
      final String fileName = Paths.get(path).toFile().getName();
      return fileName.substring(0, fileName.lastIndexOf("."));
    };

    /**
     * 親ディレクトリを返す。
     */
    final Function<String, File> getDirectory = path -> {
      return Paths.get(path).getParent().toFile();
    };

    final Locale locale = Locale.getDefault();
    final String fileName = getFileName.apply(attr.getPath());

    final URL[] url = {getDirectory.apply(attr.getPath()).toURI().toURL()};
    final URLClassLoader loader = new URLClassLoader(url);

    final ResourceBundle bundle = ResourceBundle.getBundle(fileName, locale, loader);

    final ConnectionInfo cnInfo = new ConnectionInfo();

    if (bundle.containsKey("hostName")) {
      cnInfo.setHostName(bundle.getString("hostName"));
    }

    if (bundle.containsKey("portNumber")) {
      cnInfo.setPortNumber(bundle.getString("portNumber"));
    }

    if (bundle.containsKey("encoding")) {
      cnInfo.setEncoding(bundle.getString("encoding"));
    }

    if (bundle.containsKey("timeZone")) {
      cnInfo.setTimeZone(bundle.getString("timeZone"));
    }

    if (bundle.containsKey("dbName")) {
      cnInfo.setDbName(bundle.getString("dbName"));
    }

    if (bundle.containsKey("userName")) {
      cnInfo.setUserName(bundle.getString("userName"));
    }

    if (bundle.containsKey("password")) {
      cnInfo.setPassword(bundle.getString("password"));
    }

    return cnInfo;
  }
}
