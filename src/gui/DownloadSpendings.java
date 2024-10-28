/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.DefaultListModel;
import java.sql.ResultSet;
import java.sql.*;// Import ResultSet class
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

/**
 *
 * @author shyam
 */
public class DownloadSpendings extends javax.swing.JFrame {

    private Connection c;
    private Statement st;

    /**
     * Creates new form DownloadSpendings
     */
    public DownloadSpendings() {
        try {
            st = DriverManager.getConnection("jdbc:mysql://localhost:3306/spendingdb?useSSL=false", "root", "root").createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        initComponents();
        getEntries();
    }

    public void getEntries() {
        try {

            ResultSet rs = st.executeQuery("SELECT * FROM spendings;");

            // Create a list model to add data to the JList
            DefaultListModel<String> listModel = new DefaultListModel<>();

            // Iterate through the result set and add each entry to the list model
            while (rs.next()) {

                int sid = rs.getInt("sid");
                String category = rs.getString("category");
                String date = rs.getString("date");
                double amount = rs.getDouble("amount");
                // Format the entry and add it to the list model
                String entry = "ID: " + sid + ", Category: " + category + ", Date: " + date + ", Amount: " + amount;
                listModel.addElement(entry);
            }

            // Set the model to the JList
            jList1.setModel(listModel);
            System.out.println("List model updated."); // Debugging statement

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // Method to generate a PDF of expenses

    public static void generateExpensePDF(JList<String> jList1) {
        Document document = new Document();
        try {
            // Use JFileChooser to select save location
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath() + ".pdf"; // Append .pdf extension

                // Create a PDF writer instance
                PdfWriter.getInstance(document, new FileOutputStream(filePath));

                // Open the document for writing
                document.open();

                // Get the expense data from the JList
                ListModel<String> listModel = jList1.getModel();
                int size = listModel.getSize();

                // Write each expense as a new paragraph in the PDF document
                for (int i = 0; i < size; i++) {
                    String expense = listModel.getElementAt(i);
                    document.add(new Paragraph(expense));
                }

                JOptionPane.showMessageDialog(null, "Expense PDF downloaded successfully at " + filePath);

            }
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while downloading PDF: " + e.getMessage());
        } finally {
            // Close the document
            document.close();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jList1.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(jList1);

        jButton1.setText("DOWNLOAD ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jButton1)
                .addContainerGap(184, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        generateExpensePDF(jList1);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
