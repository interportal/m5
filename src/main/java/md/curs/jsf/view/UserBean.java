package md.curs.jsf.view;

import md.curs.model.User;
import md.curs.service.UserService;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

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
public class UserBean {

    @ManagedProperty("#{userService}")
    private UserService userService;

    private String searchQuery;
    private List<User> userList;

    private Long editUserId;
    private User editUser;

    private User lastSavedUser;
    private List<User> addedUsers = new ArrayList<>();

    @PostConstruct
    public void init() {
        //view1.xhtml
        this.lastSavedUser = (User) Faces.getFlash().get("lastSavedUser");
        search();

    }

    public void search() {
        this.userList = userService.findUsers(searchQuery);
    }

    public void initEditUser() {
        System.out.println("Calliing init");
        if (editUserId != null) {
            editUser = userService.getUser(editUserId).orElse(new User());
        } else {
            editUser = new User();
        }
    }

    public void initNewUser() {
        System.out.println("New user created");
        editUser = new User();
    }

    public String saveUser() throws IOException {
        userService.saveUser(editUser);
        if (editUser.getId() == null) {
            this.addedUsers.add(editUser);
        }
        userList = userService.findUsers(searchQuery);

        Messages.addGlobalInfo("User successfully saved");
        Faces.getFlash().put("lastSavedUser", editUser);
        Faces.getFlash().setKeepMessages(true);

        RequestContext.getCurrentInstance().execute("jsf.alert('test')");

        return "/user/user-list?faces-redirect=true";
    }

    public void deleteUser(User user) {
        userService.deleteUser(user);
        userList.remove(user);
        Messages.addGlobalInfo("User {0} deleted successfully", user.getName());
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

    public User getLastSavedUser() {
        return lastSavedUser;
    }

    public List<User> getAddedUsers() {
        return addedUsers;
    }
}
