package webgr7.hotelbk.response;

import lombok.*;
import webgr7.hotelbk.model.Picture;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter @Setter
@NoArgsConstructor
public class HotelResponse {
    private Long id;

    private String name;
    private String star;
    private String location;
    private String type;
    private String status;
    private String tel;
    private String contact;
    private String des;

    private List<RoomResponse> rooms = new ArrayList<>();
    private List<PictureResponse> pictures = new ArrayList<>();

    public HotelResponse(Long id, String name, String star, String location, String type, String status, String tel, String contact, String des) {
        this.id = id;
        this.name = name;
        this.star = star;
        this.location = location;
        this.type = type;
        this.status = status;
        this.tel = tel;
        this.contact = contact;
        this.des = des;
    }

}
