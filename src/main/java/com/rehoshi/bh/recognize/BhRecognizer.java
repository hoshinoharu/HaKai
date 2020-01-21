package com.rehoshi.bh.recognize;

import com.rehoshi.bh.auto.Hakai;
import com.rehoshi.bh.auto.HakaiId;
import com.rehoshi.bh.booter.BhDriver;
import com.rehoshi.bh.checker.RecognizeChecker;
import com.rehoshi.bh.domain.MatchResult;
import com.rehoshi.bh.domain.Rect;
import com.rehoshi.bh.domain.RecognizeResult;
import org.apache.commons.io.FileUtils;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;

public class BhRecognizer implements Recognizer {

    private BhDriver bhDriver;
    private boolean debug = true;

    public void bindDriver(BhDriver driver) {
        this.bhDriver = driver;
        deployResource();
    }

    public MatchResult findIn(String target, String sense) {
        return findIn(target, sense, "Match Success");
    }

    public MatchResult findIn(String target, String sense, String tag) {
        // 1 获取待匹配图片
        Mat templete;
        templete = Imgcodecs.imread(sense);
        // 2 获取匹配模板
        Mat demo;
        demo = Imgcodecs.imread(target);
        int width = templete.cols() - demo.cols() + 1;
        int height = templete.rows() - demo.rows() + 1;
        // 3 创建32位模板匹配结果Mat
        Mat result = new Mat(width, height, CvType.CV_32FC1);
        int method = Imgproc.TM_SQDIFF_NORMED;
        // 4 调用 模板匹配函数
        Imgproc.matchTemplate(templete, demo, result, method);
        // 5 归一化
        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        // 6 获取模板匹配结果
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
//        System.out.println("识别结果 " + tag + " " + Math.abs(0 - mmr.minVal));
        // 7 绘制匹配到的结果
        double x, y;
        if (method == Imgproc.TM_SQDIFF_NORMED || method == Imgproc.TM_SQDIFF) {
            x = mmr.minLoc.x;
            y = mmr.minLoc.y;
        } else {
            x = mmr.maxLoc.x;
            y = mmr.maxLoc.y;
        }
        if(debug){
            Imgproc.rectangle(templete, new Point(x, y), new Point(x + demo.cols(), y + demo.rows()), new Scalar(0, 0, 255), 2, Imgproc.LINE_AA);
            Imgproc.putText(templete, tag, new Point(x, y), Imgproc.FONT_HERSHEY_SCRIPT_COMPLEX, 1.0, new Scalar(0, 255, 0), 1, Imgproc.LINE_AA);

            BufferedImage image = (BufferedImage) HighGui.toBufferedImage(templete);
            try {
                ImageIO.write(image, "png", new FileOutputStream("D:\\HakiOut\\" + System.currentTimeMillis() + ".png"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Rect rect = new Rect(x, y, demo.width(), demo.height());
        MatchResult matchResult = new MatchResult();
        matchResult.setMatchRect(rect);
        matchResult.setSense(templete);
        matchResult.setTarget(demo);
        matchResult.setMinVal(mmr.minVal);
        return matchResult;
    }

    public MatchResult findInScreen(String imgInRes) {
        return findInScreen(imgInRes, "");
    }

    private final static String runtimePath = getRuntimePath() ;

    public MatchResult findInScreen(String imgInRes, String info) {
        File screenAsFile = this.bhDriver.getScreenAsFile();
        String screenPath = screenAsFile.getAbsolutePath();
        if (!imgInRes.startsWith("/")) {
            imgInRes = "/" + imgInRes;
        }
        String targetPath = runtimePath + imgInRes ;
        MatchResult in = findIn(targetPath, screenPath, info);
        return in;
    }

    private static String getRuntimePath(){
        String runtimePath ;
        String path = BhRecognizer.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File root = new File(path) ;
        runtimePath = root.getParent() ;
        return runtimePath;
    }

    public void deployResource(){
        deployImgs();
    }


    /**
     * 自动复制jar包中的资源到jar包的根目录
     */
    private void deployImgs(){
        File imgsRoot = new File(runtimePath, "imgs") ;
        if(imgsRoot.exists()){//清除之前的缓存
            imgsRoot.delete() ;
        }
        imgsRoot.mkdirs() ;//创建目录

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver() ;
        try {
            //获取所有的img资源
            Resource[] resources = resolver.getResources("classpath*:imgs/**");
            String rootPath = BhRecognizer.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            if(resources != null){
                Arrays.asList(resources).forEach(resource -> {
                    try {
                        URL url = resource.getURL();
                        String resPath = url.getPath() ;
                        String relativePath ;
                        if(rootPath.endsWith(".jar")){
                            String jarPath = "file:" + rootPath + "!/imgs" ;
                            relativePath = resPath.replace(jarPath, "") ;
                        }else {
                            relativePath = resPath.replace(rootPath+"imgs", "") ;
                        }
                        if(resource.isReadable()){
                            FileUtils.copyInputStreamToFile(resource.getInputStream(), new File(imgsRoot, relativePath));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @HakaiId
    public RecognizeResult findTaskConfirm() {
        MatchResult inScreen = findInScreen("/imgs/dialog/task_confirm.png");
        return $().targetX(365).targetY(333)
                .inSense(inScreen)
                .checker(RecognizeChecker.X_ONLY)
                .desc("任务完成");
    }

    @HakaiId
    public RecognizeResult findError() {
        MatchResult inScreen = findInScreen("/imgs/dialog/dialog_error.png");
        return new RecognizeResult(210, 121, inScreen)
                .desc("游戏报错")
                .intentRect(new Rect(558, 365, 20, 20))//继续游戏
//                .intentRect(new Rectangle2D.Double(296, 363, 20, 20))//退出按钮
                ;
    }

    @HakaiId
    public RecognizeResult findBackHome() {
        return $().targetX(117).targetY(9)
                .desc("返回主菜单")
                .inSense(findInScreen("imgs/home/home_back_btn.png"));
    }

    public static Mat inputStream2Mat(InputStream inputStream) throws IOException {
        BufferedInputStream is = new BufferedInputStream(inputStream);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = is.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.flush();
        os.close();
        is.close();

        Mat encoded = new Mat(1, os.size(), 0);
        encoded.put(0, 0, os.toByteArray());

        Mat decoded = Imgcodecs.imdecode(encoded, -1);
        encoded.release();
        return decoded;
    }

    private Mat bufferedImageToMat(BufferedImage bufferedImage) {
        byte[] data = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        Mat template = new Mat(width, height, CvType.CV_8UC3);
        template.put(0, 0, data);
        return template;
    }

    protected RecognizeResult $() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        boolean findHakaiId = false;
        String className = null;
        String methodName = null;
        for (int i = 1; i < stackTrace.length && !findHakaiId; i++) {
            StackTraceElement element = stackTrace[i];
            methodName = element.getMethodName();
            if (!"$".equals(methodName)) {
                findHakaiId = true;
                String[] split = element.getClassName().split("\\.");
                //获取简单类名
                className = split[split.length - 1];
            }
        }

        RecognizeResult result = new RecognizeResult();
        if (findHakaiId) {
            String cls = Hakai.Id.class.getName();
            String idCls = cls + "$" + className;
            try {
                Class<?> aClass = Class.forName(idCls);
                Field declaredField = aClass.getDeclaredField(methodName);
                Object o = declaredField.get(aClass);
                result.id((Integer) o);
            } catch (Exception ignore) {
            }
        }
        System.out.println("识别结果{ID}:" + result.getId());
        return result;
    }

}
