/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.services;

import com.dao.AddNotiDao;
import com.model.Notifications;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author khwon
 */
public class ReceiveNotiService {
    public static void receiveNotiService(String receiver){

        AddNotiDao noti = new AddNotiDao();
        List<Notifications> notiarray = new ArrayList<Notifications>();
        notiarray = noti.findDataByCus(receiver);
        for (Notifications notis : notiarray){
            ImageIcon img = new ImageIcon("src//img//info.png");
            JOptionPane.showMessageDialog(null, notis.getContent(),"noti",JOptionPane.INFORMATION_MESSAGE,img);
        }
    }
            
}
