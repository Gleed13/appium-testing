import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AppiumTest extends Thread{

    private HashMap<String, String> desiredCapabilities;
    private String driverURL;
    private AndroidAutoTest androidAutoTest;

    public AppiumTest(HashMap<String, String> desiredCapabilities, String driverURL, AndroidAutoTest androidAutoTest) {
        this.desiredCapabilities = desiredCapabilities;
        this.driverURL = driverURL;
        this.androidAutoTest = androidAutoTest;
    }

    @Override
    public void run() {
        DesiredCapabilities dc = new DesiredCapabilities();
        for (Map.Entry<String, String> entry : desiredCapabilities.entrySet()) {
            dc.setCapability(entry.getKey(), entry.getValue());
        }
        try {
            AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL(driverURL), dc);
            androidAutoTest.runAppiumTest(driver);
        }
        catch (MalformedURLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
