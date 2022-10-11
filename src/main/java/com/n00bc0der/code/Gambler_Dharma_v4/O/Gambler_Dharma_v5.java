package com.n00bc0der.code.Gambler_Dharma_v4.O;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.service.DriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.email.durgesh.Email;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;

public class Gambler_Dharma_v5 {

	public static void main(String[] args) throws Exception {
		
//		String fetched_uid = "null";
//		
//		InetAddress addr = InetAddress.getLocalHost();
//		NetworkInterface iface = NetworkInterface.getByInetAddress(addr);
//		byte[] mac = iface.getHardwareAddress();
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < mac.length; i++) {
//			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? i+99 : ""));
//		}
//		String uid = sb.reverse().toString();
//		
//		System.out.println("License Key : "+uid+"\n");
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://sql6.freesqldatabase.com:3306/sql6461715", "sql6461715", "a5E1HjtJhL");
//			String query = "select UID from License where UID = ?";
//			PreparedStatement pst = con.prepareStatement(query);
//			pst.setString(1, uid);
//			ResultSet set = pst.executeQuery();
//			while (set.next()) {
//				fetched_uid = set.getString(1);
//			}
//		} catch (Exception e) {
//			System.out.println("\nHuh huh  _(-_-)  Something went wrong while updating timezone for stadium in database............\n");
//			System.out.println(e.toString());
//		}
//		
//		if(fetched_uid.equals(uid)) {
//			System.out.println("License Check : License Validated");
		
		MyFrame frame1 = new MyFrame();
		
		String name = frame1.name.getText();
		DateFormat sysDate = new SimpleDateFormat("d-M-yyyy");
	    String sdate = sysDate.format(frame1.picker.getDate());
	    String date = sdate.split("-")[0];
	    String month = sdate.split("-")[1];
	    String year = sdate.split("-")[2];
		String hour = frame1.hour.getSelectedItem().toString();
		String minute = frame1.minute.getSelectedItem().toString();
		String second = frame1.second.getSelectedItem().toString();
		String stadium = frame1.stadium.getSelectedItem().toString();
		
		String sname = null;
		String latitude = null;
		String longitude = null;
		String timezone = null;

		System.out.println(
				"\n********* Sport's Prediction Using Vedic & Western Astrology - Gambler's Dharma + Frawley's Testimonies Report *********\n\n");

//--------------------------------------------------------------------------------------------------------------------
//												STADIUM DATABASE
//--------------------------------------------------------------------------------------------------------------------
		
		if(!stadium.equals("ADD NEW STADIUM")) {
			if(stadium.equals("UPDATE EXISTING TIMEZONE")) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stadiums", "root", "root");
					String query = "UPDATE stadiums SET timezone = ? WHERE alias = ? OR name like ?";
					PreparedStatement pst = con.prepareStatement(query);
					pst.setString(1, frame1.stimezone.getText());
					pst.setString(2, frame1.salias.getText());
					pst.setString(3, frame1.sname.getText());
					pst.executeUpdate();
					System.out.println("\nTimezone for Stadium "+frame1.sname.getText()+"("+frame1.salias.getText()+") Updated............");
					stadium = frame1.sname.getText();
					con.close();
				} catch (Exception e) {
					System.out.println("\nHuh huh  <(-_-)>  Something went wrong while updating timezone for stadium in database............\n");
					System.out.println(e.toString());
				}
			}
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stadiums", "root", "root");
			String query = "select * from stadiums where name = ? OR alias = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, stadium);
			pst.setString(2, frame1.salias.getText());
			ResultSet set = pst.executeQuery();
			while (set.next()) {
				sname = set.getString(2);
				latitude = set.getString(3);
				longitude = set.getString(4);
				timezone = set.getString(5);
				System.out.println("\nYou have selected stadium : " + sname);
				System.out.println("Alias : " + set.getString(1));
				System.out.println("Latitude : " + latitude);
				System.out.println("Longitude : " + longitude);
				System.out.println("Timezone : " + timezone + "\n\n");
			} con.close();
		} catch (Exception e) {
			System.out.println(
					"\nHuh huh  <(-_-)>  Something went wrong while retrieving your stadium from database....\n");
			System.out.println(e);
		}

		} else {
			try {
				String stadium_alias = frame1.salias.getText();
				sname = frame1.sname.getText();
				latitude = frame1.slatitude.getText();
				longitude = frame1.slongitude.getText();
				timezone = frame1.stimezone.getText();
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stadiums", "root", "root");
				String query = "insert into stadiums(alias,name,latitude,longitude,timezone) values(?,?,?,?,?)";
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, stadium_alias);
				pst.setString(2, sname);
				pst.setString(3, latitude);
				pst.setString(4, longitude);
				pst.setString(5, timezone);
				pst.executeUpdate();
				System.out.println("\nStadium "+sname+" added to database.");
				con.close();
			} catch (Exception e) {
				System.out
						.println("\nHuh huh  <(-_-)> Something went wrong while adding your stadium to database....\n");
				System.out.println(e);
			}
		}

		PrintStream o = new PrintStream(new File("C://Users//yadav//Desktop//Gambler's Dharma//" + name + " " + sdate + ".txt"));
		PrintStream console = System.out;
		System.setOut(o);
		// System.setOut(console);

		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");
		System.out.println("Sports Contest Details :-\n");
		System.out.println("Name : " + name);
		System.out.println("Date : " + date + "-" + month + "-" + year);
		System.out.println("Time : " + hour + ":" + minute + ":" + second);
		System.out.println("Timezone : " + timezone);
		System.out.println("Stadium : " + sname);
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "\n\n");

		JFrame frame = new JFrame();
		JProgressBar bar = new JProgressBar(0, 100);
		bar.setValue(0);
		bar.setBounds(0, 0, 420, 50);
		bar.setStringPainted(true);
		bar.setFont(new Font("MV Boli", Font.BOLD, 25));
		bar.setForeground(Color.green);
		bar.setBackground(Color.gray);
		frame.add(bar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 420);
		frame.setLayout(null);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		frame.setResizable(false);

		HashBiMap<String, Integer> signs_id = HashBiMap.create();
		signs_id.put("Ari", 1);
		signs_id.put("Tau", 2);
		signs_id.put("Gem", 3);
		signs_id.put("Can", 4);
		signs_id.put("Leo", 5);
		signs_id.put("Vir", 6);
		signs_id.put("Lib", 7);
		signs_id.put("Sco", 8);
		signs_id.put("Sag", 9);
		signs_id.put("Cap", 10);
		signs_id.put("Aqu", 11);
		signs_id.put("Pis", 12);

		HashBiMap<String, Integer> planets_id = HashBiMap.create();
		planets_id.put("Ascendant", 0);
		planets_id.put("Sun", 1);
		planets_id.put("Moon", 2);
		planets_id.put("Mars", 3);
		planets_id.put("Mercury", 4);
		planets_id.put("Jupiter", 5);
		planets_id.put("Venus", 6);
		planets_id.put("Saturn", 7);
		planets_id.put("Rahu", 8);
		planets_id.put("Ketu", 9);
		planets_id.put("Uranus", 10);
		planets_id.put("Neptune", 11);
		planets_id.put("Pluto", 12);
		planets_id.put("Upaketu", 13);
		planets_id.put("POF", 14);

		HashMap<Integer, String> lords = new HashMap<Integer, String>();
		lords.put(1, "Mars");
		lords.put(2, "Venus");
		lords.put(3, "Mercury");
		lords.put(4, "Moon");
		lords.put(5, "Sun");
		lords.put(6, "Mercury");
		lords.put(7, "Venus");
		lords.put(8, "Mars");
		lords.put(9, "Jupiter");
		lords.put(10, "Saturn");
		lords.put(11, "Saturn");
		lords.put(12, "Jupiter");

		HashBiMap<Integer, String> retro = HashBiMap.create();
		retro.put(0, "");
		retro.put(1, "(R)");
		retro.put(2, "**");
		retro.put(3, "++");

//		System.setProperty("webdriver.opera.driver", "D:\\drivers\\operadriver.exe");
//		OperaOptions options = new OperaOptions();
//		options.addArguments("user-data-dir=C:\\Users\\yadav\\AppData\\Roaming\\Opera Software\\Opera Stable");
//		WebDriver driver = new OperaDriver(options);

		Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

		System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		@SuppressWarnings("rawtypes")
		DriverService.Builder serviceBuilder = new ChromeDriverService.Builder().withSilent(true);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir=C:\\Users\\yadav\\AppData\\Local\\Google\\Chrome\\User Data");
		options.addArguments("--headless");
		ChromeDriverService chromeDriverService = (ChromeDriverService) serviceBuilder.build();
		chromeDriverService.sendOutputTo(new OutputStream() {
			@Override
			public void write(int b) {
			}
		});
		WebDriver driver = new ChromeDriver(chromeDriverService, options);

		System.setOut(console);
		System.out.println(
				"\n\nYour Sport's Prediction Using Vedic & Western Astrology - Gambler's Dharma + Frawley's Testimonies Report is being generating. This may take several minutes");
		System.setOut(o);

		driver.navigate().to("https://www.rahasyavedicastrology.com/rva-software/");
		driver.manage().window().maximize();

		driver.findElement(By.id("m-name")).sendKeys(Keys.chord(Keys.CONTROL, "a"), name);

		Select dates = new Select(driver.findElement(By.id("m-date")));
		dates.selectByValue(date);
		Select months = new Select(driver.findElement(By.id("m-month")));
		months.selectByValue(month);
		Select years = new Select(driver.findElement(By.id("m-year")));
		years.selectByValue(year);

		Select hours = new Select(driver.findElement(By.id("m-hour")));
		hours.selectByValue(hour);
		Select minutes = new Select(driver.findElement(By.id("m-minute")));
		minutes.selectByValue(minute);
		Select seconds = new Select(driver.findElement(By.id("m-seconds")));
		seconds.selectByValue(second);

		driver.findElement(By.id("m-advanced-geo-option")).click();

		driver.findElement(By.id("m-hr-lat")).sendKeys(Keys.chord(Keys.CONTROL, "a"), latitude);
		driver.findElement(By.id("m-hr-lon")).sendKeys(Keys.chord(Keys.CONTROL, "a"), longitude);
		driver.findElement(By.id("m-hr-tzone")).sendKeys(Keys.chord(Keys.CONTROL, "a"), timezone);

		driver.findElement(By.id("m-submit-hr-form")).click();

//		Thread.sleep(10000);

		Multimap<Integer, Integer> planets = ArrayListMultimap.create();

		// for loop for the 12 planets
		for (int i = 1; i <= 12; i++) {

			String planet_name = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(
					"/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[2]/div/table/tbody/tr["
							+ i + "]/th")))
					.getText();

			// getting planet retro or not @ index 0
			if (planet_name.contains("(R)")) {
				planets.put(i, 1);
			} else {
				planets.put(i, 0);
			}

			// for loop for planet attributes
			for (int j = 1; j <= 7; j++) {

				// ignoring getting planet nakshatra name & planet sign lord
				if (j == 4 || j == 5) {
					continue;
				}

				String planet_prop = driver.findElement(By.xpath(
						"/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[2]/div/table/tbody/tr["
								+ i + "]/td[" + j + "]"))
						.getText();

				// getting planet sign @ index 1
				if (j == 1) {
					planets.put(i, signs_id.get(planet_prop));
				}

				// getting planet total minutes @ index 2
				if (j == 2) {
					if (planet_prop.charAt(1) == ':') {
						planet_prop = "0" + planet_prop;
						if (planet_prop.charAt(4) == ':') {
							planet_prop = planet_prop.substring(0, 3) + "0" + planet_prop.substring(3);
						}
					}
					if (planet_prop.charAt(4) == ':') {
						planet_prop = planet_prop.substring(0, 3) + "0" + planet_prop.substring(3);
					}
					int planet_D = Integer.parseInt(planet_prop.substring(0, 2));
					int planet_M = Integer.parseInt(planet_prop.substring(3, 5));
					int planet_minutes = planet_D * 60 + planet_M;
					planets.put(i, planet_minutes);
				}

				// getting planet house_no @ index 3
				if (j == 3) {
					int house_no = Integer.parseInt(planet_prop);
					planets.put(i, house_no);
				}

				// getting planet star lord @ index 4 & planet sub lord @ index 5
				if (j == 6 || j == 7) {
					planets.put(i, planets_id.get(planet_prop));
				}
			} // end of inner loop
		} // end of outer loop
		bar.setValue(10);
		// Calculation of Upaketu - 13
		planets.put(13, 0);
		int uk_sign_no = Iterables.get(planets.get(1), 1) - 1;
		int uk_house_no = Iterables.get(planets.get(1), 3) - 1;
		planets.put(13, uk_sign_no);
		planets.put(13, Iterables.get(planets.get(1), 2));
		planets.put(13, uk_house_no);
		planets.put(13, 0);
//System.out.println(planets);

//------------------------------------------------------------------------------------------------------------------

		// getting planet self-2 / tenanted-0 / untenanted-3 @ index 6
		for (int i = 1; i <= 9; i++) {
			boolean ut = true;
			for (int j = 1; j <= 9; j++) {
				int planet_star = Iterables.get(planets.get(j), 4);
				if (planet_star == i && j == i) {
					planets.put(i, 2);
					ut = false;
					break;
				} else if (planet_star == i) {
					planets.put(i, 0);
					ut = false;
					break;
				}
			}
			if (ut) {
				planets.put(i, 3);
			}
		}

