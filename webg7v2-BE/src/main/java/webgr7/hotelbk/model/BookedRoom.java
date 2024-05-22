package webgr7.hotelbk.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter @Setter
@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "bookedroom")
public class BookedRoom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date checkIn;
    private Date checkOut;
    private int quantity;
    private Float price;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "slip_id")
    private Slip slip;
}
