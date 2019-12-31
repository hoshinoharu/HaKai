package com.rehoshi.bh.booter;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BhDriver{
    /**
     * 一帧中的屏幕截图
     */
    private File screenInFrame ;
    private AndroidDriver driver;
    public BhDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "127.0.0.1:62001"); //
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.miHoYo.enterprise.NGHSoD");
        desiredCapabilities.setCapability("appActivity", "com.miHoYo.overridenativeactivity.OverrideNativeActivity");
        desiredCapabilities.setCapability("unicodeKeyboard", true);
        desiredCapabilities.setCapability("resetKeyboard", true); // 是否重置输入法到原状态
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("sessionOverride", false);
        desiredCapabilities.setCapability("newCommandTimeout", "30000");

        URL url = null;
        url = new URL("http://127.0.0.1:4723/wd/hub");
        this.driver = new AndroidDriver(url, desiredCapabilities);
    }

    private AndroidDriver getDriver() {
        return driver;
    }

    /**
     * 一帧的开始
     * 可以在这里作一次帧分析的缓存初始化操作
     */
    public void startFrame(){
        this.screenInFrame = null ;
    }

    /**
     * 一帧的结束
     * 释放资源
     */
    public void endFrame(){
        if(this.screenInFrame != null){
            this.screenInFrame.delete() ;
        }
    }

    public File getScreenAsFile(){
        if(screenInFrame == null){
            screenInFrame = getDriver().getScreenshotAs(OutputType.FILE) ;
        }
        return screenInFrame ;
    }

    public boolean click(Rectangle2D.Double rect){
        TouchAction touchAction = new AndroidTouchAction(getDriver()) ;
        touchAction.tap(PointOption.point(new Point((int)(rect.x + rect.width / 2), (int)(rect.y + rect.height / 2)))).release().perform();
        return true ;
    }

}
