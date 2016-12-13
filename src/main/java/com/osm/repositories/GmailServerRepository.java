package com.osm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osm.persistences.GmailServer;

@Repository
public interface GmailServerRepository extends JpaRepository<GmailServer, Integer>{

}
