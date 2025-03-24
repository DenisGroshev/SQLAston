package evil.dalas.yandex.ru.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;

    @Column(name="order_name")
    private String order_Name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;


    public void setOrder_Name(String order_Name) {
        this.order_Name = order_Name;
    }

    public Order() {
    }
    public void setUser(User user) { // Земля земля, я Юпитер, как слышно юзерные мои?
        this.user = user;
    }
}
