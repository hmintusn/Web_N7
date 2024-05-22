package webgr7.hotelbk.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelDTO {
    private String name;
    private String star;
    private String location;
    private String type;
    private String status;
    private String tel;
    private String contact;
    private String des;
}
