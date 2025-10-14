package APS_2.APS_2_ArqObj.Autenticacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passowrd;

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassowrd() { return passowrd; }

    public void setPassowrd(String passowrd) { this.passowrd = passowrd; }
}
