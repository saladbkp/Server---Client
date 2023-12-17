/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author khwon
 */
public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    
    public Client(Socket socket, String username){
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
            
        }catch(IOException e){
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
        
    }
    public void sendMessasge(String messageToSend){
            try{
                    bufferedWriter.write(username);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    if(socket.isConnected()){
                        String msg = username+"(send):"+messageToSend;
                        bufferedWriter.write(msg);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        //System.out.println(msg);
                    }


                //    bufferedWriter.write(username);
                //    bufferedWriter.newLine();
                //    bufferedWriter.flush();
                //    while (socket.isConnected()){
                //        bufferedWriter.write(username+"(send):"+messageToSend);
                //        bufferedWriter.newLine();
                //        bufferedWriter.flush();
                //    }
                
            }
            catch(IOException e){
                closeEverything(socket,bufferedReader,bufferedWriter);
            }
    }
    public void listenForMessage(){
        new Thread(new Runnable(){
            public void run(){
                String msgFromGroupChat;
                while(socket.isConnected()){
                    try{
                        msgFromGroupChat = bufferedReader.readLine();
                        System.out.println(username+"(receive):"+msgFromGroupChat);
                        if(msgFromGroupChat=="123"){
                            System.out.println("ok");

                            // ReceiveNotiService.receiveNotiService(username);
                        }
                    }catch(IOException e){
                        closeEverything(socket,bufferedReader,bufferedWriter);
                    }
                }
            }
        }).start();
            
        
    }
    public void closeEverything(Socket socket, BufferedReader bufferedReader,BufferedWriter bufferedWriter){

        try{
            if (bufferedReader != null){
                bufferedReader.close();
            }
            if (bufferedWriter != null){
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
   public static void main(String[] args) throws IOException{
       System.out.print("Enter ur name: ");
       Scanner sc = new Scanner(System.in);
       String username = sc.nextLine();
       Socket socket = new Socket("localhost",1234);
       Client client = new Client(socket,username);
       client.listenForMessage();
       client.sendMessasge("123");

   }
}
