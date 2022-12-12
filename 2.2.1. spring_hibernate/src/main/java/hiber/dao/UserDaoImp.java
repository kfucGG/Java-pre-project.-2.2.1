package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Transactional
   public Car getCarByModelAndSeries(String model, int series) {
      Query query = sessionFactory.getCurrentSession().createQuery("FROM Car where model = :m and series = :s", Car.class);
      query.setParameter("m", model);
      query.setParameter("s", series);
      return (Car) query.getSingleResult();
   }

   @Transactional
   public User getUserByCarModelAndSeries(String model, int series) {
      Query query = sessionFactory.getCurrentSession().createQuery("FROM User where car_id = :i", User.class);
      query.setParameter("i",getCarByModelAndSeries(model,series).getId());
      return (User) query.getSingleResult();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
