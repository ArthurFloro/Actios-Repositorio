import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 

    @Column(nullable = false)
    private String nome;

    @Column(unique = true , nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    private String curso; 

    @ManyToOne
    @JoinColumn(name = "faculdade_id") 
    private Faculdade faculdade; 
}
