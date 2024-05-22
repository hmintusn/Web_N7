package webgr7.hotelbk.response;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

@Data
@NoArgsConstructor
public class RoomResponse {
    private Long id;
    private String name;
    private int quantity;
    private String type;
    private int pnum;
    private String des;
    private Float price;
    private Float offer;
    private String photo;

    public RoomResponse(Long id, byte[] photoBytes, String name, int quantity, String type, int pnum, String des, Float price, Float offer) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.type = type;
        this.pnum = pnum;
        this.des = des;
        this.price = price;
        this.offer = offer;
        this.photo = photoBytes != null ? Base64.encodeBase64String(photoBytes) : null;
    }

}
