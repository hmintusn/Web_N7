package webgr7.hotelbk.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

@Data
@NoArgsConstructor
public class PictureResponse {
    private Long id;
    private String photo;

    public PictureResponse(Long id, byte[] photoBytes) {
        this.id = id;
        this.photo = photoBytes != null ? Base64.encodeBase64String(photoBytes) : null;
    }
}
