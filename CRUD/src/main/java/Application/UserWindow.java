package Application;

import entity.NotesEntity;
import entity.UserEntity;
import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCrypt;
import javax.persistence.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserWindow {
    private JTextField loginCreateTextField;
    private JTextField passwordCreateTextField;
    private JTextField nameCreateTextField;
    private JTextField surnameCreateTextField;

    private JButton createButton;
    private JTextField passwordTextField;
    private JButton loginButton;

    private JPanel userPanel;
    private JTabbedPane accountTabbedPane;
    private JTextField loginTextField;
    private static JFrame mainFrame;

    private static UserWindow userWindow;
    private NotesWindow notesWindow;

    public UserWindow() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        Session session = manager.unwrap(Session.class);

        mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                manager.close();
                factory.close();
                System.exit(0);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String login = loginTextField.getText();
                String password = passwordTextField.getText();

                if (login.length() > 0 && password.length() > 0) {
                    TypedQuery<UserEntity> users = session.createNamedQuery("UserEntity.ByLogin", UserEntity.class);
                    users.setParameter(1, login);

                    if (users.getResultList().size() == 0)
                        JOptionPane.showMessageDialog(null, "Login or password is incorrect!");
                    else {
                        UserEntity user = users.getSingleResult();

                        if (BCrypt.checkpw(password, user.getPassword())) {
                            notesWindow = new NotesWindow(user, factory, manager, transaction, session);
                            mainFrame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Password is incorrect!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "The form was filled incorrectly!");
                }
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String login = loginCreateTextField.getText();
                String password = passwordCreateTextField.getText();
                String name = nameCreateTextField.getText();
                String surname = surnameCreateTextField.getText();

                if (login.length() > 0 && password.length() > 0 && name.length() > 0 && surname.length() > 0) {

                    String nameRegexPattern = "^(?=.{1,256}$)[A-ZĆŁŚŻŹa-ząćęńóśżź\\p{L}]+['-]?[A-ZĆŁŚŻŹa-ząćęńóśżź]+";

                    if (!checkPattern(name, nameRegexPattern) || !checkPattern(surname, nameRegexPattern))
                        JOptionPane.showMessageDialog(null, "Name or surname has been entered incorrectly!");
                    else {
                        TypedQuery<UserEntity> user = null;
                        String username = loginCreateTextField.getText();
                        user = session.createNamedQuery("UserEntity.ByLogin", UserEntity.class);
                        user.setParameter(1, username);
                        List<UserEntity> userEntities = user.getResultList();

                        if (userEntities.size() == 1) {
                            JOptionPane.showMessageDialog(null, "Username is already taken!");
                        }
                        else {
                            try {
                                transaction.begin();
                                UserEntity newUser = new UserEntity();
                                newUser.setName(name);
                                newUser.setSurname(surname);
                                newUser.setLogin(login);
                                newUser.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
                                newUser.setNotesByIdUser(new ArrayList<NotesEntity>());
                                manager.persist(newUser);
                                transaction.commit();
                            } finally {
                                if (transaction.isActive())
                                    transaction.rollback();
                            }
                            clearFields();
                            JOptionPane.showMessageDialog(null, "User has been created!");
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "The form was filled incorrectly!");
                }
            }
        });
    }

    public boolean checkPattern(String line, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(line).matches();
    }


    private void clearFields() {
        loginCreateTextField.setText("");
        passwordCreateTextField.setText("");
        nameCreateTextField.setText("");
        surnameCreateTextField.setText("");
    }

    public static void main(String[] args) {
        mainFrame = new JFrame("Log In");
        userWindow = new UserWindow();
        mainFrame.setContentPane(userWindow.userPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

}
