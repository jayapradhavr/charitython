import org.json.JSONObject;

/**
 * Created by bootcamp on 9/20/15.
 */
public class JSONHandler {
    public JSONObject getJSONObject(String jsonString){
        return new JSONObject(jsonString);
    }

    public static void main(String args[]){
        JSONHandler jsonHandler = new JSONHandler();
        System.out.println(jsonHandler.getJSONObject("{\"Name\":\"Amit Barve\",\"Contact\":\"9595036117\",\"Item\":\"rice\",\"quantity\":\"3KG\",\"emp_Id\":\"tempid@paypal.com\"}").toString(2));
    }
}
