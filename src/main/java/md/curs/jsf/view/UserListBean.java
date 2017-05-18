package md.curs.jsf.view;

import md.curs.model.User;
import md.curs.service.UserService;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MG
 */
@ManagedBean
@ViewScoped
public class UserListBean {

    @ManagedProperty("#{userService}")
    private UserService userService;

    private String searchQuery;
    private List<User> userList;

    private User addUser;

    private User lastSavedUser;
    private List<User> addedUsers = new ArrayList<>();

    @PostConstruct
    public void init() {
        System.out.println("UserBean created...");
        this.lastSavedUser = (User) Faces.getFlash().get("lastSavedUser");
        search();

    }

    public void search() {
        this.userList = userService.findUsers(searchQuery);
    }

    public void initNewUser() {
        System.out.println("New user created");
        addUser = new User();
    }

    public void saveUser() throws IOException {
        try {
            userService.saveUser(addUser);
            if (addUser.getId() == null) {
                this.addedUsers.add(addUser);
            }
            userList = userService.findUsers(searchQuery);
            Messages.addGlobalInfo("User successfully added");
        } catch (Exception e) {
            LoggerFactory.getLogger(UserListBean.class.getName()).error("add error: ", e);
            Messages.addGlobalError("Could not add user: " + e.getMessage());
        }

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

    public User getAddUser() {
        return addUser;
    }

    public void setAddUser(User addUser) {
        this.addUser = addUser;
    }

    public User getLastSavedUser() {
        return lastSavedUser;
    }

    public List<User> getAddedUsers() {
        return addedUsers;
    }
}
