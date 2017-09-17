/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cupcake1.pkg0;

//import java.awt.Image;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author Kishan
 */
public class FXMLDocumentController implements Initializable {
     private ObservableList<ShortcutList> data;
    int i;
    String[] btn=new String[]{"adBtn1","adBtn2","adBtn3","adBtn4","adBtn5","adBtn6"};
    String[] hb=new String[]{"img1","img2","img3","img4","img5","img6"};
    String[] path=new String[6];
    String[] kind=new String[6];
    String[] number=new String[6];
    @FXML
    private Label label;
    @FXML
    private Label title1;
    @FXML
    private Label title2;
    @FXML
    private Label title3;
    @FXML
    private Label title4;
    @FXML
    private Label title5;
    @FXML
    private Label title6;
    @FXML
    private Button adBtn1;
    
    @FXML
    private Button adBtn2;
    @FXML
    private Button adBtn3;
    @FXML
    private Button adBtn4;
    @FXML
    private Button adBtn5;
    @FXML
    private Button adBtn6;
    
    @FXML
    private Image img1;
    @FXML
    private Image img2;
    @FXML
    private Image img3;
    @FXML
    private Image img4;
    @FXML
    private Image img5;
    @FXML
    private Image img6;
    @FXML
    private ImageView imgv1;
    @FXML
    private ImageView imgv2;
    @FXML
    private ImageView imgv3;
    @FXML
    private ImageView imgv4;
    @FXML
    private ImageView imgv5;
    @FXML
    private ImageView imgv6;
    
    @FXML
    private MenuButton mb;
    
    @FXML
    private TableView<ShortcutList> ShortcutTable;
    @FXML
    private TableColumn<ShortcutList,String> dataTable;
    @FXML
    private TableColumn<ShortcutList,String> kindTable;
    @FXML
    private TableColumn<ShortcutList,String> pathTable;
    private Connection con=null;
    private PreparedStatement pst;
    private ResultSet rs=null;
    private ResultSet rs1=null;
    private Connection con1=null;
    private PreparedStatement pst1; 
     private PreparedStatement pst2;
     private URL privateUrl;
     private ResourceBundle privateRb;
    @FXML
    private RadioButton Folder;
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        con1=DBConnection.DBConnection.cakeconnection();
        System.out.println("You clicked me!!!!!!!!!!");
        System.out.println(((Control)event.getSource()).getId());
        String b=((Control)event.getSource()).getId();
        String p = null;
        switch(b)
                {
                    case "adBtn1":  
                          p=number[0];
                        System.out.println("trying to delete"+p+"\n");
                        title1.setText("");
                        break;
                    case "adBtn2":  
                        p=number[1];
                        title2.setText("");
                        break;
                    case "adBtn3":  
                        p=number[2];
                        title3.setText("");
                        break;
                    case "adBtn4":  
                        p=number[3];
                        title4.setText("");
                        break;
                    case "adBtn5":  
                        p=number[4];
                        title5.setText("");
                        break;
                    case "adBtn6":  
                        p=number[5];
                        title6.setText("");
                        break;
                    }
         
        String sql="delete from details where number = ?";
        pst1=con1.prepareStatement(sql);

