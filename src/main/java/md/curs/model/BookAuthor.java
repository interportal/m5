package md.curs.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.util.Date;

/**
 * Created by MG
 */
@Embeddable
@Access(AccessType.FIELD)
public class BookAuthor {

    private String authorName;
    private String authorSurname;
    private Date authorBirthDate;
    private String authorAddress;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public Date getAuthorBirthDate() {
        return authorBirthDate;
    }

    public void setAuthorBirthDate(Date authorBirthDate) {
        this.authorBirthDate = authorBirthDate;
    }

    public String getAuthorAddress() {
        return authorAddress;
    }

    public void setAuthorAddress(String authorAddress) {
        this.authorAddress = authorAddress;
    }
}
