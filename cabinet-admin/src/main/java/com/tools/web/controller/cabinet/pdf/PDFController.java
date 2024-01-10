package com.tools.web.controller.cabinet.pdf;

import com.tools.common.pdfbox.PDFBoxUtils;
import com.tools.common.pdfbox.PDFVariable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @Title: PDFController
 * @Author LinLuoChen
 * @ProjectName ToolCabinet
 * @Description: PDF 相关操作类
 * @Date 2023/4/20 11:04
 */
@RestController
@RequestMapping("/pdf")
public class PDFController {

    /**
     * @Description 验证 PDF 内是否包含指定内容
     * @Author LinLuoChen
     * @Date 2023/4/20 11:09
     * @Param [inputStream, str]
     * @Return void
     **/
    @GetMapping("/pdfOperator")
    @PreAuthorize("@ss.hasPermi('pdf:pdfOperator')")
    public boolean getPDFOperator(Boolean flag, String str) {
        File file = new File("C:\\Users\\30447\\Desktop\\睿泽\\住宅加装电梯项目\\济南市.pdf");
        // 验证 PDF 文件是否包含此内容
        return flag ? PDFBoxUtils.getPDFVerify(file, PDFVariable.PDF_STATUS_TITLE)
                : PDFBoxUtils.getPDFVerify(file, str);
    }

}
