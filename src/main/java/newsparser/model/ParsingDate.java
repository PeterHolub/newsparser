package newsparser.model;

//POJO class for Parsing dates
public class ParsingDate  {
    private String datetime;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public ParsingDate(String datetime) {

        this.datetime = datetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParsingDate that = (ParsingDate) o;

        return datetime != null ? datetime.equals(that.datetime) : that.datetime == null;
    }

    @Override
    public int hashCode() {
        return datetime != null ? datetime.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ParsingDate{" +
                "datetime='" + datetime + '\'' +
                '}';
    }
}
