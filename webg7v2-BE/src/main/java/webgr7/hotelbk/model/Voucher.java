package webgr7.hotelbk.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter @Setter
@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "voucher")
public class Voucher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
            CascadeType.DETACH, CascadeType.MERGE})
    @JoinTable(
            name = "client_voucher",
            joinColumns = @JoinColumn(name = "voucher_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id")
    )
    private List<Client> clients;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
            CascadeType.DETACH, CascadeType.MERGE})
    @JoinTable(
            name = "slip_voucher",
            joinColumns = @JoinColumn(name = "voucher_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id")
    )
    private List<Slip> slips;
}
