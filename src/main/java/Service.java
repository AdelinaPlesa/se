import java.util.*;

@Service
public class Service{
	
	public void resolveDir(String path) {
		try (Stream<Path> paths = Files.walk(Paths.get(path))) {
		    paths
		        .filter(Files::isRegularFile)
		        .map(file -> getContentOfTheFile(file))
		        .map(content -> executeExeCommand(content).getProcessOutput);
		} 
	}
	
	private String getContentOfTheFile(File file)) {
	
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		  
		  String st;
		  while ((st = br.readLine()) != null)
		    sb.append(st);
		  }
	
	    return sb.toString();
	}
	
	
	private Process executeExeCommand(String content) {
		Process process = new ProcessBuilder("C:\\Users\\Adelina\\AppData\\Roaming\\Python\\Python37\\Scripts","-d"+ " " +content).start();

		return process;
	}
	
	private String getProcessOutput(Process process) {
		BufferedReader stdInput = new BufferedReader(new 
			     InputStreamReader(process.getInputStream()));
		
		return stdInput.readLine();
	}
}