package com.example.demo.security;

import com.example.demo.model.user._User;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;



@Data
public class JwtUser implements UserDetails {

    private _User _user;

    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser(_User _user) { this._user = _user; }



    public JwtUser(_User _user, Collection<? extends GrantedAuthority> authorities){
        this._user = _user;
        this.authorities = authorities;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
 return _user.getPassword();

    }
    public String getRole(){
        return _user.getRole();
    }
    @Override
    public String getUsername() {
 return _user.getEmail();
    }

    public int getId(){ return _user.getId();}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
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
