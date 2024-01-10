package com.tools.common.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Title: PDFBoxUtils
 * @Author LinLuoChen
 * @ProjectName ToolCabinet
 * @Description: 操作 PDFBOX 解析 PDF
 * @Date 2023/4/10 9:24
 */
public class PDFBoxUtils {

    /**
     * @Description 判断 PDF 是否存在指定文字 [ true 存在 / false 不存在]
     * @Author LinLuoChen
     * @Date 2023/4/10 10:02
     * @Param [
     * inputStream 文件流
     * str 要验证的文字
     * ]
     * @Return java.lang.Boolean
     **/
    public static Boolean getPDFVerify(File file, String str) {
        // 测试！
        // File file = new File("C:\\Users\\30447\\Desktop\\睿泽\\住宅加装电梯项目\\济南市既有住宅增设电梯有关手续办理导则(3).pdf");
        try {
            // 读取 PDF 文件
            PDDocument document = PDDocument.load(file);
            // 实例化 PDFTextStripper 类
            PDFTextStripper pdfStripper = new PDFTextStripper();
            // 指定获取页码
            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(2);
            // 提取 PDF 文字
            String text = pdfStripper.getText(document);
            // 判断是否包含指定文字
            if (text.indexOf(str) > 0) {
                // 包含
                return true;
            }
            // 关闭连接
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * @Description 提取 PDF 文字
     * @Author LinLuoChen
     * @Date 2023/4/10 10:17
     * @Param [
     * inputStream 文件流
     * ]
     * @Return java.lang.String
     **/
    public static String getPDFText(InputStream inputStream) {
        // 测试！
        // File file = new File("C:\\Users\\30447\\Desktop\\睿泽\\住宅加装电梯项目\\济南市既有住宅增设电梯有关手续办理导则(3).pdf");
        String text = "";
        try {
            // 读取 PDF 文件
            PDDocument document = PDDocument.load(inputStream);
            // 实例化 PDFTextStripper 类
            PDFTextStripper pdfStripper = new PDFTextStripper();
            // 提取 PDF 文字
            text = pdfStripper.getText(document);
            // 关闭文档
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * @Description 提取 PDF 文字
     * @Author LinLuoChen
     * @Date 2023/4/10 10:17
     * @Param [
     * inputStream 文件流
     * ]
     * @Return java.lang.String
     **/
    public static void getPDFImage(InputStream inputStream) {
        try {
            // 读取 PDF 文件
            PDDocument document = PDDocument.load(inputStream);
            // 实例化 PDFRenderer 类
            PDFRenderer renderer = new PDFRenderer(document);
            // 从 PDF 文档渲染图像
            BufferedImage image = renderer.renderImage(0);
            // 将图像写入文件
            ImageIO.write(image, "JPEG", new File("D:/PdfBox_Examples/myimage.jpg"));
            // 关闭文档
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 测试方法
     * @Author LinLuoChen
     * @Date 2023/4/20 15:09
     * @Param [args]
     * @Return void
     **/
    public static void main(String[] args) throws Exception {
        // 测试！
        File file = new File("C:\\Users\\30447\\Desktop\\睿泽\\住宅加装电梯项目\\济南市.pdf");
        // 读取 PDF 文件
        PDDocument document = PDDocument.load(file);
        // 实例化 PDFTextStripper 类
        PDFTextStripper pdfStripper = new PDFTextStripper();
        // 指定获取页码
        pdfStripper.setStartPage(1);
        pdfStripper.setEndPage(2);
        // 提取 PDF 文字
        String text = pdfStripper.getText(document);
        // 判断是否包含指定文字
        if (text.indexOf("济南市既有住宅增设电梯有关手续办理导则") > 0) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        // 关闭连接
        document.close();
    }

}
