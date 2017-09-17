package pt.ipb.nutrimeal;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;

import org.apache.derby.jdbc.ClientDataSource;
import org.eclipse.jetty.jaas.JAASLoginService;
import org.eclipse.jetty.jndi.factories.MailSessionReference;
import org.eclipse.jetty.plus.jndi.Resource;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.Configuration.ClassList;
import org.eclipse.jetty.webapp.WebAppContext;


public class App {

	private static final String WEBAPPDIR = "webapp/";
	private static final String BASEWEBPATH = "/";
	private static final int PORT = 8083;

	public static void main(String[] args) throws IOException, NamingException {
		Properties props = new Properties();
		props.load(App.class.getResourceAsStream("/nutrimeal.properties"));

		Server server = new Server(PORT);

		// Enable JNDI
		// Enable parsing of jndi-related parts of web.xml and jetty-env.xml
		ClassList classlist = ClassList.setServerDefault(server);
		classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration",
				"org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration");
		// To support annotations
		classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
				"org.eclipse.jetty.annotations.AnnotationConfiguration");

		// JNDI jdbc references
		ClientDataSource ds = new ClientDataSource();
		ds.setServerName("localhost");
		ds.setDatabaseName("nutrimealdb;create=true");
		ds.setCreateDatabase("true");
		ds.setUser("nutrimeal");
		ds.setPassword("nutrimeal123");
		Resource jdbcDataSource = new Resource("jdbc/nutrimeal", ds);
		server.addBean(jdbcDataSource);

		// JNDI mail references
		MailSessionReference mail = new MailSessionReference();
		mail.setPassword(props.getProperty("mail.password"));
		mail.setUser(props.getProperty("mail.user"));
		mail.setProperties(props);
		Resource mailResource = new Resource("mail/nutrimeal", mail);
		server.addBean(mailResource);

		// Security handler
		JAASLoginService loginService = new JAASLoginService("nutrimealRealm");
		loginService.setLoginModuleName("ds");
		if (System.getProperty("java.security.auth.login.config") == null) {
			URL jaasConfigURL = App.class.getClassLoader().getResource("login.conf");
			System.out.println("jaasConfig" + jaasConfigURL);
			if (jaasConfigURL != null) {
				System.setProperty("java.security.auth.login.config", jaasConfigURL.toString());
			}
		}


		server.addBean(loginService);

		// setup the web pages/scripts app
		final URL warUrl = WebAppContext.class.getClassLoader().getResource(WEBAPPDIR);
		final String warUrlString = warUrl.toExternalForm();
		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath(BASEWEBPATH);
		webapp.setWar(warUrlString);
		server.setHandler(webapp);



		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (server.isStarted())
				server.destroy();
		}
	}

	public static Handler createServletHandler(HttpServlet servlet, String basePath, String path) {
		ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		handler.setContextPath(basePath);
		ServletHolder holder = new ServletHolder(servlet);
		handler.addServlet(holder, path);
		return handler;
	}

	public static Handler createServletHandler(Class<? extends HttpServlet> servlet, String basePath, String path) {
		return createServletHandler(servlet, basePath, path, null);
	}

	public static Handler createServletHandler(Class<? extends HttpServlet> servlet, String basePath, String path,
			Map<String, String> init) {
		ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		handler.setContextPath(basePath);
		final ServletHolder holder = handler.addServlet(servlet, path);
		if (init != null && !init.isEmpty()) {
			for (Map.Entry<String, String> entry : init.entrySet()) {
				holder.setInitParameter(entry.getKey(), entry.getValue());
			}
		}
		return handler;
	}
}