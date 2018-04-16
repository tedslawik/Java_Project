/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static sun.security.krb5.Confounder.bytes;


/**
 *
 */
public class NewJFrame extends javax.swing.JFrame implements ActionListener, Runnable{

    static String serwer="localhost";
	static int port=4444;
	static String nick="Domyslny";
	DataOutputStream dataoutputstream;
	DataInputStream datainputstream;
	String dane;
	Socket socket;
	ServerSocket ssocket;
	Thread thread;
	DefaultListModel userlist;
        private String file;
	int uzytkownikow;
	JPanel topPanel;
	JTable table;
	String nazwa[]={"Wiadomosc"};
	ArrayList wiadomosci;
        
        List<String> nowi = new ArrayList<String>();
	
        
    public NewJFrame() { 
        userlist = new DefaultListModel();
        initComponents();
        main.setEditable(false);
      lista_ziom.setEditable(false);
      //  this.setVisible( true );
    }

private void SendMessageToServer(String Message)
	{
		try 
		{
			dataoutputstream.writeBytes("\r\n"+Message+"\r\n");	
		}
		catch(IOException e) 
		{ 
			e.printStackTrace();
		}			
	}


private void ConnectToServer()
	{				
		try 
		{
			socket = new Socket(serwer,port);					
			dataoutputstream = new DataOutputStream(socket.getOutputStream());
			SendMessageToServer("HELO "+nick);			
			datainputstream  = new DataInputStream(socket.getInputStream());
			thread = new Thread(this);
			thread.start();			
			main.setText("Polaczono z serwerem\r\n");
                        //lista_ziom.size();
                        main.repaint();
                        main.setEditable(false);
                          lista_ziom.setEditable(false);
                        // main.setText("\r");
			System.out.println("KLient:Polaczono z serwerem \r\n" + serwer + "na porcie " + port);
		}
		catch(IOException e) 
		{ 
			JOptionPane.showMessageDialog(this, "Klient:Nie mozna polaczyc sie z serwerem!", "Blad!", JOptionPane.ERROR_MESSAGE);
		}			
	}
private void DisconnectFromServer()
	{		
		if(socket != null)
		{
		try 
		{					
			dataoutputstream = new DataOutputStream(socket.getOutputStream());
			SendMessageToServer("QUIT "+nick);	
			System.out.println("Klient:Zakonczono polaczenie\r\n");
                       
                        main.setText("Rozlaczono z serwerem\r\n");
			socket.close();
			socket=null;
		}
		catch(IOException e) 
		{ 
			JOptionPane.showMessageDialog(this, "Klient:Nie mozna zamknac polaczenia! Moze juz zamkniete?", "Blad!", JOptionPane.ERROR_MESSAGE);
		}	
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Nie jestes podlaczony do serwera!","About",JOptionPane.YES_OPTION);
		}
	}


    @Override
    public void run()
	{
           
		while(thread != null)
		{
			try 
			{
                            
				dane = datainputstream.readLine();									
                              
				if (dane.startsWith("EXIS"))
				{	//lista_ziom.get;					
					wiadomosci.add(nick + " juz jest w uzyciu! Zmien nicka!\n");
					main.setText("Nick jest juz w uzyciu!\n");
					main.repaint();
                                           
				}					 

				if (dane.startsWith("REMO"))
				{						
					main.setText(main.getText() + "\r\nUzytkownik " + dane.substring(5) + " wylogowal sie\n");
					main.repaint();
                                           
				}
                                
                                if (dane.startsWith("LIST"))
                                {
                                    
                                    lista_ziom.setText(" ");
                                    String expr = dane.substring(4);
                                     StringTokenizer st = new StringTokenizer(expr);
                                      while (st.hasMoreTokens()) {
                                     String s = st.nextToken();
                                    lista_ziom.setText(lista_ziom.getText() +s+"\n");
                                      }
		
                                    //
                                    //lista_ziom.setText(lista_ziom.getText() + dane.substring(4)+"\n");
                                    //lista_ziom.setText(lista_ziom.getText() +s;
                                     
				}
                                  if (dane.startsWith("FILE"))
                                {
                                // BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
                                 }
		
                                   
                                     
				

				if( dane.startsWith("MESS"))
				{		
					//main.setText(main.getText() + "\r\n" + dane.substring(5,dane.indexOf(":")) + "  " + dane.substring(dane.indexOf(":")));	
                                           
                                    
                                    main.setText(main.getText() + "\r\n" + dane.substring(5));	
                                        main.repaint();
                                        
				}



			}
			catch(Exception e) 
			{ 
				//e.printStackTrace();
			}
		}
		
	}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        txaMessages = new javax.swing.JTextArea();
        chooser = new javax.swing.JFileChooser();
        send = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        input = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        main = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtfile = new javax.swing.JTextField();
        btnfile = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        polet = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        lista_ziom = new javax.swing.JTextArea();

