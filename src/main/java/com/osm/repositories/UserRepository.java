package com.osm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.osm.persistences.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public List<User> findByUsernameAndPassword(String username, String password);

	@Query("select count(u) from User u where u.username=?1")
	public long getCountUsername(String username);
	
	public User findByUsername(String username);
	
	@Modifying
	@Query("update User set verified = true where id=?1")
	public void setVerifyToUser(long userid);
	
	public List<User> findByEmail(String email);
}
