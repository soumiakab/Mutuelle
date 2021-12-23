package com.mutuelle.Impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;  

public class Mail {
  public static void send(String to){
	  
	  String from="mutuellecentralisee.app@gmail.com";
	  String pwd="javaappmutuelle";
	  String sub="Inscription Mutuelle";
	  String msg="vous ete inscrit au mutuellle ";
	  
    //Propriétés
    Properties p = new Properties();
    p.put("mail.smtp.host", "smtp.gmail.com");
    p.put("mail.smtp.socketFactory.port", "465");
    p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    p.put("mail.smtp.auth", "true");
    p.put("mail.smtp.port", "465");
    
    //Session
    Session s = Session.getDefaultInstance(p,
      new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication(from, pwd);
      }
    });
    
    
    //composer le message
    try {
      MimeMessage m = new MimeMessage(s);
      m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
      m.setSubject(sub);
      m.setText(msg);
      
      //envoyer le message
      Transport.send(m);
      System.out.println("Message envoyé avec succès");
      
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    
  }
}

//public class Main {
// public static void main(String[] args) {
//   //from, password, to, subject, message
//   Mail.send(
//    "from@gmail.com",
//    "password",
//    "to@gmail.com",
//    "Bienvenu sur WayToLearnX",
//    "mail de test!"
//  );
// }
//}
