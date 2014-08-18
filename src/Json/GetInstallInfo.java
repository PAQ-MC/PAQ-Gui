/*
This work is licensed under the Creative Commons
Attribution-NonCommercial 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/3.0/.
 */

/***
 Created By Isaac Wheeler
 */

package Json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;

public class GetInstallInfo {
	public static Versioninfo Versioninfo(String mod) throws Exception {
		Gson gson = new Gson();
		BufferedReader br;
		if (mod != null) {

			br = read(mod);
		} else {
			br = read("http://mage-tech.org/PAQ/versioninfo.json");
		}

		Versioninfo Versioninfo = gson.fromJson(br, Versioninfo.class);

		return Versioninfo;

	}
	
	public static ModPacks modpacks() throws Exception {
		Gson gson = new Gson();
		BufferedReader br;
			
		br = read("http://mage-tech.org/PAQ/ModPacks.json");
	
		ModPacks modpacks = gson.fromJson(br, ModPacks.class);

		return modpacks;

	}

	private static BufferedReader read(String url) throws Exception {

		return new BufferedReader(

		new InputStreamReader(

		new URL(url).openStream()));
	}
}