//------------------------------------------------------------------------------------------------------------------

		Multimap<Integer, Integer> cusps = ArrayListMultimap.create();

		// for 12 signs
		for (int i = 1; i <= 12; i++) {

			// for sign attributes
			for (int j = 1; j <= 6; j++) {

				// ignoring getting cusp nakshatra name, cusp sign lord & cusp star lord
				if (j == 3 || j == 4 || j == 5) {
					continue;
				}

				String cusp_prop = driver.findElement(By.xpath(
						"/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[1]/div/table/tbody/tr["
								+ i + "]/td[" + j + "]"))
						.getText();

				// getting cusp sign @ index 0
				if (j == 1) {
					cusps.put(i, signs_id.get(cusp_prop));
				}

				// getting cusp total minutes @ index 1
				if (j == 2) {
					if (cusp_prop.charAt(1) == ':') {
						cusp_prop = "0" + cusp_prop;
						if (cusp_prop.charAt(4) == ':') {
							cusp_prop = cusp_prop.substring(0, 3) + "0" + cusp_prop.substring(3);
						}
					}
					if (cusp_prop.charAt(4) == ':') {
						cusp_prop = cusp_prop.substring(0, 3) + "0" + cusp_prop.substring(3);
					}
					int cusp_D = Integer.parseInt(cusp_prop.substring(0, 2));
					int cusp_M = Integer.parseInt(cusp_prop.substring(3, 5));
					int cusp_minutes = cusp_D * 60 + cusp_M;
					cusps.put(i, cusp_minutes);
				}

				// getting cusp sub lord @ index 3
				if (j == 6) {
					cusps.put(i, planets_id.get(cusp_prop));
				}
			} // end of inner loop
		} // end of outer loop
			// System.out.println(cusps);

		// placing ASC as planet with key=0 in planets multimap
		planets.put(0, 0);
		planets.put(0, Iterables.get(cusps.get(1), 0));
		planets.put(0, Iterables.get(cusps.get(1), 1));
		planets.put(0, 0);
		planets.put(0, 0);
		// System.out.println(planets);

		Multimap<String, Integer> D1_Lords = ArrayListMultimap.create();

		// Logic to form D1 Whole Sign
		HashBiMap<Integer, Integer> whole_sign = HashBiMap.create();
		int counter = 0;
		for (int i = 1; i <= 12; i++) {
			int sign = Iterables.get(cusps.get(1), 0) + counter;
			if (sign >= 13) {
				sign = sign % 12;
			}
			whole_sign.put(i, sign);
			D1_Lords.put(lords.get(sign), i);
			counter++;
		}
		// System.out.println(whole_sign);
		// System.out.println(D1_Lords);

		// Calculation of POF
		int asc_sign = Iterables.get(planets.get(0), 1);
		int asc_minutes = Iterables.get(planets.get(0), 2);
		asc_minutes = asc_minutes + (asc_sign - 1) * 1800;

		int moon_sign = Iterables.get(planets.get(2), 1);
		int moon_minutes = Iterables.get(planets.get(2), 2);
		moon_minutes = moon_minutes + (moon_sign - 1) * 1800;

		int sun_sign = Iterables.get(planets.get(1), 1);
		int sun_minutes = Iterables.get(planets.get(1), 2);
		sun_minutes = sun_minutes + (sun_sign - 1) * 1800;

		int pof_minutes = asc_minutes + moon_minutes - sun_minutes;
		if (pof_minutes < 0) {
			pof_minutes = pof_minutes + 21600;
		}
		int pof_sign = pof_minutes / 1800 + 1;
		if (pof_sign > 12) {
			pof_sign = pof_sign % 12;
		}
		pof_minutes = pof_minutes % 1800;

		planets.put(14, 0);
		planets.put(14, pof_sign);
		planets.put(14, pof_minutes);
		planets.put(14, whole_sign.inverse().get(pof_sign));
		planets.put(14, 0);

		// 0 -> navamsa_degree , 1 -> navamsa_sign
		Multimap<Integer, Integer> navamsa = ArrayListMultimap.create();

		// Calculation of Navamsa
		for (int i = 0; i <= 14; i++) {

			int planet_sign_no = Iterables.get(planets.get(i), 1);
			int nplanet_sign_no;

			int planet_minutes = Iterables.get(planets.get(i), 2);
			int n = (int) Math.ceil(planet_minutes / 200.0) - 1;

			// Movable Signs
			if (planet_sign_no == 1 || planet_sign_no == 4 || planet_sign_no == 7 || planet_sign_no == 10) {
				nplanet_sign_no = planet_sign_no + n;
				if (nplanet_sign_no > 12) {
					nplanet_sign_no = nplanet_sign_no % 12;
				}
			} // Fixed Signs
			else if (planet_sign_no == 2 || planet_sign_no == 5 || planet_sign_no == 8 || planet_sign_no == 11) {
				nplanet_sign_no = planet_sign_no + 8 + n;
				if (nplanet_sign_no > 12) {
					nplanet_sign_no = nplanet_sign_no % 12;
				}
			} else { // Dual Signs
				nplanet_sign_no = planet_sign_no + 4 + n;
				if (nplanet_sign_no > 12) {
					nplanet_sign_no = nplanet_sign_no % 12;
				}
			}

			int navamsa_minutes = (planet_minutes % 200) * 9;

			navamsa.put(i, navamsa_minutes); if (nplanet_sign_no == 0) { nplanet_sign_no = 12; }
			navamsa.put(i, nplanet_sign_no);

		}  // System.out.println(navamsa);

		Multimap<String, Integer> D9_Lords = ArrayListMultimap.create();

		// Calculation of D9 Whole Sign
		HashBiMap<Integer, Integer> navamsa_whole_sign = HashBiMap.create();
		int n_counter = 0;
		for (int i = 1; i <= 12; i++) {
			int sign = Iterables.get(navamsa.get(0), 1) + n_counter;
			if (sign >= 13) {
				sign = sign % 12;
			}
			if (sign == 0) {
				sign = 12;
			}
			navamsa_whole_sign.put(i, sign);
			D9_Lords.put(lords.get(sign), i);
			n_counter++;
		}
//		 System.out.println(navamsa_whole_sign);
//		 System.out.println(D9_Lords);

//----------------------------------------------------------------------------------------------------------------------
		System.out.println("D1 Lagna : " + Iterables.get(planets.get(0), 2) / 60 + "°" + Iterables.get(planets.get(0), 2) % 60 + "' " + signs_id.inverse().get(Iterables.get(planets.get(0), 1)));
		System.out.println("\nD9 Lagna : " + Iterables.get(navamsa.get(0), 0) / 60 + "°" + Iterables.get(navamsa.get(0), 0) % 60 + "' " + signs_id.inverse().get(navamsa_whole_sign.get(1))+"\n\n");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");
		System.out.println("(Module 1)					D1 Cuspal Strength : ");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");
		for (int i = 1; i <= 14; i++) {

			int planet_house_no = Iterables.get(planets.get(i), 3);
			int planet_sign = Iterables.get(planets.get(i), 1);
			int planet_retro = Iterables.get(planets.get(i), 0);

			int cusp_sign = Iterables.get(cusps.get(planet_house_no), 0);

			int planet_minutes = Iterables.get(planets.get(i), 2);
			int cusp_minutes = Iterables.get(cusps.get(planet_house_no), 1);

			int orb = cusp_minutes - planet_minutes;

			if (cusp_sign == planet_sign) {
				if ((Math.abs(orb) <= 150) && (planet_house_no == 1 || planet_house_no == 7 || planet_house_no == 10
						|| planet_house_no == 4 || planet_house_no == 6 || planet_house_no == 12)) {
					System.out.println(
							"\n***************************************************************************************************************************");
					System.out.println("Lord of " + D1_Lords.get(planets_id.inverse().get(i)) + " house "
							+ planets_id.inverse().get(i) + retro.get(Iterables.get(planets.get(i), 0)) + " ("
							+ signs_id.inverse().get(planet_sign) + ") is on " + planet_house_no
							+ " cusp within orb of " + (orb / 60) + "°" + Math.abs(orb % 60) + "'");
					System.out.println(
							"***************************************************************************************************************************\n");
				} else if ((Math.abs(orb) <= 180) && (planet_retro == 1)
						&& (planet_house_no == 1 || planet_house_no == 7 || planet_house_no == 10
								|| planet_house_no == 4 || planet_house_no == 6 || planet_house_no == 12)) {
					System.out.println(
							"\n***************************************************************************************************************************");
					System.out.println("Lord of " + D1_Lords.get(planets_id.inverse().get(i)) + " house "
							+ planets_id.inverse().get(i) + retro.get(Iterables.get(planets.get(i), 0)) + " ("
							+ signs_id.inverse().get(planet_sign) + ") is on " + planet_house_no
							+ " cusp within orb of " + (orb / 60) + "°" + Math.abs(orb % 60)
							+ "' (3° orb for retro planets)");
					System.out.println(
							"***************************************************************************************************************************\n");
				} else if ((Math.abs(orb) <= 150)
						&& (planet_house_no == 2 || planet_house_no == 8 || planet_house_no == 3 || planet_house_no == 9
								|| planet_house_no == 5 || planet_house_no == 11)) {
					System.out.println("\n**** Lord of " + D1_Lords.get(planets_id.inverse().get(i)) + " house "
							+ planets_id.inverse().get(i) + retro.get(Iterables.get(planets.get(i), 0)) + " ("
							+ signs_id.inverse().get(planet_sign) + ") is on " + planet_house_no
							+ " cusp within orb of " + (orb / 60) + "°" + Math.abs(orb % 60) + "' ****\n");
				} else {
					System.out.println("Lord of " + D1_Lords.get(planets_id.inverse().get(i)) + " house "
							+ planets_id.inverse().get(i) + retro.get(Iterables.get(planets.get(i), 0)) + " ("
							+ signs_id.inverse().get(planet_sign) + ") is on " + planet_house_no
							+ " cusp but within orb of " + (orb / 60) + "°" + Math.abs(orb % 60)
							+ "' (out of 2°30' orb)");
				}
			}

//					boolean show = true;
			for (int j = 1; j <= 12; j++) {

				int cuspal_sign = Iterables.get(cusps.get(j), 0);
				int cuspal_minutes = Iterables.get(cusps.get(j), 1);

				int orb2 = cuspal_minutes - planet_minutes;

				if ((cuspal_sign == planet_sign && j != planet_house_no && Math.abs(orb2) <= 150)
						&& (planet_house_no == 1 || planet_house_no == 7 || planet_house_no == 10
								|| planet_house_no == 4 || planet_house_no == 6 || planet_house_no == 12)) {
//							 if(show) {
//									System.out.println("\n\nStolen Cusps : ");
//									System.out.println("=============\n"); show = false; }
					System.out.println("\n[STOLEN CUSP] Lord of " + D1_Lords.get(planets_id.inverse().get(i))
							+ " house " + planets_id.inverse().get(i) + retro.get(Iterables.get(planets.get(i), 0))
							+ " (" + signs_id.inverse().get(planet_sign) + ") is on " + j + " cusp within orb of "
							+ (orb2 / 60) + "°" + Math.abs(orb2 % 60) + "' but moved to " + planet_house_no
							+ " house\n");
				}
			}
		}
		bar.setValue(20);
		Thread.sleep(1000);

//---------------------------------------------------------------------------------------------------------------------

		System.out.println(
				"\n\n\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");
		System.out.println("(Module 2)					D9 Cuspal Strength & D9 Combo : ");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");

		int navamsa_asc_sign = navamsa_whole_sign.get(1);
		int navamsa_dsc_sign = navamsa_whole_sign.get(7);
		int navamsa_mc_sign = navamsa_whole_sign.get(10);
		int navamsa_ic_sign = navamsa_whole_sign.get(4);

		int navamsa_asc_minutes = Iterables.get(navamsa.get(0), 0);

		String d9_first = "";
		String d9_seventh = "";

		for (int i = 1; i <= 14; i++) {

			int nplanet_minutes = Iterables.get(navamsa.get(i), 0);

			int orb = navamsa_asc_minutes - nplanet_minutes;

			int navamsa_planet_sign = Iterables.get(navamsa.get(i), 1);

			if (navamsa_asc_sign == navamsa_planet_sign) {
				if (Math.abs(orb) <= 150) {
					System.out.println(
							"\n***************************************************************************************************************************");
					System.out.println("Lord of " + D9_Lords.get(planets_id.inverse().get(i)) + " house "
							+ planets_id.inverse().get(i) + retro.get(Iterables.get(planets.get(i), 0)) + " ("
							+ signs_id.inverse().get(navamsa_planet_sign) + ")"
							+ " is in D9 Ascendant (1st) within orb of " + (orb / 60) + "°" + Math.abs(orb % 60) + "'");
					System.out.println(
							"***************************************************************************************************************************\n");
					d9_first = planets_id.inverse().get(i) + retro.get(Iterables.get(planets.get(i), 0)) + " + "
							+ d9_first;
				} else {
					System.out.println("Lord of " + D9_Lords.get(planets_id.inverse().get(i)) + " house "
							+ planets_id.inverse().get(i) + retro.get(Iterables.get(planets.get(i), 0)) + " ("
							+ signs_id.inverse().get(navamsa_planet_sign) + ")"
							+ " is in D9 Ascendant (1st) but within orb of " + (orb / 60) + "°" + Math.abs(orb % 60)
							+ "' (out of 2°30' orb)");
					d9_first = planets_id.inverse().get(i) + retro.get(Iterables.get(planets.get(i), 0)) + " + "
							+ d9_first;
				}
			}
			if (navamsa_dsc_sign == navamsa_planet_sign) {
				if (Math.abs(orb) <= 150) {
					System.out.println(
							"\n***************************************************************************************************************************");
					System.out.println("Lord of " + D9_Lords.get(planets_id.inverse().get(i)) + " house "
							+ planets_id.inverse().get(i) + retro.get(Iterables.get(planets.get(i), 0)) + " ("
							+ signs_id.inverse().get(navamsa_planet_sign) + ")"
							+ " is in D9 Descendant (7th) within orb of " + (orb / 60) + "°" + Math.abs(orb % 60) + "'");
					System.out.println(
							"***************************************************************************************************************************\n");
					d9_seventh = planets_id.inverse().get(i) + retro.get(Iterables.get(planets.get(i), 0)) + " + "
							+ d9_seventh;
				} else {
					System.out.println("Lord of " + D9_Lords.get(planets_id.inverse().get(i)) + " house "
							+ planets_id.inverse().get(i) + retro.get(Iterables.get(planets.get(i), 0)) + " ("
							+ signs_id.inverse().get(navamsa_planet_sign) + ")"
							+ " is in D9 Descendant (7th) but within orb of " + (orb / 60) + "°" + Math.abs(orb % 60)
							+ "' (out of 2°30' orb)");
					d9_seventh = planets_id.inverse().get(i) + retro.get(Iterables.get(planets.get(i), 0)) + " + "
							+ d9_seventh;
				}
			}
			if (navamsa_mc_sign == navamsa_planet_sign) {
				if (Math.abs(orb) <= 150) {
					System.out.println(
							"\n***************************************************************************************************************************");
					System.out.println("Lord of " + D9_Lords.get(planets_id.inverse().get(i)) + " house "
							+ planets_id.inverse().get(i) + retro.get(Iterables.get(planets.get(i), 0)) + " ("
							+ signs_id.inverse().get(navamsa_planet_sign) + ")" + " is in D9 10th within orb of "
							+ (orb / 60) + "°" + Math.abs(orb % 60) + "'");
					System.out.println(
							"***************************************************************************************************************************\n");
				} else {
					System.out.println("Lord of " + D9_Lords.get(planets_id.inverse().get(i)) + " house "
							+ planets_id.inverse().get(i) + retro.get(Iterables.get(planets.get(i), 0)) + " ("
							+ signs_id.inverse().get(navamsa_planet_sign) + ")" + " is in D9 10th but within orb of "
							+ (orb / 60) + "°" + Math.abs(orb % 60) + "' (out of 2°30' orb)");
				}
			}
			if (navamsa_ic_sign == navamsa_planet_sign) {
				if (Math.abs(orb) <= 150) {
					System.out.println(
							"\n***************************************************************************************************************************");
					System.out.println("Lord of " + D9_Lords.get(planets_id.inverse().get(i)) + " house "
							+ planets_id.inverse().get(i) + retro.get(Iterables.get(planets.get(i), 0)) + " ("
							+ signs_id.inverse().get(navamsa_planet_sign) + ")" + " is in D9 4th within orb of "
							+ (orb / 60) + "°" + Math.abs(orb % 60) + "'");
					System.out.println(
							"***************************************************************************************************************************\n");
				} else {
					System.out.println("Lord of " + D9_Lords.get(planets_id.inverse().get(i)) + " house "
							+ planets_id.inverse().get(i) + retro.get(Iterables.get(planets.get(i), 0)) + " ("
							+ signs_id.inverse().get(navamsa_planet_sign) + ")" + " is in D9 4th but within orb of "
							+ (orb / 60) + "°" + Math.abs(orb % 60) + "' (out of 2°30' orb)");
				}
			}

		}

		System.out.println("\n\nD9 Combo : ");
		System.out.println("\nD9 ASC 1st : " + StringUtils.chop(StringUtils.chop(d9_first)));
		System.out.println("D9 DSC 7th : " + StringUtils.chop(StringUtils.chop(d9_seventh)));
		bar.setValue(30);
		Thread.sleep(1000);

