import java.io.*;
import java.util.Properties;

public class DiaDiaProperties {
	
	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		prop.setProperty("cfu_iniziali", "20");
		prop.setProperty("peso_max_borsa", "10");
		prop.store(new FileWriter("diadia.properties"), "Configurazione del gioco DIADIA");
	}
}