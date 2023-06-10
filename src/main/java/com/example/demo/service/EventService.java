package com.example.demo.service;

import com.example.demo.repository.EventList;
import com.example.demo.model.Event;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class EventService {

    private final EventList eventList;
    
    public List<Event> getEventsByDay(int day) {
        return eventList.getEventsByDay(day);
    }

    
    public List<Event> getEventsByWeek(Integer weekNumber) {
        return eventList.getEventsByWeek(weekNumber);
    }

    
    public List<Event> getEventsByMonth(Integer monthNumber) {
        return eventList.getEventsByMonth(monthNumber);
    }

    
    public String getDetailsByEvent(Event event) throws Throwable {
        return eventList.getDetailsByEvent(event);
    }

    
    public Event addEvent(Event event) {
        return eventList.addToEventList(event);
    }

    
    public void deleteEvent(Long id) { eventList.deleteEvent(id); }

    
    public Event modifyEvent(Long id, Event event){
        return eventList.modifyEvent(id,event);
    }

    
    public List<Event> getEvents() {
        return eventList.getEventList();
    }

    
    public Event getEventById(Long id) {
        return eventList.getEventById(id);
    }

    
    public byte[] downloadFile() throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, out);

        document.open();

        PdfPTable table = new PdfPTable(5);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("Nazwa wydarzenia");
        list.add("Data wydarzenia");
        list.add("Rodzaj wydarzenia");
        list.add("Opis wydarzenia");
        list.forEach(el -> {
            PdfPCell pdfPCell = new PdfPCell();
            pdfPCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pdfPCell.setPhrase(new Phrase(el));
            pdfPCell.setBorderWidth(2);
            table.addCell(pdfPCell);
        });

        List<Event> eventList1 = eventList.getEventList();
        int j = 1;
        for(Event event: eventList1){
            table.addCell(String.valueOf(j));
            table.addCell(event.getName());
            table.addCell(event.getEventDate().toString());
            table.addCell(event.getType());
            table.addCell(event.getDetails());
            j++;
        }
        Font font = FontFactory.getFont(FontFactory.TIMES_ITALIC, 20, BaseColor.BLACK);
        Chunk chunk = new Chunk("Zestawienie wydarzen\n", font);
        Paragraph paragraph = new Paragraph(chunk);
        paragraph.setSpacingAfter(50f);
        paragraph.setAlignment(Element.ALIGN_CENTER);

        document.add(paragraph);
        document.add(table);
        document.close();

        return out.toByteArray();
    }
}
