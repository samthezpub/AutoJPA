package com.example.autojpa.Entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name="user_entity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "encryted_password")
    private String encrytedPassword;

    @Column(name = "enabled")
    private boolean enabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return enabled == user.enabled
                && Objects.equals(Id, user.Id)
                && Objects.equals(userName, user.userName)
                && Objects.equals(encrytedPassword, user.encrytedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, userName, encrytedPassword, enabled);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "userId=" + Id +
                ", userName='" + userName + '\'' +
                ", encrytedPassword='" + encrytedPassword + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
