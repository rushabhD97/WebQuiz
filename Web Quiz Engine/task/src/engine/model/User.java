package engine.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Email(regexp = "^[a-zA-Z0-9._%+-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$")
    private String email;

    @Size(min = 5)
    private String password;

    public User() {
    }

    public User(int id, @Email(regexp = "^[a-zA-Z0-9._%+-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$") String email, @Size(min = 5) String password) {
        this.id = id;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
