package md.curs.jsf.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by MG
 */
@ManagedBean
@SessionScoped
public class LocaleBean implements Serializable {
    private String language = "en";

    public void changeLanguage(String lang) {
        this.language = lang;
    }

    public String getLanguage() {
        return language;
    }
}