//-------------------------------------------------------------------------------------------------------------------

		System.out.println(
				"\n\n\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");
		System.out.println("(Module 3)					The Sublord Technique : ");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");

		String asc_csl = driver.findElement(By.xpath(
				"/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[1]/div/table/tbody/tr[1]/td[6]"))
				.getText();
		String dsc_csl = driver.findElement(By.xpath(
				"/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[1]/div/div[1]/div/table/tbody/tr[7]/td[6]"))
				.getText();
		int ASC_SUB = 0;
		int DSC_SUB = 0;
		String planet_status;

		int ASC = 0;
		int DSC = 0;

		Multimap<Integer, String> house_view = ArrayListMultimap.create();
		for (int i = 1; i <= 12; i++) {
			for (int j = 1; j <= 4; j++) {
				String s = driver.findElement(By.xpath(
						"/html/body/div[2]/div[2]/div/main/article/div/div[4]/div[2]/div[2]/div/div[1]/div/table/tbody/tr["
								+ i + "]/td[" + j + "]"))
						.getText();
				if ((s.equals("Uranus")) || (s.equals("Neptune")) || (s.equals("Pluto"))) {
					house_view.put(i, "");
				} else {
					house_view.put(i, s);
				}
			}
		}

		for (int i = 1; i <= 12; i++) {

			if ((i == 6 || i == 11)
					&& ((Iterables.get(house_view.get(i), 0).equals(""))
							&& (Iterables.get(house_view.get(i), 1).equals(""))
							&& (Iterables.get(house_view.get(i), 2).equals(""))
							&& (Iterables.get(house_view.get(i), 3).contains(asc_csl)))) {
				ASC_SUB = ASC_SUB + 4;
			} else if ((i == 5 || i == 12)
					&& ((Iterables.get(house_view.get(i), 0).equals(""))
							&& (Iterables.get(house_view.get(i), 1).equals(""))
							&& (Iterables.get(house_view.get(i), 2).equals(""))
							&& (Iterables.get(house_view.get(i), 3).contains(asc_csl)))) {
				ASC_SUB = ASC_SUB - 4;
			} else if ((i == 6 || i == 11)
					&& ((Iterables.get(house_view.get(i), 0).equals(""))
							&& (Iterables.get(house_view.get(i), 1).equals("")))) {
				if (Iterables.get(house_view.get(i), 2).contains(asc_csl)) {
					ASC_SUB = ASC_SUB + 4;
				} else if (Iterables.get(house_view.get(i), 3).contains(asc_csl)) {
					ASC_SUB = ASC_SUB + 2;
				}
			} else if ((i == 5 || i == 12)
					&& ((Iterables.get(house_view.get(i), 0).equals(""))
							&& (Iterables.get(house_view.get(i), 1).equals("")))) {
				if (Iterables.get(house_view.get(i), 2).contains(asc_csl)) {
					ASC_SUB = ASC_SUB - 4;
				} else if (Iterables.get(house_view.get(i), 3).contains(asc_csl)) {
					ASC_SUB = ASC_SUB - 2;
				}
			} else {
				int cnt = 4;
				for (int j = 0; j <= 3; j++) {
					String s = Iterables.get(house_view.get(i), j);

					if ((i == 6 || i == 11) && (s.contains(asc_csl))) {
						ASC_SUB = ASC_SUB + cnt;
					} else if ((i == 5 || i == 12) && (s.contains(asc_csl))) {
						ASC_SUB = ASC_SUB - cnt;
					}
					cnt = cnt - 1;
				}
			}

		}

		System.out.println("1st CSL : " + asc_csl + " = " + ASC_SUB);

		for (int i = 1; i <= 12; i++) {

			if ((i == 6 || i == 11)
					&& ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == "")
							&& (Iterables.get(house_view.get(i), 2) == ""
									&& (Iterables.get(house_view.get(i), 3).contains(dsc_csl))))) {
				DSC_SUB = DSC_SUB - 4;
			} else if ((i == 5 || i == 12)
					&& ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == "")
							&& (Iterables.get(house_view.get(i), 2) == ""
									&& (Iterables.get(house_view.get(i), 3).contains(dsc_csl))))) {
				DSC_SUB = DSC_SUB + 4;
			} else if ((i == 6 || i == 11)
					&& ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == ""))) {
				if (Iterables.get(house_view.get(i), 2).contains(dsc_csl)) {
					DSC_SUB = DSC_SUB - 4;
				} else if (Iterables.get(house_view.get(i), 3).contains(dsc_csl)) {
					DSC_SUB = DSC_SUB - 2;
				}
			} else if ((i == 5 || i == 12)
					&& ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == ""))) {
				if (Iterables.get(house_view.get(i), 2).contains(dsc_csl)) {
					DSC_SUB = DSC_SUB + 4;
				} else if (Iterables.get(house_view.get(i), 3).contains(dsc_csl)) {
					DSC_SUB = DSC_SUB + 2;
				}
			} else {
				int cnt = 4;
				for (int j = 0; j <= 3; j++) {
					String s = Iterables.get(house_view.get(i), j);

					if ((i == 6 || i == 11) && (s.contains(dsc_csl))) {
						DSC_SUB = DSC_SUB - cnt;
					} else if ((i == 5 || i == 12) && (s.contains(dsc_csl))) {
						DSC_SUB = DSC_SUB + cnt;
					}
					cnt = cnt - 1;
				}
			}

		}
		System.out.println("7th CSL : " + dsc_csl + " = " + DSC_SUB + "\n");

		if (ASC_SUB == DSC_SUB) {
			System.out.println("Sublords are neutral");
			planet_status = "(1) Planet Level : Neutral";
		} else if (asc_csl.equals(dsc_csl)) {
			if (ASC_SUB > 0) {
				System.out.println("Sublords are in favor of ASC by " + ASC_SUB);
				planet_status = "(1) Planet Level : ASC by " + ASC_SUB;
				ASC = ASC + ASC_SUB;
			} else {
				System.out.println("Sublords are in favor of DSC by " + DSC_SUB);
				planet_status = "(1) Planet Level : DSC by " + DSC_SUB;
				DSC = DSC + DSC_SUB;
			}
		} else if (ASC_SUB > DSC_SUB) {
			System.out.println("Sublords are in favor of ASC by " + (ASC_SUB - DSC_SUB));
			planet_status = "(1) Planet Level : ASC by " + (ASC_SUB - DSC_SUB);
			ASC = ASC + (ASC_SUB - DSC_SUB);
		} else {
			System.out.println("Sublords are in favor of DSC by " + (DSC_SUB - ASC_SUB));
			planet_status = "(1) Planet Level : DSC by " + (DSC_SUB - ASC_SUB);
			DSC = DSC + (DSC_SUB - ASC_SUB);
		}

//------------------------------------------------------------------------------------------------------------------
//									  ADVANCED
//------------------------------------------------------------------------------------------------------------------

		System.out.println("\n\nADVANCED : Using Star and Sub of 1st & 7th CSL");
		System.out.println("==============================================\n");

		int asc_csl_star = Iterables.get(planets.get(planets_id.get(asc_csl)), 4);
		int asc_csl_sub = Iterables.get(planets.get(planets_id.get(asc_csl)), 5);

		int dsc_csl_star = Iterables.get(planets.get(planets_id.get(dsc_csl)), 4);
		int dsc_csl_sub = Iterables.get(planets.get(planets_id.get(dsc_csl)), 5);

		String asc_csl_stars = planets_id.inverse().get(asc_csl_star);
		String asc_csl_subs = planets_id.inverse().get(asc_csl_sub);

		String dsc_csl_stars = planets_id.inverse().get(dsc_csl_star);
		String dsc_csl_subs = planets_id.inverse().get(dsc_csl_sub);

		int ASC_SUB_STAR = 0;
		int DSC_SUB_STAR = 0;
		int ASC_SUB_SUB = 0;
		int DSC_SUB_SUB = 0;
		String star_status;
		String sub_status;

//-------------------------------------------------------------------------------------------------------------------
// Calculations of 1st & 7th CSL Star Lord

		for (int i = 1; i <= 12; i++) {

			if ((i == 6 || i == 11)
					&& ((Iterables.get(house_view.get(i), 0).equals(""))
							&& (Iterables.get(house_view.get(i), 1).equals(""))
							&& (Iterables.get(house_view.get(i), 2).equals(""))
							&& (Iterables.get(house_view.get(i), 3).contains(asc_csl_stars)))) {
				ASC_SUB_STAR = ASC_SUB_STAR + 4;
			} else if ((i == 5 || i == 12)
					&& ((Iterables.get(house_view.get(i), 0).equals(""))
							&& (Iterables.get(house_view.get(i), 1).equals(""))
							&& (Iterables.get(house_view.get(i), 2).equals(""))
							&& (Iterables.get(house_view.get(i), 3).contains(asc_csl_stars)))) {
				ASC_SUB_STAR = ASC_SUB_STAR - 4;
			} else if ((i == 6 || i == 11)
					&& ((Iterables.get(house_view.get(i), 0).equals(""))
							&& (Iterables.get(house_view.get(i), 1).equals("")))) {
				if (Iterables.get(house_view.get(i), 2).contains(asc_csl_stars)) {
					ASC_SUB_STAR = ASC_SUB_STAR + 4;
				} else if (Iterables.get(house_view.get(i), 3).contains(asc_csl_stars)) {
					ASC_SUB_STAR = ASC_SUB_STAR + 2;
				}
			} else if ((i == 5 || i == 12)
					&& ((Iterables.get(house_view.get(i), 0).equals(""))
							&& (Iterables.get(house_view.get(i), 1).equals("")))) {
				if (Iterables.get(house_view.get(i), 2).contains(asc_csl_stars)) {
					ASC_SUB_STAR = ASC_SUB_STAR - 4;
				} else if (Iterables.get(house_view.get(i), 3).contains(asc_csl_stars)) {
					ASC_SUB_STAR = ASC_SUB_STAR - 2;
				}
			} else {
				int cnt = 4;
				for (int j = 0; j <= 3; j++) {
					String s = Iterables.get(house_view.get(i), j);

					if ((i == 6 || i == 11) && (s.contains(asc_csl_stars))) {
						ASC_SUB_STAR = ASC_SUB_STAR + cnt;
					} else if ((i == 5 || i == 12)
							&& (s.contains(asc_csl_stars))) {
						ASC_SUB_STAR = ASC_SUB_STAR - cnt;
					}
					cnt = cnt - 1;
				}
			}

		}

		for (int i = 1; i <= 12; i++) {

			if ((i == 6 || i == 11)
					&& ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == "")
							&& (Iterables.get(house_view.get(i), 2) == ""
									&& (Iterables.get(house_view.get(i), 3).contains(dsc_csl_stars))))) {
				DSC_SUB_STAR = DSC_SUB_STAR - 4;
			} else if ((i == 5 || i == 12)
					&& ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == "")
							&& (Iterables.get(house_view.get(i), 2) == ""
									&& (Iterables.get(house_view.get(i), 3).contains(dsc_csl_stars))))) {
				DSC_SUB_STAR = DSC_SUB_STAR + 4;
			} else if ((i == 6 || i == 11)
					&& ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == ""))) {
				if (Iterables.get(house_view.get(i), 2).contains(dsc_csl_stars)) {
					DSC_SUB_STAR = DSC_SUB_STAR - 4;
				} else if (Iterables.get(house_view.get(i), 3).contains(dsc_csl_stars)) {
					DSC_SUB_STAR = DSC_SUB_STAR - 2;
				}
			} else if ((i == 5 || i == 12)
					&& ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == ""))) {
				if (Iterables.get(house_view.get(i), 2).contains(dsc_csl_stars)) {
					DSC_SUB_STAR = DSC_SUB_STAR + 4;
				} else if (Iterables.get(house_view.get(i), 3).contains(dsc_csl_stars)) {
					DSC_SUB_STAR = DSC_SUB_STAR + 2;
				}
			} else {
				int cnt = 4;
				for (int j = 0; j <= 3; j++) {
					String s = Iterables.get(house_view.get(i), j);

					if ((i == 6 || i == 11) && (s.contains(dsc_csl_stars))) {
						DSC_SUB_STAR = DSC_SUB_STAR - cnt;
					} else if ((i == 5 || i == 12)
							&& (s.contains(dsc_csl_stars))) {
						DSC_SUB_STAR = DSC_SUB_STAR + cnt;
					}
					cnt = cnt - 1;
				}
			}

		}

		if (ASC_SUB_STAR == DSC_SUB_STAR) {
			star_status = "(2) Star Level : Neutral";
		} else if (asc_csl_stars.equals(dsc_csl_stars)) {
			if (ASC_SUB_STAR > 0) {
				star_status = "(2) Star Level : ASC by " + ASC_SUB_STAR;
				ASC = ASC + (ASC_SUB_STAR * 2);
			} else {
				star_status = "(2) Star Level : DSC by " + DSC_SUB_STAR;
				DSC = DSC + (DSC_SUB_STAR * 2);
			}
		} else if (ASC_SUB_STAR > DSC_SUB_STAR) {
			star_status = "(2) Star Level : ASC by " + (ASC_SUB_STAR - DSC_SUB_STAR);
			ASC = ASC + (ASC_SUB_STAR - DSC_SUB_STAR) * 2;
		} else {
			star_status = "(2) Star Level : DSC by " + (DSC_SUB_STAR - ASC_SUB_STAR);
			DSC = DSC + (DSC_SUB_STAR - ASC_SUB_STAR) * 2;
		}

