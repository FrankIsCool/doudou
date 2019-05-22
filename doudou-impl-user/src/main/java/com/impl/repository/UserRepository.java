package com.impl.repository;

import com.service.model.UserNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "userRepository")
public interface UserRepository extends Neo4jRepository<UserNode,Long> {
    @Query("MATCH (n:User) RETURN n ")
    List<UserNode> getUserNodeList();

    @Query("MATCH (n:User{userName:{userName}}) RETURN n ")
    List<UserNode> getUserByName(@Param("userName") String userName);

    @Query("MATCH (n:User) where n.userCode={userCode}  RETURN n")
    UserNode getUserByCode(@Param("userCode") String userCode);

    @Query("MATCH (n:User) where id(n)={id}  RETURN n")
    UserNode getUserById(@Param("id") Long id);
}
