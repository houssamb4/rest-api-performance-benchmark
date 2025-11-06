import exception.GenericExceptionMapper;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class Application extends ResourceConfig {
    public Application() {
        packages("resource", "exception");
        register(JacksonFeature.class);
        register(GenericExceptionMapper.class);
    }
}
