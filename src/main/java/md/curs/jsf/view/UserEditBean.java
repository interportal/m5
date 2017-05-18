package md.curs.jsf.view;

import md.curs.model.User;
import md.curs.service.UserService;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by MG
 */
@ManagedBean
@ViewScoped
public class UserEditBean {

    @ManagedProperty("#{userService}")
    private UserService userService;

    private Long editUserId;
    private User editUser;

    @PostConstruct
    public void init() {
        System.out.println("Edit UserBean created...");
    }

    public void initEditUser() {
        System.out.println("Calliing init");
        if (editUserId != null) {
            editUser = userService.getUser(editUserId).orElse(new User());
        } else {
            editUser = new User();
        }
    }

    public String saveUser() throws IOException {
        try {
            userService.saveUser(editUser);
            Faces.getFlash().put("lastSavedUser", editUser);
            Messages.addGlobalInfo("User successfully saved");
        } catch (Exception e) {
            LoggerFactory.getLogger("userEditBean").error("Save error", e);
            Messages.addGlobalError("Could not save user: " + e.getMessage());
        }

        Faces.getFlash().setKeepMessages(true);
        return "/user/user-list?faces-redirect=true";
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Long getEditUserId() {
        return editUserId;
    }

    public void setEditUserId(Long editUserId) {
        this.editUserId = editUserId;
    }

    public User getEditUser() {
        return editUser;
    }

    public void setEditUser(User editUser) {
        this.editUser = editUser;
    }
}
