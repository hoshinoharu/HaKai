package com.rehoshi.bh.orc;

import com.rehoshi.bh.domain.Rect;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Mat;

import javax.annotation.Resource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

public class Ocer {
    private static Ocer instance;

    public synchronized static Ocer getInstance() {
        if (instance == null) {
            instance = new Ocer();
        }
        return instance;
    }

    private Tesseract tesseract;

    private Ocer() {
        this.tesseract = new Tesseract();
        tesseract.setDatapath(Resource.class.getResource("/tessdata").getPath().replaceFirst("/", ""));
        tesseract.setLanguage("chi_sim_vert");
    }

//    public String ocr(BufferedImage bufferedImage){
//        return this.ocr(bufferedImage, null) ;
//    }

    public String ocr(BufferedImage bufferedImage, Rectangle rectangle) {
        try {
            return tesseract.doOCR(bufferedImage,rectangle ).replaceAll("\\s", "") ;
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return "" ;
    }

    /**
     * 识别数字
     * 默认调用英语识别
     * @return
     */
    public String ocrNum(Mat mat, Rect rectangle){
        return ocrEng(mat, rectangle) ;
    }

    /**
     * 识别英文
     * 数字的准确率也挺高
     * @param mat
     * @param rectangle
     * @return
     */
    public String ocrEng(Mat mat, Rect rectangle){
        return ocr(mat, rectangle, "eng") ;
    }

    /**
     * 识别中文
     * 识别包含非中文的场景准确率会下降
     * @return
     */
    public String ocrChiness(Mat mat, Rect rectangle){
        return ocr(mat, rectangle, "chi_sim_vert") ;
    }

    /**
     * 识别opencv中的Mat数据
     * @param mat
     * @param rectangle
     * @param lang
     * @return
     */
    public String ocr(Mat mat, Rect rectangle, String lang){
        tesseract.setLanguage(lang);
        int cols = mat.cols();
        int rows = mat.rows();
        byte[] bytes = new byte[cols * rows] ;
        mat.get(0, 0, bytes) ;
        return ocr(ByteBuffer.wrap(bytes), cols, rows, rectangle.toAwtRectangle()) ;
    }

    public String ocr(ByteBuffer byteBuffer, int cols, int rows, Rectangle rectangle){
        try {
            //直接读取指定区域的灰度图像
            return tesseract.doOCR(cols, rows, byteBuffer, rectangle, 8) ;
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return "" ;
    }

}