         pst1.setString(1,p);
            
           
        pst1.executeUpdate();
            {
                System.out.println("done......."+pst1);
            }
            pst1.close();
            loadDataFromDatabase();
       
    }
    
    @FXML
    private void loadAddSingle(ActionEvent event) {
        System.out.println("You clicked folder!");
        loadWindow("/Adding/AddingItem.fxml", "Folder");
    }
    @FXML
    private void loadAddMultiple(ActionEvent event) {
        System.out.println("You clicked folder!");
        loadWindow("/Adding/AddingMultipleItem.fxml", "Folder");
    }
    @FXML
    private void loadAddFile(ActionEvent event) {
        System.out.println("You clicked file!");
        loadWindow("/Adding/AddingItem.fxml", "File");label.setText("Hello World!");
    }
    @FXML
    private void loadAddLink(ActionEvent event) {
        System.out.println("You clicked link!");
        loadWindow("/Adding/AddingItem.fxml", "Link");
        label.setText("Hello World!");
    }
    void loadWindow(String loc, String type) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Adding Shortcut");
            
            stage.setScene(new Scene(parent));
            stage.show();
            System.out.println("./././././");
        
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                    System.out.println("Stage is closing");
                    loadDataFromDatabase();
                    }
            });
            stage.setOnHiding(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                    System.out.println("Stage is closing");
                    loadDataFromDatabase();
                    }
            });
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        privateUrl=url;
        privateRb=rb;
       title4.setText("");
        con=DBConnection.DBConnection.cakeconnection();
        i=0;
        loadDataFromDatabase();
        
         imgv1.setOnMouseClicked(event -> {
            try {
                firstShortcut(0);
            } catch (URISyntaxException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         imgv2.setOnMouseClicked(event -> {
            try {
                firstShortcut(1);
            } catch (URISyntaxException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         imgv3.setOnMouseClicked(event -> {
            try {
                firstShortcut(2);
            } catch (URISyntaxException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         imgv4.setOnMouseClicked(event -> {
            try {
                firstShortcut(3);
            } catch (URISyntaxException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         imgv5.setOnMouseClicked(event -> {
            try {
                firstShortcut(4);
            } catch (URISyntaxException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         imgv6.setOnMouseClicked(event -> {
            try {
                firstShortcut(5);
            } catch (URISyntaxException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }    
    
    public void loadDataFromDatabase(){
        try {
           
            System.out.println("..data...");
            pst=con.prepareStatement("Select * from details");
            rs=pst.executeQuery();
        adBtn1.setVisible(false);
        adBtn2.setVisible(false);
        adBtn3.setVisible(false);
        adBtn4.setVisible(false);
        adBtn5.setVisible(false);
        adBtn6.setVisible(false);
        Image image1 = new Image(getClass().getResource("add_small_green.png").toExternalForm());
        imgv1.setImage(image1);
        imgv2.setImage(image1);
        imgv3.setImage(image1);
        imgv4.setImage(image1);
        imgv5.setImage(image1);
        imgv6.setImage(image1);
        title1.setText("");
        title2.setText("");
        title3.setText("");
        title4.setText("");
        title5.setText("");
        title6.setText("");
        path=null;
        path=new String[6];
        kind=new String[6];
        if(!rs.isBeforeFirst() )
            {
                System.out.println("no data..");
            }    
            else{
                i=0;
            while(rs.next())
            {
               if(rs.getString(4).equals("multiple")){
                   Image image = new Image(getClass().getResource("multiple3.png").toExternalForm());
                   int id=rs.getInt(1);
                   System.out.println("check id========"+id);
                   int numbers=rs.getInt(5);
                   kind[i]="multiple";
                   number[i]=rs.getString(5);
                   StringBuffer temp = new StringBuffer(rs.getString(3));
                   System.out.println("===1=="+temp+"number="+number[i]);
            //       String tag="Multiple";
                     String tag=rs.getString(2);
                       
// temp.append(rs.getString(3));
                  
                    pst2=con.prepareStatement("Select * from details where number = ? and id <> ?"); 
                    
                    pst2.setString(2,Integer.toString(id));
                    temp.append(";;;");
                     String t=Integer.toString(numbers);
                    pst2.setString(1,t);
                    rs1=pst2.executeQuery();
                    while(rs1.next()){
                            rs.next();
                            tag=tag+",";
                            tag=tag+rs1.getString(2);
                            temp.append(rs1.getString(3));
                            
                            temp.append(";;;");
                            System.out.println("==="+temp);
                    }
                    path[i]=temp.toString();
                    switch(hb[i])
                {
                    case "img1":  
                        System.out.println("+++++++++++++++");
                        if(!tag.equals("default"))
                        {title1.setText(tag);}
                        adBtn1.setVisible(true);
                        imgv1.setImage(image);
                        break;
                    case "img2":  
                        if(!tag.equals("default"))
                        {title2.setText(tag);}
                        adBtn2.setVisible(true);
                        imgv2.setImage(image);    
                        break;
                    case "img3":  
                        if(!tag.equals("default"))
                        {title3.setText(tag);}
                        adBtn3.setVisible(true);
                        imgv3.setImage(image);    
                        break;
                    case "img4":  
                        if(!tag.equals("default"))
                        {title4.setText(tag);}
                        adBtn4.setVisible(true);
                        imgv4.setImage(image);    
                        break;
                    case "img5":  
                        if(!tag.equals("default"))
                        {title5.setText(tag);}
                        adBtn5.setVisible(true);
                        imgv5.setImage(image);    
                        break;
                    case "img6":  
                        if(!tag.equals("default"))
                        {title6.setText(tag);}
                        adBtn6.setVisible(true);
                        imgv6.setImage(image);    
                        break;
                        // cardImg1.setImage(new Image("imagescards/" + CardsGame() + ".png"));
                }
                    i++;
               }
               else{
                
                String tag=rs.getString(2);
                path[i]=rs.getString(3);
                Image image=new Image(getClass().getResource("file1.png").toExternalForm());
                if(path[i].toLowerCase().contains("https:".toLowerCase()) || path[i].toLowerCase().contains("www".toLowerCase())|| path[i].toLowerCase().contains("http".toLowerCase()))
                {
                    image = new Image(getClass().getResource("web1.png").toExternalForm());
                }
                if(path[i].toLowerCase().contains("facebook".toLowerCase()))
                {
                    image = new Image(getClass().getResource("facebook.png").toExternalForm());
                }
                else if(path[i].toLowerCase().contains("youtube".toLowerCase()))
                {
                    image = new Image(getClass().getResource("youtube6.jpg").toExternalForm());
                }
                 else if(path[i].toLowerCase().contains("\\".toLowerCase()))
                {
                    image = new Image(getClass().getResource("folder2.png").toExternalForm());
                }
                kind[i]=rs.getString(4);
                number[i]=rs.getString(5);
                System.out.println("========"+i+"::"+hb[i]+"========"+path[i]);
                switch(hb[i])
                {
                    case "img1":  
                        System.out.println("+++++++++++++++");
                        if(!tag.equals("default"))
                        {title1.setText(tag);}
                        adBtn1.setVisible(true);
                        imgv1.setImage(image);
                        break;
                    case "img2":  
                        if(!tag.equals("default"))
                        {title2.setText(tag);}
                        adBtn2.setVisible(true);
                        imgv2.setImage(image);    
                        break;
                    case "img3":  
                        if(!tag.equals("default"))
                        {title3.setText(tag);}
                        adBtn3.setVisible(true);
                        imgv3.setImage(image);    
                        break;
                    case "img4":  
                        if(!tag.equals("default"))
                        {title4.setText(tag);}
                        adBtn4.setVisible(true);
                        imgv4.setImage(image);    
                        break;
                    case "img5":  
                        if(!tag.equals("default"))
                        {title5.setText(tag);}
                        adBtn5.setVisible(true);
                        imgv5.setImage(image);    
                        break;
                    case "img6":  
                        if(!tag.equals("default"))
                        {title6.setText(tag);}
                        adBtn6.setVisible(true);
                        imgv6.setImage(image);    
                        break;
                        // cardImg1.setImage(new Image("imagescards/" + CardsGame() + ".png"));
                }
                System.out.println("firsttttttttt"+rs.getString(4));
//                data.add(new ShortcutList(rs.getString(2),rs.getString(3),rs.getString(4)));
//                System.out.println("data..."+data.toString()+"xxxxx");
                    
                    i++;
            }
            }
           //     ShortcutTable.setItems(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
   }

    private void firstShortcut(int n) throws URISyntaxException, IOException {
        
           if(n>=i){
              mb.show();
               
               System.out.println(".........back............");
               loadDataFromDatabase();
           }
           else{
               if(kind[n].equalsIgnoreCase("link"))
               {
                   Desktop.getDesktop().browse(new URI(path[n]));
               }
               else if(kind[n].equalsIgnoreCase("application"))
               {
                   Runtime runTime = Runtime.getRuntime();
                   Process process = runTime.exec(path[n]);
               }
               else if(kind[n].equalsIgnoreCase("folder"))
               {
                      Desktop.getDesktop().open(new File(path[n]));
                }
               else if(kind[n].equalsIgnoreCase("multiple"))
               {
                   String[] d = path[n].split(";;;");
                   int z1=0;
                   System.out.println("started "+d.length);
                   while(z1<d.length)
                   {
                       System.out.println("ddddd: "+d[z1]);
                       if(d[z1].toLowerCase().contains("https:".toLowerCase()) || d[z1].toLowerCase().contains("www".toLowerCase())|| d[z1].toLowerCase().contains("http".toLowerCase()))
                        {
                            Desktop.getDesktop().browse(new URI(d[z1]));
                        }
                        else if(d[z1].toLowerCase().contains(".".toLowerCase()))
                        {
                            Runtime runTime = Runtime.getRuntime();
                            Process process = runTime.exec(d[z1]);
                        }
                        else if(d[z1].toLowerCase().contains("\\".toLowerCase()))
                        {
                            Desktop.getDesktop().open(new File(d[z1]));
                        }
                       z1++;
                   }
               }
           }
        //To change body of generated methods, choose Tools | Templates.
    }
    
}
