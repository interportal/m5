package md.curs.jsf.view;

import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.time.Period;
import java.util.Date;

/**
 * Created by MG
 */
@ManagedBean
@ViewScoped
public class TestBean {
    private Date date;

    @PostConstruct
    public void init() {
        date = new Date();
    }

    public String buttonClick() {
        Messages.addGlobalInfo("You clicked me!");
        return null;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
