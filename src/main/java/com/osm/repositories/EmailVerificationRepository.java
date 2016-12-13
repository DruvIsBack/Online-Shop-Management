package com.osm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.osm.persistences.EmailVerification;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Integer>{

	@Query("select ev from EmailVerification as ev where ev.userid=?1")
	public List<EmailVerification> findByUserID(long id);
}
