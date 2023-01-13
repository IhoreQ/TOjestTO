package pl.ibobek.chowmaker.repository;

import pl.ibobek.chowmaker.database.Database;

public class Repository {

    protected Database database = null;

    public Repository() {
        database = Database.getInstance();
    }
}
