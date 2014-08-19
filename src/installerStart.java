import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

public class installerStart {
	public static void start(String[] args) throws IOException,
			ClassNotFoundException, NoSuchMethodException,
			InvocationTargetException, IllegalAccessException {
		System.out.println("Downloading Installer");
		File tmp = File.createTempFile("Installer", ".jar");
		tmp.deleteOnExit();
		try (InputStream is = new URL(
				"http://mage-tech.org/installer/jar/PAQ-Installer-v3.0.jar")
				.openStream()) {
			StreamUtils.saveTo(is, tmp);
		}
		String[] args2 = new String[2];
		if (args == null) {
			if (Settings.modpack != null) {
				System.out.println("-m "
						+ Settings.modpack.ModPacks_info()
								.get(Settings.ModPackId).Install_Info());
				args[args.length] = "-m "
						+ Settings.modpack.ModPacks_info()
								.get(Settings.ModPackId).Install_Info();
			}

			if (Settings.ModPackVersion != null) {
				args[args.length] = "-v " + Settings.ModPackVersion;
				System.out.println("-v " + Settings.ModPackVersion);
			}
			System.out.println("Starting installer");
			Launch.jar(tmp, args);
		}
		else {
			if (Settings.modpack != null) {
				System.out.println("-m "
						+ Settings.modpack.ModPacks_info()
								.get(Settings.ModPackId).Install_Info());
				args2[0] = "-m "
						+ Settings.modpack.ModPacks_info()
								.get(Settings.ModPackId).Install_Info();
			}

			if (Settings.ModPackVersion != null) {
				args2[1] = "-v " + Settings.ModPackVersion;
				System.out.println("-v " + Settings.ModPackVersion);
			}
			System.out.println("Starting installer");
			Launch.jar(tmp, args2);
		}
		

		
	}
}
