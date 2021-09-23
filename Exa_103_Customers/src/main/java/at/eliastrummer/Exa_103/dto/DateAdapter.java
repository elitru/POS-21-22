package at.eliastrummer.Exa_103.dto;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateAdapter extends XmlAdapter<String, LocalDate> {
    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);

    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v, DTF);
    }

    public String marshal(LocalDate v) throws Exception {
        return DTF.format(v);
    }
}
