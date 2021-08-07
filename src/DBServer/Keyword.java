package DBServer;

public class Keyword {

    private String keyword;
    private Integer level;

    public Keyword() {
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getKeyword() {
        return keyword;
    }

    public Integer getLevel() {
        return level;
    }
}
