package test;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServer {

    public String serverAddress;
    public int port;
    public static AppiumDriverLocalService service;//In charge of starting and stopping the server
    private static AppiumServiceBuilder builder;//In charge of building the service with different options

    public AppiumServer(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public void startAppiumServer() {
        builder = new AppiumServiceBuilder();
        builder.usingPort(port);
        builder.withIPAddress(serverAddress);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "warn");
        service = builder.build();
        service.start();
    }

    public AppiumDriverLocalService getService() {
        return service;
    }
    
}
