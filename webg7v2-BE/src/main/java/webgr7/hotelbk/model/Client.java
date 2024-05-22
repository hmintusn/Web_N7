package webgr7.hotelbk.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@DiscriminatorValue("Client")
public class Client extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String idCard;
    private Date dob;
    private String gender;

    @OneToMany(mappedBy="client",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH})
    private List<Slip> slips;

    @OneToMany(mappedBy="client",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE, CascadeType.DETACH})
    private List<Bill> bills;

    @OneToMany(mappedBy="client",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE, CascadeType.DETACH})
    private List<Review> reviews;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "client_voucher",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "voucher_id", referencedColumnName = "id")
    )
    private List<Voucher> vouchers;
}
