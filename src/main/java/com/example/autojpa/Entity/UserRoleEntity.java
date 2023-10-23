
package com.example.autojpa.Entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_role")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="app_user_user_id", nullable = false)
    private UserEntity appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_role_id", nullable = false)
    private RoleEntity role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleEntity userRole = (UserRoleEntity) o;
        return Objects.equals(id, userRole.id)
                && Objects.equals(appUser, userRole.appUser)
                && Objects.equals(role, userRole.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appUser, role);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", appUser=" + appUser +
                ", role=" + role +
                '}';
    }

}