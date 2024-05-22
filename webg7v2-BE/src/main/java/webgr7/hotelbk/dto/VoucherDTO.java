package webgr7.hotelbk.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoucherDTO {
    private String name;
    private String type;
    private String hotelType;
    private String location;
    private Date stTime;
    private Date edTime;
    private Float value;
    private Float percent;
    private int quantity;
    private String des;
}
