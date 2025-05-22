package productionCode;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CookReportPDF {

    private static finance financeObj;

    public CookReportPDF(finance financeObj) {
        this.financeObj = financeObj;
    }
    public String pathMaker(){
        String first = "reports/";
        String second = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String third = ".pdf";
        return first + second + third;
    }


    public void generateReportPDF() {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            addContentToPage(page, document);
            document.save(this.pathMaker());
            System.out.println("PDF saved to: " + this.pathMaker());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addContentToPage(PDPage page, PDDocument document) throws IOException {
        PDPageContentStream cs = new PDPageContentStream(document, page);
        float margin = 50;
        float y = page.getMediaBox().getUpperRightY() - margin;

        // Title
        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA_BOLD, 18);
        cs.newLineAtOffset(page.getMediaBox().getWidth() / 2 - 30, y);
        cs.showText("sir mohammed");
        cs.endText();

        y -= 40;
        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA, 12);
        cs.newLineAtOffset(margin, y);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        cs.showText("BUY REPORT for :" + LocalDate.now().format(formatter));
        cs.endText();

        y -= 20;

// Current Balance Line
        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA, 12);
        cs.newLineAtOffset(margin, y);
        cs.showText("CURRENT BALANCE : " + finance.getBalance() + " $");
        cs.endText();

// Move down for border
        y -= 15;
        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA, 12);
        cs.newLineAtOffset(margin, y);
        String border = "------------------------------------------------------------";
        cs.showText(border);
        cs.endText();

        y -= 20;
        String items = financeObj.printHistory();
        for (String item : items.split("\n")) {
            y = printWrappedText(cs, item, margin + 10, y, page.getMediaBox().getWidth() - 2 * margin);
            y -= 10;
        }

        cs.close();
    }

    public static float printWrappedText(PDPageContentStream cs, String text, float x, float y, float pageWidth) throws IOException {
        final float fontSize = 12;
        final float leading = 1.5f * fontSize;
        float margin = 50;
        PDType1Font font = PDType1Font.HELVETICA;
        float width = font.getStringWidth(text) / 1000 * fontSize;

        if (width > pageWidth - 2 * margin) {
            String[] words = text.split(" ");
            StringBuilder line = new StringBuilder();
            for (String word : words) {
                line.append(word).append(" ");
                if (font.getStringWidth(line.toString()) / 1000 * fontSize > pageWidth - 2 * margin) {
                    cs.beginText();
                    cs.setFont(font, fontSize);
                    cs.newLineAtOffset(x, y);
                    cs.showText(line.toString().trim());
                    cs.endText();
                    line = new StringBuilder(word + " ");
                    y -= leading;
                }
            }
            if (line.length() > 0) {
                cs.beginText();
                cs.setFont(font, fontSize);
                cs.newLineAtOffset(x, y);
                cs.showText(line.toString().trim());
                cs.endText();
                y -= leading;
            }
        } else {
            cs.beginText();
            cs.setFont(font, fontSize);
            cs.newLineAtOffset(x, y);
            cs.showText(text);
            cs.endText();
            y -= leading;
        }

        return y;
    }


}
