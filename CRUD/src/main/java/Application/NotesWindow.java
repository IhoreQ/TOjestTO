package Application;

import entity.NotesEntity;
import entity.UserEntity;
import org.hibernate.Session;

import javax.persistence.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NotesWindow {

    private JPanel notesPanel;
    private JTabbedPane mainTabbedPane;
    private JTextField noteNewTitleTextArea;
    private JTextArea noteNewContentTextArea;
    private JButton createNewNoteButton;
    private JTextArea readContentTextArea;
    private JTextArea deleteContentTextArea;
    private JTextArea updateContentTextArea;
    private JButton updateButton;
    private JButton deleteButton;
    private JComboBox<NotesEntity> readTitleComboBox;
    private JComboBox<NotesEntity> updateTitleComboBox;
    private JComboBox<NotesEntity> deleteTitleComboBox;
    private JTextField updateTitleTextField;
    private final UserEntity user;
    private final Session session;


    public NotesWindow(UserEntity user, EntityManagerFactory factory, EntityManager manager, EntityTransaction transaction, Session session) {
        JFrame mainFrame = new JFrame(user.getLogin() + "'s notes");
        mainFrame.setContentPane(notesPanel);
        this.user = user;
        this.session = session;
        getNotesList();
        mainFrame.pack();
        mainFrame.setVisible(true);

        mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                manager.close();
                factory.close();
                System.exit(0);
            }
        });

        readTitleComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NotesEntity note = (NotesEntity) readTitleComboBox.getSelectedItem();
                if (note != null)
                    readContentTextArea.setText(note.getContent());
            }
        });

        updateTitleComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NotesEntity note = (NotesEntity) updateTitleComboBox.getSelectedItem();
                if (note != null) {
                    updateTitleTextField.setText(note.getTitle());
                    updateContentTextArea.setText(note.getContent());
                }
            }
        });

        deleteTitleComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NotesEntity note = (NotesEntity) deleteTitleComboBox.getSelectedItem();
                if (note != null)
                    deleteContentTextArea.setText(note.getContent());
            }
        });

        createNewNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = noteNewTitleTextArea.getText();
                String content = noteNewContentTextArea.getText();

                if (title.length() > 0 && content.length() > 0) {
                    try {
                        transaction.begin();
                        NotesEntity newNote = new NotesEntity();
                        newNote.setTitle(title);
                        newNote.setContent(content);
                        newNote.setIdUser(user.getIdUser());
                        manager.persist(newNote);
                        transaction.commit();
                        addNewNoteToComboBoxes(newNote);
                        noteNewTitleTextArea.setText("");
                        noteNewContentTextArea.setText("");
                        JOptionPane.showMessageDialog(null, "New note has been added!");
                    } finally {
                        if (transaction.isActive())
                            transaction.rollback();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Title or content has been filled incorrectly!");
                }

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    transaction.begin();
                    NotesEntity note = (NotesEntity) deleteTitleComboBox.getSelectedItem();
                    if (note != null) {
                        Query deleteQuery = session.createQuery("DELETE NotesEntity WHERE idNote = ?1");
                        deleteQuery.setParameter(1, note.getIdNote());
                        deleteQuery.executeUpdate();
                    }
                    transaction.commit();

                    deleteNoteFromComboBoxes(note);
                    JOptionPane.showMessageDialog(null, "The note has been removed successfully!");

                } finally {
                    if (transaction.isActive())
                        transaction.rollback();
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = updateTitleTextField.getText();
                String content = updateContentTextArea.getText();

                if (title.length() > 0 && content.length() > 0) {
                    try {
                        transaction.begin();
                        NotesEntity note = (NotesEntity) updateTitleComboBox.getSelectedItem();
                        if (note != null) {
                            note.setTitle(title);
                            note.setContent(content);
                            session.update(note);
                        }
                        transaction.commit();

                        readTitleComboBox.setSelectedIndex(-1);
                        JOptionPane.showMessageDialog(null, "Note has been updated!");
                    } finally {
                        if (transaction.isActive())
                            transaction.rollback();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Title or content has been filled incorrectly!");
                }
            }
        });
    }

    private void addNewNoteToComboBoxes(NotesEntity note) {
        readTitleComboBox.addItem(note);
        updateTitleComboBox.addItem(note);
        deleteTitleComboBox.addItem(note);
        clearContent();
    }

    private void deleteNoteFromComboBoxes(NotesEntity note) {
        readTitleComboBox.removeItem(note);
        updateTitleComboBox.removeItem(note);
        deleteTitleComboBox.removeItem(note);
        clearContent();
    }

    private void clearContent() {
        readTitleComboBox.setSelectedIndex(-1);
        updateTitleComboBox.setSelectedIndex(-1);
        deleteTitleComboBox.setSelectedIndex(-1);
        deleteContentTextArea.setText("");
        updateTitleTextField.setText("");
        updateContentTextArea.setText("");
        readContentTextArea.setText("");
    }

    private void getNotesList() {
        TypedQuery<NotesEntity> notesByLogin = session.createNamedQuery("NotesEntity.ByLogin", NotesEntity.class);
        notesByLogin.setParameter(1, user.getLogin());
        List<NotesEntity> notesEntityList = notesByLogin.getResultList();
        for (NotesEntity entity : notesEntityList) {
            readTitleComboBox.addItem(entity);
            updateTitleComboBox.addItem(entity);
            deleteTitleComboBox.addItem(entity);
        }
        readTitleComboBox.setSelectedIndex(-1);
        updateTitleComboBox.setSelectedIndex(-1);
        deleteTitleComboBox.setSelectedIndex(-1);
    }

}
