package specs;

import config.ApiConfig;
import helpers.CustomApiListener;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class RequiestSpecs {

    static ApiConfig config = ConfigFactory.create(ApiConfig.class);
    static String baseUri = config.baseUri();
    public static RequestSpecification bookRequestSpec = with()
            .log().headers()
            //.log().body()
            .contentType(JSON)
            .filters(CustomApiListener.withCustomTemplates())
            .baseUri(baseUri);

}
