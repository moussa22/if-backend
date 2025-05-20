package ifsa.solution.ifsa_backend.entites;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
@Table(name = "CLIENT")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String email;

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    private String telephone;

    public Client() {

    }

    public Client(int id, String email,String telephone) {
        this.id = id;
        this.email = email;

        this.telephone=telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
