package evil.dalas.yandex.ru.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "passport")
    public class Passport {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "number_of_passport", nullable = false)
        private Integer numberOfPassport;

        @OneToOne
        @JoinColumn(name = "user_id")
        private User user; // Это свойство имеет персональную важность ееее

    public void setNumberOfPassport(Integer numberOfPassport) { // не хотело сетаться через ломбок
        this.numberOfPassport = numberOfPassport;
    }

    public void setUser(User user) { // Земля земля, я Юпитер, как слышно юзерные мои?
            this.user = user;
        }
    }



