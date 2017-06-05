package md.curs.dao;

import md.curs.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by MG
 */
@Repository
public class UserJpaDao {

    @PersistenceContext
    private EntityManager em;

    /**
     * Find users that contain in their name or surname the specified query
     *
     * @param query query to search for
     * @return The list of Users that match
     */
    public List<User> findUsers(String query) {
        query = "%" + query + "%";
        return em.createQuery("FROM User WHERE name LIKE :query OR surname LIKE :query", User.class)
                .setParameter("query", query)
                .getResultList();
    }

    /**
     * Gets an User by it's ID
     *
     * @param id
     * @return The found user or {@code null} if not found
     */
    public User getUser(long id) {
        return em.find(User.class, id);
    }

    public User findByUsername(String username) {
        return em.createQuery("FROM User u JOIN FETCH u.permissions WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    /**
     * creates or updates an user
     *
     * @param user user to save
     * @return the managed user
     */
    public User save(User user) {
        return em.merge(user);
    }


    /**
     * Delete an User by it's ID
     *
     * @param userId The id to delete
     * @return the number of affected rows (should be 1 if userId provided is a valid one)
     */
    public int delete(long userId) {
        return em.createQuery("DELETE FROM User where id = :userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }

    public int minorsCount() {
        return em.createQuery("SELECT COUNT(u) FROM User u WHERE u.age < 18", Integer.class)
                .getSingleResult();
    }


}
