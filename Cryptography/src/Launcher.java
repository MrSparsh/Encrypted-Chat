
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
public class Launcher extends javax.swing.JFrame {

    static void consoleBoxTextArea(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private final Socket readSocket;
    private final Socket writeSocket;
    private ObjectOutputStream oos,foos;
    private ObjectInputStream ois,fois;
    private PublicKey publicKeyServer;
    private PrivateKey keyRSAPrivate;
    private static Socket socket;
    public final static String UPDATE_USERS = "updateuserslist:";
    public static String sessionUsername = null;
    private Key AESKey,DESKey;
    static int val=1;
    File file;
    String fileName1="E:/java/Cryptography/src/plain.txt";
    public Launcher(Socket readSocket,Socket writeSocket,Key AESKey,Key DESKey,ObjectInputStream  ois,ObjectOutputStream oos,Socket fileSendSocket) throws Exception {
        
        initComponents();
        foos = new ObjectOutputStream(fileSendSocket.getOutputStream());
        fois = new ObjectInputStream(fileSendSocket.getInputStream());
        this.readSocket = readSocket;
        this.writeSocket = writeSocket;
        this.AESKey = AESKey;
        this.DESKey = DESKey;
        
        this.oos=oos;
        this.ois=ois;
        new Thread()
        {
            public void run()
            {
                while(true)
                {
                    try{
                     Message encryptedMessage= (Message) fois.readObject();
                            String type = encryptedMessage.getType();
                            MessageDecryption mess=null;
                            if(type.equals("AES")){
                                {
                                    mess = new MessageDecryption(encryptedMessage.getMessage(),AESKey,type);
                                }
                            }else{
                                mess = new MessageDecryption(encryptedMessage.getMessage(),DESKey,type);
                            }
                            String plainMessageString = mess.getMessage();

                                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName1, true))) {

                                    writer.append(plainMessageString+"\n");

                                }
                            chatBoxTextArea.setText("file has been  recieved from another client at time "+new Date()+"\n");
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                }
            }
        }.start();
        new Thread(){
            public void run(){
                try {
                    while(true){
                        Message encryptedMessage= (Message) ois.readObject();
                        String type = encryptedMessage.getType();
                        MessageDecryption mess=null;
                        if(type.equals("AES")){
                            {
                                mess = new MessageDecryption(encryptedMessage.getMessage(),AESKey,type);
                            }
                        }else{
                            mess = new MessageDecryption(encryptedMessage.getMessage(),DESKey,type);
                        }
                        String plainMessageString = mess.getMessage();
                        System.out.println(plainMessageString + " FROM using "+encryptedMessage.getType());
                        chatBoxTextArea.append(plainMessageString+"\n");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
        
        

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        headerLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        sendDESBtn = new javax.swing.JButton();
        sendAESBtn = new javax.swing.JButton();
        msgTextField = new javax.swing.JTextField();
        sendFileDESBtn = new javax.swing.JButton();
        sendFileAESBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatBoxTextArea = new javax.swing.JTextArea();
        graphButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        consoleBoxTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat On");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        headerLabel.setBackground(new java.awt.Color(0, 153, 153));
        headerLabel.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        headerLabel.setForeground(new java.awt.Color(255, 255, 153));
        headerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel.setText("Cryptography");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hacker.png"))); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.black));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(179, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(headerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(235, 235, 235))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(headerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        sendDESBtn.setBackground(new java.awt.Color(153, 153, 153));
        sendDESBtn.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        sendDESBtn.setForeground(new java.awt.Color(255, 255, 255));
        sendDESBtn.setText("Send (DES)");
        sendDESBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendDESBtnActionPerformed(evt);
            }
        });

        sendAESBtn.setBackground(new java.awt.Color(153, 153, 153));
        sendAESBtn.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        sendAESBtn.setForeground(new java.awt.Color(255, 255, 255));
        sendAESBtn.setText("Send (AES)");
        sendAESBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendAESBtnActionPerformed(evt);
            }
        });

        msgTextField.setBackground(new java.awt.Color(153, 255, 153));
        msgTextField.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        msgTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        msgTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msgTextFieldActionPerformed(evt);
            }
        });

        sendFileDESBtn.setBackground(new java.awt.Color(153, 153, 153));
        sendFileDESBtn.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        sendFileDESBtn.setForeground(new java.awt.Color(255, 255, 255));
        sendFileDESBtn.setText("Send File (DES)");
        sendFileDESBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendFileDESBtnActionPerformed(evt);
            }
        });

        sendFileAESBtn.setBackground(new java.awt.Color(153, 153, 153));
        sendFileAESBtn.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        sendFileAESBtn.setForeground(new java.awt.Color(255, 255, 255));
        sendFileAESBtn.setText("Send File (AES)");
        sendFileAESBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendFileAESBtnActionPerformed(evt);
            }
        });

        chatBoxTextArea.setBackground(new java.awt.Color(255, 255, 204));
        chatBoxTextArea.setColumns(20);
        chatBoxTextArea.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        chatBoxTextArea.setRows(5);
        chatBoxTextArea.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        chatBoxTextArea.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        chatBoxTextArea.setEnabled(false);
        jScrollPane1.setViewportView(chatBoxTextArea);

        graphButton.setBackground(new java.awt.Color(153, 153, 153));
        graphButton.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        graphButton.setForeground(new java.awt.Color(255, 255, 255));
        graphButton.setText("Show on Graph");
        graphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphButtonActionPerformed(evt);
            }
        });

        consoleBoxTextArea.setBackground(new java.awt.Color(255, 255, 204));
        consoleBoxTextArea.setColumns(20);
        consoleBoxTextArea.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        consoleBoxTextArea.setRows(5);
        consoleBoxTextArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        consoleBoxTextArea.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        consoleBoxTextArea.setEnabled(false);
        jScrollPane2.setViewportView(consoleBoxTextArea);

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Chat Area");
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel1.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(153, 153, 153));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Console");
        jLabel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel2.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(msgTextField))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sendFileDESBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(sendDESBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(sendFileAESBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(sendAESBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(graphButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(msgTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(graphButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sendAESBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sendFileDESBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(sendFileAESBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sendDESBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendFileDESBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendFileDESBtnActionPerformed
       
        
        JFileChooser chooser = new JFileChooser();
        int values1 = chooser.showOpenDialog(null);
        file = chooser.getSelectedFile();
        val=1;
        if (values1 == JFileChooser.APPROVE_OPTION)
        {
            try {
                 
                System.out.println(file.getPath());
                String fileName = file.getAbsolutePath();
                System.out.println(fileName);
                String msgStr,msgStr2="";
               //BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
                chatBoxTextArea.append("Me:   file: "+fileName+" has been sent to another client at time "+new Date()+"\n");
                String msg="";
                    FileInputStream fs = new FileInputStream(fileName);
                    byte[] buffer = new byte[1024];
                    Integer bytesRead;
                    while((bytesRead = fs.read(buffer)) > 0){
                        msg += new String(buffer, StandardCharsets.UTF_8);
                    }
                    MessageEncryption messEncryption = new MessageEncryption(msg,DESKey,"DES");
                    String encryptedMsgStr = messEncryption.getMessageString();
                    Message encryptedMsg = new Message(encryptedMsgStr,"DES"); 
                    chatBoxTextArea.append(msgStr2+" \n");
                    foos.writeObject(encryptedMsg);
                   
                 
                
                
            } catch (Exception ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
            }    
        } else if (values1 == JFileChooser.CANCEL_OPTION) {
            System.out.println("No file is selected");
        } else if (values1 == JFileChooser.ERROR_OPTION) {
            System.out.println("Error!");
        } else if (file == null) {
            System.out.println("No File is chosen");
        }
            
    }//GEN-LAST:event_sendFileDESBtnActionPerformed

    private void msgTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msgTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_msgTextFieldActionPerformed

    private void sendAESBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendAESBtnActionPerformed

        try {
            
            String msgStr = msgTextField.getText();
            if(msgStr.equals("")==true)
                return;
            chatBoxTextArea.append("Me: "+msgStr+"\n");
            MessageEncryption messEncryption = new MessageEncryption(msgStr,AESKey,"AES");
            String encryptedMsgStr = messEncryption.getMessageString();
            Message encryptedMsg = new Message(encryptedMsgStr,"AES");
            oos.writeObject(encryptedMsg);
            
        } catch (Exception ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_sendAESBtnActionPerformed

    private void sendDESBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendDESBtnActionPerformed
        try {
            String msgStr = msgTextField.getText();
            if(msgStr.equals("")==true)
                return;
            chatBoxTextArea.append("Me: "+msgStr+"\n");
            MessageEncryption messEncryption = new MessageEncryption(msgStr,DESKey,"DES");
            String encryptedMsgStr = messEncryption.getMessageString();
            Message encryptedMsg = new Message(encryptedMsgStr,"DES");
            oos.writeObject(encryptedMsg);
            
        } catch (Exception ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sendDESBtnActionPerformed

    private void graphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphButtonActionPerformed
       
            Frame f = new Frame();
        f.setBounds(200,200,800,300);
        f.add(new Graph());
        f.setResizable(false);
        f.setVisible(true);
        try {
            sleep(8000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        f.dispose();
        
        
    }//GEN-LAST:event_graphButtonActionPerformed

    private void sendFileAESBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendFileAESBtnActionPerformed
       
        JFileChooser chooser = new JFileChooser();
        int values1 = chooser.showOpenDialog(null);
        file = chooser.getSelectedFile();
        val=1;
        if (values1 == JFileChooser.APPROVE_OPTION)
        {
            try {
                 
                System.out.println(file.getPath());
                String fileName = file.getAbsolutePath();
                System.out.println(fileName);
                String msgStr,msgStr2="";
                /*BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
                
                while((msgStr=br.readLine())!=null)
                {
                    msgStr2+=(msgStr+"\n");
                }*/
                    
                    String msg="";
                    FileInputStream fs = new FileInputStream(fileName);
                    byte[] buffer = new byte[1024];
                    Integer bytesRead;
                    while((bytesRead = fs.read(buffer)) > 0){
                        msg += new String(buffer, StandardCharsets.UTF_8);
                    }
                    chatBoxTextArea.append("Me:   file: "+fileName+" has been sent to another client at time "+new Date()+"\n");
                    MessageEncryption messEncryption = new MessageEncryption(msg,AESKey,"AES");
                    String encryptedMsgStr = messEncryption.getMessageString();
                    Message encryptedMsg = new Message(encryptedMsgStr,"AES"); 
                    chatBoxTextArea.append(msgStr2+" \n");
                   
                    foos.writeObject(encryptedMsg);
                    
                
            } catch (Exception ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
            }    
        } else if (values1 == JFileChooser.CANCEL_OPTION) {
            System.out.println("No file is selected");
        } else if (values1 == JFileChooser.ERROR_OPTION) {
            System.out.println("Error!");
        } else if (file == null) {
            System.out.println("No File is chosen");
        }
            
    }//GEN-LAST:event_sendFileAESBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatBoxTextArea;
    public static javax.swing.JTextArea consoleBoxTextArea;
    private javax.swing.JButton graphButton;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField msgTextField;
    private javax.swing.JButton sendAESBtn;
    private javax.swing.JButton sendDESBtn;
    private javax.swing.JButton sendFileAESBtn;
    private javax.swing.JButton sendFileDESBtn;
    // End of variables declaration//GEN-END:variables
}