//-------------------------------------------------------------------------------------------------------------------
// Calculations of 1st & 7th CSL Sub Lord

		for (int i = 1; i <= 12; i++) {

			if ((i == 6 || i == 11)
					&& ((Iterables.get(house_view.get(i), 0).equals(""))
							&& (Iterables.get(house_view.get(i), 1).equals(""))
							&& (Iterables.get(house_view.get(i), 2).equals(""))
							&& (Iterables.get(house_view.get(i), 3).contains(asc_csl_subs)))) {
				ASC_SUB_SUB = ASC_SUB_SUB + 4;
			} else if ((i == 5 || i == 12)
					&& ((Iterables.get(house_view.get(i), 0).equals(""))
							&& (Iterables.get(house_view.get(i), 1).equals(""))
							&& (Iterables.get(house_view.get(i), 2).equals(""))
							&& (Iterables.get(house_view.get(i), 3).contains(asc_csl_subs)))) {
				ASC_SUB_SUB = ASC_SUB_SUB - 4;
			} else if ((i == 6 || i == 11)
					&& ((Iterables.get(house_view.get(i), 0).equals(""))
							&& (Iterables.get(house_view.get(i), 1).equals("")))) {
				if (Iterables.get(house_view.get(i), 2).contains(asc_csl_subs)) {
					ASC_SUB_SUB = ASC_SUB_SUB + 4;
				} else if (Iterables.get(house_view.get(i), 3).contains(asc_csl_subs)) {
					ASC_SUB_SUB = ASC_SUB_SUB + 2;
				}
			} else if ((i == 5 || i == 12)
					&& ((Iterables.get(house_view.get(i), 0).equals(""))
							&& (Iterables.get(house_view.get(i), 1).equals("")))) {
				if (Iterables.get(house_view.get(i), 2).contains(asc_csl_subs)) {
					ASC_SUB_SUB = ASC_SUB_SUB - 4;
				} else if (Iterables.get(house_view.get(i), 3).contains(asc_csl_subs)) {
					ASC_SUB_SUB = ASC_SUB_SUB - 2;
				}
			} else {
				int cnt = 4;
				for (int j = 0; j <= 3; j++) {
					String s = Iterables.get(house_view.get(i), j);

					if ((i == 6 || i == 11) && (s.contains(asc_csl_subs))) {
						ASC_SUB_SUB = ASC_SUB_SUB + cnt;
					} else if ((i == 5 || i == 12)
							&& (s.contains(asc_csl_subs))) {
						ASC_SUB_SUB = ASC_SUB_SUB - cnt;
					}
					cnt = cnt - 1;
				}
			}

		}

		System.out.println("1st CSL : " + asc_csl + retro.get(Iterables.get(planets.get(planets_id.get(asc_csl)), 0))
				+ retro.get(Iterables.get(planets.get(planets_id.get(asc_csl)), 6)) + " = " + ASC_SUB);
		System.out.println(asc_csl + " Star Lord : " + asc_csl_stars
				+ retro.get(Iterables.get(planets.get(planets_id.get(asc_csl_stars)), 0)) + " = " + ASC_SUB_STAR);
		System.out.println(asc_csl + " Sub Lord : " + asc_csl_subs
				+ retro.get(Iterables.get(planets.get(planets_id.get(asc_csl_subs)), 0)) + " = " + ASC_SUB_SUB);

		for (int i = 1; i <= 12; i++) {

			if ((i == 6 || i == 11)
					&& ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == "")
							&& (Iterables.get(house_view.get(i), 2) == ""
									&& (Iterables.get(house_view.get(i), 3).contains(dsc_csl_subs))))) {
				DSC_SUB_SUB = DSC_SUB_SUB - 4;
			} else if ((i == 5 || i == 12)
					&& ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == "")
							&& (Iterables.get(house_view.get(i), 2) == ""
									&& (Iterables.get(house_view.get(i), 3).contains(dsc_csl_subs))))) {
				DSC_SUB_SUB = DSC_SUB_SUB + 4;
			} else if ((i == 6 || i == 11)
					&& ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == ""))) {
				if (Iterables.get(house_view.get(i), 2).contains(dsc_csl_subs)) {
					DSC_SUB_SUB = DSC_SUB_SUB - 4;
				} else if (Iterables.get(house_view.get(i), 3).contains(dsc_csl_subs)) {
					DSC_SUB_SUB = DSC_SUB_SUB - 2;
				}
			} else if ((i == 5 || i == 12)
					&& ((Iterables.get(house_view.get(i), 0) == "") && (Iterables.get(house_view.get(i), 1) == ""))) {
				if (Iterables.get(house_view.get(i), 2).contains(dsc_csl_subs)) {
					DSC_SUB_SUB = DSC_SUB_SUB + 4;
				} else if (Iterables.get(house_view.get(i), 3).contains(dsc_csl_subs)) {
					DSC_SUB_SUB = DSC_SUB_SUB + 2;
				}
			} else {
				int cnt = 4;
				for (int j = 0; j <= 3; j++) {
					String s = Iterables.get(house_view.get(i), j);

					if ((i == 6 || i == 11) && (s.contains(dsc_csl_subs))) {
						DSC_SUB_SUB = DSC_SUB_SUB - cnt;
					} else if ((i == 5 || i == 12)
							&& (s.contains(dsc_csl_subs))) {
						DSC_SUB_SUB = DSC_SUB_SUB + cnt;
					}
					cnt = cnt - 1;
				}
			}

		}

		if (ASC_SUB_SUB == DSC_SUB_SUB) {
			sub_status = "(3) Sub Level : Neutral";
		} else if (asc_csl_subs.equals(dsc_csl_subs)) {
			if (ASC_SUB_SUB > 0) {
				sub_status = "(3) Sub Level : ASC by " + ASC_SUB_SUB;
				ASC = ASC + (ASC_SUB_SUB * 3);
			} else {
				sub_status = "(3) Sub Level : DSC by " + DSC_SUB_SUB;
				DSC = DSC + (DSC_SUB_SUB * 3);
			}
		} else if (ASC_SUB_SUB > DSC_SUB_SUB) {
			sub_status = "(3) Sub Level : ASC by " + (ASC_SUB_SUB - DSC_SUB_SUB);
			ASC = ASC + (ASC_SUB_SUB - DSC_SUB_SUB) * 3;
		} else {
			sub_status = "(3) Sub Level : DSC by " + (DSC_SUB_SUB - ASC_SUB_SUB);
			DSC = DSC + (DSC_SUB_SUB - ASC_SUB_SUB) * 3;
		}

		System.out.println("\n7th CSL : " + dsc_csl + retro.get(Iterables.get(planets.get(planets_id.get(dsc_csl)), 0))
				+ retro.get(Iterables.get(planets.get(planets_id.get(dsc_csl)), 6)) + " = " + DSC_SUB);
		System.out.println(dsc_csl + " Star Lord : " + dsc_csl_stars
				+ retro.get(Iterables.get(planets.get(planets_id.get(dsc_csl_stars)), 0)) + " = " + DSC_SUB_STAR);
		System.out.println(dsc_csl + " Sub Lord : " + dsc_csl_subs
				+ retro.get(Iterables.get(planets.get(planets_id.get(dsc_csl_subs)), 0)) + " = " + DSC_SUB_SUB + "\n");

		System.out.println(planet_status);
		System.out.println(star_status);
		System.out.println(sub_status);

		System.out.println("\nASC : " + ASC + "  ,  DSC : " + DSC + "\n");
		if (ASC == DSC) {
			System.out.println("************ BALANCED ************");
		} else if (ASC > DSC) {
			System.out.println("************ ASC by " + (ASC - DSC) + " ************");
		} else {
			System.out.println("************ DSC by " + (DSC - ASC) + " ************");
		}

		bar.setValue(40);
		Thread.sleep(1000);

//-------------------------------------------------------------------------------------------------------------------

		System.out.println(
				"\n\n\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");
		System.out.println("(Module 4)					Victory House Technique : ");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");

		float ASC_VHQ = 0;
		float DSC_VHQ = 0;

		int Mars = Iterables.get(planets.get(3), 3);
		int Mars_sign = Iterables.get(planets.get(3), 1);
		int Mars_star = Iterables.get(planets.get(3), 4);
		int MarsR = Iterables.get(planets.get(3), 0);
		if (Mars == 1 || Mars == 3 || Mars == 6 || Mars == 10 || Mars == 11) {
			ASC_VHQ = (float) (ASC_VHQ + 2.5);
			if (Mars_sign == 1 || Mars_sign == 8 || Mars_sign == 10) {
				ASC_VHQ = (float) (ASC_VHQ + 0.5);
			}
			if (Mars_star == 3) {
				ASC_VHQ = (float) (ASC_VHQ + 0.5);
			}
			if (MarsR == 1) {
				ASC_VHQ = (float) (ASC_VHQ + 0.5);
			}
		} else if (Mars == 7 || Mars == 9 || Mars == 12 || Mars == 4 || Mars == 5) {
			DSC_VHQ = (float) (DSC_VHQ + 2.5);
			if (Mars_sign == 1 || Mars_sign == 8 || Mars_sign == 10) {
				DSC_VHQ = (float) (DSC_VHQ + 0.5);
			}
			if (Mars_star == 3) {
				DSC_VHQ = (float) (DSC_VHQ + 0.5);
			}
			if (MarsR == 1) {
				DSC_VHQ = (float) (DSC_VHQ + 0.5);
			}
		}

		int Saturn = Iterables.get(planets.get(7), 3);
		int Saturn_sign = Iterables.get(planets.get(7), 1);
		int Saturn_star = Iterables.get(planets.get(7), 4);
		int SaturnR = Iterables.get(planets.get(7), 0);
		if (Saturn == 1 || Saturn == 3 || Saturn == 6 || Saturn == 10 || Saturn == 11) {
			ASC_VHQ = (float) (ASC_VHQ + 2.5);
			if (Saturn_sign == 10 || Saturn_sign == 11 || Saturn_sign == 7) {
				ASC_VHQ = (float) (ASC_VHQ + 0.5);
			}
			if (Saturn_star == 7) {
				ASC_VHQ = (float) (ASC_VHQ + 0.5);
			}
			if (SaturnR == 1) {
				ASC_VHQ = (float) (ASC_VHQ + 0.5);
			}
		} else if (Saturn == 7 || Saturn == 9 || Saturn == 12 || Saturn == 4 || Saturn == 5) {
			DSC_VHQ = (float) (DSC_VHQ + 2.5);
			if (Saturn_sign == 10 || Saturn_sign == 11 || Saturn_sign == 7) {
				DSC_VHQ = (float) (DSC_VHQ + 0.5);
			}
			if (Saturn_star == 7) {
				DSC_VHQ = (float) (DSC_VHQ + 0.5);
			}
			if (SaturnR == 1) {
				DSC_VHQ = (float) (DSC_VHQ + 0.5);
			}
		}

		int Sun = Iterables.get(planets.get(1), 3);
		int Sun_sign = Iterables.get(planets.get(1), 1);
		int Sun_star = Iterables.get(planets.get(1), 4);
		if (Sun == 1 || Sun == 3 || Sun == 6 || Sun == 10 || Sun == 11) {
			ASC_VHQ = (float) (ASC_VHQ + 2.5);
			if (Sun_sign == 5 || Sun_sign == 1) {
				ASC_VHQ = (float) (ASC_VHQ + 0.5);
			}
			if (Sun_star == 1) {
				ASC_VHQ = (float) (ASC_VHQ + 0.5);
			}
		} else if (Sun == 7 || Sun == 9 || Sun == 12 || Sun == 4 || Sun == 5) {
			DSC_VHQ = (float) (DSC_VHQ + 2.5);
			if (Sun_sign == 5 || Sun_sign == 1) {
				DSC_VHQ = (float) (DSC_VHQ + 0.5);
			}
			if (Sun_star == 1) {
				DSC_VHQ = (float) (DSC_VHQ + 0.5);
			}
		}

