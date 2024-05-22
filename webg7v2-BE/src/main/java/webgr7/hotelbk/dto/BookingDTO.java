package webgr7.hotelbk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDTO {
    private Long hotelId;
    private List<Long> voucherIds;
    private List<Long> roomIds;
    private List<Integer> quantity;
    private Long clientId;
    private Date time;
    private Date checkIn;
    private Date checkOut;
}
