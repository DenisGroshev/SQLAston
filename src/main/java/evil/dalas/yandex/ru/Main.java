package evil.dalas.yandex.ru;

import evil.dalas.yandex.ru.entity.Passport;
import evil.dalas.yandex.ru.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();


            // Создаем объект User
            User user = new User();
            user.setUsername("Eugeniy_Sibiryak");
            user.setUserpassword("jendos");

            // Создаем объект Passport
            Passport passport = new Passport();
            passport.setNumberOfPassport(123123);

            // Устанавливаем связь между User и Passport
            user.setPassport(passport);

            // Сохраняем User (Passport сохранится автоматически благодаря cascade)
            session.save(user);

            // Фиксируем прибыль
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            HibernateUtil.shutdown();
        }
    }
}