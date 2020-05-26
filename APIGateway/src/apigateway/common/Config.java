package apigateway.common;

import com.vng.csm.configuration.ConfigException;
import com.vng.csm.configuration.Configuration;
import com.vng.csm.configuration.INIFileConfiguration;

public class Config {

    private static Configuration conf = null;

    public static void setConfigPath(String file) throws ConfigException {
        String confFile = System.getProperty("configuration");
        if (confFile == null) {
            confFile = file;
        }
        conf = new INIFileConfiguration(confFile);
    }

    public static String getParameter(String section, String key) {
        return conf.getConfig(section, key);
    }
}
