package newsparser.daoimpl;

import newsparser.dao.ParsingDateDAO;
import newsparser.util.DatabaseConnection;
import newsparser.model.ParsingDate;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class ParsingDateImpl implements ParsingDateDAO {

    public List<ParsingDate> getParsingDate() throws SQLException, ClassNotFoundException, IOException {

        ArrayList<ParsingDate> parsingDate = new ArrayList<>();

        Connection connection = DatabaseConnection.getConnection();

        String sql = "SELECT parsedatetime FROM newsparser";

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String datetime = resultSet.getString("parsedatetime");
            parsingDate.add(new ParsingDate(datetime));
        }
        //removing duplicate values using HashSet collection
        return new ArrayList<>(new HashSet<>(parsingDate));
    }

}
