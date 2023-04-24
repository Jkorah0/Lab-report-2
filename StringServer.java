import java.io.IOException;
import java.net.URI;
import java.util.*;

class Handler implements URLHandler {
    List<String> a = new ArrayList<>();
    String rs = "";
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            rs = "";
            for(String x : a){
                rs += x;
                rs += "\n";
            }
            return rs;
        }
        else if(url.getPath().contains("/add")){
            System.out.println("Path: " + url.getPath());
            String[] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")){
                rs = ""; 
                a.add(parameters[1]);
                for(String x : a){
                    rs += x;
                    rs += "\n";
                }
                return rs;
            }
            else{ return "404 not found";}

        }
    else if(url.getPath().equals("/clear")){
        rs =  "";
        a.removeAll(a);
        return "Words cleared.";
    }
    else{
        return "404 not found";
    }
}
}
class StringServer{
    public static void main(String[] args) throws IOException{
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}