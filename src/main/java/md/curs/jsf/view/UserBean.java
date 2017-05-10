package md.curs.jsf.view;

import md.curs.model.User;
import md.curs.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
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
    private List<User> users;

    @PostConstruct
    public void init() {
        search();
    }

    public void search() {
        this.users = userService.findUsers(searchQuery);
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

    public List<User> getUsers() {
        return users;
    }
}
