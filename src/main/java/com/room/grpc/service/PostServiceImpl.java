package com.room.grpc.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.grpc.server.service.GrpcService;

import com.room.grpc.entity.Post;
import com.room.grpc.mapper.PostMapper;
import com.room.grpc.repository.PostRepository;
import com.room.grpc.socialmedia.PostListRequest;
import com.room.grpc.socialmedia.PostListResponse;
import com.room.grpc.socialmedia.PostServiceGrpc.PostServiceImplBase;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@GrpcService
@AllArgsConstructor
@Slf4j
public class PostServiceImpl extends PostServiceImplBase{

    private PostRepository postRepository;
    private PostMapper postMapper;

    @Override
    public void fetchPostList(PostListRequest request, StreamObserver<PostListResponse> responseObserver) {
        try {
        int page = Integer.valueOf( request.getPage());
        int size = Integer.valueOf(request.getSize());
        if(page < 0){
            responseObserver.onError(
                io.grpc.Status.INVALID_ARGUMENT
                .withDescription("Page number must be greater than 0")
                .asException()
            );
            return;
        }
        if( size > 1000){
            responseObserver.onError(   
                io.grpc.Status.INVALID_ARGUMENT
                .withDescription("Size must be less than 1000")
                .asException()
            );
        }
      
        Pageable pageable = PageRequest.of(page, size);
        List<Post> postList = postRepository.findAll(pageable).getContent();


        PostListResponse response = PostListResponse.newBuilder()
                    .addAllPosts(postMapper.toProto(postList))
                    .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

         } catch (Exception e) {
            responseObserver.onError(
                io.grpc.Status.INTERNAL
                .withDescription("Internal Server error")
                .asException()
            );
        }
        
    }
}
