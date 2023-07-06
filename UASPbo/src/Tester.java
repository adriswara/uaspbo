import java.sql.*; // mengimport kelas-kelas yang terkait dengan JDBC untuk berinteraksi dengan database. 
import java.io.InputStreamReader; //InputStreamReader digunakan untuk mengkonversi byte input menjadi karakter.
import java.io.BufferedReader; // digunakan untuk membaca teks dengan lebih efisien, 
// karena ia melakukan pembacaan sekelompok karakter (buffer) sekaligus daripada karakter per karakter.
import java.io.IOException; //  I/O exceptions digunakan untuk menangani situasi yang tidak terduga atau 
// error yang terjadi saat membaca atau menulis data ke sumber daya input/output, seperti berkas, socket, atau input stream.

public class Tester {

        static InputStreamReader InputStreamReader = new InputStreamReader(System.in); // mendeklarasikan objek, InputStreamReader 
        // untuk membaca input dari pengguna melalui konsol.
        static BufferedReader input = new BufferedReader(InputStreamReader); // mendeklarasikan objek, BufferedReader, 
        // untuk membaca input dari pengguna melalui konsol.

    public static void main(String[] args) throws IOException{ // metode utama yang akan dieksekusi saat program Java dijalankan. 
        // Ia juga menunjukkan bahwa ada kemungkinan terjadi IOException
        String url = "jdbc:mysql://localhost/uaspbo"; // inisialisasi var url dengan isi url koneksi java dengan mysql database
        String username = "root"; // inisialisasi var username dengan isi username mysql
        String password = ""; // inisialisasi var password dengan isi password mysql
        
        
        DBConnection koneksiDB = new DBConnection(url, username, password);
        // membuat objek DBConnection dengan menggunakan informasi koneksi ke database.
        boolean loop = true; // var loop diinisialisasi sebagai true untuk memulai loop program.
        do { // yang menampilkan menu program kepada pengguna dan membaca pilihan pengguna dari konsol menggunakan objek 
        System.out.println("Menu Program"); // sebuah label
        System.out.println("============="); // sebuah label
        System.out.println("1. Insert activity"); // sebuah label
        System.out.println("2. Show data todo list"); // sebuah label
        System.out.println("3. Edit activity"); // sebuah label
        System.out.println("4. Delete activity"); // sebuah label
        System.out.println("5. Exit"); // sebuah label
        System.out.print("masukkan pilihan : "); // sebuah label
        
        
        
            try {
            int pilih = Integer.parseInt(input.readLine()); //  Membaca input dari pengguna dan mengkonversinya menjadi bilangan bulat. 
            // Input ini akan menentukan pilihan menu yang akan dieksekusi.
            
            switch(pilih){ // digunakan untuk memilih tindakan berdasarkan nilai pilih
                case 1: {
                    koneksiDB.insertData(); // : Jika pilih sama dengan 1, maka memanggil metode insertData() dari objek koneksiDB
                    break;
                }
                case 2:{
                    koneksiDB.displayData(); // : Jika pilih sama dengan 2, maka memanggil metode displayData() dari objek koneksiDB
                    break;
                }   
                case 3: {
                    koneksiDB.updateData(); // : Jika pilih sama dengan 3, maka memanggil metode updateData() dari objek koneksiDB
                    break;
                }
                case 4: {
                    koneksiDB.deleteData(); // : Jika pilih sama dengan 4, maka memanggil metode deleteData() dari objek koneksiDB
                    break;
                }
                case 5: { // : Jika pilih sama dengan 5, maka keluar dari program/loop
                    loop = false;
                    System.exit(0);
                    break;
                }
                default:
                    System.out.println("Pilihan yang anda masukkan salah woy!"); // jika selain dari pilihan tampilkan pesan ini
            }
            
        } catch (IOException | NumberFormatException e) {
            
        }
    } while(loop);
        }
        
    
}
