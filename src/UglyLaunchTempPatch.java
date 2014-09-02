/*
This work is licensed under the Creative Commons
Attribution-NonCommercial 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/3.0/.
 */

/***
 Created By Isaac Wheeler
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

public class UglyLaunchTempPatch {

	public static void jar(File jarFile, String[] args) throws IOException,
			ClassNotFoundException, NoSuchMethodException,
			InvocationTargetException, IllegalAccessException,
			InterruptedException {

		System.out.print("\"" + jarFile.getAbsolutePath() + "\"");

		jarFile.deleteOnExit();


		String args2 = "";
		/* bugged work on latter
		if(args.length != 0){
			for(int i =1; i <= args.length; i++){
				if(args[i] != null){
					args2 = args2 + args[i];
				}
			
		}
		}
		*/
		
		System.out.println(args2);
		System.out.println("running_ " + jarFile.getAbsolutePath() );
		Process p = Runtime.getRuntime().exec(
				"java -jar " + "\"" + jarFile.getAbsolutePath() + "\" "
						+ args2 );
		p.getOutputStream();
		p.getErrorStream();
		p.getInputStream();
		p.waitFor();
		System.out.println("no longer running");
		if (p.exitValue() != 0) {
		}
	}

}
