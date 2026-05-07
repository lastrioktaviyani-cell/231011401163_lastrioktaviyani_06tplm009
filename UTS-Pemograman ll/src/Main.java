import java.util.ArrayList;
import java.util.Scanner;

class Product {
    String nama;
    int harga;
    int stok;

    public Product(String nama, int harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    void tampil() {
        System.out.println(nama + " | Harga: " + harga + " | Stok: " + stok);
    }
}

public class Main {
    static ArrayList<Product> produkList = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int pilih;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Tambah Produk");
            System.out.println("2. Tampilkan Produk");
            System.out.println("3. Beli Produk");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            pilih = input.nextInt();
            input.nextLine();

            switch (pilih) {
                case 1:
                    tambahProduk();
                    break;
                case 2:
                    tampilProduk();
                    break;
                case 3:
                    beliProduk();
                    break;
            }

        } while (pilih != 0);
    }

    static void tambahProduk() {
        System.out.print("Nama: ");
        String nama = input.nextLine();

        System.out.print("Harga: ");
        int harga = input.nextInt();

        System.out.print("Stok: ");
        int stok = input.nextInt();
        input.nextLine();

        produkList.add(new Product(nama, harga, stok));
        System.out.println("Produk ditambahkan!");
    }

    static void tampilProduk() {
        if (produkList.isEmpty()) {
            System.out.println("Belum ada produk.");
            return;
        }

        for (int i = 0; i < produkList.size(); i++) {
            System.out.print((i + 1) + ". ");
            produkList.get(i).tampil();
        }
    }

    static void beliProduk() {
        tampilProduk();
        if (produkList.isEmpty()) return;

        System.out.print("Pilih nomor: ");
        int index = input.nextInt() - 1;

        if (index < 0 || index >= produkList.size()) {
            System.out.println("Pilihan salah!");
            return;
        }

        System.out.print("Jumlah beli: ");
        int jumlah = input.nextInt();

        Product p = produkList.get(index);

        if (jumlah <= p.stok) {
            int total = jumlah * p.harga;
            p.stok -= jumlah;

            System.out.println("Berhasil beli!");
            System.out.println("Total: " + total);
        } else {
            System.out.println("Stok tidak cukup!");
        }
    }
}