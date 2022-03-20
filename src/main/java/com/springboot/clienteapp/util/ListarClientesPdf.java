package com.springboot.clienteapp.util;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.springboot.clienteapp.entity.Cliente;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.Map;

@Component("views/clientes/listar.pdf")
public class ListarClientesPdf extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response)
                                    throws Exception {

        List<Cliente> listClientes = (List<Cliente>) model.get("clientes");
        /* Configurando documento */
        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(-10,-10,40,20);
        document.open();
        /*Fuentes  */
        Font fuenteTitulo = FontFactory.getFont("Helvetica",16,Color.DARK_GRAY);
        Font fuenteTituloCol = FontFactory.getFont(FontFactory.HELVETICA_BOLD,12,Color.DARK_GRAY);
        Font fuenteCell = FontFactory.getFont(FontFactory.COURIER,10,Color.DARK_GRAY);
        /*Titulo  */
        PdfPTable pdfTableTitulo = new PdfPTable(1);
        PdfPCell celda= null;
        celda = new PdfPCell(new Phrase("LISTADO GENERAL DE CLIENTES",fuenteTitulo));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(164,224,241));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(30);

        pdfTableTitulo.addCell(celda);
        pdfTableTitulo.setSpacingAfter(30);

        /*Informacion de CLientes en la tabla */
        PdfPTable pdfTableClientes = new PdfPTable(6);
        pdfTableClientes.setWidths(new float[] {0.8f, 2f, 2f, 1.5f, 3.5f, 1.5f});
        celda = new PdfPCell(new Phrase("ID",fuenteTituloCol));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(219, 233, 233));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        pdfTableClientes.addCell(celda);

        celda = new PdfPCell(new Phrase("NOMBRES",fuenteTituloCol));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(219, 233, 233));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        pdfTableClientes.addCell(celda);

        celda = new PdfPCell(new Phrase("APELLIDOS",fuenteTituloCol));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(219, 233, 233));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        pdfTableClientes.addCell(celda);

        celda = new PdfPCell(new Phrase("TELEFONO",fuenteTituloCol));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(219, 233, 233));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        pdfTableClientes.addCell(celda);

        celda = new PdfPCell(new Phrase("EMAIL",fuenteTituloCol));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(219, 233, 233));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        pdfTableClientes.addCell(celda);

        celda = new PdfPCell(new Phrase("CIUDAD",fuenteTituloCol));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(219, 233, 233));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        pdfTableClientes.addCell(celda);

        listClientes.forEach(cliente->{
            PdfPCell c1 = new PdfPCell(new Phrase(cliente.getId().toString(),fuenteCell));
            c1.setPadding(5);
            pdfTableClientes.addCell(c1);

            c1 = new PdfPCell(new Phrase(cliente.getNombres(),fuenteCell));
            c1.setPadding(5);
            pdfTableClientes.addCell(c1);

            c1 = new PdfPCell(new Phrase(cliente.getApellidos(),fuenteCell));
            c1.setPadding(5);
            pdfTableClientes.addCell(c1);

            c1 = new PdfPCell(new Phrase(cliente.getTelefono(),fuenteCell));
            c1.setPadding(5);
            pdfTableClientes.addCell(c1);

            c1 = new PdfPCell(new Phrase(cliente.getEmail(),fuenteCell));
            c1.setPadding(5);
            pdfTableClientes.addCell(c1);

            c1 = new PdfPCell(new Phrase(cliente.getCiudad().getCiudad(),fuenteCell));
            c1.setPadding(5);
            pdfTableClientes.addCell(c1);

        });
             document.add(pdfTableTitulo);
             document.add(pdfTableClientes);
        }



}


