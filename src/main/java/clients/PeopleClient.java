package clients;

import java.util.HashMap;
import java.util.Map;

public class PeopleClient extends BaseClient {
    public static final String PEOPLE_URL = "people/";
    public static final String ONE_PEOPLE_URL = PEOPLE_URL + "{id}";
    private Map<Integer, String> responseCodeAndBody = new HashMap<>();

    public Map<Integer, String> getPeople() {
        return get(PEOPLE_URL);
    }

    public String getPeopleByIndexAndReturnFieldValue(int index, String field) {
        return getWithParams(ONE_PEOPLE_URL, "id", index)
                .then()
                .log()
                .all()
                .extract()
                .path(field);
    }

    public String getPeopleByIndexAndReturnFieldValue2(int index, String field) {
        return getWithParams(ONE_PEOPLE_URL, "id", index)
                .then()
                .log()
                .all()
                .extract()
                .jsonPath()
                .getString(field);
    }

    public int getPeopleByIndex(int index) {
        return getWithParams(ONE_PEOPLE_URL, "id", index)
                .then()
                .log()
                .all()
                .extract()
                .statusCode();
    }
}
