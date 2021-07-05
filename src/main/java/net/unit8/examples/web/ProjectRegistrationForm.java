package net.unit8.examples.web;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
public class ProjectRegistrationForm implements Serializable {
    @Length(max = 20)
    @NotBlank
    private String name;
    private String description;

    @NotBlank
    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4} - \\d{2}/\\d{2}/\\d{4}")
    private String recruitmentPeriod;

    public LocalDate getRecruitmentBeginOn() throws ParseException {
        String[] range = recruitmentPeriod.split("\\s+-\\s+",2);
        return LocalDate.parse(range[0], DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public LocalDate getRecruitmentEndOn() throws ParseException {
        String[] range = recruitmentPeriod.split("\\s+-\\s+",2);
        return LocalDate.parse(range[1], DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }
}
