package com.rehoshi.bh;

import com.rehoshi.bh.booter.LoginBooter;
import com.rehoshi.bh.domain.Rect;
import com.rehoshi.bh.driver.AdbDriver;
import com.rehoshi.bh.log.Log;
import com.rehoshi.bh.orc.Ocer;

import org.apache.commons.io.FileUtils;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Main {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public static void main(String[] args) {
        Moniter moniter = new Moniter(new LoginBooter());
        moniter.start();
//        AdbDriver adbDriver = new AdbDriver();
//        try {
//            adbDriver.connectTarget("127.0.0.1", 7555);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }



    static void threshold(){
        Mat mat = Imgcodecs.imread("D:\\HakiOut\\1578295224982.png") ;
        Mat src = new Mat() ;
        Imgproc.cvtColor(mat, src, Imgproc.COLOR_RGB2GRAY);
        Mat target = new Mat();
        // 二值化处理
        Imgproc.threshold(src, target, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);//灰度图像二值化
        // 保存二值化后图片
        Imgcodecs.imwrite("D:/b160_2.png", target);
        Imgproc.adaptiveThreshold(src, target, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 5, 0);
        // 保存二值化后图片
        Imgcodecs.imwrite("D:/threshold.png", target);
        Mat bitwise = new Mat() ;
        Core.bitwise_not(target, bitwise);
        Imgcodecs.imwrite("D:/bitwise.png", bitwise);
        String ocr = Ocer.getInstance().ocrChiness(src, new Rect(498, 18, 75, 19));
        System.out.println(ocr);
    }

    static void testOcr(){
        Mat mat = Imgcodecs.imread("D:\\HakiOut\\1578295224982.png") ;
        Mat sense = new Mat() ;
        Imgproc.cvtColor(mat, sense, Imgproc.COLOR_RGB2GRAY);
        int cols = sense.cols();
        int rows = sense.rows();
        byte[] bytes = new byte[cols * rows] ;
        sense.get(0, 0, bytes) ;
        String ocr = Ocer.getInstance().ocr(ByteBuffer.wrap(bytes), cols, rows, new Rectangle(440, 122, 93, 41));
        System.out.println("ocr结果 " + ocr);
//        boolean matches = ocr.replaceAll("\\s", "").matches(".*?" + text + ".*?");
    }

    private static int colorToRGB(int alpha, int red, int green, int blue) {
        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;
        return newPixel;
    }
    public static BufferedImage graify(String src) throws IOException {
        BufferedImage bufferedImage
                = ImageIO.read(new File(src));
        BufferedImage grayImage =
                new BufferedImage(bufferedImage.getWidth(),
                        bufferedImage.getHeight(),
                        bufferedImage.getType());
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                final int color = bufferedImage.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >> 8) & 0xff;
                final int b = color & 0xff;
                int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);
                int newPixel = colorToRGB(255, gray, gray, gray);
                grayImage.setRGB(i, j, newPixel);
            }
        }
        return grayImage ;
    }

    /**
     * OpenCV-4.1.0 模板匹配
     * <table border="1" cellpadding="8">
     * <tr><th>输入参数</th><th>参数解释</th></tr>
     * <tr><td align="left">TM_SQDIFF是平方差匹配、TM_SQDIFF_NORMED是标准平方差匹配</td><td>利用平方差来进行匹配,最好匹配为0.匹配越差,匹配值越大。</td></tr>
     * <tr><td align="left">TM_CCORR是相关性匹配、TM_CCORR_NORMED是标准相关性匹配</td><td>采用模板和图像间的乘法操作,数越大表示匹配程度较高, 0表示最坏的匹配效果。</td></tr>
     * <tr><td align="left">TM_CCOEFF是相关性系数匹配、TM_CCOEFF_NORMED是标准相关性系数匹配</td><td>将模版对其均值的相对值与图像对其均值的相关值进行匹配,1表示完美匹配,-1表示糟糕的匹配,0表示没有任何相关性(随机序列)。</td></tr>
     * <tr><td colspan="2">随着从简单的测量(平方差)到更复杂的测量(相关系数),我们可获得越来越准确的匹配(同时也意味着越来越大的计算代价)。</td></tr>
     * <tr><td colspan="2">相关性是越接近1越大越好，平方差是越小越好，所以TM_SQDIFF在使用时和其他的是有所区别的。</td></tr>
     * <tr><td colspan="2">模板匹配结果Mat要是32位的。</td></tr>
     * </table>
     * @return: void
     * @date: 2019年5月7日12:16:55
     */
    public static void templete(int method) {

        // 1 获取待匹配图片
        Mat templete=Imgcodecs.imread("D:\\HoshiTemp\\screenshot18254847361867441.png");
        // 2 获取匹配模板
//        Mat demo=Imgcodecs.imread("D:\\HakiOut\\task_confirm.PNG");
        Mat demo=Imgcodecs.imread("D:\\HakiOut\\1577778831347.png");
        int width=templete.cols()-demo.cols()+1;
        int height=templete.rows()-demo.rows()+1;
        // 3 创建32位模板匹配结果Mat
        Mat result=new Mat(width,height,CvType.CV_32FC1);
        // 4 调用 模板匹配函数
        Imgproc.matchTemplate(templete, demo, result, method);
        // 5 归一化
        Core.normalize(result, result,0, 1, Core.NORM_MINMAX, -1, new Mat());
        // 6 获取模板匹配结果
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
        // 7 绘制匹配到的结果
        double x,y;
        if (method==Imgproc.TM_SQDIFF_NORMED || method==Imgproc.TM_SQDIFF) {
            x = mmr.minLoc.x;
            y = mmr.minLoc.y;
        } else {
            x = mmr.maxLoc.x;
            y = mmr.maxLoc.y;
        }
        Imgproc.rectangle(templete,new Point(x,y),new Point(x+demo.cols(),y+demo.rows()),new Scalar( 0, 0, 255),2,Imgproc.LINE_AA);
        Imgproc.putText(templete,"Match Success",new Point(x,y),Imgproc.FONT_HERSHEY_SCRIPT_COMPLEX, 1.0, new Scalar(0, 255, 0),1,Imgproc.LINE_AA);
        // 8 显示结果
        HighGui.imshow("模板匹配", templete);
        HighGui.waitKey(0);
    }
}
