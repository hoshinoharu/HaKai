package com.rehoshi.bh.driver;

import com.rehoshi.bh.controller.action.AdbTouchAction;
import com.rehoshi.bh.controller.action.BhTouchAction;
import com.rehoshi.bh.domain.Point;
import com.rehoshi.bh.domain.Rect;
import com.rehoshi.bh.log.Log;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AdbDriver implements BhDriver {

    private File screenInFrame;
    private String host;
    private int port;
    private String tempDir = "D:/HakaiTemp";

    @Override
    public void connectTarget(String host, int port) throws Exception {
        this.host = host;
        this.port = port;
        String targetDevices = getTargetDevices();
        boolean success = executeAdbCommand(String.format("adb connect %s", targetDevices));
        if (!success) {
            throw new RuntimeException("连接目标设备异常");
        }

        //强制关闭app
        executeAdbCommand(String.format("adb shell am force-stop %s", APP_PACKAGE));

        //重新打开
        if (!executeAdbCommand(String.format("adb shell am start -n %s/%s", APP_PACKAGE, APP_ACTIVITY))) {
            throw new RuntimeException("启动APP异常");
        }

        File file = new File("D:/HakaiTemp");
        if (file.exists()) {
            file.delete() ;
        }
        file.mkdirs();
    }

    @Override
    public void startFrame() {
        screenInFrame = null;
    }

    @Override
    public void endFrame() {
        if (screenInFrame != null && screenInFrame.exists()) {
            screenInFrame.delete();
        }
    }

    @Override
    public File getScreenAsFile() {
        if (screenInFrame == null) {
            screenInFrame = screenShot();
        }
        return screenInFrame;
    }

    private File screenShot() {
        String fileName = String.format("%s/%d.png", tempDir, System.nanoTime());
        File file = new File(fileName);
        try {
            Process exec = Runtime.getRuntime().exec("adb exec-out screencap -p");
            FileUtils.copyInputStreamToFile(exec.getInputStream(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    @Override
    public BhTouchAction newTouch() {
        return new AdbTouchAction(this);
    }


    public boolean executeAdbCommand(String... cmds) {
        return executeAdbCommand(Arrays.asList(cmds));
    }

    public boolean executeAdbCommand(List<String> cmd) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process exec = null;
            for (String c : cmd) {
                System.out.println("执行命令：" + c);
                exec = runtime.exec(c);
                Log.log(exec);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getTargetDevices() {
        return String.format("%s:%d", host, port);
    }
}
