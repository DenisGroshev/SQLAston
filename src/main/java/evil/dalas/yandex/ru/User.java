package evil.dalas.yandex.ru;

import jakarta.persistence.*;
@Table(name = "users", schema="public", catalog = "postgres")
@Entity
public class User {
    @Id
    @Column(name= "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Basic
    @Column(name= "password", nullable = false)
    private String password;

    @Basic
    @Column(name="username", nullable = false)
    private String username;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
