/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adding;

//import java.awt.Image;
import cupcake1.pkg0.*;
import java.awt.Desktop;
import javafx.scene.input.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 *
 * @author Kishan
 */
public class FXMLDocumentController2 implements Initializable {
    @FXML
    private TextField path;
    
   @FXML
   private TextField tag;
    
   @FXML
   private AnchorPane Anchor_Pane;
   
    @FXML
    private Button apply;
     @FXML
    private TableView<Selection> tableSelection;
    @FXML
    private TableColumn<Selection,String> tagC;
    @FXML
    private TableColumn<Selection,String> pathC;
   
    @FXML
    private TableColumn<Selection,String> actionC;
    
    private ObservableList<Selection> data
           = FXCollections.observableArrayList(); 
    
    private PreparedStatement pst;
    private PreparedStatement pst1;
        private ResultSet rs;
    private Connection con=null;
    int i=-1;
    private StringBuffer pathFinal=null;
    @FXML 
     protected void insertOnlyOneData(ActionEvent event) throws SQLException, Exception {
     con=DBConnection.DBConnection.cakeconnection();
         int number = 0;
         String temp_sql="SELECT MAX(number) FROM details";
         pst1=con.prepareStatement(temp_sql);
         rs=pst1.executeQuery();
        int id2 = 0; 
        if ( rs.next() ){
        id2 = rs.getInt(1);  
        }
        number=id2;
        System.out.println(".;.;.;."+id2);
         number++;
         String sql="Insert into details (data,path,kind,number) values(?,?,?,?)";
         String data_value=tag.getText();
        String path_value=path.getText();
        String kind_value=null;
             if(path_value.toLowerCase().contains("https:".toLowerCase()) || path_value.toLowerCase().contains("www".toLowerCase())|| path_value.toLowerCase().contains("http".toLowerCase()))
               {
                   System.out.println(".......li");
                   kind_value="link";
               }
               else if(path_value.toLowerCase().contains(".".toLowerCase()))
               {
                   kind_value="application";
               }
               else if(path_value.toLowerCase().contains("\\".toLowerCase()))
               {
                   kind_value="folder";
               }
            System.out.println("..........data value="+data_value+"kind="+kind_value);
             
            pst=con.prepareStatement(sql);
            pst.setString(1,data_value);
            pst.setString(2,path_value);
            pst.setString(3,kind_value);
             String t=Integer.toString(number);
            pst.setString(4,t);
            int z=pst.executeUpdate();
            if(z == 1)
            {
                System.out.println("successful");
            }
            path.clear();
            tag.clear();
              Stage stage = (Stage) apply.getScene().getWindow();
           
            stage.hide();
        
              
     }
     @FXML 
     protected void closeWindow(ActionEvent event){
         
              Stage stage = (Stage) apply.getScene().getWindow();
           
            stage.hide();
     }
    @FXML 
    protected void dropped(ActionEvent event) throws SQLException {
         System.out.println("......gotcha");
     } 
    @FXML 
     protected void insertData(ActionEvent event) throws SQLException {
         insertOneData(event);
         int temp=0;
         con=DBConnection.DBConnection.cakeconnection();
         int number = 0;
         String temp_sql="SELECT MAX(number) FROM details";
         pst1=con.prepareStatement(temp_sql);
         rs=pst1.executeQuery();
        int id2 = 0; 
        if ( rs.next() ){
        id2 = rs.getInt(1);  
        }
        number=id2;
        System.out.println(".;.;.;."+id2);
         number++;
         try{
         while(temp<=i)
         {
             
         System.out.println("i==="+i+"temp=="+temp);
             System.out.println("not null");
             
            String sql="Insert into details (data,path,kind,number) values(?,?,?,?)";
         Selection select=data.get(temp);
             
            String path_value=select.getPath1();
            String data_value=select.getTag1();
            System.out.println("nottttttt");
           String kind_value = null;
             if(i>0){
            kind_value = "multiple";
             }
             else{
            if(path_value.toLowerCase().contains("https:".toLowerCase()) || path_value.toLowerCase().contains("www".toLowerCase())|| path_value.toLowerCase().contains("http".toLowerCase()))
               {
                   System.out.println(".......li");
                   kind_value="link";
               }
               else if(path_value.toLowerCase().contains(".".toLowerCase()))
               {
                   kind_value="application";
               }
               else if(path_value.toLowerCase().contains("\\".toLowerCase()))
               {
                   kind_value="folder";
               }
             }
            pst=con.prepareStatement(sql);
             
            if(data_value.equals(""))
            {
               data_value="default"; 
            }
            System.out.println("path "+path_value+"tag here="+data_value+" i="+i+"kind="+kind_value);
            pst.setString(1,data_value);
            pst.setString(2,path_value);
            pst.setString(3,kind_value);
            String t=Integer.toString(number);
            pst.setString(4,t);
            int z=pst.executeUpdate();
            System.out.println("sql====="); 
            if(z == 1)
            {
                System.out.println("done.......");
            }
            pst.close();
            temp++;
             
         }
         }catch(Exception e){System.out.println("eroor");}
         System.out.println("running");
          Stage stage = (Stage) apply.getScene().getWindow();
        stage.hide();
        
     }

     @FXML 
     protected void insertOneData(ActionEvent event){
         i++;
         setCellTable();
         
         String path_value=path.getText();
         if(path_value!=null){
         String tag_value=tag.getText();
            String kind_value = "multiple";
             
            if(tag_value.equals(""))
            {
               tag_value="default"; 
            }
            System.out.println("path="+path_value+"tag="+tag_value);
            data.add(new Selection(tag_value,path_value,"Delete"));
            path.clear();
            tag.clear();
         }
            
     }
     @FXML 
     protected void loadMultipleData(){
            System.out.println("..........data value="+"kind=4545454");
            tableSelection.setEditable(true);
            tableSelection.getSelectionModel().setCellSelectionEnabled(true); 
            setCellTable();
        }
     
     private void setCellTable()
   {
        tableSelection.setEditable(true);
        
           tableSelection.getSelectionModel().setCellSelectionEnabled(true); 
       tagC.setCellValueFactory(new PropertyValueFactory<>("tag1"));
       pathC.setCellValueFactory(new PropertyValueFactory<>("path1"));
       Button btn=new Button("hey");
       int flag=0;
       tableSelection.setOnMousePressed(new EventHandler<MouseEvent>() {
     @Override 
        public void handle(MouseEvent event) {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                Selection sel=tableSelection.getSelectionModel().getSelectedItem();
                System.out.println(tableSelection.getSelectionModel().getSelectedItem());
                System.out.println("data="+data.toString()+"..."+data.contains(sel));

               data.remove(sel);
               i--;
                tableSelection.refresh();
               
        }
    }
});
       actionC.setCellValueFactory(new PropertyValueFactory<>("action1"));
       
       
       tableSelection.setItems(data);
    
   }
     private void loadDataFromDatabase(){
    
   }
      
     private void mouseDragDropped(final DragEvent e) {
         System.out.println("......ppppp");
     }
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
  
    
    }
}
    
    

