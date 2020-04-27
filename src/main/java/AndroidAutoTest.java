import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public interface AndroidAutoTest {
    void runAppiumTest(AndroidDriver<AndroidElement> driver) throws InterruptedException;
}
