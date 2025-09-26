package com.example.vivaha_v100.service;

import com.example.vivaha_v100.dto.CardDTO;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.font.PdfFontFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class PdfService {

    // Modify this method to accept the data list
    public void createPdf(String filePath, List<Map.Entry<CardDTO, Integer>> dataList) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));

        PdfDocument pdfDoc = new PdfDocument(new com.itextpdf.kernel.pdf.PdfWriter(fileOutputStream));
        pdfDoc.setDefaultPageSize(PageSize.A4.rotate());

        Document document = new Document(pdfDoc);

        PdfFont timesRomanFont = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);

        // Define all column headers, total 22 columns (21 CardDTO fields + 1 Integer value)
        String[] headers = {
                "First Name", "Last Name", "Age", "Marital Status", "Height",
                "Weight", "Salary Package", "Job Location", "Education", "Occupation",
                "Mangalik", "Disability", "Rashi Name", "Nakshatra Name", "Gotra Name",
                "Paada", "Caste Name", "Religion Name", "City", "State",
                "Country", "Annual Income", "Match Score"
        };

        float[] columnWidths = new float[headers.length];
        for (int i = 0; i < headers.length; i++) {
            columnWidths[i] = 1;
        }

        Table table = new Table(columnWidths);

        // Add header cells
        for (String header : headers) {
            table.addCell(new Cell().add(new Paragraph(header).setFont(timesRomanFont).setFontSize(12))
                    .setBackgroundColor(ColorConstants.LIGHT_GRAY));
        }

        // Add data rows
        for (Map.Entry<CardDTO, Integer> entry : dataList) {
            CardDTO card = entry.getKey();
            Integer matchScore = entry.getValue();

            // Add each field as a cell
            table.addCell(new Cell().add(new Paragraph(card.getFname()).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(card.getLname()).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(card.getAge())).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(card.getMaritalStatus()).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(card.getHeight())).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(card.getWeight())).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(card.getSalaryPackage())).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(card.getJobLocation()).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(card.getEducation()).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(card.getOccupation()).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(card.isMangalik())).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(card.isDisability())).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(card.getRashiName()).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(card.getNakshatraName()).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(card.getGotraName()).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(card.getPaada())).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(card.getCasteName()).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(card.getReligionName()).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(card.getCity()).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(card.getState()).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(card.getCountry()).setFont(timesRomanFont).setFontSize(11)));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(card.getAnnualIncome())).setFont(timesRomanFont).setFontSize(11)));

            // Add match score (integer)
            table.addCell(new Cell().add(new Paragraph(String.valueOf(matchScore)).setFont(timesRomanFont).setFontSize(11)));
        }

        document.add(table);

        document.close();
    }
}