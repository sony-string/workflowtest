package moanote.backend.entity;

import java.util.*;
import jakarta.persistence.*;

@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String username; // 로그인시 받는 id값

    @Column
    private String password; // 로그인시 받는 password값

    // @ElementCollection
    // @CollectionTable(name="roles",joinColumns=@JoinColumn(name="user_id"))
    // @Column(name="role")
    // private List<String> hasRole = new ArrayList<>(); // 가지고 있는 권한 정보

    public User() {} // 기본 생성자

    // getter/setter 추가
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

    // public List<String> getHasRole() {
    //     return hasRole;
    // }

    // public void setHasRole(List<String> hasRole) {
    //     this.hasRole = hasRole;
    // }
}