//					int Rahu = Iterables.get(planets.get(8), 3);
//					if(Rahu == 1 || Rahu == 3 || Rahu == 6 || Rahu == 10 || Rahu == 11) {
//						ASC_VHQ = (float) (ASC_VHQ + 2);
//					} else if(Rahu == 7 || Rahu == 9 || Rahu == 12 || Rahu == 4 || Rahu == 5) {
//						DSC_VHQ = (float) (DSC_VHQ + 2);
//					}

		int JupiterR = Iterables.get(planets.get(5), 0);
		int Jupiter = Iterables.get(planets.get(5), 3);
		int Jupiter_sign = Iterables.get(planets.get(5), 1);
		int Jupiter_star = Iterables.get(planets.get(5), 4);
		if ((JupiterR == 1 || Jupiter_sign == 4)
				&& (Jupiter == 1 || Jupiter == 3 || Jupiter == 6 || Jupiter == 10 || Jupiter == 11)) {
			ASC_VHQ = ASC_VHQ + 2;
			if (Jupiter_sign == 9 || Jupiter_sign == 12) {
				ASC_VHQ = (float) (ASC_VHQ + 0.5);
			}
			if (Jupiter_star == 5) {
				ASC_VHQ = (float) (ASC_VHQ + 0.5);
			}
			if (JupiterR == 1 && Jupiter_sign == 4) {
				ASC_VHQ = (float) (ASC_VHQ + 0.5);
			}
		} else if ((JupiterR == 1 || Jupiter_sign == 4)
				&& (Jupiter == 7 || Jupiter == 9 || Jupiter == 12 || Jupiter == 4 || Jupiter == 5)) {
			DSC_VHQ = DSC_VHQ + 2;
			if (Jupiter_sign == 9 || Jupiter_sign == 12) {
				DSC_VHQ = (float) (DSC_VHQ + 0.5);
			}
			if (Jupiter_star == 5) {
				DSC_VHQ = (float) (DSC_VHQ + 0.5);
			}
			if (JupiterR == 1 && Jupiter_sign == 4) {
				DSC_VHQ = (float) (DSC_VHQ + 0.5);
			}
		}

		int VenusR = Iterables.get(planets.get(6), 0);
		int Venus = Iterables.get(planets.get(6), 3);
		int Venus_sign = Iterables.get(planets.get(6), 1);
		int Venus_star = Iterables.get(planets.get(6), 4);
		if ((VenusR == 1 || Venus_sign == 12)
				&& (Venus == 1 || Venus == 3 || Venus == 6 || Venus == 10 || Venus == 11)) {
			ASC_VHQ = ASC_VHQ + 2;
			if (Venus_sign == 2 || Venus_sign == 7) {
				ASC_VHQ = (float) (ASC_VHQ + 0.5);
			}
			if (Venus_star == 6) {
				ASC_VHQ = (float) (ASC_VHQ + 0.5);
			}
			if (VenusR == 1 && Venus_sign == 12) {
				ASC_VHQ = (float) (ASC_VHQ + 0.5);
			}
		} else if ((VenusR == 1 || Venus_sign == 12)
				&& (Venus == 7 || Venus == 9 || Venus == 12 || Venus == 4 || Venus == 5)) {
			DSC_VHQ = DSC_VHQ + 2;
			if (Venus_sign == 2 || Venus_sign == 7) {
				DSC_VHQ = (float) (DSC_VHQ + 0.5);
			}
			if (Venus_star == 6) {
				DSC_VHQ = (float) (DSC_VHQ + 0.5);
			}
			if (VenusR == 1 && Venus_sign == 12) {
				DSC_VHQ = (float) (DSC_VHQ + 0.5);
			}
		}

		int MercuryR = Iterables.get(planets.get(4), 0);
		int Mercury = Iterables.get(planets.get(4), 3);
		int Mercury_sign = Iterables.get(planets.get(4), 1);
		int Mercury_star = Iterables.get(planets.get(4), 4);
		if ((MercuryR == 1 || Mercury_sign == 6)
				&& (Mercury == 1 || Mercury == 3 || Mercury == 6 || Mercury == 10 || Mercury == 11)) {
			ASC_VHQ = ASC_VHQ + 2;
			if (Mercury_sign == 3) {
				ASC_VHQ = (float) (ASC_VHQ + 0.5);
			}
			if (Mercury_star == 4) {
				ASC_VHQ = (float) (ASC_VHQ + 0.5);
			}
			if (MercuryR == 1 && Mercury_sign == 6) {
				ASC_VHQ = (float) (ASC_VHQ + 0.5);
			}
		} else if ((MercuryR == 1 || Mercury_sign == 6)
				&& (Mercury == 7 || Mercury == 9 || Mercury == 12 || Mercury == 4 || Mercury == 5)) {
			DSC_VHQ = DSC_VHQ + 2;
			if (Mercury_sign == 3) {
				DSC_VHQ = (float) (DSC_VHQ + 0.5);
			}
			if (Mercury_star == 4) {
				DSC_VHQ = (float) (DSC_VHQ + 0.5);
			}
			if (MercuryR == 1 && Mercury_sign == 6) {
				DSC_VHQ = (float) (DSC_VHQ + 0.5);
			}
		}

		System.out.println("Ascendant (ASC) : " + ASC_VHQ);
		System.out.println("Descendant (DSC) : " + DSC_VHQ + "\n");

		if (ASC_VHQ == DSC_VHQ) {
			System.out.println("Malefics are balanced");
		} else if (ASC_VHQ > DSC_VHQ) {
			System.out.println("Malefics are in favor of ASC by " + (ASC_VHQ - DSC_VHQ));
		} else {
			System.out.println("Malefics are in favor of DSC by " + (DSC_VHQ - ASC_VHQ));
		}

		bar.setValue(50);
		Thread.sleep(1000);

//-------------------------------------------------------------------------------------------------------------------

		System.out.println(
				"\n\n\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");
		System.out.println("(Module 5)					SKY / PKY : ");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");

		Venus = Iterables.get(planets.get(6), 3);
		Jupiter = Iterables.get(planets.get(5), 3);
		Mercury = Iterables.get(planets.get(4), 3);
		int Ketu = Iterables.get(planets.get(9), 3);
		int Rahu = Iterables.get(planets.get(8), 3);

		if ((Venus == 2 || Jupiter == 2 || Mercury == 2) && (Venus == 12 || Jupiter == 12 || Mercury == 12)) {
			System.out.println("Shubha Kartari Yoga forming on Ascendant (1st House)");
		}

		if ((Venus == 6 || Jupiter == 6 || Mercury == 6) && (Venus == 8 || Jupiter == 8 || Mercury == 8)) {
			System.out.println("Shubha Kartari Yoga forming on Descendant (7th House)");
		}

		if ((Saturn == 2 || Mars == 2 || Sun == 2) && (Saturn == 12 || Mars == 12 || Sun == 12)) {
			System.out.println("Paap Kartari Yoga forming on Ascendant (1st House)");
		} else if ((Saturn == 2 || Mars == 2 || Sun == 2) && (Ketu == 12 || Rahu == 12)) {
			System.out.println("Half PKY forming on Ascendant (1st House)");
		} else if ((Saturn == 12 || Mars == 12 || Sun == 12) && (Ketu == 2 || Rahu == 2)) {
			System.out.println("Half PKY forming on Ascendant (1st House)");
		}

		if ((Saturn == 6 || Mars == 6 || Sun == 6) && (Saturn == 8 || Mars == 8 || Sun == 8)) {
			System.out.println("Paap Kartari Yoga forming on Descendant (7th House)");
		} else if ((Saturn == 6 || Mars == 6 || Sun == 6) && (Ketu == 8 || Rahu == 8)) {
			System.out.println("Half PKY forming on Descendant (7th House)");
		} else if ((Saturn == 8 || Mars == 8 || Sun == 8) && (Ketu == 6 || Rahu == 6)) {
			System.out.println("Half PKY forming on Descendant (7th House)");
		}

		if ((Venus == 9 || Jupiter == 9 || Mercury == 9) && (Venus == 11 || Jupiter == 11 || Mercury == 11)) {
			System.out.println("[EXPERIMENTAL] : Shubha Kartari Yoga forming on MC (10th House)");
		}

		if ((Venus == 3 || Jupiter == 3 || Mercury == 3) && (Venus == 5 || Jupiter == 5 || Mercury == 5)) {
			System.out.println("[EXPERIMENTAL] : Shubha Kartari Yoga forming on IC (4th House)");
		}

		if ((Saturn == 9 || Mars == 9 || Sun == 9) && (Saturn == 11 || Mars == 11 || Sun == 11)) {
			System.out.println("[EXPERIMENTAL] : Paap Kartari Yoga forming on MC (10th House)");
		}

		if ((Saturn == 3 || Mars == 3 || Sun == 3) && (Saturn == 5 || Mars == 5 || Sun == 5)) {
			System.out.println("[EXPERIMENTAL] : Paap Kartari Yoga forming on IC (4th House)");
		}

		bar.setValue(60);
		Thread.sleep(1000);

