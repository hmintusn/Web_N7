package webgr7.hotelbk.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Blob;
import java.util.List;

@Getter @Setter
@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "room")
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int quantity;
    private String type;
    private int pnum;
    private String des;
    private Float price;
    private Float offer;
    private Blob photo;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy="room",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE, CascadeType.DETACH})
    private List<BookedRoom> bookedRooms;

}
