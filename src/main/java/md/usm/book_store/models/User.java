package md.usm.book_store.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private UUID user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private Integer role_id;
}