//-------------------------------------------------------------------------------------------------------------------

		System.out.println(
				"\n\n\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");
		System.out.println("(Module 6)					Nakshatra Tara (Fixed Star) : ");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");

		for (int i = 1; i <= 12; i++) {
			int cusp_sign = Iterables.get(cusps.get(i), 0);
			int cusp_minutes = Iterables.get(cusps.get(i), 1);

			int regulus_orb = 360 - cusp_minutes;
			int pollux_orb = 1770 - cusp_minutes;
			int zuben_orb = 1260 - cusp_minutes;
			int bettelguese_orb = 300 - cusp_minutes;
			int spica_orb = 1795 - cusp_minutes;
			int denebola_orb = 1665 - cusp_minutes;
			int algol_orb = 120 - cusp_minutes;
			int antares_orb = 960 - cusp_minutes;
			int purva_orb = 1050 - cusp_minutes;
			int krittika_orb = 360 - cusp_minutes;

			if ((i == 1 || i == 7 || i == 10 || i == 4) && (cusp_sign == 5 && Math.abs(regulus_orb) <= 60)) {
				System.out.println(
						"\n***************************************************************************************************************************");
				System.out.println(i + " cusp is on The Regulus (6° Leo) within orb of " + (regulus_orb / 60) + "°"
						+ Math.abs(regulus_orb % 60) + "'");
				System.out.println(
						"***************************************************************************************************************************\n");
			}

			if ((i == 1 || i == 7 || i == 10 || i == 4) && (cusp_sign == 3 && Math.abs(pollux_orb) <= 60)) {
				System.out.println(
						"\n***************************************************************************************************************************");
				System.out.println(i + " cusp is on The Pollux (29° 30' Gemini) within orb of " + (pollux_orb / 60)
						+ "°" + Math.abs(pollux_orb % 60) + "'");
				System.out.println(
						"***************************************************************************************************************************\n");
			}

			if ((i == 1 || i == 7 || i == 10 || i == 4) && (cusp_sign == 7 && Math.abs(zuben_orb) <= 60)) {
				System.out.println(
						"\n***************************************************************************************************************************");
				System.out.println(i + " cusp is on The Zuben Elgenubi (21° Libra) within orb of " + (zuben_orb / 60)
						+ "°" + Math.abs(zuben_orb % 60) + "'");
				System.out.println(
						"***************************************************************************************************************************\n");
			}

			if ((i == 1 || i == 7 || i == 10 || i == 4) && (cusp_sign == 3 && Math.abs(bettelguese_orb) <= 60)) {
				System.out.println(
						"\n***************************************************************************************************************************");
				System.out.println(i + " cusp is on The Bettelguese (5° Gemini) within orb of " + (bettelguese_orb / 60)
						+ "°" + Math.abs(bettelguese_orb % 60) + "'");
				System.out.println(
						"***************************************************************************************************************************\n");
			}

			if ((i == 1 || i == 7 || i == 10 || i == 4) && (cusp_sign == 6 && Math.abs(spica_orb) <= 60)) {
				System.out.println(
						"\n***************************************************************************************************************************");
				System.out.println(i + " cusp is on The Spica (29° 55' Virgo) within orb of " + (spica_orb / 60) + "°"
						+ Math.abs(spica_orb % 60) + "'");
				System.out.println(
						"***************************************************************************************************************************\n");
			}

			if ((i == 1 || i == 7 || i == 10 || i == 4) && (cusp_sign == 5 && Math.abs(denebola_orb) <= 60)) {
				System.out.println(
						"\n***************************************************************************************************************************");
				System.out.println(i + " cusp is on The Denebola (27° 45' Leo) within orb of " + (denebola_orb / 60)
						+ "°" + Math.abs(denebola_orb % 60) + "'");
				System.out.println(
						"***************************************************************************************************************************\n");
			}

			if ((i == 1 || i == 7 || i == 10 || i == 4) && (cusp_sign == 2 && Math.abs(algol_orb) <= 60)) {
				System.out.println(i + " cusp is on The Algol (2° Taurus) within orb of " + (algol_orb / 60) + "°"
						+ Math.abs(algol_orb % 60) + "'");
			}

			if ((i == 1 || i == 7 || i == 10 || i == 4) && (cusp_sign == 8 && Math.abs(antares_orb) <= 60)) {
				System.out.println(i + " cusp is on The Antares (16° Scorpio) within orb of " + (antares_orb / 60) + "°"
						+ Math.abs(antares_orb % 60) + "'");
			}

			if ((i == 1 || i == 7 || i == 10 || i == 4) && (cusp_sign == 5 && Math.abs(purva_orb) <= 60)) {
				System.out.println(i + " cusp is on The Purva Phalguni (17° 30' Leo) within orb of " + (purva_orb / 60)
						+ "°" + Math.abs(purva_orb % 60) + "'");
			}

			if ((i == 1 || i == 7 || i == 10 || i == 4) && (cusp_sign == 2 && Math.abs(krittika_orb) <= 60)) {
				System.out.println(i + " cusp is on The Krittika (6° Taurus) within orb of " + (krittika_orb / 60) + "°"
						+ Math.abs(krittika_orb % 60) + "'");
			}

		}

		int l1 = planets_id.get(lords.get(whole_sign.get(1)));
		int l1_minutes = Iterables.get(planets.get(l1), 2);
		int l1_sign = Iterables.get(planets.get(l1), 1);

		int l7 = planets_id.get(lords.get(whole_sign.get(7)));
		int l7_minutes = Iterables.get(planets.get(l7), 2);
		int l7_sign = Iterables.get(planets.get(l7), 1);

		int l10 = planets_id.get(lords.get(whole_sign.get(10)));
		int l10_minutes = Iterables.get(planets.get(l10), 2);
		int l10_sign = Iterables.get(planets.get(l10), 1);

		int l4 = planets_id.get(lords.get(whole_sign.get(4)));
		int l4_minutes = Iterables.get(planets.get(l4), 2);
		int l4_sign = Iterables.get(planets.get(l4), 1);

		int l1_regulus = 360 - l1_minutes;
		int l1_pollux = 1770 - l1_minutes;
		int l1_zuben = 1260 - l1_minutes;
		int l1_bettelguese = 300 - l1_minutes;
		int l1_spica = 1795 - l1_minutes;

		int l7_regulus = 360 - l7_minutes;
		int l7_pollux = 1770 - l7_minutes;
		int l7_zuben = 1260 - l7_minutes;
		int l7_bettelguese = 300 - l7_minutes;
		int l7_spica = 1795 - l7_minutes;

		int l10_regulus = 360 - l10_minutes;
		int l10_pollux = 1770 - l10_minutes;
		int l10_zuben = 1260 - l10_minutes;
		int l10_bettelguese = 300 - l10_minutes;
		int l10_spica = 1795 - l10_minutes;

		int l4_regulus = 360 - l4_minutes;
		int l4_pollux = 1770 - l4_minutes;
		int l4_zuben = 1260 - l4_minutes;
		int l4_bettelguese = 300 - l4_minutes;
		int l4_spica = 1795 - l4_minutes;

		System.out.println("\n\nLord's Conjuction with Nakshatra Taras : ");
		System.out.println("========================================\n");
		if (l1_sign == 5 && Math.abs(l1_regulus) <= 60) {
			System.out.println("L1 " + planets_id.inverse().get(l1) + " conjuct regulus within orb of "
					+ (l1_regulus / 60) + "°" + Math.abs(l1_regulus % 60) + "'");
		}
		if (l7_sign == 5 && Math.abs(l7_regulus) <= 60) {
			System.out.println("L7 " + planets_id.inverse().get(l7) + " conjuct regulus within orb of "
					+ (l7_regulus / 60) + "°" + Math.abs(l7_regulus % 60) + "'");
		}
		if (l10_sign == 5 && Math.abs(l10_regulus) <= 60) {
			System.out.println("L10 " + planets_id.inverse().get(l10) + " conjuct regulus within orb of "
					+ (l10_regulus / 60) + "°" + Math.abs(l10_regulus % 60) + "'");
		}
		if (l4_sign == 5 && Math.abs(l4_regulus) <= 60) {
			System.out.println("L4 " + planets_id.inverse().get(l4) + " conjuct regulus within orb of "
					+ (l4_regulus / 60) + "°" + Math.abs(l4_regulus % 60) + "'");
		}

		if (l1_sign == 3 && Math.abs(l1_pollux) <= 60) {
			System.out.println("L1 " + planets_id.inverse().get(l1) + " conjuct pollux within orb of "
					+ (l1_pollux / 60) + "°" + Math.abs(l1_pollux % 60) + "'");
		}
		if (l7_sign == 3 && Math.abs(l7_pollux) <= 60) {
			System.out.println("L7 " + planets_id.inverse().get(l7) + " conjuct pollux within orb of "
					+ (l7_pollux / 60) + "°" + Math.abs(l7_pollux % 60) + "'");
		}
		if (l10_sign == 3 && Math.abs(l10_pollux) <= 60) {
			System.out.println("L10 " + planets_id.inverse().get(l10) + " conjuct pollux within orb of "
					+ (l10_pollux / 60) + "°" + Math.abs(l10_pollux % 60) + "'");
		}
		if (l4_sign == 3 && Math.abs(l4_pollux) <= 60) {
			System.out.println("L4 " + planets_id.inverse().get(l4) + " conjuct pollux within orb of "
					+ (l4_pollux / 60) + "°" + Math.abs(l4_pollux % 60) + "'");
		}

		if (l1_sign == 7 && Math.abs(l1_zuben) <= 60) {
			System.out.println("L1 " + planets_id.inverse().get(l1) + " conjuct zuben elgenubi within orb of "
					+ (l1_zuben / 60) + "°" + Math.abs(l1_zuben % 60) + "'");
		}
		if (l7_sign == 7 && Math.abs(l7_zuben) <= 60) {
			System.out.println("L7 " + planets_id.inverse().get(l7) + " conjuct zuben elgenubi within orb of "
					+ (l7_zuben / 60) + "°" + Math.abs(l7_zuben % 60) + "'");
		}
		if (l10_sign == 7 && Math.abs(l10_zuben) <= 60) {
			System.out.println("L10 " + planets_id.inverse().get(l10) + " conjuct zuben elgenubi within orb of "
					+ (l10_zuben / 60) + "°" + Math.abs(l10_zuben % 60) + "'");
		}
		if (l4_sign == 7 && Math.abs(l4_zuben) <= 60) {
			System.out.println("L4 " + planets_id.inverse().get(l4) + " conjuct zuben elgenubi within orb of "
					+ (l4_zuben / 60) + "°" + Math.abs(l4_zuben % 60) + "'");
		}

		if (l1_sign == 3 && Math.abs(l1_bettelguese) <= 60) {
			System.out.println("L1 " + planets_id.inverse().get(l1) + " conjuct bettelguese within orb of "
					+ (l1_bettelguese / 60) + "°" + Math.abs(l1_bettelguese % 60) + "'");
		}
		if (l7_sign == 3 && Math.abs(l7_bettelguese) <= 60) {
			System.out.println("L7 " + planets_id.inverse().get(l7) + " conjuct bettelguese within orb of "
					+ (l7_bettelguese / 60) + "°" + Math.abs(l7_bettelguese % 60) + "'");
		}
		if (l10_sign == 3 && Math.abs(l10_bettelguese) <= 60) {
			System.out.println("L10 " + planets_id.inverse().get(l10) + " conjuct bettelguese within orb of "
					+ (l10_bettelguese / 60) + "°" + Math.abs(l10_bettelguese % 60) + "'");
		}
		if (l4_sign == 3 && Math.abs(l4_bettelguese) <= 60) {
			System.out.println("L4 " + planets_id.inverse().get(l4) + " conjuct bettelguese within orb of "
					+ (l4_bettelguese / 60) + "°" + Math.abs(l4_bettelguese % 60) + "'");
		}

		if (l1_sign == 6 && Math.abs(l1_spica) <= 60) {
			System.out.println("L1 " + planets_id.inverse().get(l1) + " conjuct spica within orb of " + (l1_spica / 60)
					+ "°" + Math.abs(l1_spica % 60) + "'");
		}
		if (l7_sign == 6 && Math.abs(l7_spica) <= 60) {
			System.out.println("L7 " + planets_id.inverse().get(l7) + " conjuct spica within orb of " + (l7_spica / 60)
					+ "°" + Math.abs(l7_spica % 60) + "'");
		}
		if (l10_sign == 6 && Math.abs(l10_spica) <= 60) {
			System.out.println("L10 " + planets_id.inverse().get(l10) + " conjuct spica within orb of "
					+ (l10_spica / 60) + "°" + Math.abs(l10_spica % 60) + "'");
		}
		if (l4_sign == 6 && Math.abs(l4_spica) <= 60) {
			System.out.println("L4 " + planets_id.inverse().get(l4) + " conjuct spica within orb of " + (l4_spica / 60)
					+ "°" + Math.abs(l4_spica % 60) + "'");
		}

		bar.setValue(70);
		Thread.sleep(1000);

//-------------------------------------------------------------------------------------------------------------------

		System.out.println(
				"\n\n\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");
		System.out.println("(Module 7)					Part of Fortune & Moon Aspects : ");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");

		System.out.println("Part of Fortune (POF) : " + pof_minutes / 60 + "°" + pof_minutes % 60 + "' "
				+ signs_id.inverse().get(pof_sign) + "\n");

		int MOON_minutes = Iterables.get(planets.get(2), 2);
		int MOON_houseno = Iterables.get(planets.get(2), 3);

		int POF_minutes = Iterables.get(planets.get(14), 2);
		int POF_houseno = Iterables.get(planets.get(14), 3);
		int POF_orb = POF_minutes - MOON_minutes;

		int L1 = planets_id.get(lords.get(whole_sign.get(1)));
		int L1_minutes = Iterables.get(planets.get(L1), 2);
		int L1_houseno = Iterables.get(planets.get(L1), 3);
		int L1_orb = L1_minutes - MOON_minutes;
		int L1_poforb = POF_minutes - L1_minutes;

		int L7 = planets_id.get(lords.get(whole_sign.get(7)));
		int L7_minutes = Iterables.get(planets.get(L7), 2);
		int L7_houseno = Iterables.get(planets.get(L7), 3);
		int L7_orb = L7_minutes - MOON_minutes;
		int L7_poforb = POF_minutes - L7_minutes;

		int L10 = planets_id.get(lords.get(whole_sign.get(10)));
		int L10_minutes = Iterables.get(planets.get(L10), 2);
		int L10_houseno = Iterables.get(planets.get(L10), 3);
		int L10_orb = L10_minutes - MOON_minutes;
		int L10_poforb = POF_minutes - L10_minutes;

		int L4 = planets_id.get(lords.get(whole_sign.get(4)));
		int L4_minutes = Iterables.get(planets.get(L4), 2);
		int L4_houseno = Iterables.get(planets.get(L4), 3);
		int L4_orb = L4_minutes - MOON_minutes;
		int L4_poforb = POF_minutes - L4_minutes;

		if (MOON_minutes < L1_minutes || MOON_minutes > L1_minutes && L1 != 2) {
			if (Math.abs(L1_orb) <= 180) {
				if (Math.abs(L1_houseno - MOON_houseno) == 2) {
					System.out.println("Moon is applying to sextile L1 " + planets_id.inverse().get(L1)
							+ " within orb of " + (L1_orb / 60) + "°" + Math.abs(L1_orb % 60) + "'");
				} else if (Math.abs(L1_houseno - MOON_houseno) == 3) {
					System.out.println("Moon is applying to square L1 " + planets_id.inverse().get(L1)
							+ " within orb of " + (L1_orb / 60) + "°" + Math.abs(L1_orb % 60) + "'");
				} else if (Math.abs(L1_houseno - MOON_houseno) == 6) {
					System.out.println("Moon is applying to oppose L1 " + planets_id.inverse().get(L1)
							+ " within orb of " + (L1_orb / 60) + "°" + Math.abs(L1_orb % 60) + "'");
				} else if (Math.abs(L1_houseno - MOON_houseno) == 0) {
					System.out.println("Moon is applying to conjuct L1 " + planets_id.inverse().get(L1)
							+ " within orb of " + (L1_orb / 60) + "°" + Math.abs(L1_orb % 60) + "'");
				}
			}
		}

		if (MOON_minutes < L7_minutes || MOON_minutes > L7_minutes && L7 != 2) {
			if (Math.abs(L7_orb) <= 180) {
				if (Math.abs(L7_houseno - MOON_houseno) == 2) {
					System.out.println("Moon is applying to sextile L7 " + planets_id.inverse().get(L7)
							+ " within orb of " + (L7_orb / 60) + "°" + Math.abs(L7_orb % 60) + "'");
				} else if (Math.abs(L7_houseno - MOON_houseno) == 3) {
					System.out.println("Moon is applying to square L7 " + planets_id.inverse().get(L7)
							+ " within orb of " + (L7_orb / 60) + "°" + Math.abs(L7_orb % 60) + "'");
				} else if (Math.abs(L7_houseno - MOON_houseno) == 6) {
					System.out.println("Moon is applying to oppose L7 " + planets_id.inverse().get(L7)
							+ " within orb of " + (L7_orb / 60) + "°" + Math.abs(L7_orb % 60) + "'");
				} else if (Math.abs(L7_houseno - MOON_houseno) == 0) {
					System.out.println("Moon is applying to conjuct L7 " + planets_id.inverse().get(L7)
							+ " within orb of " + (L7_orb / 60) + "°" + Math.abs(L7_orb % 60) + "'");
				}
			}
		}

		if (MOON_minutes < L10_minutes || MOON_minutes > L10_minutes && L10 != 2) {
			if (Math.abs(L10_orb) <= 180) {
				if (Math.abs(L10_houseno - MOON_houseno) == 2) {
					System.out.println("Moon is applying to sextile L10 " + planets_id.inverse().get(L10)
							+ " within orb of " + (L10_orb / 60) + "°" + Math.abs(L10_orb % 60) + "'");
				} else if (Math.abs(L10_houseno - MOON_houseno) == 3) {
					System.out.println("Moon is applying to square L10 " + planets_id.inverse().get(L10)
							+ " within orb of " + (L10_orb / 60) + "°" + Math.abs(L10_orb % 60) + "'");
				} else if (Math.abs(L10_houseno - MOON_houseno) == 6) {
					System.out.println("Moon is applying to oppose L10 " + planets_id.inverse().get(L10)
							+ " within orb of " + (L10_orb / 60) + "°" + Math.abs(L10_orb % 60) + "'");
				} else if (Math.abs(L10_houseno - MOON_houseno) == 0) {
					System.out.println("Moon is applying to conjuct L10 " + planets_id.inverse().get(L10)
							+ " within orb of " + (L10_orb / 60) + "°" + Math.abs(L10_orb % 60) + "'");
				}
			}
		}

		if (MOON_minutes < L4_minutes || MOON_minutes > L4_minutes && L4 != 2) {
			if (Math.abs(L4_orb) <= 180) {
				if (Math.abs(L4_houseno - MOON_houseno) == 2) {
					System.out.println("Moon is applying to sextile L4 " + planets_id.inverse().get(L4)
							+ " within orb of " + (L4_orb / 60) + "°" + Math.abs(L4_orb % 60) + "'");
				} else if (Math.abs(L4_houseno - MOON_houseno) == 3) {
					System.out.println("Moon is applying to square L4 " + planets_id.inverse().get(L4)
							+ " within orb of " + (L4_orb / 60) + "°" + Math.abs(L4_orb % 60) + "'");
				} else if (Math.abs(L4_houseno - MOON_houseno) == 6) {
					System.out.println("Moon is applying to oppose L4 " + planets_id.inverse().get(L4)
							+ " within orb of " + (L4_orb / 60) + "°" + Math.abs(L4_orb % 60) + "'");
				} else if (Math.abs(L4_houseno - MOON_houseno) == 0) {
					System.out.println("Moon is applying to conjuct L4 " + planets_id.inverse().get(L4)
							+ " within orb of " + (L4_orb / 60) + "°" + Math.abs(L4_orb % 60) + "'");
				}
			}
		}

		if (MOON_minutes < POF_minutes || MOON_minutes > POF_minutes) {
			if (Math.abs(POF_orb) <= 180) {
				if (Math.abs(POF_houseno - MOON_houseno) == 2) {
					System.out.println("Moon is applying to sextile POF within orb of " + (POF_orb / 60) + "°"
							+ Math.abs(POF_orb % 60) + "'");
				} else if (Math.abs(POF_houseno - MOON_houseno) == 3) {
					System.out.println("Moon is applying to square POF within orb of " + (POF_orb / 60) + "°"
							+ Math.abs(POF_orb % 60) + "'");
				} else if (Math.abs(POF_houseno - MOON_houseno) == 6) {
					System.out.println("Moon is applying to oppose POF within orb of " + (POF_orb / 60) + "°"
							+ Math.abs(POF_orb % 60) + "'");
				} else if (Math.abs(POF_houseno - MOON_houseno) == 0) {
					System.out.println("Moon is applying to conjuct POF within orb of " + (POF_orb / 60) + "°"
							+ Math.abs(POF_orb % 60) + "'");
				}
			}
		}

		if (L1_minutes < POF_minutes || L1_minutes > POF_minutes) {
			if (Math.abs(L1_poforb) <= 180) {
				if (Math.abs(POF_houseno - L1_houseno) == 6) {
					System.out
							.println("L1 " + planets_id.inverse().get(L1) + " is applying to oppose POF within orb of "
									+ (L1_poforb / 60) + "°" + Math.abs(L1_poforb % 60) + "'");
				} else if (Math.abs(POF_houseno - L1_houseno) == 0) {
					System.out
							.println("L1 " + planets_id.inverse().get(L1) + " is applying to conjuct POF within orb of "
									+ (L1_poforb / 60) + "°" + Math.abs(L1_poforb % 60) + "'");
				}
			}
		}

		if (L7_minutes < POF_minutes || L7_minutes > POF_minutes) {
			if (Math.abs(L7_poforb) <= 180) {
				if (Math.abs(POF_houseno - L7_houseno) == 6) {
					System.out
							.println("L7 " + planets_id.inverse().get(L7) + " is applying to oppose POF within orb of "
									+ (L7_poforb / 60) + "°" + Math.abs(L7_poforb % 60) + "'");
				} else if (Math.abs(POF_houseno - L7_houseno) == 0) {
					System.out
							.println("L7 " + planets_id.inverse().get(L7) + " is applying to conjuct POF within orb of "
									+ (L7_poforb / 60) + "°" + Math.abs(L7_poforb % 60) + "'");
				}
			}
		}

		if (L10_minutes < POF_minutes || L10_minutes > POF_minutes) {
			if (Math.abs(L10_poforb) <= 180) {
				if (Math.abs(POF_houseno - L10_houseno) == 6) {
					System.out.println(
							"L10 " + planets_id.inverse().get(L10) + " is applying to oppose POF within orb of "
									+ (L10_poforb / 60) + "°" + Math.abs(L10_poforb % 60) + "'");
				} else if (Math.abs(POF_houseno - L10_houseno) == 0) {
					System.out.println(
							"L10 " + planets_id.inverse().get(L10) + " is applying to conjuct POF within orb of "
									+ (L10_poforb / 60) + "°" + Math.abs(L10_poforb % 60) + "'");
				}
			}
		}

		if (L4_minutes < POF_minutes || L4_minutes > POF_minutes) {
			if (Math.abs(L4_poforb) <= 180) {
				if (Math.abs(POF_houseno - L4_houseno) == 6) {
					System.out
							.println("L4 " + planets_id.inverse().get(L4) + " is applying to oppose POF within orb of "
									+ (L4_poforb / 60) + "°" + Math.abs(L4_poforb % 60) + "'");
				} else if (Math.abs(POF_houseno - L4_houseno) == 0) {
					System.out
							.println("L4 " + planets_id.inverse().get(L4) + " is applying to conjuct POF within orb of "
									+ (L4_poforb / 60) + "°" + Math.abs(L4_poforb % 60) + "'");
				}
			}
		}
		bar.setValue(80);
		Thread.sleep(1000);
