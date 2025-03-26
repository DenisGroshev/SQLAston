package evil.dalas.yandex.ru;

import evil.dalas.yandex.ru.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MainTest {
    @Mock
    private Session session;
    @Mock
    private Transaction transaction;
    @Mock
    private Query<User> userQuery;
    @Mock
    private NativeQuery<Long> nativeQuery;


    // тест на взятие берлина
    @Test
    public void testGetAllUsers(){
        when(session.createQuery(anyString(), eq(User.class))).thenReturn(userQuery);

        User user1=new User();
        User user2=new User();
        user1.setUsername("EugeniyK");
        user2.setUsername("Eugeniy_Sibiryak");

        List<User> mockUsers=new ArrayList<>();
        mockUsers.add(user1);
        mockUsers.add(user2);


        when(userQuery.getResultList()).thenReturn(mockUsers);


        // EMOlation
        Query<User> query=session.createQuery("FROM User", User.class);
        List<User> users=query.getResultList();
        // Trying

        Assertions.assertEquals(2, users.size());
        Assertions.assertEquals("EugeniyK", users.get(0).getUsername());
        verify(session).createQuery("FROM User", User.class);
        verify(userQuery).getResultList();

    }

    // Тест на взятие имени взятия берлина
    @Test
    public void testGetUserByName(){

        // Mock settings
        when(session.createQuery(anyString(), eq(User.class))).thenReturn(userQuery);
        when(userQuery.setParameter(eq("name"), anyString())).thenReturn(userQuery).thenReturn(userQuery);

        User mockUser = new User();
        mockUser.setUsername("Eugeniy_Sibiryak");
        when(userQuery.uniqueResult()).thenReturn(mockUser);

        // Query

        Query<User> query=session.createQuery("FROM User WHERE username = :name", User.class);
        query.setParameter("name", "Eugeniy_Sibiryak");
        User user= query.uniqueResult();

        // Trying
        assertEquals("Eugeniy_Sibiryak", user.getUsername());
        verify(userQuery).setParameter("name", "Eugeniy_Sibiryak");
    }


    // Тест на количество заказов
    @Test
    public void testGetOrderCount(){
        // Настройка москитов
        when(session.createNativeQuery(anyString(), eq(Long.class))).thenReturn(nativeQuery);
        when(nativeQuery.uniqueResult()).thenReturn(5L);

        // Выполнение кодика
        NativeQuery<Long> query = session.createNativeQuery("SELECT COUNT(*) FROM orders", Long.class);
        Long count = query.uniqueResult();

        // Поверка счётчика
        assertEquals(5L, count);

    }

}