        jScrollPane2.setAutoscrolls(true);

        txaMessages.setColumns(20);
        txaMessages.setRows(5);
        jScrollPane2.setViewportView(txaMessages);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        send.setText("Wyślij");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        jTextField1.setText("localhost");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setText("4444");
        jTextField2.setAutoscrolls(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setText("nick");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jButton1.setText("Rozłącz");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Połącz");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Zatwierdź");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        input.setColumns(20);
        input.setRows(5);
        jScrollPane1.setViewportView(input);

        main.setColumns(20);
        main.setRows(5);
        jScrollPane3.setViewportView(main);

        jLabel1.setText("HOST");

        jLabel2.setText("PORT");

        jLabel3.setText("NICK");

        btnfile.setText("wybierz");
        btnfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfileActionPerformed(evt);
            }
        });

        jButton5.setText("wyslij");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        polet.setText("jTextField4");
        polet.setUI(null);

        lista_ziom.setColumns(20);
        lista_ziom.setRows(5);
        jScrollPane5.setViewportView(lista_ziom);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel2)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtfile, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(polet))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(228, 228, 228))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(261, 261, 261))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(txtfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnfile))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(polet, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5))))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
          
        if(socket != null)
			{ 
                           
           
                         
				SendMessageToServer("MESS " +nick + ": " + input.getText());
				input.setText("");
				input.repaint();
			} 
          
			else 
			{
				main.setText("Nie jestes podlaczony do serwera!");
			}
    }//GEN-LAST:event_sendActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
DisconnectFromServer();        
lista_ziom.setText("");
java.awt.Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(new java.awt.event.WindowEvent(this, java.awt.event.WindowEvent.WINDOW_CLOSING));

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        main.setText("poszlo ? ");
        
        if(jTextField1.getText().compareTo("") != 0 && jTextField2.getText().compareTo("") != 0 && jTextField3.getText().compareTo("") != 0)
			{
                            //main.setEditable(false);
				NewJFrame.serwer=jTextField1.getText();
				NewJFrame.port=Integer.valueOf(jTextField2.getText());
				NewJFrame.nick=jTextField3.getText();
                                main.setText("poszlo  ");
                                
				//main.disable();
                                    //this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Nie podales wymaganych danych!", "Blad!", JOptionPane.ERROR_MESSAGE);
		
			}        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       ConnectToServer();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         file = txtfile.getText();
         if(socket != null)
			{ 
        
           
                main.setText("Sending...");
                 byte[] array = null;
             try {
                 array = Files.readAllBytes(new File(file).toPath());
             } catch (IOException ex) {
                 Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
                                  SendMessageToServer("FILE"+array);
            
            }
                        
         else
         {
                main.setText("Nie jestes podlaczony do serwera by wysłać zdjęcie!");
	  		}
    }//GEN-LAST:event_jButton5ActionPerformed
public String getThisFilename(String path){
        File p = new File(path);
        String fname = p.getName();
        return fname.replace(" ", "_");
    }
    private void btnfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfileActionPerformed
        try {
            showOpenDialog();
            BufferedImage img = ImageIO.read(new File(txtfile.getText()));
            ImageIcon icon = new ImageIcon(img);
            JLabel label = new JLabel(icon);
            JOptionPane.showMessageDialog(null, label);
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnfileActionPerformed
public void showOpenDialog(){
        int intval = chooser.showOpenDialog(this);
        if(intval == chooser.APPROVE_OPTION){
            txtfile.setText(chooser.getSelectedFile().toString());
        }else{
            txtfile.setText("");
        }
    }

    /**
     * 
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        new NewJFrame();
       
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
              
                new NewJFrame().setVisible(true);
                
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnfile;
    private javax.swing.JFileChooser chooser;
    private javax.swing.JTextArea input;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextArea lista_ziom;
    private javax.swing.JTextArea main;
    private javax.swing.JTextField polet;
    private javax.swing.JButton send;
    private javax.swing.JTextArea txaMessages;
    private javax.swing.JTextField txtfile;
    // End of variables declaration//GEN-END:variables
}
