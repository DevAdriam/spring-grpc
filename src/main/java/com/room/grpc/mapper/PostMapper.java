package com.room.grpc.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.room.grpc.entity.Post;
import com.room.grpc.socialmedia.PostMessage;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
    List<PostMessage> toProto(List<Post> postList);
}
