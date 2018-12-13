


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sample.Decryption.DecryptRSAwithSignature;
import sample.Messages.AESkeyAndSignature;
public class Launcher extends javax.swing.JFrame {

    private Socket readSocket,writeSocket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private PublicKey publicKeyServer;
    private PrivateKey keyRSAPrivate;
    private static Socket socket;
    public final static String UPDATE_USERS = "updateuserslist:";
    public static String sessionUsername = null;
    private Key AESKey,DESKey;

    public Launcher(Socket readSocket,Socket writeSocket,Key AESKey,Key DESKey,ObjectInputStream  ois,ObjectOutputStream oos) throws Exception {
        System.out.println("amit");
        initComponents();
        
        this.readSocket = readSocket;
        this.writeSocket = writeSocket;
        this.AESKey = AESKey;
        this.DESKey = DESKey;
        System.out.println("sparsh");
        this.oos=oos;
        this.ois=ois;
        new Thread(){
            public void run(){
                try {
                    while(true){
                        Message encryptedMessage= (Message) ois.readObject();
                        String type = encryptedMessage.getType();
                        MessageDecryption mess=null;
                        if(type.equals("AES")){
                            mess = new MessageDecryption(encryptedMessage.getMessage(),AESKey,type);
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

        jPanel1 = new javax.swing.JPanel();
        headerLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        sendDESBtn = new javax.swing.JButton();
        sendAESBtn = new javax.swing.JButton();
        msgTextField = new javax.swing.JTextField();
        sendFileDESBtn = new javax.swing.JButton();
        sendFileAESBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatBoxTextArea = new javax.swing.JTextArea();
        sendKeyBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        headerLabel.setBackground(new java.awt.Color(153, 255, 102));
        headerLabel.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        headerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel.setText("Cryptography");
        headerLabel.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(102, 255, 153));

        sendDESBtn.setText("Send (DES)");
        sendDESBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendDESBtnActionPerformed(evt);
            }
        });

        sendAESBtn.setText("Send (AES)");
        sendAESBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendAESBtnActionPerformed(evt);
            }
        });

        msgTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msgTextFieldActionPerformed(evt);
            }
        });

        sendFileDESBtn.setText("Send File (DES)");
        sendFileDESBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendFileDESBtnActionPerformed(evt);
            }
        });

        sendFileAESBtn.setText("Send File (AES)");

        chatBoxTextArea.setColumns(20);
        chatBoxTextArea.setRows(5);
        jScrollPane1.setViewportView(chatBoxTextArea);

        sendKeyBtn.setText("Send Key (RSA)");
        sendKeyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendKeyBtnActionPerformed(evt);
            }
        });

        jButton1.setText("Show on Graph");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(msgTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendDESBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendAESBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(sendFileDESBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendFileAESBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sendKeyBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sendAESBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sendDESBtn))
                    .addComponent(msgTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendFileDESBtn)
                    .addComponent(sendFileAESBtn)
                    .addComponent(sendKeyBtn)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendFileDESBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendFileDESBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sendFileDESBtnActionPerformed

    private void msgTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msgTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_msgTextFieldActionPerformed

    private void sendAESBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendAESBtnActionPerformed

        try {
            String msgStr = msgTextField.getText();
            chatBoxTextArea.append(msgStr+"\n");
            MessageEncryption messEncryption = new MessageEncryption(msgStr,AESKey,"AES");
            String encryptedMsgStr = messEncryption.getMessageString();
            Message encryptedMsg = new Message(encryptedMsgStr,"AES");
            oos.writeObject(encryptedMsg);

        } catch (Exception ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_sendAESBtnActionPerformed

    private void sendKeyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendKeyBtnActionPerformed
        
    }//GEN-LAST:event_sendKeyBtnActionPerformed

    private void sendDESBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendDESBtnActionPerformed
        try {
            String msgStr = msgTextField.getText();
            chatBoxTextArea.append(msgStr+"\n");
            MessageEncryption messEncryption = new MessageEncryption(msgStr,DESKey,"DES");
            String encryptedMsgStr = messEncryption.getMessageString();
            Message encryptedMsg = new Message(encryptedMsgStr,"DES");
            oos.writeObject(encryptedMsg);

        } catch (Exception ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sendDESBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatBoxTextArea;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField msgTextField;
    private javax.swing.JButton sendAESBtn;
    private javax.swing.JButton sendDESBtn;
    private javax.swing.JButton sendFileAESBtn;
    private javax.swing.JButton sendFileDESBtn;
    private javax.swing.JButton sendKeyBtn;
    // End of variables declaration//GEN-END:variables
}
