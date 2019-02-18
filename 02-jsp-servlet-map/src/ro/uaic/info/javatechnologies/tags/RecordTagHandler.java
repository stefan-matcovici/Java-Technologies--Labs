package ro.uaic.info.javatechnologies.tags;

import ro.uaic.info.javatechnologies.models.Record;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RecordTagHandler extends SimpleTagSupport {

    private String key;
    private String category;

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        List<Record> records = (List<Record>) getJspContext().getAttribute("StoreServlet.records", PageContext.SESSION_SCOPE);
        if (category != null) {
            records = records.stream().filter(record -> record.getCategory().equals(category)).collect(Collectors.toList());
        }

        JspFragment f = getJspBody();
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        if (f != null) {
            f.invoke(printWriter);
        }

        String bodyRecords = stringWriter.toString().trim();
        if (bodyRecords.isEmpty()) {
            records.stream().filter(record -> record.getKey().equals(key)).findFirst().ifPresent(record -> {
                try {
                    out.print("key: " + record.getKey());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        else {
            List<Record> finalRecords = records;
            Arrays.stream(bodyRecords.split(",")).forEach(s ->
            {
                finalRecords.stream().filter(record -> record.getKey().equals(s)).findFirst().ifPresent(record -> {
                    try {
                        out.println("<h6>key: " + record.getKey()+ "</h6>");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            });
        }
    }

}