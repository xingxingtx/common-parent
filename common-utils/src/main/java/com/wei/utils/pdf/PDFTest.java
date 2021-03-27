package com.wei.utils.pdf;

import java.io.IOException;

/**
 * @Describe PDFTest
 * @Author a_pen
 * @Date 2020年09月16日
 */
public class PDFTest {

    public static void main(String[] args) throws IOException {

        String text = getPdfFileText("23423");
        System.out.println(text);
        /*System.out.println(System.getProperty("java.io.tmpdir"));
*/
        /*System.out.print(getPdfFileText("D:\\workFile\\Bug.pdf"));*/
    }
    public static String getPdfFileText(String fileName) throws IOException {
        try {
            int a = 0;
            int b = 3/a;
            return fileName;
        }catch ( Exception e){
            throw new IOException();
        }
        /*PdfReader reader = new PdfReader(fileName);
        reader.getPdfVersion();
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        StringBuffer buff = new StringBuffer();
        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i,
                    new SimpleTextExtractionStrategy());
            buff.append(strategy.getResultantText());
        }*/
    }

}
