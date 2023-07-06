import java.sql.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * 
 * @author Adriswara
 * @function Connection Declare
 */
public class DBConnection {  
    public String url;  
    public String username;  
    public String password;  
    
    static Connection conn;  
    static Statement stmt;  
    static ResultSet rs;  
    
    static InputStreamReader InputStreamReader = new InputStreamReader(System.in); 
    static BufferedReader input = new BufferedReader(InputStreamReader);  

    public DBConnection(String url, String username, String password) { 
        this.url = url;  
        this.username = username;  
        this.password = password; 
                
    }
    
    /**
     * @function read
     */
    public void displayData(){ 
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             String query = "SELECT * FROM todo";
             ResultSet rs = stmt.executeQuery(query);
             
            while (rs.next()) {  
                int id = rs.getInt("id"); 
                String todo = rs.getString("todo"); 
                String kategori = rs.getString("kategori");  
                String tanggalselesai = rs.getString("tanggalselesai"); 
                String status = rs.getString("status");  

                
                 System.out.println("Data Barang \n==============\n"  
                                    + "id = " + id + "\n"   
                                    + "todo  = " + todo + "\n"  
                                    + "kategori = " + kategori + "\n"  
                                    + "tanggalselesai = " + tanggalselesai +"\n"   
                                    + "status = " + status);  
                
               
        } 
             rs.close();  
            stmt.close();  
            conn.close(); 
        } catch(ClassNotFoundException e){  
             System.out.println("Failed to load JDBC driver");  
            e.printStackTrace();  
        } catch (SQLException e) { 
            System.out.println("Database connection error");
            e.printStackTrace();  
        } catch(NullPointerException e){  
            e.printStackTrace();  
        }
            
        
    }
    
    /**
     * @function insert
     */
    public void insertData(){  
        try {
            conn = DriverManager.getConnection(url, username, password); 
             stmt = conn.createStatement();
             
         System.out.print("Todo Task: ");  
        String todo = input.readLine().trim();  
        System.out.print("kategori: ");  
        String kategori = input.readLine().trim();  
        System.out.print("tanggalselesai: "); 
        String tanggalselesai = input.readLine().trim(); 
        System.out.print("status: "); 
        String status = input.readLine().trim();  
        
         String sql = "INSERT INTO todo (todo, kategori, tanggalselesai,status) VALUES('"+todo+"', '"+kategori+"','"+tanggalselesai+"','"+status+"')";
         sql = String.format(sql, todo, kategori, tanggalselesai,status);
        
         stmt.execute(sql);  
        
    } catch (Exception e) { 
        e.printStackTrace(); 
    }
    }
    
    /**
     * @function update
     */
    public void updateData(){ 
        try {  
            conn = DriverManager.getConnection(url, username, password); 
             stmt = conn.createStatement();
             
           System.out.print("pilih ID yang mau diedit: ");  
        int idTodo = Integer.parseInt(input.readLine()); 
        System.out.print("Todo Task: ");  
        String todo = input.readLine().trim();  
        System.out.print("kategori: ");  
        String kategori = input.readLine().trim();  
        System.out.print("tanggalselesai: "); 
        String tanggalselesai = input.readLine().trim();  
        System.out.print("status: ");  
        String status = input.readLine().trim();  

         String sql = "UPDATE todo SET todo='"+todo+"', kategori='"+kategori+"', tanggalselesai='"+tanggalselesai+"', status='"+status+"' WHERE id='"+idTodo+"'";
         
        
        stmt.execute(sql);  
        
    } catch (Exception e) {  
        e.printStackTrace();  
    }
    }
    /**'
     * @function delete
     */
    public void deleteData(){  
        try {
        conn = DriverManager.getConnection(url, username, password);
         stmt = conn.createStatement();
         
         System.out.print("pilih ID yang mau dihapus: ");  
        int idBarang = Integer.parseInt(input.readLine());  
        
         String sql = String.format("DELETE FROM todo WHERE id=%d", idBarang);
         
         stmt.execute(sql);  
        
        System.out.println("Data task terhapus...");  
    } catch (Exception e) {  
        e.printStackTrace();  
    }
    }
}
