package com.hms.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

public class CustomUserDetailsTest {

    @Test
    public void testConstructorAndGetters() {
        CustomUserDetails userDetails = new CustomUserDetails("user", "pass", "ROLE_USER");
        assertEquals("user", userDetails.getUsername());
        assertEquals("pass", userDetails.getPassword());
        
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        assertEquals(1, authorities.size());
        assertEquals("ROLE_USER", authorities.iterator().next().getAuthority());
    }

    @Test
    public void testAccountNonExpired() {
        CustomUserDetails userDetails = new CustomUserDetails("user", "pass", "ROLE_USER");
        assertTrue(userDetails.isAccountNonExpired());
    }

    @Test
    public void testAccountNonLocked() {
        CustomUserDetails userDetails = new CustomUserDetails("user", "pass", "ROLE_USER");
        assertTrue(userDetails.isAccountNonLocked());
    }

    @Test
    public void testCredentialsNonExpired() {
        CustomUserDetails userDetails = new CustomUserDetails("user", "pass", "ROLE_USER");
        assertTrue(userDetails.isCredentialsNonExpired());
    }

}