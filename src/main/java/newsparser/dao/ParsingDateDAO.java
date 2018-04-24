package newsparser.dao;

import newsparser.model.ParsingDate;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ParsingDateDAO {
    List<ParsingDate> getParsingDate() throws SQLException, ClassNotFoundException, IOException;
}
