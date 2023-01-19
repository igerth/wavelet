import java.io.IOException;
import java.net.URI;
import java.util.*;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;
    ArrayList<String> names = new ArrayList<String>();

    public String handleRequest(URI url) {
      if (url.getPath().equals("/")) {
          return String.format("Primative Search Engine");
      } else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                names.add(parameters[1]);
                return String.format("The words you have added", parameters[1]);
            }
            return "404 Not Found!";
      } else {
        if (url.getPath().contains("/search")) {
          String[] search = url.getQuery().split("=");
          if (search[0].equals("s")) {
              for(int i = 0; i < names.size(); i++){
                if ((names.get(i)).contains(search[1])) {
                  return String.format(names.get(i));
                }

              }
              
          }
        }
        return "404 Not Found!";
      }
          
    }
  

    
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
