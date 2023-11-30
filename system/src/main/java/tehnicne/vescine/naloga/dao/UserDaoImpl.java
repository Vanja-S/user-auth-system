package tehnicne.vescine.naloga.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tehnicne.vescine.naloga.entity.User;
import tehnicne.vescine.naloga.exception.UserNotFoundException;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

	private EntityManager entityManager;

	@Autowired
	public UserDaoImpl(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}

	@Override
	public User findByUserName(String theUserName) throws UserNotFoundException {

		// retrieve/read from database using username
		TypedQuery<User> theQuery = entityManager.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUserName);

		User theUser;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			throw new UserNotFoundException(theUserName);
		}
		return theUser;
	}

	@Override
	public boolean exists(String userName) {
		try {
			findByUserName(userName);
		} catch (UserNotFoundException e) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public void save(User theUser) {
		entityManager.merge(theUser);
	}

	@Override
	@Transactional
	public void update(User theUser) {
		entityManager.merge(theUser);
	}

	@Override
	public List<User> findAll() {
		TypedQuery<User> theQuery = entityManager.createQuery("from User", User.class);
		List<User> theUsers;
		try {
			theUsers = theQuery.getResultList();
		} catch (Exception e) {
			theUsers = null;
		}
		return theUsers;
	}

	@Override
	@Transactional
	public void delete(User theUser) {
		theUser.getRoles().clear();
		entityManager.remove(theUser);
	}
}
