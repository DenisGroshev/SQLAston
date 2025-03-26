package evil.dalas.yandex.ru;

import evil.dalas.yandex.ru.entity.Order;
import evil.dalas.yandex.ru.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

//            // Создаем пользователя
//            User user = new User();
//            user.setUsername("EugeniyK");
//            user.setUserpassword("jendos222");
//
//            // Создаем заказы
//            Order order1 = new Order();
//            order1.setOrder_Name("Заказ на ноутбук");
//            order1.setUser(user);  // Устанавливаем связь
//
//            Order order2 = new Order();
//            order2.setOrder_Name("Заказ на телефон");
//            order2.setUser(user);  // Устанавливаем связь
//
//            // Сохраняем пользователя (заказы сохранятся автоматически благодаря cascade)
//            session.save(user);
//            session.save(order1);
//            session.save(order2);
//
//            transaction.commit();
//            System.out.println("Данные успешно сохранены!");


            // 1. Получение всх пользователей
            System.out.println("\n Все пользователи ");
            Query<User> userQuery = session.createQuery("FROM User", User.class);
            List<User> users = userQuery.getResultList();
            users.forEach(user -> System.out.println(user.getUsername()));

            // 2. Запросик с параметром (пользователёчек с именем 'Eugeniy_Sibiryak')
            System.out.println("\n Пользователь с именем Eugeniy_Sibiryak");
            Query<User> specificUserQuery = session.createQuery(
                    "FROM User WHERE username = :name", User.class);
            specificUserQuery.setParameter("name", "Eugeniy_Sibiryak");
            User user = specificUserQuery.uniqueResult();
            System.out.println(user != null ? user.getUsername() : "Не найден");

            // 3. Получение всех заказов пользователя (JOIN)
            System.out.println("\nВсе заказы пользователя ");
            Query<Order> ordersQuery = session.createQuery(
                    "SELECT o FROM Order o WHERE o.user.username = :name", Order.class);
            ordersQuery.setParameter("name", "Eugeniy_Sibiryak");
            List<Order> orders = ordersQuery.getResultList();
            orders.forEach(order -> System.out.println(order.getOrder_Name()));

            // 4. Нативный SQL-запросик
            System.out.println("\n Количество заказов ");
            Query<Long> nativeQuery = session.createNativeQuery(
                    "SELECT COUNT(*) FROM orders", Long.class);
            Long orderCount = nativeQuery.uniqueResult();
            System.out.println("Всего заказов: " + orderCount);

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