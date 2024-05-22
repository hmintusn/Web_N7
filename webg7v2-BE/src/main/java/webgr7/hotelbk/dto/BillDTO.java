package webgr7.hotelbk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import webgr7.hotelbk.model.Client;

import java.util.Date;
@Getter
@Setter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillDTO {
    private Float amount;
    private String type;
    private Date time;
    private Long clientID;
    private Long slipID;
}
