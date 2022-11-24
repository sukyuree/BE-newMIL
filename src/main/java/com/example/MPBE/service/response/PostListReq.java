package com.example.MPBE.service.response;

import com.example.MPBE.service.dto.PostDto;
import lombok.Getter;

import java.util.List;

@Getter
public class PostListReq extends BaseResponse{
    List<PostDto> postDtoList;

    public PostListReq(String msg, Integer status, List<PostDto> postDtoList){
        super(msg,status);
        this.postDtoList=postDtoList;
    }
}
