package commons;

import org.aeonbits.owner.ConfigFactory;
import utils.Environment;

import java.io.File;
import java.util.Objects;

public class GlobalConstants {
	static Environment environment;

	static {
		String environmentName = System.getProperty("environment");
		ConfigFactory.setProperty("env", Objects.requireNonNullElse(environmentName, "dev"));
		environment = ConfigFactory.create(Environment.class);
	}


	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 30;

	public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String HOMEPAGE_URL = environment.appUrl();
	public static final String LOGIN_URL = HOMEPAGE_URL + "login?returnUrl=%2F";
	public static final String REGISTER_URL = HOMEPAGE_URL + "register?returnUrl=%2F";

}
