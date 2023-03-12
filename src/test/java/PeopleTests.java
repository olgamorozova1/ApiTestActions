import clients.PeopleClient;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PeopleTests {
    PeopleClient peopleClient = new PeopleClient();
    private Map<Integer, String> responseCodeAndBody = new HashMap<>();

    @Test
    public void getInfo () {
        responseCodeAndBody.putAll(peopleClient.getPeople());
        Assert.assertTrue(responseCodeAndBody.containsKey(200));
        responseCodeAndBody.get(200);
    }

    @Test
    public void getPeopleByIndex() {
         String name = peopleClient.getPeopleByIndexAndReturnFieldValue(1, "name");
         Assert.assertEquals(name, "Luke Skywalker");
         peopleClient.getPeopleByIndexAndReturnFieldValue2(1, "name");
         Assert.assertEquals(name, "Luke Skywalker");
    }

    @Test
    public void validateIncorrectPersonId() {
        int actualStatusCode = peopleClient.getPeopleByIndex(32745689);
        Assert.assertEquals(actualStatusCode, 404);
    }
}
