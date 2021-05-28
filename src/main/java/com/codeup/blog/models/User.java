package com.codeup.blog.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "followers",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "followed_id")}
    )
//    @JsonSerialize(using = FollowingListSerializer.class)
    @JsonIgnore
    private List<User> followingList;

    @ManyToMany(mappedBy = "followingList")
    @JsonIgnore
    private List<User> followerList;

    public User (){

    }

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

    public User (String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User (long id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(long id, String email, String username, String password, List<User> followingList) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.followingList = followingList;
    }

    public User(String email, String username, String password, List<User> followingList) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.followingList = followingList;
    }

    public User(String email, String username, String password, List<User> followingList, List<User> followerList) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.followingList = followingList;
        this.followerList = followerList;
    }

    public User(long id, String email, String username, String password, List<User> followingList, List<User> followerList) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.followingList = followingList;
        this.followerList = followerList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getFollowingList() {
        return followingList;
    }

    public void setFollowingList(List<User> followingList) {
        this.followingList = followingList;
    }

    public List<User> getFollowerList() {
        return followerList;
    }

    public void setFollowerList(List<User> followerList) {
        this.followerList = followerList;
    }
}
