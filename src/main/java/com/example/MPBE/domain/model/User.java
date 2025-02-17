package com.example.MPBE.domain.model;

import com.example.MPBE.util.enums.Identity;
import com.example.MPBE.util.enums.Track;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseModel{

    @NotNull
    @Column(unique = true)
    String userId;

    @NotNull
    String name;

    @NotNull
    @Column(unique = true)
    String nickName;

    @NotNull
    @Column(unique = true)
    String email;

    @NotNull
    String password;

    @NotNull
    @Column(unique = true)
    Integer studentId;

    @NotNull
    @Enumerated(EnumType.STRING)
    Identity identity;

    String company;

    @NotNull
    @Enumerated(EnumType.STRING)
    Track track;

    @CreatedDate
    LocalDateTime createdAt;

    LocalDateTime nickNameModifiedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<MajorSubject> majorSubjectList;
    public void addMajorSubject(MajorSubject majorSubject) {
        if (this.majorSubjectList == null) {
            this.majorSubjectList = new LinkedList<>();
        }
        this.majorSubjectList.add(majorSubject);
        majorSubject.setUser(this);
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<NonMajorSubject> nonMajorSubjectList;
    public void addNonMajorSubject(NonMajorSubject nonMajorSubject) {
        if (this.nonMajorSubjectList == null) {
            this.nonMajorSubjectList = new LinkedList<>();
        }
        this.nonMajorSubjectList.add(nonMajorSubject);
        nonMajorSubject.setUser(this);
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Post> postList;
    public void addBoard(Post post) {
        if(this.postList == null) {
            this.postList = new LinkedList<>();
        }
        this.postList.add(post);
        post.setUser(this);
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<PostLike> likeList = new LinkedList<>();
    public void addLike(PostLike postLike) {
        if(this.likeList == null) {
            this.likeList = new LinkedList<>();
        }
        this.likeList.add(postLike);
        postLike.setUser(this);
    }

    public Boolean isLikedPost(Post post) {
        for(PostLike postLike : this.likeList){
            if(postLike.getPost().getId()==post.getId()) return true;
        }
        return false;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Comment> commentList = new LinkedList<>();
    public void addComment(Comment comment) {
        if(this.commentList == null) {
            this.commentList = new LinkedList<>();
        }
        this.commentList.add(comment);
        comment.setUser(this);
    }

    public void updateMyInfo(String company, Track track){
        this.company = company;
        this.track = track;
    }

    public void updateMyNickName(String nickName){
        this.nickName = nickName;
    }
    // Todo : User Password 암호화 메소드 추가
}
