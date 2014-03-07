package PA1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;


public class Server {

    
    private static final int PORT = 4119;

    
    private static HashSet<String> names = new HashSet<String>();
    private static HashSet<String> lastnames = new HashSet<String>();

    
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

    
    public static void main(String[] args) throws Exception {
        System.out.println("The server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

   
    private static class Handler extends Thread {
        private String name;
        private String pass;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

       
        public Handler(Socket socket) {
            this.socket = socket;
        }

        
        public void run() {
            try {

                
                in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                
                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    out.println("PASSWORD");
                    pass = in.readLine();
                    if (name == null) {
                        return;
                    }
                    else if (name.equals("Columbia") && pass.equals("116bway")){
                    	synchronized (names) {
                            if (!names.contains(name)) {
                                names.add(name);
                           }
                        }
                        synchronized (lastnames) {
                            if (!lastnames.contains(name)) {
                                lastnames.add(name);
                                break;
                            }
                        }
                    } 
                    else if (name.equals("SEAS") && pass.equals("summerisover")){
                    	synchronized (names) {
                            if (!names.contains(name)) {
                                names.add(name);
                           }
                        }
                        synchronized (lastnames) {
                            if (!lastnames.contains(name)) {
                                lastnames.add(name);
                                break;
                            }
                        }
                    } 
                    else if (name.equals("csee4119") && pass.equals("lotsofexams")){
                    	synchronized (names) {
                            if (!names.contains(name)) {
                                names.add(name);
                           }
                        }
                        synchronized (lastnames) {
                            if (!lastnames.contains(name)) {
                                lastnames.add(name);
                                break;
                            }
                        }
                    } 
                    else if (name.equals("foobar") && pass.equals("passpass")){
                    	synchronized (names) {
                            if (!names.contains(name)) {
                                names.add(name);
                           }
                        }
                        synchronized (lastnames) {
                            if (!lastnames.contains(name)) {
                                lastnames.add(name);
                                break;
                            }
                        }
                    } 
                    else if (name.equals("windows") && pass.equals("withglass")){
                    	synchronized (names) {
                            if (!names.contains(name)) {
                                names.add(name);
                           }
                        }
                        synchronized (lastnames) {
                            if (!lastnames.contains(name)) {
                                lastnames.add(name);
                                break;
                            }
                        }
                    } 
                    else if (name.equals("Google") && pass.equals("hasglasses")){
                    	synchronized (names) {
                            if (!names.contains(name)) {
                                names.add(name);
                           }
                        }
                        synchronized (lastnames) {
                            if (!lastnames.contains(name)) {
                                lastnames.add(name);
                                break;
                            }
                        }
                    } 
                    else if (name.equals("facebook") && pass.equals("wastingtime")){
                    	synchronized (names) {
                            if (!names.contains(name)) {
                                names.add(name);
                           }
                        }
                        synchronized (lastnames) {
                            if (!lastnames.contains(name)) {
                                lastnames.add(name);
                                break;
                            }
                        }
                    } 
                    else if (name.equals("wikipedia") && pass.equals("donation")){
                    	synchronized (names) {
                            if (!names.contains(name)) {
                                names.add(name);
                           }
                        }
                        synchronized (lastnames) {
                            if (!lastnames.contains(name)) {
                                lastnames.add(name);
                                break;
                            }
                        }
                    } 
                    else if (name.equals("network") && pass.equals("seemsez")){
                    	synchronized (names) {
                            if (!names.contains(name)) {
                                names.add(name);
                           }
                        }
                        synchronized (lastnames) {
                            if (!lastnames.contains(name)) {
                                lastnames.add(name);
                                break;
                            }
                        }
                    } 
                    
                    
                    
                    else{socket.close();
                    
                    }
                    
                }

               
                out.println("NAMEACCEPTED");
                writers.add(out);
                
                for (PrintWriter writer : writers) {
                    writer.println("MESSAGE " + "Server" + ": " + name + " has logged in! Welcome to simple server!");
                }

                
                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
                    else if (input.equals("whoelse")){
                    for (PrintWriter writer : writers) {
                    	String namess = names.toString();
                        writer.println("MESSAGE " + "Server to " + name + ": Server tells names (connnected right now): " + namess);}
                    }
                    else if (input.equals("wholasthr")){
                        for (PrintWriter writer : writers) {
                        	String lastnamess = lastnames.toString();
                            writer.println("MESSAGE " +"Server to " + name + ": Server tells names (last hour): " + lastnamess );}
                    }
                    else if (input.equals("broadcast")){
                        for (PrintWriter writer : writers) {
                            writer.println("MESSAGE " + "Server to "+ name + ": All messages are under broadcast every time! " );}
                    }
                    else {
                        for (PrintWriter writer : writers) {
                            writer.println("MESSAGE " + "ERROR! Server does not understand this command. Change another one!" );}
                    }
                    
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
               
                if (name != null) {
                    names.remove(name);
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}