package com.abd.abcrbts.abcrbts.Model;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class CustomUserDetails extends Users implements UserDetails {

    private Collection authorities;

    public CustomUserDetails(Users user){
        super(user);

        if(user.getRoles()!=null)
            setAuthorities(user.getRoles());

    }

    private void setAuthorities(Set<Role> roles) {

        authorities = new HashSet(roles.size());

        for(Role role: roles) {
            authorities.add(new SimpleGrantedAuthority( role.getRole().toUpperCase()));
            System.out.println("roles = [" + role.getRole() + "]");
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !(super.isLocked());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
