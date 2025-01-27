package Uranaishi;

import Uranaishi.lib.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Berkas java utama untuk aplikasi Uranishi
 * @author Josep Marcello
 * 25 Februari 2021
 */
public class App {
    private static void printResult(ArrayList<ArrayList<Node>> nodes) {
        int i = 1;
        for (ArrayList<Node> vertList : nodes) {
            System.out.print("Semester " + i + ": ");
            int j = 0;
            for (Node node : vertList) {
                if (j != vertList.size()-1) {
                    System.out.print(node.getInfo() + ", ");
                } else {
                    System.out.print(node.getInfo());
                }
                ++j;
            }
            System.out.println();
            ++i;
        }
    }
    public static void main(String[] args) throws IOException, FileNotFoundException {
        //System.out.println("current working directory: " + System.getProperty("user.dir"));
        Scanner scan = new Scanner(System.in);
        Parser fp = null;
        Graph g1 = new Graph();

        // buat ngebaca argument dari user
        if (args.length != 0) {
            for (int i = 0; i < args.length; ++i) {
                if (args[i].equals("-f") || args[i].equals("--file")) {
                    fp = new Parser(args[++i]);
                } else if (args[i].equals("--help") || args[i].equals("-h")) {
                    System.out.println("uranaishi [nama jar] [--file|-f file-name] [--help|-h]");
                    System.out.println("\t-f\t--file\tArgumen ini diikuti path ke file yang berisi data graf.");
                    System.out.println("\t-h\t--help\tMenuliskan bantuan ini.");
                    System.exit(0);
                }
            }
        }

        // Ngebaca file kalo belom dibaca dari argumen
        if (fp == null) {
            System.out.print("Tuliskan path ke file yang berisi data graf: ");
            String input = scan.nextLine();
            fp = new Parser(input);
        }

        fp.openFile();
        g1 = fp.parse();
        scan.close();
        fp.close();

        System.out.println("Hasil topological sort:\n");
        long start = System.nanoTime();
        ArrayList<ArrayList<Node>> nodes = g1.topoSort();
        long elapsedTime = System.nanoTime() - start;
        printResult(nodes);
        System.out.println("\nWaktu untuk memproses graf: " + elapsedTime + " nanodetik.");
        g1.print();
    }
}