//-------------------------------------------------------------------------------------------------------------------

		System.out.println(
				"\n\n\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");
		System.out.println("(Module 8)					Good and Bad Conjuctions : ");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");

		int ketu_houseno = Iterables.get(planets.get(9), 3);
		int ketu_minutes = Iterables.get(planets.get(9), 2);

		int upaketu_houseno = Iterables.get(planets.get(13), 3);
		int upaketu_minutes = Iterables.get(planets.get(13), 2);

		int neptune_houseno = Iterables.get(planets.get(11), 3);
		int neptune_minutes = Iterables.get(planets.get(11), 2);

		int pluto_houseno = Iterables.get(planets.get(12), 3);
		int pluto_minutes = Iterables.get(planets.get(12), 2);

		int sunny_houseno = Iterables.get(planets.get(1), 3);
		int sunny_minutes = Iterables.get(planets.get(1), 2);

		int rahu_houseno = Iterables.get(planets.get(8), 3);
		int rahu_minutes = Iterables.get(planets.get(8), 2);

		int uranus_houseno = Iterables.get(planets.get(10), 3);
		int uranus_minutes = Iterables.get(planets.get(10), 2);

		int L1_ketorb = L1_minutes - ketu_minutes;
		int L1_ukorb = L1_minutes - upaketu_minutes;
		int L1_neporb = L1_minutes - neptune_minutes;
		int L1_pluorb = L1_minutes - pluto_minutes;
		int L1_sunorb = L1_minutes - sunny_minutes;
		int L1_rahuorb = L1_minutes - rahu_minutes;
		int L1_uraorb = L1_minutes - uranus_minutes;

		int L7_ketorb = L7_minutes - ketu_minutes;
		int L7_ukorb = L7_minutes - upaketu_minutes;
		int L7_neporb = L7_minutes - neptune_minutes;
		int L7_pluorb = L7_minutes - pluto_minutes;
		int L7_sunorb = L7_minutes - sunny_minutes;
		int L7_rahuorb = L7_minutes - rahu_minutes;
		int L7_uraorb = L7_minutes - uranus_minutes;

		int L10_ketorb = L10_minutes - ketu_minutes;
		int L10_ukorb = L10_minutes - upaketu_minutes;
		int L10_neporb = L10_minutes - neptune_minutes;
		int L10_pluorb = L10_minutes - pluto_minutes;
		int L10_sunorb = L10_minutes - sunny_minutes;
		int L10_rahuorb = L10_minutes - rahu_minutes;
		int L10_uraorb = L10_minutes - uranus_minutes;

		int L4_ketorb = L4_minutes - ketu_minutes;
		int L4_ukorb = L4_minutes - upaketu_minutes;
		int L4_neporb = L4_minutes - neptune_minutes;
		int L4_pluorb = L4_minutes - pluto_minutes;
		int L4_sunorb = L4_minutes - sunny_minutes;
		int L4_rahuorb = L4_minutes - rahu_minutes;
		int L4_uraorb = L4_minutes - uranus_minutes;

		int POF_ketorb = POF_minutes - ketu_minutes;
		int POF_ukorb = POF_minutes - upaketu_minutes;
		int POF_neporb = POF_minutes - neptune_minutes;
		int POF_pluorb = POF_minutes - pluto_minutes;
		int POF_sunorb = POF_minutes - sunny_minutes;
		int POF_rahuorb = POF_minutes - rahu_minutes;
		int POF_uraorb = POF_minutes - uranus_minutes;

		if (Math.abs(L1_ketorb) <= 120 && ketu_houseno == L1_houseno) {
			System.out.println("L1 " + planets_id.inverse().get(L1) + " conjuct ketu within orb of " + (L1_ketorb / 60)
					+ "°" + Math.abs(L1_ketorb % 60) + "'");
		}
		if (Math.abs(L1_ukorb) <= 120 && upaketu_houseno == L1_houseno) {
			System.out.println("L1 " + planets_id.inverse().get(L1) + " conjuct upaketu within orb of "
					+ (L1_ukorb / 60) + "°" + Math.abs(L1_ukorb % 60) + "'");
		}
		if (Math.abs(L1_neporb) <= 120 && neptune_houseno == L1_houseno) {
			System.out.println("L1 " + planets_id.inverse().get(L1) + " conjuct neptune within orb of "
					+ (L1_neporb / 60) + "°" + Math.abs(L1_neporb % 60) + "'");
		}
		if (Math.abs(L1_pluorb) <= 120 && pluto_houseno == L1_houseno) {
			System.out.println("L1 " + planets_id.inverse().get(L1) + " conjuct pluto within orb of " + (L1_pluorb / 60)
					+ "°" + Math.abs(L1_pluorb % 60) + "'");
		}
		if (Math.abs(L1_sunorb) <= 120 && sunny_houseno == L1_houseno && L1 != 1) {
			System.out.println("L1 " + planets_id.inverse().get(L1) + " is combusted by sun within orb of "
					+ (L1_sunorb / 60) + "°" + Math.abs(L1_sunorb % 60) + "'");
		}
		if (Math.abs(L1_rahuorb) <= 120 && rahu_houseno == L1_houseno) {
			System.out.println("L1 " + planets_id.inverse().get(L1) + " conjuct rahu within orb of " + (L1_rahuorb / 60)
					+ "°" + Math.abs(L1_rahuorb % 60) + "'");
		}
		if (Math.abs(L1_uraorb) <= 120 && uranus_houseno == L1_houseno) {
			System.out.println("L1 " + planets_id.inverse().get(L1) + " conjuct uranus within orb of "
					+ (L1_uraorb / 60) + "°" + Math.abs(L1_uraorb % 60) + "'");
		}

		if (Math.abs(L7_ketorb) <= 120 && ketu_houseno == L7_houseno) {
			System.out.println("L7 " + planets_id.inverse().get(L7) + " conjuct ketu within orb of " + (L7_ketorb / 60)
					+ "°" + Math.abs(L7_ketorb % 60) + "'");
		}
		if (Math.abs(L7_ukorb) <= 120 && upaketu_houseno == L7_houseno) {
			System.out.println("L7 " + planets_id.inverse().get(L7) + " conjuct upaketu within orb of "
					+ (L7_ukorb / 60) + "°" + Math.abs(L7_ukorb % 60) + "'");
		}
		if (Math.abs(L7_neporb) <= 120 && neptune_houseno == L7_houseno) {
			System.out.println("L7 " + planets_id.inverse().get(L7) + " conjuct neptune within orb of "
					+ (L7_neporb / 60) + "°" + Math.abs(L7_neporb % 60) + "'");
		}
		if (Math.abs(L7_pluorb) <= 120 && pluto_houseno == L7_houseno) {
			System.out.println("L7 " + planets_id.inverse().get(L7) + " conjuct pluto within orb of " + (L7_pluorb / 60)
					+ "°" + Math.abs(L7_pluorb % 60) + "'");
		}
		if (Math.abs(L7_sunorb) <= 120 && sunny_houseno == L7_houseno && L7 != 1) {
			System.out.println("L7 " + planets_id.inverse().get(L7) + " is combusted by sun within orb of "
					+ (L7_sunorb / 60) + "°" + Math.abs(L7_sunorb % 60) + "'");
		}
		if (Math.abs(L7_rahuorb) <= 120 && rahu_houseno == L7_houseno) {
			System.out.println("L7 " + planets_id.inverse().get(L7) + " conjuct rahu within orb of " + (L7_rahuorb / 60)
					+ "°" + Math.abs(L7_rahuorb % 60) + "'");
		}
		if (Math.abs(L7_uraorb) <= 120 && uranus_houseno == L7_houseno) {
			System.out.println("L7 " + planets_id.inverse().get(L7) + " conjuct uranus within orb of "
					+ (L7_uraorb / 60) + "°" + Math.abs(L7_uraorb % 60) + "'");
		}

		if (Math.abs(L10_ketorb) <= 120 && ketu_houseno == L10_houseno) {
			System.out.println("L10 " + planets_id.inverse().get(L10) + " conjuct ketu within orb of "
					+ (L10_ketorb / 60) + "°" + Math.abs(L10_ketorb % 60) + "'");
		}
		if (Math.abs(L10_ukorb) <= 120 && upaketu_houseno == L10_houseno) {
			System.out.println("L10 " + planets_id.inverse().get(L10) + " conjuct upaketu within orb of "
					+ (L10_ukorb / 60) + "°" + Math.abs(L10_ukorb % 60) + "'");
		}
		if (Math.abs(L10_neporb) <= 120 && neptune_houseno == L10_houseno) {
			System.out.println("L10 " + planets_id.inverse().get(L10) + " conjuct neptune within orb of "
					+ (L10_neporb / 60) + "°" + Math.abs(L10_neporb % 60) + "'");
		}
		if (Math.abs(L10_pluorb) <= 120 && pluto_houseno == L10_houseno) {
			System.out.println("L10 " + planets_id.inverse().get(L10) + " conjuct pluto within orb of "
					+ (L10_pluorb / 60) + "°" + Math.abs(L10_pluorb % 60) + "'");
		}
		if (Math.abs(L10_sunorb) <= 120 && sunny_houseno == L10_houseno && L10 != 1) {
			System.out.println("L10 " + planets_id.inverse().get(L10) + " is combusted by sun within orb of "
					+ (L10_sunorb / 60) + "°" + Math.abs(L10_sunorb % 60) + "'");
		}
		if (Math.abs(L10_rahuorb) <= 120 && rahu_houseno == L10_houseno) {
			System.out.println("L10 " + planets_id.inverse().get(L10) + " conjuct rahu within orb of "
					+ (L10_rahuorb / 60) + "°" + Math.abs(L10_rahuorb % 60) + "'");
		}
		if (Math.abs(L10_uraorb) <= 120 && uranus_houseno == L10_houseno) {
			System.out.println("L10 " + planets_id.inverse().get(L10) + " conjuct uranus within orb of "
					+ (L10_uraorb / 60) + "°" + Math.abs(L10_uraorb % 60) + "'");
		}

		if (Math.abs(L4_ketorb) <= 120 && ketu_houseno == L4_houseno) {
			System.out.println("L4 " + planets_id.inverse().get(L4) + " conjuct ketu within orb of " + (L4_ketorb / 60)
					+ "°" + Math.abs(L4_ketorb % 60) + "'");
		}
		if (Math.abs(L4_ukorb) <= 120 && upaketu_houseno == L4_houseno) {
			System.out.println("L4 " + planets_id.inverse().get(L4) + " conjuct upaketu within orb of "
					+ (L4_ukorb / 60) + "°" + Math.abs(L4_ukorb % 60) + "'");
		}
		if (Math.abs(L4_neporb) <= 120 && neptune_houseno == L4_houseno) {
			System.out.println("L4 " + planets_id.inverse().get(L4) + " conjuct neptune within orb of "
					+ (L4_neporb / 60) + "°" + Math.abs(L4_neporb % 60) + "'");
		}
		if (Math.abs(L4_pluorb) <= 120 && pluto_houseno == L4_houseno) {
			System.out.println("L4 " + planets_id.inverse().get(L4) + " conjuct pluto within orb of " + (L4_pluorb / 60)
					+ "°" + Math.abs(L4_pluorb % 60) + "'");
		}
		if (Math.abs(L4_sunorb) <= 120 && sunny_houseno == L4_houseno && L4 != 1) {
			System.out.println("L4 " + planets_id.inverse().get(L4) + " is combusted by sun within orb of "
					+ (L4_sunorb / 60) + "°" + Math.abs(L4_sunorb % 60) + "'");
		}
		if (Math.abs(L4_rahuorb) <= 120 && rahu_houseno == L4_houseno) {
			System.out.println("L4 " + planets_id.inverse().get(L4) + " conjuct rahu within orb of " + (L4_rahuorb / 60)
					+ "°" + Math.abs(L4_rahuorb % 60) + "'");
		}
		if (Math.abs(L4_uraorb) <= 120 && uranus_houseno == L4_houseno) {
			System.out.println("L4 " + planets_id.inverse().get(L4) + " conjuct uranus within orb of "
					+ (L4_uraorb / 60) + "°" + Math.abs(L4_uraorb % 60) + "'");
		}

		if (Math.abs(POF_ketorb) <= 120 && ketu_houseno == POF_houseno) {
			System.out.println(
					"POF conjuct ketu within orb of " + (POF_ketorb / 60) + "°" + Math.abs(POF_ketorb % 60) + "'");
		}
		if (Math.abs(POF_ukorb) <= 120 && upaketu_houseno == POF_houseno) {
			System.out.println(
					"POF conjuct upaketu within orb of " + (POF_ukorb / 60) + "°" + Math.abs(POF_ukorb % 60) + "'");
		}
		if (Math.abs(POF_neporb) <= 120 && neptune_houseno == POF_houseno) {
			System.out.println(
					"POF conjuct neptune within orb of " + (POF_neporb / 60) + "°" + Math.abs(POF_neporb % 60) + "'");
		}
		if (Math.abs(POF_pluorb) <= 120 && pluto_houseno == POF_houseno) {
			System.out.println(
					"POF conjuct pluto within orb of " + (POF_pluorb / 60) + "°" + Math.abs(POF_pluorb % 60) + "'");
		}
		if (Math.abs(POF_sunorb) <= 120 && sunny_houseno == POF_houseno) {
			System.out.println("POF is combusted by sun within orb of " + (POF_sunorb / 60) + "°"
					+ Math.abs(POF_sunorb % 60) + "'");
		}
		if (Math.abs(POF_rahuorb) <= 120 && rahu_houseno == POF_houseno) {
			System.out.println(
					"POF conjuct rahu within orb of " + (POF_rahuorb / 60) + "°" + Math.abs(POF_rahuorb % 60) + "'");
		}
		if (Math.abs(POF_uraorb) <= 120 && uranus_houseno == POF_houseno) {
			System.out.println(
					"POF conjuct uranus within orb of " + (POF_uraorb / 60) + "°" + Math.abs(POF_uraorb % 60) + "'");
		}
		bar.setValue(90);
		Thread.sleep(1000);
		
