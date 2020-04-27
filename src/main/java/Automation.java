import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Automation {
    //appium server and udids
    public static final String APPIUM_SERVER_IP = "127.0.0.1";
    public static final String[] PORTS = {
            "4723",
//            "5000",
    };
    public static final String[] UDIDS = {
            "7643ca010504",
//            "3fcb6850",
    };

    //capabilities
    public static final HashMap<String, String> BASE_CAPABILITIES = new HashMap<String, String>() {
        {
            put(MobileCapabilityType.PLATFORM_NAME, "android");
            put(AndroidMobileCapabilityType.APP_PACKAGE, "com.crypto.bitslotac");
            put(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
            put(MobileCapabilityType.NO_RESET, "true");
            put(MobileCapabilityType.FULL_RESET, "false");
            put(AndroidMobileCapabilityType.SKIP_DEVICE_INITIALIZATION, "true");
        }
    };

    public static void main(String[] args) {
        AndroidAutoTest androidAutoTest = driver -> {
            Random rand = new Random();

            MobileElement el1 = driver.findElementById("com.crypto.bitslotac:id/rollButton");
            Thread.sleep(rand.nextInt(4000) + 3000);
            el1.click();
            Thread.sleep(rand.nextInt(9200) + 200);
            el1.click();
            Thread.sleep(rand.nextInt(1500) + 500);
            el1.click();
//        MobileElement el2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[10]/android.view.View");
//        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
            Thread.sleep(rand.nextInt(4000) + 16000);
            MobileElement el2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View");
            driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
            el2.click();
            Thread.sleep(rand.nextInt(9200) + 200);
            el1.click();
        };

        for (int i = 0; i < UDIDS.length; i++) {
            HashMap<String, String> desiredCapabilities = new HashMap<>(BASE_CAPABILITIES);
            desiredCapabilities.put(MobileCapabilityType.UDID, UDIDS[i]);
            desiredCapabilities.put(MobileCapabilityType.DEVICE_NAME, "Device " + i);
            desiredCapabilities.put(AndroidMobileCapabilityType.SYSTEM_PORT, (8201 + i) + "");

            String url = getUrlAt(i);

            AppiumTest appiumTest = new AppiumTest(desiredCapabilities, url, androidAutoTest);

            appiumTest.start();
        }
    }

    public static String getUrlAt(int number) {
        return "http://" + APPIUM_SERVER_IP + ":" + PORTS[number] + "/wd/hub";
    }
}
