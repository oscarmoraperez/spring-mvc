package org.oka.springmvc.service;

import com.lowagie.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.util.Map;

@Service
@Slf4j
public class PdfService {
    @Autowired
    private TemplateEngine templateEngine;

    public ByteArrayOutputStream generatePdfContent(String templateName, Map<String, Object> map) throws DocumentException {
        Context context = new Context();
        context.setVariables(map);

        String htmlContent = templateEngine.process(templateName, context);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        renderer.createPDF(byteArrayOutputStream, true);

        return byteArrayOutputStream;
    }
}
