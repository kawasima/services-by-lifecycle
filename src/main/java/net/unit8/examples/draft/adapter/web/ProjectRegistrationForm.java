package net.unit8.examples.draft.adapter.web;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public Date getRecruitmentBeginOn() throws ParseException {
        String[] range = recruitmentPeriod.split("\\s+-\\s+",2);
        return new SimpleDateFormat("MM/dd/yyyy").parse(range[0]);
    }

    public Date getRecruitmentEndOn() throws ParseException {
        String[] range = recruitmentPeriod.split("\\s+-\\s+",2);
        return new SimpleDateFormat("MM/dd/yyyy").parse(range[1]);
    }
}
