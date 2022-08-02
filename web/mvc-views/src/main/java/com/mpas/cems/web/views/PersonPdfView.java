
package com.mpas.cems.web.views;

import com.mpas.cems.dao.Person;
import com.mpas.cems.util.DateProcessor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;



public class PersonPdfView extends AbstractPdfView {

    @SuppressWarnings("unchecked")
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"persons.pdf\"");
        List<Person> persons = (List<Person>) model.get("persons");
        document.open();
        document.addAuthor("Maniek");
        document.addTitle("CEMS Persons");
        document.add(new Paragraph("CEMS Persons List"));
        for (short i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            document.add(new Paragraph(person.getFirstName()
                    + " " + person.getLastName() + " " + DateProcessor.toString(person.getHiringDate())));
        }
    }
}
