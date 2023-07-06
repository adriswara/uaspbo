import java.io.InputStreamReader;
import java.io.BufferedReader; 

import java.io.IOException; 

/**
 * @author Adriswara
 * @npm 41155055220088
 * @function main
 */


public class Tester {

        static InputStreamReader InputStreamReader = new InputStreamReader(System.in); 
       
        static BufferedReader input = new BufferedReader(InputStreamReader); 

    public static void main(String[] args) throws IOException{ 
        String url = "jdbc:mysql://localhost/uaspbo"; 
        String username = "root"; 
        String password = ""; 
        
        
        DBConnection koneksiDB = new DBConnection(url, username, password);
       
        boolean loop = true;
        do { 
        System.out.println("Menu Program");  
        System.out.println("=============");
        System.out.println("1. Insert activity");
        System.out.println("2. Show data todo list");
        System.out.println("3. Edit activity");
        System.out.println("4. Delete activity"); 
        System.out.println("5. Exit");
        System.out.print("masukkan pilihan : "); 
        
        
        
            try {
            int pilih = Integer.parseInt(input.readLine());  
            
            switch(pilih){ 
                case 1: {
                    koneksiDB.insertData(); 
                    break;
                }
                case 2:{
                    koneksiDB.displayData();
                    break;
                }   
                case 3: {
                    koneksiDB.updateData(); 
                    break;
                }
                case 4: {
                    koneksiDB.deleteData(); 
                    break;
                }
                case 5: { 
                    loop = false;
                    System.exit(0);
                    break;
                }
                default:
                    System.out.println("Menu Tidak valid!"); 
            }
            
        } catch (IOException | NumberFormatException e) {
            
        }
    } while(loop);
        }
        
    
}
