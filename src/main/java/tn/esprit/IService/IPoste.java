package tn.esprit.IService;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IPoste<T> {
    void Add (T t) throws SQLException;
    ArrayList<T> getAll();
    void Update(T t);
    void Delete(T t);
}