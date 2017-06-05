package md.curs.jsf.view;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by MG
 */
@ManagedBean
@SessionScoped
public class AuthBean implements Serializable {

    private String username;
    private String password;
    private String error;

    public void initPage() {
        if ("login-failed".equals(error)) {
            Messages.addGlobalError("Username or password not valid");
        }
    }

    public void login() throws IOException {
        Faces.getExternalContext().dispatch("/login");
    }

    public boolean hasAuthority(String authority) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.isAuthenticated() &&
                auth.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals(authority));
    }

    public boolean hasAnyAuthority(String... authorities) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for (String authority : authorities) {
            boolean hasAuthority = auth.isAuthenticated() &&
                                    auth.getAuthorities().stream()
                                    .anyMatch(a -> a.getAuthority().equals(authority));
            if (hasAuthority) return true;
        }
        return false;
    }

    public boolean hasAllAuthority(String... authorities) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for (String authority : authorities) {
            boolean hasAuthority = auth.isAuthenticated() &&
                    auth.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals(authority));
            if (!hasAuthority) return false;
        }
        return true;
    }

    public boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ANONYMOUS"));
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

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
