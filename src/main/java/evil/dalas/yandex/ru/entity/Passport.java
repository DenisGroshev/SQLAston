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
        private User user; // Это свойство должно быть!

        // Геттеры и сеттеры
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Integer getNumberOfPassport() {
            return numberOfPassport;
        }

        public void setNumberOfPassport(Integer numberOfPassport) {
            this.numberOfPassport = numberOfPassport;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }



