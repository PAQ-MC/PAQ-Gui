import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;




public class installerStart {
	public static void start(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException{
		System.out.println("Downloading Installer");
		File tmp = File.createTempFile("Installer", ".jar");
		tmp.deleteOnExit();
		try (InputStream is = new URL("http://mage-tech.org/installer/jar/PAQ-Installer-v3.0.jar").openStream()) {
			StreamUtils.saveTo(is, tmp);
		}

		System.out.print("Starting installer");
		Launch.jar(tmp, args);
	}
}
