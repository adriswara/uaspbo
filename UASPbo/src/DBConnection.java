import java.sql.*;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class DBConnection { // class DBConnection. bertanggung jawab untuk mengatur koneksi ke database.
    public String url;  // variabel String digunakan untuk menyimpan url yang diperlukan untuk koneksi ke database,
    public String username; // variabel String digunakan untuk menyimpan username yang diperlukan untuk koneksi ke database,
    public String password; // variabel String digunakan untuk menyimpan password yang diperlukan untuk koneksi ke database,
    
    static Connection conn; // conn adalah variabel Connection yang akan digunakan untuk menyimpan objek koneksi ke database.
    static Statement stmt; // stmt adalah variabel Statement yang akan digunakan untuk mengeksekusi pernyataan SQL pada database.
    static ResultSet rs; // rs adalah variabel ResultSet yang akan digunakan untuk menyimpan hasil query dari database.
    
    static InputStreamReader InputStreamReader = new InputStreamReader(System.in); // digunakan untuk membaca karakter dari System.in.
    static BufferedReader input = new BufferedReader(InputStreamReader); // digunakan untuk membaca baris input dari InputStreamReader.

    public DBConnection(String url, String username, String password) { // konstruktor
        this.url = url; // inisialisasi var url dengan isi yang ada dalam parameter url
        this.username = username; // inisialisasi var username dengan isi yang ada dalam parameter username
        this.password = password; // inisialisasi var password dengan isi yang ada dalam parameter password
                
    }
    
    
    public void displayData(){ // fungsi untuk membaca/mendisplay data yang ada di database
        try{
            Class.forName("com.mysql.jdbc.Driver"); // digunakan untuk memuat kelas driver JDBC MySQL.
            Connection conn = DriverManager.getConnection(url, username, password);
            // digunakan untuk membuat koneksi ke database dengan menggunakan nilai dari variabel url, username, dan password.
            Statement stmt = conn.createStatement();
            // digunakan untuk membuat objek Statement yang akan digunakan untuk mengeksekusi pernyataan SQL.
            String query = "SELECT * FROM todo";
            // var query bertipe data string untuk menyimpan query SQL yang akan dieksekusi.
            ResultSet rs = stmt.executeQuery(query);
            // digunakan untuk menjalankan query SQL dan menyimpan hasilnya dalam objek ResultSet rs.
            
            while (rs.next()) { // untuk menglooping setiap baris hasil query. 
                int id = rs.getInt("id"); // var id di inisialisasi dengan nilai dari rs.getInt("id")
                String todo = rs.getString("todo"); // var nama_barang di inisialisasi dengan nilai dari rs.getString("nama_barang")
                String kategori = rs.getString("kategori"); // var harga di inisialisasi dengan nilai dari rs.getInt("harga")
                String tanggalselesai = rs.getString("tanggalselesai"); // var ukuran di inisialisasi dengan nilai dari rs.getString("ukuran")
                String status = rs.getString("status"); // var ukuran di inisialisasi dengan nilai dari rs.getString("ukuran")

                
                // sebuah label yang menampilkan data dari database
                System.out.println("Data Barang \n==============\n" // sebuah label
                                    + "id = " + id + "\n"  // menampilkan data id
                                    + "todo  = " + todo + "\n" // menampilkan data nama barang
                                    + "kategori = " + kategori + "\n"  // menampilkan data harga
                                    + "tanggalselesai = " + tanggalselesai +"\n"   // // menampilkan data ukuran
                                    + "status = " + status);  // // menampilkan data ukuran
                
               
        } 
             rs.close(); // menutup aliran/stream objek rs
            stmt.close(); // menutup aliran/stream objek stmt
            conn.close(); // menutup aliran/stream objek conn
        } catch(ClassNotFoundException e){ // menangkap dan menangani exception yang mungkin terjadi selama eksekusi di try
             System.out.println("Failed to load JDBC driver"); // menampilkan pesan errornya
            e.printStackTrace(); // digunakan untuk mencetak trace exception ke output standar.
        } catch (SQLException e) { // menangkap dan menangani exception yang mungkin terjadi selama eksekusi di try
            System.out.println("Database connection error");
            e.printStackTrace(); // digunakan untuk mencetak trace exception ke output standar.
        } catch(NullPointerException e){ // menangkap dan menangani exception yang mungkin terjadi selama eksekusi di try
            e.printStackTrace(); // digunakan untuk mencetak trace exception ke output standar.
        }
            
        
    }
    public void insertData(){ // fungsi untuk menambahkan data ke dalam database
        try {
            conn = DriverManager.getConnection(url, username, password); 
            // digunakan untuk membuat koneksi ke database dengan menggunakan nilai dari variabel url, username, dan password
            stmt = conn.createStatement();
            // digunakan untuk membuat objek Statement yang akan digunakan untuk mengeksekusi pernyataan SQL.
            
        // ambil input dari user
        System.out.print("Todo Task: "); // sebuah label
        String todo = input.readLine().trim(); // membaca inputan dari keyboard
        System.out.print("kategori: "); // sebuah label
        String kategori = input.readLine().trim(); // membaca inputan dari keyboard
        System.out.print("tanggalselesai: "); // sebuah label
        String tanggalselesai = input.readLine().trim(); // membaca inputan dari keyboard
        System.out.print("status: "); // sebuah label
        String status = input.readLine().trim(); // membaca inputan dari keyboard
        
        // query simpan
        String sql = "INSERT INTO todo (todo, kategori, tanggalselesai,status) VALUES('"+todo+"', '"+kategori+"','"+tanggalselesai+"','"+status+"')";
        // var sql bertipe data string untuk menyimpan query SQL yang akan dieksekusi.
        sql = String.format(sql, todo, kategori, tanggalselesai,status);
        
        // simpan barang
        stmt.execute(sql); // digunakan untuk menjalankan pernyataan SQL yang menyisipkan data baru ke dalam tabel.
        
    } catch (Exception e) { // menangkap dan menangani exception yang mungkin terjadi selama eksekusi di try
        e.printStackTrace(); // digunakan untuk mencetak trace exception ke output standar.
    }
    }
    public void updateData(){ // fungsi untuk mengubah data dalam database
        try {  
            conn = DriverManager.getConnection(url, username, password); 
            // digunakan untuk membuat koneksi ke database dengan menggunakan nilai dari variabel url, username, dan password
            stmt = conn.createStatement();
            // digunakan untuk membuat objek Statement yang akan digunakan untuk mengeksekusi pernyataan SQL.
            
        // ambil input dari user
          System.out.print("pilih ID yang mau diedit: "); // sebuah label
        int idTodo = Integer.parseInt(input.readLine()); // membaca inputan dari keyboard
        System.out.print("Todo Task: "); // sebuah label
        String todo = input.readLine().trim(); // membaca inputan dari keyboard
        System.out.print("kategori: "); // sebuah label
        String kategori = input.readLine().trim(); // membaca inputan dari keyboard
        System.out.print("tanggalselesai: "); // sebuah label
        String tanggalselesai = input.readLine().trim(); // membaca inputan dari keyboard
        System.out.print("status: "); // sebuah label
        String status = input.readLine().trim(); // membaca inputan dari keyboard

        // query update
        String sql = "UPDATE todo SET todo='"+todo+"', kategori='"+kategori+"', tanggalselesai='"+tanggalselesai+"', status='"+status+"' WHERE id='"+idTodo+"'";
        // var sql bertipe data string untuk menyimpan query SQL yang akan dieksekusi.
        
        
        stmt.execute(sql); // digunakan untuk menjalankan pernyataan SQL yang menyisipkan data baru ke dalam tabel.
        
    } catch (Exception e) { // menangkap dan menangani exception yang mungkin terjadi selama eksekusi di try
        e.printStackTrace(); // digunakan untuk mencetak trace exception ke output standar.
    }
    }
    public void deleteData(){ // fungsi untuk menghapus data dalam database
        try {
        conn = DriverManager.getConnection(url, username, password);
        // digunakan untuk membuat koneksi ke database dengan menggunakan nilai dari variabel url, username, dan password
        stmt = conn.createStatement();
        // digunakan untuk membuat objek Statement yang akan digunakan untuk mengeksekusi pernyataan SQL.
        
        // ambil input dari user
        System.out.print("pilih ID yang mau dihapus: "); // sebuah label
        int idBarang = Integer.parseInt(input.readLine()); // membaca inputan dari keyboard 
        
        // buat query hapus
        String sql = String.format("DELETE FROM todo WHERE id=%d", idBarang);
        // var sql bertipe data string untuk menyimpan query SQL yang akan dieksekusi.
        
        // hapus data
        stmt.execute(sql); // digunakan untuk menjalankan pernyataan SQL yang menyisipkan data baru ke dalam tabel.
        
        System.out.println("Data task terhapus..."); // sebuah label
    } catch (Exception e) { // menangkap dan menangani exception yang mungkin terjadi selama eksekusi di try
        e.printStackTrace(); // digunakan untuk mencetak trace exception ke output standar.
    }
    }
}
