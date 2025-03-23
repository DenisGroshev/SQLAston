import evil.dalas.yandex.ru.HibernateUtil;
import evil.dalas.yandex.ru.User;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        session.close();
        HibernateUtil.shutdown();

    }
}
