package com.softpig.model;

import android.content.Context;
import android.util.Log;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

public class Report {

    private Context context;
    private Date hoy;
    private File reporte;
    private Document document;
    private Paragraph paragraph;
    private Font fTitle= new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
    private Font fSubTitle= new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private Font fText= new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private Font fHighText= new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.RED);



    public Report(Context context){
        this.context = context;
    }
    public void openDocument(){
        createFile();
        try{

            hoy = new Date();
            String nombre= "Reporte-" + hoy+ ".pdf"; //Para ajusta el nombre del PDF
            document = new Document(PageSize.A4);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(reporte)); //Instancia para manejo de pdf
            document.open();

        }catch (Exception e){
            Log.e("OpenDocument", e.toString());
        }
    }

    private void createFile(){
        hoy = new Date();
        String nombre= "Reporte-" + hoy+ ".pdf";
        File folder = new File (context.getFilesDir(), "Reportes");
        if (!folder.exists())
            folder.mkdirs();
        reporte = new File(folder, nombre);
    }

    public void closeDocument(){
        document.close();
    }

    public void addMetaData(String title, String subject, String author){
        document.addTitle(title);
        document.addSubject(subject);
        document.addAuthor(author);
    }

    public void addTitles (String title, String subTitle, String date){
        try {
            paragraph = new Paragraph();
            addChildP(new Paragraph(title, fTitle));
            addChildP(new Paragraph(subTitle, fSubTitle));
            addChildP(new Paragraph("Generado: " + date, fHighText));
            paragraph.setSpacingAfter(30);
            document.add(paragraph);
        }catch(Exception e){
            Log.e("addTitles", e.toString());
        }
    }

    private void addChildP(Paragraph childParagraph){
        childParagraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(childParagraph);
    }

    public void addParagraph(String text){
        try {
            paragraph = new Paragraph(text, fText);
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(5);
            document.add(paragraph);
        }catch (Exception e){
            Log.e("addParagraph",e.toString());
        }
    }

    public void createTable(String[] header,  List< String []> clients){

        try {
            paragraph = new Paragraph();
            paragraph.setFont(fText);
            PdfPTable pdfPTable = new PdfPTable(header.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;
            while (indexC < header.length) {
                pdfPCell = new PdfPCell(new Phrase(header[indexC++], fSubTitle));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdfPCell.setBackgroundColor(BaseColor.PINK);
                pdfPTable.addCell(pdfPCell);
            }

            for (int indexRow = 0; indexRow < clients.size(); indexRow++) {
                String[] row = clients.get(indexRow);
                for (int indexCol = 0; indexCol < clients.size(); indexCol++) {
                    pdfPCell = new PdfPCell(new Phrase(row[indexCol]));
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(40);
                    pdfPTable.addCell(pdfPCell);
                }
            }

            paragraph.add(pdfPTable);
            document.add(paragraph);
        }catch (Exception e){
            Log.e("createTable",e.toString());
        }
    }
}
