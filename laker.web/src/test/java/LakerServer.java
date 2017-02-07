import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by haojiahong on 16/7/25.
 */
public class LakerServer {
    public static void main(String[] args) {
        Server server = new Server(8086);

        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setDescriptor("laker.web/src/main/webapp/WEB-INF/web.xml");
        context.setResourceBase("laker.web/src/main/webapp");
        //解决静态资源缓存后再ide里面不能修改问题
        // context.setDefaultsDescriptor("magic.web/src/test/resources/webdefault.xml");
        context.setParentLoaderPriority(true);
        server.setHandler(context);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
