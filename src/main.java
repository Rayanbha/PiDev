import models.user;
import services.UserService;

// Press Shift twice to open the Search Everywhere dialog and type show whitespaces,
// then press Enter. You can now see whitespace characters in your code.
public class main {
    public static void main(String[] args) {
        user u= new user("Rayan","Hadj","sefg",4572,"rgdsgsg");
        user u1= new user("Hmed","eez din","sefg",4522852,"rgdsgsg");
        user u2= new user("Lotif","papi","sefg",2852,"rgdsgsg");

        user newuser= new user();
        
        UserService userserv = new UserService();
        String name="labib";
       /* userserv.add(u1);
        userserv.add(u2);
        userserv.add(u);

        userserv.delete(u2);

        userserv.update(u,name);*/

        userserv.read();



    }
}


