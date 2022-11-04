package entity;

import javax.persistence.*;

@Entity
@NamedQuery(name="UserEntity.ByLogin", query="SELECT u FROM UserEntity u WHERE u.login=?1")
@NamedQuery(name="NotesEntity.ByLogin", query="SELECT n FROM NotesEntity n, UserEntity u WHERE u.login = ?1 AND n.userById = u")
@Table(name = "notes", schema = "notatki", catalog = "")
public class NotesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_note")
    private int idNote;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "id_user")
    private int idUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", insertable = false, updatable = false)
    private UserEntity userById;

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotesEntity that = (NotesEntity) o;

        if (idNote != that.idNote) return false;
        if (idUser != that.idUser) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idNote;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + idUser;
        return result;
    }

    @Override
    public String toString() {
        return title;
    }
}
