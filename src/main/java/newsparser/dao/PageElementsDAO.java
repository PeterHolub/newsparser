package newsparser.dao;

import newsparser.model.PageElements;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PageElementsDAO {
 void savetoDatabase(ArrayList<PageElements> elements) throws SQLException, ClassNotFoundException, IOException;
 ArrayList<PageElements> getPageElementsByDate(String date) throws SQLException, ClassNotFoundException, IOException;

}
