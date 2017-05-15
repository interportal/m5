package md.curs.jsf.view;

import md.curs.model.User;
import md.curs.service.UserService;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

/**
 * Created by MG
 */
@ManagedBean
@ViewScoped
public class UserBean {

    @ManagedProperty("#{userService}")
    private UserService userService;

    private String searchQuery;
    private List<User> userList;

    private Long editUserId;
    private User editUser;

    @PostConstruct
    public void init() {
        search();
    }

    public void search() {
        this.userList = userService.findUsers(searchQuery);
    }

    public void initEditUser() {
        if (editUserId != null) {
            editUser = userService.getUser(editUserId).orElse(new User());
        } else {
            editUser = new User();
        }
    }

    public String saveUser() throws IOException {
        userService.saveUser(editUser);
        Messages.addGlobalInfo("User successfully saved");

        Faces.getFlash().setKeepMessages(true);

        return "/user/user-list?faces-redirect=true";
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public List<User> getUserList() {
        return userList;
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
