package com.room.grpc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.room.grpc.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,String>{
}
