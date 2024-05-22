package webgr7.hotelbk.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDTO {
    private String name;
    private int quantity;
    private String type;
    private int pnum;
    private String des;
    private Float price;
    private Float offer;
    private MultipartFile file;
}