//--------------------------------------------------------------------------------------------------------------------
				
				System.out.println(
						"\n\n\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
								+ "");
				System.out.println("(Module 9)					Planetary War : ");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
								+ "");
				
				int pw1 = L1_minutes - L7_minutes;
				int pw2 = L10_minutes - L4_minutes;
				
				System.out.println("Tip :- The planet with lower magnitude (more brighter) and higher declination (more higher) wins the war");
				
				if(Math.abs(pw1) <= 60 && L1_houseno == L7_houseno) {
					System.out.println("\nThere is planetary war between L1 "+planets_id.inverse().get(L1)+" and L7 "+planets_id.inverse().get(L7));
				}
				
				if(Math.abs(pw2) <= 60 && L4_houseno == L10_houseno) {
					System.out.println("\nThere is planetary war between L4 "+planets_id.inverse().get(L4)+" and L10 "+planets_id.inverse().get(L10));
				}
				
//--------------------------------------------------------------------------------------------------------------------

		System.out.println(
				"\n\n\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");
		System.out.println("(Module 10)					Star Lord Reversal (SLR) : ");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");

		int L1_starlord = Iterables.get(planets.get(L1), 4);
		int L1_starlord_position = Iterables.get(planets.get(L1_starlord), 3);

		int L7_starlord = Iterables.get(planets.get(L7), 4);
		int L7_starlord_position = Iterables.get(planets.get(L7_starlord), 3);

		boolean flag = true;

		if (L1_starlord == L1) {
			System.out.println("\nASC Lord is in self star, Hence Star Lord Reversal is not applicable in this chart");
		}
		if (L7_starlord == L7) {
			System.out.println("\nDSC Lord is in self star, Hence Star Lord Reversal is not applicable in this chart");
		}
		if (L1_starlord_position == 1) {
			System.out.println("\nASC Lord's Star Lord " + planets_id.inverse().get(L1_starlord)
					+ " is sitting in ASC itself, Hence Star Lord Reversal is not applicable in this chart");
		}
		if (L7_starlord_position == 7) {
			System.out.println("\nDSC Lord's Star Lord " + planets_id.inverse().get(L7_starlord)
					+ " is sitting in DSC itself, Hence Star Lord Reversal is not applicable in this chart");
		}
		if (L1_starlord == L7) {
			System.out
					.println("\nASC Lord is in star of DSC Lord, Hence Star Lord Reversal is applicable in this chart");
			flag = false;
		}
		if (L7_starlord == L1) {
			System.out
					.println("\nDSC Lord is in star of ASC Lord, Hence Star Lord Reversal is applicable in this chart");
			flag = false;
		}
		if (L1_starlord_position == 7) {
			System.out.println("\nASC Lord's Star Lord " + planets_id.inverse().get(L1_starlord)
					+ " is sitting in DSC, Hence Star Lord Reversal is applicable in this chart");
			flag = false;
		}
		if (L7_starlord_position == 1) {
			System.out.println("\nDSC Lord's Star Lord " + planets_id.inverse().get(L7_starlord)
					+ " is sitting in ASC, Hence Star Lord Reversal is applicable in this chart");
			flag = false;
		}
		if(L1 == 1 && L1_starlord == 8) {
			System.out.println("L1 Sun in nakshatra of Rahu who owns Aquarius results into Star Lord Reversal");
			flag = false;
		}
		if(L7 == 1 && L7_starlord == 8) {
			System.out.println("L7 Sun in nakshatra of Rahu who owns Aquarius results into Star Lord Reversal");
			flag = false;
		}
		if (flag) {
			System.out.println("\n[FINAL] : Star Lord Reversal is not applicable in this chart");
		}
		if (L1_houseno == 1 && L7_houseno == 7) {
			System.out.println("\n[EXPERIMENTAL] : ASC Lord " + planets_id.inverse().get(L1) + " and DSC Lord "
					+ planets_id.inverse().get(L7) + " are in Ascendant and Descendant respectively");
		}
		if (L1_houseno == 7 && L7_houseno == 1) {
			System.out.println("\n[EXPERIMENTAL] : ASC Lord " + planets_id.inverse().get(L1)
					+ " is in DSC and DSC Lord " + planets_id.inverse().get(L7) + " is in ASC");
		}
		
		System.out.println(
				"\n\n\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("												THE END");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "");
		
		System.out.println("All Planets Signification Value");
		System.out.println("================================");
		System.out.println("Sun =>  " + Muhurata.Calculate_Muhurata_ASC(house_view, "Sun"));
		System.out.println("Moon =>  " + Muhurata.Calculate_Muhurata_ASC(house_view, "Moon"));
		System.out.println("Mars =>  " + Muhurata.Calculate_Muhurata_ASC(house_view, "Mars"));
		System.out.println("Mercury =>  " + Muhurata.Calculate_Muhurata_ASC(house_view, "Mercury"));
		System.out.println("Venus =>  " + Muhurata.Calculate_Muhurata_ASC(house_view, "Venus"));
		System.out.println("Jupiter =>  " + Muhurata.Calculate_Muhurata_ASC(house_view, "Jupiter"));
		System.out.println("Saturn =>  " + Muhurata.Calculate_Muhurata_ASC(house_view, "Saturn"));
		System.out.println("Rahu =>  " + Muhurata.Calculate_Muhurata_ASC(house_view, "Rahu"));
		System.out.println("Ketu =>  " + Muhurata.Calculate_Muhurata_ASC(house_view, "Ketu"));
		
		bar.setValue(100);
		Thread.sleep(1000);
//		bar.setString("Send Email [ Y / N ] : ");
		
//------------------------------------------------------------------------------------------------------------------
//					bar.setString("Generating PDF....");
		// Code to generate PDF
//					Document document = new Document(PageSize.A4);
//					File f = new File("C://Users//yadav//Desktop//Gambler's Dharma//PDF//" + name + ".pdf");
//					try
//		            {
//						PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(f));
//						document.open();
//						document.add(new Paragraph("Gambler's Dharma - Sport's Prediction Using Vedic Astrology Automated Report"));
//		               //Text file Data
//		               try (BufferedReader br = new BufferedReader(new FileReader("C://Users//yadav//Desktop//Gambler's Dharma//" + name + ".txt"))) {
//		                      
//		                      String sCurrentLine;
//
//		                      while ((sCurrentLine = br.readLine()) != null) {
//		                          document.add(new Paragraph(sCurrentLine));
//		                      }
//
//		                      } catch (IOException exception) {
//		                      exception.printStackTrace();
//		                  }
//		               
//		               document.close();
//		               writer.close();
//		               
//		            } catch (DocumentException exception)
//		            {
//		               exception.printStackTrace();
//		            } catch (FileNotFoundException exception)
//		            {
//		               exception.printStackTrace();
//		            } 

//--------------------------------------------------------------------------------------------------------------------

		driver.quit();

		System.setOut(console);
		System.out.println(
				"\n\n\n\n\n\n\n\nYour Sport's Prediction Using Vedic & Western Astrology - Gambler's Dharma + Frawley's Testimonies Report has been generated successfully. Thank You for your patience");

		//System.out.print("\n\nDo you want to send out this report via Email [ Y / N ] : ");
//		String input = "Disabled";
		
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(frame1.email.getText());
		
		if(matcher.matches()) {
//			System.out.print("\nEnter Email-ID for receiving this report : ");
			String email_id = frame1.email.getText();
			bar.setString("Sending Email");
			
//--------------------------------------------------------------------------------------------------------------------

			System.out.println("\n\nSending ASTRO-SPORTS-REPORT via Email to " + email_id + ".........");

			try {
				Email email = new Email("udit01061998@gmail.com", "bhajjiterminator");
				email.setFrom("astro-sports@gmail.com", "ASTRO-SPORTS");
				email.setSubject(name + "  (" + date + "-" + month + "-" + year + ")");
				// Create the message part
				BodyPart messageBodyPart = new MimeBodyPart();

				// Now set the actual message
				messageBodyPart.setText("Please find attached ASTRO-SPORTS-REPORT based on Gambler's Dharma and Frawley's for  :  \n\n" + "Name  :  " + name
						+ "\n\n" + "Date  :  " + date + "-" + month + "-" + year + "\n\n" + "Time  :  " + hour + ":"
						+ minute + ":" + second + "\n\n" + "Stadium  :  " + sname
						+ "\n\n" + "Timezone : " + timezone + "\n\n\n\nTHANK YOU !!   BEST OF LUCK");

				// Create a multipar message
				Multipart multipart = new MimeMultipart();

				// Set text message part
				multipart.addBodyPart(messageBodyPart);

				// Part two is attachment
				messageBodyPart = new MimeBodyPart();
				String filename = "C:\\Users\\yadav\\Desktop\\Gambler's Dharma\\" + name + ".txt";
				DataSource source = new FileDataSource(filename);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(name + "-" + date + "-" + month + "-" + year + ".txt");
				multipart.addBodyPart(messageBodyPart);

				// Send the complete message parts
				email.setContent(multipart, "");
				// email.setContent("<h1>This is to inform you that this is just a testing
				// mail</h1>", "text/html");
				email.addRecipient(email_id);
				email.send();
			} catch (Exception e) {
				System.out
						.println("\nHuh huh  <(-_-)>  Something got really messed up in Email Sending Mechanism....\n");
				System.out.println(e);
			}

			bar.setString("Email Sent");
			Thread.sleep(1000);
			bar.setString("BYE! :)");
			frame.setVisible(false);
			frame.dispose();

		} else if(frame1.email.getText().equals("OPTIONAL")) {
			bar.setString("BYE! :)");
			Thread.sleep(1000);
			frame.setVisible(false);
			frame.dispose();
		} else {
			System.out.println("\n\n************ INVALID EMAIL ADDRESS ENTERED. PLEASE ENTER VALID EMAIL ADDRESS ************\n\n");
			bar.setString("BYE! :)");
			Thread.sleep(1000);
			frame.setVisible(false);
			frame.dispose();
		}

		System.out.println("\n\n\nThis program is going to exit now..........");
		Thread.sleep(1000);
		System.out.println("\nTHANK YOU !!  BEST OF LUCK");

//	} else {
//		System.out.println("License Check : License Expired or Invalid License Key");
//		System.out.println("\nPlease contact license support to renew your license. This program is going to exit now.");
//		Thread.sleep(25000);
//	}
//--------------------------------------------------------------------------------------------------------------------

	} // end of main()

} // end of class