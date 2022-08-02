
package com.mpas.cems.web.views;

import com.mpas.cems.dao.Person;
import com.mpas.cems.util.DateProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;


public class PersonExcelView extends AbstractXlsView {

        @SuppressWarnings("unchecked")
        @Override
        protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) {
            response.setHeader("Content-Disposition", "attachment; filename=\"persons.xls\"");
            List<Person> persons = (List<Person>) model.get("persons");
            Sheet sheet = workbook.createSheet();
            for (short i = 0; i < persons.size(); i++) {
                Person person = persons.get(i);
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(person.getFirstName());
                row.createCell(1).setCellValue(person.getLastName());
                row.createCell(2).setCellValue(DateProcessor.toString(person.getHiringDate()));
            }
        }
}
