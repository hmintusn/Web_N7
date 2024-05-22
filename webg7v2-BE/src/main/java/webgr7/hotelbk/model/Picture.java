package webgr7.hotelbk.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Blob;

@Getter @Setter
@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "picture")
public class Picture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Blob data;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
