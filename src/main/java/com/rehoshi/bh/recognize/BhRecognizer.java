package com.rehoshi.bh.recognize;

import com.rehoshi.bh.booter.BhDriver;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;

public class BhRecognizer implements Recognizer {

    private BhDriver bhDriver;

    public void bindDriver(BhDriver driver) {
        this.bhDriver = driver;
    }

    public Rectangle2D.Double findIn(String target, String sense) {
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
        // 7 绘制匹配到的结果
        double x, y;
        if (method == Imgproc.TM_SQDIFF_NORMED || method == Imgproc.TM_SQDIFF) {
            x = mmr.minLoc.x;
            y = mmr.minLoc.y;
        } else {
            x = mmr.maxLoc.x;
            y = mmr.maxLoc.y;
        }
        Imgproc.rectangle(templete,new Point(x,y),new Point(x+demo.cols(),y+demo.rows()),new Scalar( 0, 0, 255),2,Imgproc.LINE_AA);
        Imgproc.putText(templete,"Match Success",new Point(x,y),Imgproc.FONT_HERSHEY_SCRIPT_COMPLEX, 1.0, new Scalar(0, 255, 0),1,Imgproc.LINE_AA);

        BufferedImage image = (BufferedImage) HighGui.toBufferedImage(templete);
        try {
            ImageIO.write(image, "png", new FileOutputStream("C:\\Users\\hoshi\\Desktop\\Out\\" + System.currentTimeMillis() +".png")) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Rectangle2D.Double(x, y, demo.width(), demo.height());
    }

    public Rectangle2D.Double findInScreen(String imgInRes) {
        File screenAsFile = this.bhDriver.getScreenAsFile();
        String screenPath = screenAsFile.getAbsolutePath();
        String targetPath = Resource.class.getResource(imgInRes).getPath().replaceFirst("/", "");
        Rectangle2D.Double in = findIn(targetPath, screenPath);
        screenAsFile.delete() ;
        return in ;
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


}
