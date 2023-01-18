import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    private ArrayList<String> list; 
    public Handler() {
        list = new ArrayList<String>(); 
    }
    @Override
    public String handleRequest(URI url) {
        if(url.getPath().equals("/add")) {
            String[] parameters = url.getQuery().split("="); 
            if(parameters[0].equals("s")) {
                list.add(parameters[1]);
                return "Added " + parameters[1]; 
            }
        }
        else if(url.getPath().equals("/search")) {
            String[] parameters = url.getQuery().split("="); 
            if(parameters[0].equals("s")) { 
                String result = ""; 
                for(String s : list) {
                    if(s.contains(parameters[1])) {
                        result += s + " "; 
                    }
                }
                return result; 
            }
            
        }
        return "404 Not Found";
    }
}

public class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}