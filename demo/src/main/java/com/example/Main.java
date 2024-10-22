package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("client avviato");
        Socket s = new Socket("localhost", 3000);
        System.out.println("client connesso");

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        boolean fine = true;

        do {

            System.out.println("inserisci num da tentare: ");
            Scanner scan = new Scanner(System.in);

            out.writeBytes(scan.nextLine() + "\n");

            switch (in.readLine()) {
                case "<":
                    System.out.println("numero troppo piccolo");
                    break;

                case ">":
                    System.out.println("numero troppo grande");
                    break;

                case "=":
                    System.out.println("numero indovinato");
                    System.out.println("HAI INDOVINATO IN : " + in.readLine() + " tentativi");
                    System.out.println("Vorresti iniziare un'altra partita ? si/no");
                    String ins = scan.nextLine();
                    if (ins.equals("no")) {
                        System.out.println("client in chiusura");
                        out.writeBytes(ins + "\n");
                        s.close();
                        fine = false;
                        break;
                    }
                    out.writeBytes(ins + "\n");
                    break;

                case "!":
                    System.out.println("!!! numero no stringa");
                    break;

                case "#":
                    System.out.println("!!! numero non e nel range 1-100");
                    break;
            }

        } while (fine);
    }
}