import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Window.Type;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JComboBox;

import Json.GetInstallInfo;
import Json.ModPacks;
import Json.ModPacks_info;
import Json.Versioninfo;
import Json.version;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


public class Settings {

	JFrame frmSettings;
	
	ModPacks modpack = GetInstallInfo.modpacks();
	private JComboBox cbModPackVersion;
	

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public Settings() throws Exception {
		
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frmSettings = new JFrame();
		frmSettings.setTitle("Settings");
		frmSettings.setType(Type.UTILITY);
		frmSettings.setBounds(100, 100, 250, 100);
		frmSettings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSettings.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("113px"),
				FormFactory.GROWING_BUTTON_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("28px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.MIN_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("28px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblModPack = new JLabel("Mod Pack:");
		frmSettings.getContentPane().add(lblModPack, "1, 2, left, center");
		
		final JComboBox cbModPacks = new JComboBox();
		cbModPacks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Versioninfo versioninfo = null;
				try {
					versioninfo = GetInstallInfo.Versioninfo(modpack.ModPacks_info().get(cbModPacks.getSelectedIndex()).Install_Info());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<version> versions = versioninfo.versions();
				cbModPackVersion.setModel(new DefaultComboBoxModel(versions.toArray()));
				
				cbModPackVersion.repaint();
				
				//image Downloader
				
				OutputStream out = null;
		        URLConnection conn = null;
		        InputStream in = null;
		        File Tempimage = new File("/tempimage.jpg");
		        
		        Tempimage.delete();
		        Tempimage.deleteOnExit();
		        
		        try {
		            URL url = new URL(modpack.ModPacks_info().get(cbModPacks.getSelectedIndex()).Logo_location());
		            out = new BufferedOutputStream(new FileOutputStream(Tempimage));
		            conn = url.openConnection();
		            in = conn.getInputStream();
		            byte[] buffer = new byte[1024];

		            int numRead;
		            long numWritten = 0;

		            while ((numRead = in.read(buffer)) != -1) {
		                out.write(buffer, 0, numRead);
		                numWritten += numRead;
		            }
		            
		            System.out.println("\t" + numWritten);
		        } 
		        catch (Exception exception) { 
		            exception.printStackTrace();
		        } 
		        finally {
		            try {
		                if (in != null) {
		                    in.close();
		                }
		                if (out != null) {
		                    out.close();
		                }
		            } 
		            catch (IOException ioe) {
		            }
		        }
		    
				
				ImageIcon imgThisImg = new ImageIcon(Tempimage.getAbsolutePath());
				
				System.out.println(imgThisImg);
				
				PAQInstallerV3.window.lblNewLabel.setIcon(imgThisImg);
				PAQInstallerV3.window.lblNewLabel.repaint();
				
				
			}
		});
		cbModPacks.setModel(new DefaultComboBoxModel(ModPack()));
		frmSettings.getContentPane().add(cbModPacks, "2, 2, left, top");
		
		
		JLabel lblModPackVersion = new JLabel("Mod Pack Version:");
		frmSettings.getContentPane().add(lblModPackVersion, "1, 4, left, center");
		
		cbModPackVersion = new JComboBox();
		Versioninfo versioninfo = GetInstallInfo.Versioninfo("http://mage-tech.org/PAQ/versioninfoBeta.json");
		List<version> versions = versioninfo.versions();
		cbModPackVersion.setModel(new DefaultComboBoxModel(versions.toArray()));
		frmSettings.getContentPane().add(cbModPackVersion, "2, 4, left, top");
	}


	private String[] ModPack(){
		String[] ModPack = new String[modpack.ModPacks_info().size()];
		
		
		
		for (int i=0; i <= modpack.ModPacks_info().size() - 1; i++) {
			ModPack[i] = modpack.ModPacks_info().get(i).name();
		}
		
		
		
		return ModPack;
		
	}
	protected JComboBox getCbModPackVersion() {
		return cbModPackVersion;
	}
}
