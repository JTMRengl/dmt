/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmt;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import datacenterservice.vo.User;
import gintong.demand.old.vo.TbConnectInfo;
import gintong.demand.old.vo.TbDemandCategory;
import gintong.demand.old.vo.TbDemandCount;
import gintong.demand.old.vo.TbDemandReport;
import gintong.demand.old.vo.TbDemandTag;
import gintong.demand.old.vo.TbUserCategory;
import gintong.demand.old.vo.TbUserDemandPermission;
import gintong.demand.old.vo.TbUserTag;
import gintong.parasol.associate.test.now.TbAssociate;
import gintong.parasol.directory.test.now.TbDirectory;
import gintong.parasol.directory.test.now.TbDirectorySource;
import gintong.parasol.metadata.vo.TbCodeRegion;
import gintong.permission.test.now.TbPermission;
import gintong.phoenix.user.TbUser;
import gintong.tagsTest.now.vo.TbTag;
import gintong.tagsTest.now.vo.TbTagSource;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import org.bson.Document;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author 刘烁
 */
public class DMT extends JFrame {
    private SessionFactory sessionFactory;
    
    private SessionFactory sessionFactoryold;
    private SessionFactory sessionFactorynew;    
    private SessionFactory sessionFactoryuser;    
    
    private JTextArea textArea;
    
    private String numberCode = "";


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DMT dmt = new DMT();
        dmt.init();
        
        dmt.setSize(800, 600);
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        dmt.setLocation(screenWidth / 2 - dmt.getWidth() / 2, screenHeight / 2 - dmt.getHeight() / 2);
        
        dmt.setVisible(true);
    }
    
    public void init() {
        try {
            // TODO code application logic here
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DMT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JLabel jl = new JLabel("需求数据迁移:");
        JButton openButton = new JButton("需求数据迁移:");
        
        openButton.addActionListener((ActionEvent arg0) -> {
            try {
                //mongodb
                MongoClient mongoClient = new MongoClient("localhost", 27017);
                MongoDatabase mongoDatabase = mongoClient.getDatabase("demand");
                MongoCollection<Document> collection = mongoDatabase.getCollection("DemandRelation");
                
                Document newDocument = new Document("title", "MongoDB").  
                append("description", "database").
                append("likes", 100).  
                append("by", "Fly");
                ArrayList documents = new ArrayList();
       //         List<Document>documents = new ArrayList<Document>();  
                documents.add(newDocument);  
     //           collection.insertMany(documents);
                collection.insertOne(newDocument);
                
                FindIterable<Document> findIterable = collection.find();
                MongoCursor<Document> mongoCursor = findIterable.iterator(); 
                while(mongoCursor.hasNext()){
                    Document document = mongoCursor.next();
                    System.out.println(document.get("createTime"));
                    System.out.println(document.get("demandTitle"));
                }
                
        
                //mysql
                Configuration cfg = new Configuration();
                cfg.configure("hibernate.cfg.xml");
                sessionFactory = cfg.buildSessionFactory();
                
                User user = userGet("0000000005");
                System.out.println("user.getAge()=" + user.getAge());
                textArea.append( "user.getAge()=" + user.getAge());
            } catch (RemoteException ex) {
                Logger.getLogger(DMT.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //panel.add(openButton);
        panel.add(jl);
        
 //------------------------------   mongo --->> mongo start  ------------------------------------------------------------------------------
 
        JButton button7 = new JButton("需求中地区迁移");
        button7.addActionListener((ActionEvent arg0) -> {
            int n = 0;
            //mongodb-old
//            MongoClient mongoClient = new MongoClient("192.168.101.131", 27017);
//            MongoDatabase mongoDatabase = mongoClient.getDatabase("demand-test-2");
            MongoClient mongoClient = new MongoClient("192.168.120.133", 27017);
            MongoDatabase mongoDatabase = mongoClient.getDatabase("demand-new");            
            MongoCollection<Document> collection = mongoDatabase.getCollection("Demand");

            //写入mysql-标签数据
            Configuration cfgnew = new Configuration();
            cfgnew.configure("hibernate-parasol_metadata.cfg.xml");
            sessionFactorynew = cfgnew.buildSessionFactory();
            
            //mysql-用户数据
            Configuration cfguser = new Configuration();
            cfguser.configure("phoenix_user_hibernate.cfg.xml");
            sessionFactoryuser = cfguser.buildSessionFactory();            

            //省id
            String provinceId = "";
            //市id
            String cityId = "";
            //区id
            String districtId = "";

            try {
                FindIterable<Document> findIterable = collection.find();
                MongoCursor<Document> mongoCursor = findIterable.iterator();
                while (mongoCursor.hasNext()) {
                    n++;
                    Document document = mongoCursor.next();
                    System.out.println("当前数据 _id = " + document.get("_id"));
                    Document newDocument = new Document();
                    String[] areas = null;
                    if (document.get("area") != null) {
                        areas = document.get("area").toString().split("-");
                        if (areas != null && areas.length > 1) {

                            Document provinceDoc = new Document();
                            Document cityDoc = new Document();
                            Document districtDoc = new Document();
                            Document area = new Document();

                            for (int i = 0; i < areas.length; i++) {
                                if (i == 0) {
                                    continue;
                                }

                                if (i == 1) {//省
                                    provinceId = getProvinceId(0, areas[i]) + "";
                                    //System.out.println(areas[i] + "i = " + i + " 省id = " + provinceId);
                                    provinceDoc.append("_id", provinceId).append("name", areas[i]);
                                    area.append("province", provinceDoc);
                                }
                                if (i == 2) {//市
                                    cityId = getCityIdId(provinceId, areas[i]) + "";
                                    //System.out.println(areas[i] + "i = " + i + " 市id = " + cityId);
                                    if (cityId != "") {
                                        cityDoc.append("_id", cityId).append("name", areas[i]);
                                        area.append("city", cityDoc);
                                    } else {
                                        area.append("city", cityDoc);
                                    }
                                }
                                if (i == 3) {//区
                                    districtId = getCityIdId(cityId, areas[i]) + "";
                                   // System.out.println(areas[i] + "i = " + i + " 区id = " + districtId);
                                    if (districtId != "") {
                                        districtDoc.append("_id", districtId).append("name", areas[i]);
                                        area.append("district", districtDoc);
                                    } else {
                                        area.append("district", districtDoc);
                                    }

                                }
                            }

                            newDocument.append("area", area);
                            //修改ownerIsVirtual,1是组织，2是用户

                            //collection.findOneAndUpdate(document, new Document("$set", newDocument));
                        } else {
                            //System.out.println("area is null = " + areas[0]);
                        }
                                               
                    }
                    
                            String ownerId = document.get("ownerId").toString();
                            Boolean bb = getVirtual(Long.parseLong(ownerId));
                            if(bb){
                                newDocument.append("ownerIsVirtual", 1);
                            }else{
                                newDocument.append("ownerIsVirtual", 2);
                            }  

                            collection.updateOne(document, new Document("$set", newDocument));                    
                }
            } catch (Exception ex) {
                Logger.getLogger(DMT.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            System.out.println("总计修改地区字段条数:" + n);
        });
        panel.add(button7);  
                        
  //------------------------------   mongo --->> mongo end  --------------------------------------------------------------------------------
        
 //------------------------------   mysql --->> mysql start  ------------------------------------------------------------------------------
        JButton button1 = new JButton("标签");
        button1.addActionListener((ActionEvent arg0) -> {
            
            Map<String,String> map = new HashMap<String,String>();
            long i = 0;
            int j = 0;
           

            try {            
                            //读取mysql-标签数据
                            Configuration cfgold = new Configuration();
                            cfgold.configure("hibernate-phoenix_demand.cfg.xml");
                            sessionFactoryold = cfgold.buildSessionFactory();
                            
                            //写入mysql-标签数据
                            Configuration cfgnew = new Configuration();
                            cfgnew.configure("hibernate-parasol_tags_test.cfg.xml");
                            sessionFactorynew = cfgnew.buildSessionFactory();  
                            
                            MongoClient mongoClient = new MongoClient("192.168.120.133", 27017);
                            MongoDatabase mongoDatabase = mongoClient.getDatabase("demand-new");
                            MongoCollection<Document> collection = mongoDatabase.getCollection("Demand");   
                            MongoCollection<Document> demandlog = mongoDatabase.getCollection("Demandlog");                            
                            
                            
                            //第一部分
                            List<TbUserTag> tbUserTagList = tbUserTagList();                            
                            for(TbUserTag tbUserTag : tbUserTagList){
                                
                                Session session = sessionFactorynew.openSession();
                                //ssn = session;
                                try{
                                   System.out.println("旧数据的id:" + tbUserTag.getId());

                                   //标签数据写入    
                                   TbTag tbTag = new TbTag();
                                   Transaction tx = session.beginTransaction();

                                   tbTag.setId(i+1);
                                   tbTag.setUserId(tbUserTag.getUserId());
                                   //固定字段，需求为1
                                   tbTag.setAppId(1);
                                   //固定字段，需求为4
                                   tbTag.setTagType(7);
                                   tbTag.setTagName(tbUserTag.getTag());

                                   session.save(tbTag);
                                   
                                   tx.commit();
                                                                       
                                   i++;
                                   map.put(tbUserTag.getId() + "", tbTag.getId() + "");
                                   System.out.println("旧数据的id:" + tbUserTag.getId() + "新数据的id:" + tbTag.getId());
                                   
                                                                       //日志数据写入
                                    Document logDocument = new Document("oldId",tbTag.getId()).
                                            append("type","tb_tag");
                                    demandlog.insertOne(logDocument);
                                }catch(Exception ex){
                                    textArea.append( "id" + tbUserTag.getId() + " 发生异常,\n");
                                    ex.printStackTrace();
                                    continue;
                                    
                                }finally{
                                    session.close();
                                }

                            }  
                            
                          
                            //第二部分
                            //mongodb
//                            MongoClient mongoClient = new MongoClient("192.168.101.131", 27017);
//                            MongoDatabase mongoDatabase = mongoClient.getDatabase("demand-test");
//                            MongoCollection<Document> collection = mongoDatabase.getCollection("Demand");                              
                            
                            List<TbDemandTag> tbDemandTagList = tbDemandTagList();
                            int k = 0;
                            for(TbDemandTag tbDemandTag : tbDemandTagList){
                                
                                BasicDBObject query = new BasicDBObject();
                                query.put("_id",tbDemandTag.getDemandId());
                                Document document = collection.find(query).first();  
                                if(document == null){
                                    continue;
                                }
                                
                                Session session2 = sessionFactorynew.openSession();
                                try{

                                   //标签数据写入    
                                   TbTagSource tbTagSource = new TbTagSource();
                                   Transaction tx = session2.beginTransaction();

                                   tbTagSource.setId(k+1);
                                   
                                   String str = map.get(tbDemandTag.getTagId() + "");
                                   if(str != null){
                                        tbTagSource.setTagId(Long.parseLong(str));
                                   }
                                   
                                   //固定字段，需求为1
                                   tbTagSource.setAppId(1);
                                   //userId,tag的分类（比如是：知识、组织、人、图片）默认0,需要通过tagId从tb_tag中查询userId
                                   
                                   long userId = getUserIdByTagId(tbTagSource.getTagId());
                                   if(userId  == 0){
                                       tbTagSource.setUserId(userId);
                                       System.out.println("userId = 0 时 tbDemandTag.getId :" + tbDemandTag.getId());
                                   }else{
                                       tbTagSource.setUserId(userId);
                                   }
                                   
                                   tbTagSource.setSourceId(tbDemandTag.getDemandId());
                                   //固定字段，需求为4
                                   tbTagSource.setSourceType(7);
                                   
                                   //sourceTitle,从mongo的demand中根基demand_id查询demandTitle
                                   tbTagSource.setSourceTitle(document.get("title") + "");                                                             
                                   
                                   //将日期格式转换成毫秒
                                   SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                   String time = tbDemandTag.getCtime() + "";
                                   Date date = format.parse(time);
                                   
                                   tbTagSource.setCreateAt(date.getTime());

                                   if(tbTagSource.getTagId() != 0){
                                        session2.save(tbTagSource);
                                        tx.commit();  
                                        k++;
                                        System.out.println("tbTagSource表成功入库:" + k + " 条");
                                                                            //日志数据写入
                                         Document logDocument = new Document("oldId",tbTagSource.getId()).
                                                 append("type","tb_tag_source");
                                         demandlog.insertOne(logDocument);                                        
                                   }else{
                                        continue;
                                   }
       
                                   
                                }catch(Exception ex){
                                    textArea.append( "id" + tbDemandTag.getId() + " 发生异常,\n");      
                                    ex.printStackTrace();
                                    continue;
                                }

                            }  

                            
                        } catch (RemoteException ex) {
                            ex.printStackTrace();
                          //  Logger.getLogger(DMT.class.getName()).log(Level.SEVERE, null, ex);
                        
                        }
            
        });
        panel.add(button1);        
        
        JButton button2 = new JButton("目录");
        button2.addActionListener((ActionEvent arg0) -> {
            int i = 0;
             int k = 0;
             int w = 0;
            Map<String,String> map = new HashMap<String,String>();
             try {            
                            //读取mysql-目录数据
                            Configuration cfgold = new Configuration();
                            cfgold.configure("hibernate-phoenix_demand.cfg.xml");
                            sessionFactoryold = cfgold.buildSessionFactory();
                            
                            //写入mysql-目录数据
                            Configuration cfgnew = new Configuration();
                            cfgnew.configure("hibernate-parasol_tags_test.cfg.xml");
                            sessionFactorynew = cfgnew.buildSessionFactory();  
                            
                            MongoClient mongoClient = new MongoClient("192.168.120.133", 27017);
                            MongoDatabase mongoDatabase = mongoClient.getDatabase("demand-new");
                            MongoCollection<Document> collection = mongoDatabase.getCollection("Demand");   
                            MongoCollection<Document> demandlog = mongoDatabase.getCollection("Demandlog");                            

                            
                            //第一部分
                            List<TbUserCategory> tbUserCategoryList = tbUserCategoryList();                            
                            for(TbUserCategory tbUserCategory : tbUserCategoryList){
                                Session session = sessionFactorynew.openSession();
                                try{
                                    
                                    TbDirectory tbDirectory = new TbDirectory();
                                    Transaction tx = session.beginTransaction();
                                    tbDirectory.setId(k+1);
                                    tbDirectory.setPid(tbUserCategory.getParentId());
                                    tbDirectory.setAppId(1);   
                                    tbDirectory.setUserId(tbUserCategory.getUserId());
                                    tbDirectory.setName(tbUserCategory.getCategoryName());
                                    tbDirectory.setNameIndex("");
                                    tbDirectory.setNameIndexAll("");
                                    tbDirectory.setRemark("");
 
                                    //调用递归方法返回numberCode
                                    if(tbDirectory.getPid() == 0){
                                        tbDirectory.setNumberCode(tbUserCategory.getId() + "");
                                    }else{
                                        getNumberCode(tbUserCategory.getId(),tbUserCategory.getParentId(),"",false);
                                        tbDirectory.setNumberCode(numberCode);
                                    }
                                    System.out.println("替换前NumberCode------------------------"  + " pid=" + tbDirectory.getPid());
                                    
                                    tbDirectory.setOrderNo(0);
                                    tbDirectory.setTypeId(7);
                                    tbDirectory.setUpdateAt(System.currentTimeMillis());                               
                                    
                                   session.save(tbDirectory);
                                   tx.commit();            
                                   k++;
                                   System.out.println("旧id:" + tbUserCategory.getId() + " 新id" + tbDirectory.getId());
                                   map.put(tbUserCategory.getId() + "", tbDirectory.getId() + "");
                                   
                                                                       //日志数据写入
                                    Document logDocument = new Document("oldId",tbDirectory.getId()).
                                            append("type","tb_directory");
                                    demandlog.insertOne(logDocument);                                   
                                }catch(Exception ex){
                                    textArea.append( "id" + tbUserCategory.getId() + " 发生异常,\n");      
                                    ex.printStackTrace();;
                                }finally{
                                    if(session != null){
                                        session.close();
                                    }
                                }
                                
                               
                             
                            }  
                             //循环将pid,numberCode中旧id用新id替换
                                List<TbDirectory> tbDirectoryList = tbDirectoryListByTypeId();
                                for(TbDirectory tbDirectory : tbDirectoryList){
                                    Session ss = sessionFactorynew.openSession();
                                    try{
                                        Transaction tx = ss.beginTransaction();
                                        if(tbDirectory.getPid() != 0){
                                            tbDirectory.setPid(Long.parseLong(map.get(tbDirectory.getPid() + "")));
                                        }else{
                                            tbDirectory.setPid(0);
                                        }
                                        String numberCode = tbDirectory.getNumberCode();
                                        String str = "";
                                        if(numberCode != null && numberCode.length() > 1){
                                            String[] numberCodes = numberCode.split("-");
                                            for(int j = 0; j< numberCodes.length; j++){
                                                numberCodes[j] = map.get(numberCodes[j] + "");
                                                str = str + "-" + numberCodes[j];
                                            } 
                                            str = str.substring(1);
                                            tbDirectory.setNumberCode(str);
                                            System.out.println("替换后NumberCode------------------------" + tbDirectory.getNumberCode() + " pid=" + tbDirectory.getPid());
                                        }

                                        ss.update(tbDirectory);
                                        tx.commit();
                                        i++;                                       
                                    }catch(Exception ex){
                                       textArea.append( "id" + tbDirectory.getId() + " 发生异常,\n");      
                                       ex.printStackTrace();;
                                   }finally{
                                        ss.close();
                                   }   
                                }
                            
                            //mongodb
//                            MongoClient mongoClient = new MongoClient("192.168.101.131", 27017);
//                            MongoDatabase mongoDatabase = mongoClient.getDatabase("demand-test");
//                            MongoCollection<Document> collection = mongoDatabase.getCollection("Demand");                         
                            
                            
                            //第二部分
                           
                            List<TbDemandCategory> tbDemandCategoryList = tbDemandCategoryList();
                            for(TbDemandCategory tbDemandCategory : tbDemandCategoryList){
                                
                                Session session = sessionFactorynew.openSession();
                                try{
                                   //目录数据写入    
                                   TbDirectorySource tbDirectorySource = new TbDirectorySource();
                                   
                                   //sourceTitle,从mongo的demand中根基demand_id查询demandTitle
                                   BasicDBObject query = new BasicDBObject();
                                   query.put("_id",tbDemandCategory.getDemandId());
                                   Document document = collection.find(query).first();  
                                   if(document != null){
                                            tbDirectorySource.setSourceTitle(document.get("title") + "");        
                                            
                                            if(document.get("_id") != null){
                                                 String demandId = document.get("_id").toString();
                                                long dId = Long.parseLong(demandId);
                                                tbDirectorySource.setSourceId(dId);     
                                                

                                   Transaction tx = session.beginTransaction();
                                   tbDirectorySource.setId(w+1);
                                   tbDirectorySource.setDirectoryId(Long.parseLong(map.get(tbDemandCategory.getCategoryId() + "")));
                                   tbDirectorySource.setUserId(getTbDirectoryUserIdByTagId(Long.parseLong(map.get(tbDemandCategory.getCategoryId() + ""))));
                                   tbDirectorySource.setAppId(1);
                                   tbDirectorySource.setSourceType(7);
                                   tbDirectorySource.setSourceUrl("");
                                   
                                   tbDirectorySource.setSourceData("");
                                   tbDirectorySource.setInvokeMethod("");
                                   
                                   //将日期格式转换成毫秒
                                   SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                   String time = tbDemandCategory.getCtime() + "";
                                   Date date = format.parse(time);

                                   tbDirectorySource.setCreateAt(date.getTime());

                                   session.save(tbDirectorySource);
                                   tx.commit();        
                                   w++;           
                                                                            //日志数据写入
                                         Document logDocument = new Document("oldId",tbDirectorySource.getId()).
                                                 append("type","tb_directory_source");
                                         demandlog.insertOne(logDocument);                                    
                                            }    
                                   }   
                                }catch(Exception ex){
                                    textArea.append( "id" + tbDemandCategory.getId() + " 发生异常,\n");   
                                    ex.printStackTrace();
                                    k++;
                                    System.out.println("重复数据:" + k);
                                    continue;
                                }finally{
                                    session.close();
                                }

                            }  

                            
                        } catch (RemoteException ex) {
                            ex.printStackTrace();
                          //  Logger.getLogger(DMT.class.getName()).log(Level.SEVERE, null, ex);
                        }           
        });
        panel.add(button2);
        
        
        JButton button3 = new JButton("关联");
        button3.addActionListener((ActionEvent arg0) -> {
            
            int j = 0;
            int k = 0;
             try {            
                            //读取mysql-关联数据
                            Configuration cfgold = new Configuration();
                            cfgold.configure("hibernate-phoenix_demand.cfg.xml");
                            sessionFactoryold = cfgold.buildSessionFactory();
                            
                            //写入mysql-关联数据
                            Configuration cfgnew = new Configuration();
                            cfgnew.configure("hibernate-parasol_tags_test.cfg.xml");
                            sessionFactorynew = cfgnew.buildSessionFactory();     
                            
                            MongoClient mongoClient = new MongoClient("192.168.120.133", 27017);
                            MongoDatabase mongoDatabase = mongoClient.getDatabase("demand-new");
                            MongoCollection<Document> demandlog = mongoDatabase.getCollection("Demandlog");         
                            MongoCollection<Document> demandTypecollection = mongoDatabase.getCollection("DemandType");
                            
                            //旧数据Demand链接
                           // MongoClient oldDemandmongoClient = new MongoClient("192.168.101.131", 27017);
                            //MongoDatabase oldDemandmongoDatabase = oldDemandmongoClient.getDatabase("demand-test-2");            
                            //MongoCollection<Document> demandTypecollection = oldDemandmongoDatabase.getCollection("DemandType");
                            
                            
                            List<TbConnectInfo> tbConnectInfoList = tbConnectInfoList();
                            for(TbConnectInfo tbConnectInfo : tbConnectInfoList){
                                
                                //type查询
                                String type = "";
                                BasicDBObject query = new BasicDBObject();
                                query.put("typeId",tbConnectInfo.getConnId());
                                FindIterable<Document> findIterable = demandTypecollection.find(query);
                                MongoCursor<Document> mongoCursor = findIterable.iterator(); 
                                while(mongoCursor.hasNext()){
                                    Document document = mongoCursor.next();
                                    System.out.println(document.get("oldId"));
                                    System.out.println(document.get("typeId"));
                                    System.out.println(document.get("type"));
                                    type = document.get("type") + "";
                                    if(type.length() > 0){
                                        break;
                                    }
                                }
                                
                                //columnType查询
                                String columnType = "";
                                BasicDBObject columnTypeQuery = new BasicDBObject();
                                query.put("oldId",tbConnectInfo.getDemandId());
                                FindIterable<Document> columnTypefindIterable = demandTypecollection.find(columnTypeQuery);
                                MongoCursor<Document> columnTypemongoCursor = columnTypefindIterable.iterator(); 
                                while(columnTypemongoCursor.hasNext()){
                                    Document document = columnTypemongoCursor.next();
                                    System.out.println(document.get("oldId"));
                                    Object o = document.get("columnType");
                                    if(o != null){
                                        columnType = o.toString();
                                    }
                                    if(columnType.length() > 0){
                                        break;
                                    }
                                }                                
                              
                                Session session = sessionFactorynew.openSession();
                                try{
                                    if(tbConnectInfo.getConnType() == 1 || tbConnectInfo.getConnType() == 2 || tbConnectInfo.getConnType() == 5 || tbConnectInfo.getConnType() == 6){
                                        System.out.println("id:" + tbConnectInfo.getId());

                                        //关联数据写入    
                                        TbAssociate tbAssociate = new TbAssociate();
                                        Transaction tx = session.beginTransaction();

                                        //tbAssociate.setId(tbConnectInfo.getId());
                                        if(tbConnectInfo.getOwnerId() != null){
                                            tbAssociate.setUserId(tbConnectInfo.getOwnerId());
                                        }
                                        tbAssociate.setAppId(1);
                                        tbAssociate.setSourceTypeId(7);
                                        tbAssociate.setSourceId(tbConnectInfo.getDemandId());
                                        
                                        if(tbConnectInfo.getConnName() != null){
                                            tbAssociate.setAssocTitle(tbConnectInfo.getConnName());
                                        }else{
                                           tbAssociate.setAssocTitle("");
                                        }
                                        
                                        tbAssociate.setAssocDesc(tbConnectInfo.getTag());

                                        //assocTypeId
                                        if(tbConnectInfo.getConnType() == 1){
                                             tbAssociate.setAssocTypeId(7);
                                        }
                                        if(tbConnectInfo.getConnType() == 2){
                                             tbAssociate.setAssocTypeId(1);
                                        }
                                        if(tbConnectInfo.getConnType() == 5){
                                             tbAssociate.setAssocTypeId(3);
                                        }
                                        if(tbConnectInfo.getConnType() == 6){
                                             tbAssociate.setAssocTypeId(8);
                                        }                                   

                                        tbAssociate.setAssocId(tbConnectInfo.getConnId());
                                        
                                        
                                        String assocMetadata ="{";
                                        //拼接头像
                                        if(tbConnectInfo.getPicPath() == null){
                                            assocMetadata += "\"userPicPath\":\"\",";
                                        }else{
                                            //仿真数据
                                            assocMetadata += "\"userPicPath\":\"" + "http://test.online.gintong.com/cross"  + tbConnectInfo.getPicPath() + "\",";
                                        }   
                                       
                                        if(type.equals("")){
                                            assocMetadata += "\"type\":\"\",";
                                        }else{
                                            assocMetadata += "\"type\":\" " + type + " \",";
                                        }  
                                        
                                        if(!columnType.equals("0") && columnType.length() > 0){
                                            assocMetadata += "\"columnType\":\" " + columnType + " \"";
                                        }else{
                                            assocMetadata += "\"columnType\":\"\"";
                                        }                                        
                                        
                                        assocMetadata += "}";
                                        
                                        if(assocMetadata.length() > 0){
                                            tbAssociate.setAssocMetadata(assocMetadata);
                                        }
                                        
                                   //将日期格式转换成毫秒;                
                                    tbAssociate.setCreateAt(System.currentTimeMillis());
                                    
                                    tbAssociate.setUserName("");

                                    session.save(tbAssociate);
                                    
                                    System.out.println("迁移成功id:" + tbAssociate.getId());
                                    tx.commit();        
                                    k++;     
                                                                       //日志数据写入
                                    Document logDocument = new Document("oldId",tbAssociate.getId()).
                                            append("type","tb_associate");
                                    demandlog.insertOne(logDocument);                                     
                                }
                                    
                                }catch(Exception ex){
                                    textArea.append( "id" + tbConnectInfo.getId() + " 发生异常,\n");     
                                    ex.printStackTrace();
                                }finally{
                                    session.close();
                                }

                            }  

                            System.out.println("需求关联迁移条数:"+k);
                        } catch (RemoteException ex) {
                            ex.printStackTrace();
                          //  Logger.getLogger(DMT.class.getName()).log(Level.SEVERE, null, ex);
                        }           
        });
        panel.add(button3);   
        
        
        JButton button6 = new JButton("权限");
        button6.addActionListener((ActionEvent arg0) -> {
            int n = 0;
          
                            //读取mysql-权限数据
//                            Configuration cfgold = new Configuration();
//                            cfgold.configure("hibernate-phoenix_demand.cfg.xml");
//                            sessionFactoryold = cfgold.buildSessionFactory();
                            
                            //写入mysql-权限数据
                            Configuration cfgnew = new Configuration();
                            cfgnew.configure("hibernate-parasol_tags_test.cfg.xml");
                            sessionFactorynew = cfgnew.buildSessionFactory();    
                           
                            MongoClient mongoClient = new MongoClient("192.168.120.133", 27017);
                            MongoDatabase mongoDatabase = mongoClient.getDatabase("demand-new");
                            MongoCollection<Document> demandlog = mongoDatabase.getCollection("Demandlog");    
                            MongoCollection<Document> collection = mongoDatabase.getCollection("Demand");
                            
                            
//                            ServerAddress serverAddress=new ServerAddress("192.168.120.133", 27017);
//                            MongoOptions mongoOptions=new MongoOptions();
//                            Mongo m = new Mongo(serverAddress,mongoOptions); 
//                            m.addOption(com.mongodb.Bytes.QUERYOPTION_NOTIMEOUT);
                            
                            
                            FindIterable<Document> findIterable = collection.find();
                            MongoCursor<Document> mongoCursor = findIterable.iterator();
                            
                            List<Document> list = new ArrayList();
                            while (mongoCursor.hasNext()) {
                                 
                                 try {  
                                    Document document = mongoCursor.next();
                                    list.add(document);

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                  //  Logger.getLogger(DMT.class.getName()).log(Level.SEVERE, null, ex);
                                }                               
                                
                            }                            
                            for(int i=0;i<list.size();i++){
                                Session session = sessionFactorynew.openSession();
                                try { 
                                    
                                    Document document = list.get(i);
                                    //需求id
                                    long id = Long.parseLong(document.get("_id").toString());
                                    
                                    if(id > 1000000){
                                        continue;
                                    }

                                    //res_owner_id
                                    long ownerId = Long.parseLong(document.get("ownerId").toString());    
                                
                                
                                    //需求createTimes
                                    SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                                    long createTime = Long.parseLong(document.get("createTime").toString());
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTimeInMillis(createTime);
                                    calendar.getTime();
                                    System.out.println(format.format(calendar.getTime()));
                                

                                    TbPermission tbPermission = new TbPermission();
                                    Transaction tx = session.beginTransaction(); 
                                    tbPermission.setResOwnerId(ownerId);
                                    tbPermission.setResId(id);
                                    short st = 7;
                                    tbPermission.setResType(st);
                                    tbPermission.setResAccRule("");
                                    tbPermission.setPublicFlag(1);
                                    tbPermission.setShareFlag(1);
                                    tbPermission.setConnectFlag(1);
                                    tbPermission.setPerTime(calendar.getTime());
                                    long aId = 7647448850l;
                                    tbPermission.setAppId(aId);
                                
                                    session.save(tbPermission);
                                    tx.commit();
                                    //日志数据写入
                                    Document logDocument = new Document("oldId",tbPermission.getPerId()).
                                    append("type","tb_permission");
                                    demandlog.insertOne(logDocument);
                                
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                  //  Logger.getLogger(DMT.class.getName()).log(Level.SEVERE, null, ex);
                                }finally{
                                    session.close();
                                }                                    
                            }
                            
                            
//                            
//                            for(TbUserDemandPermission tbUserDemandPermission : tbUserDemandPermissionList){
//                                
//                                
//                                Session session = sessionFactorynew.openSession();
//                                try{
//
//
//                                   //权限数据写入    
//                                   TbPermission tbPermission = new TbPermission();
//                                   Transaction tx = session.beginTransaction();
//                                   tbPermission.setResId(tbUserDemandPermission.getDemandId());
//                                   short st = 7;
//                                   tbPermission.setResType(st);
//                                   tbPermission.setResAccRule("");
//                                   tbPermission.setResOwnerId(tbUserDemandPermission.getSendId());
//                                   if(tbUserDemandPermission.getPtype() == 1){
//                                       tbPermission.setPublicFlag(0);
//                                       tbPermission.setConnectFlag(0);
//                                       tbPermission.setShareFlag(0);
//                                   }
//                                   if(tbUserDemandPermission.getPtype() == 2){
//                                       if(tbUserDemandPermission.getReceiveId() == 0 || tbUserDemandPermission.getReceiveId() == -1){
//                                            tbPermission.setPublicFlag(1);
//                                            tbPermission.setConnectFlag(1);
//                                            tbPermission.setShareFlag(1);                                       
//                                       }else{
//                                            tbPermission.setPublicFlag(0);
//                                            tbPermission.setConnectFlag(1);
//                                            tbPermission.setShareFlag(1);                                       
//                                       }
//                                   }
//                                   if(tbUserDemandPermission.getPtype() == 3){
//                                            tbPermission.setPublicFlag(0);
//                                            tbPermission.setConnectFlag(1);
//                                            tbPermission.setShareFlag(0);                                  
//                                   }
//                                   if(tbUserDemandPermission.getPtype() == 4){
//                                            tbPermission.setPublicFlag(0);
//                                            tbPermission.setConnectFlag(1);
//                                            tbPermission.setShareFlag(0);                                   
//                                   }   
//                                   
//                                   //将日期格式转换成毫秒
//                                   SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                                   String time = tbUserDemandPermission.getCreatetime() + "";
//                                   Date date = format.parse(time);
//                                   System.out.print("Format To times:" + date.getTime());  
//                                   tbPermission.setPerTime(tbUserDemandPermission.getCreatetime());
//                                   tbPermission.setAppId(1);
                                   
                                   //新表
                                   //res_type = 3;
                                   //res_acc_rule = "";
                                   //res_id = demandId;
                                   //res_owner_id = userId;
                                    
                                    
//                                    public_flag = 
//                                    说明:判断
//                                    pType = 1(独乐) public_flag = 0;connect_flag=0;share_flag=0;
//                                    pType = 2(大乐) public_flag = 0;connect_flag=1;share_flag=1;
//                                    判断receiveId0或-1 public_flag = 1;connect_flag=1;share_flag=1;
//                                                    public_flag = 0;connect_flag=1;share_flag=1;
//                                    
//                                    
//                                    pType = 3(中乐) public_flag = 0;connect_flag=1;share_flag=0;
//                                    pType = 4(小乐) public_flag = 0;connect_flag=1;share_flag=0;
                                    

                                           
                                    //转类型
                                   //per_time       
                                   //app_id = 1;
                                  
//                                   session.save(tbPermission);
//                                   tx.commit(); 
                                   
//                                                                       //日志数据写入
//                                    Document logDocument = new Document("oldId",tbPermission.getPerId()).
//                                            append("type","tb_permission");
//                                    demandlog.insertOne(logDocument);                                   
                                   
//                                }catch(Exception ex){
//                                    textArea.append( "id" + tbUserDemandPermission.getId() + " 发生异常,\n");      
//                                    ex.printStackTrace();
//                                    continue;
//                                }finally{
//                                    session.close();
//                                }
//
//                            }

                            

        });
        panel.add(button6);    
        
//------------------------------   mysql --->> mysql end  ------------------------------------------------------------------------------
        
 //------------------------------   mysql --->> mongo start ------------------------------------------------------------------------------       
        JButton button4 = new JButton("举报");
        button4.addActionListener((ActionEvent arg0) -> {
            
            
                //mongodb
                //MongoClient mongoClient = new MongoClient("192.168.101.131", 27017);
                MongoClient mongoClient = new MongoClient("192.168.120.133", 27017);
                //MongoDatabase mongoDatabase = mongoClient.getDatabase("demand-test-2");
                MongoDatabase mongoDatabase = mongoClient.getDatabase("demand-new");
                MongoCollection<Document> collection = mongoDatabase.getCollection("Report");   
                MongoCollection<Document> demandlog = mongoDatabase.getCollection("Demandlog");                
            
             try {            
                
                            //读取mysql-举报数据
                            Configuration cfgold = new Configuration();
                            cfgold.configure("hibernate-phoenix_demand.cfg.xml");
                            sessionFactoryold = cfgold.buildSessionFactory();
                            
                            List<TbDemandReport> tbDemandReportList = tbDemandReportList();
                            for(TbDemandReport tbDemandReport : tbDemandReportList){
                                
                                if(tbDemandReport.getDemandId() != null){
                                    System.out.println("id:" + tbDemandReport.getId());

                                    //将日期格式转换成毫秒
                                    SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    String time = tbDemandReport.getCreateTime() + "";
                                    Date date = format.parse(time);                                                                    


                                    //举报数据写入    
                                    Document newDocument = new Document("_id",tbDemandReport.getId()).
                                    append("_class", "com.ginkgocap.ywxt.demand.model.DemandReport").  
                                    append("demandId", tbDemandReport.getDemandId()).
                                    append("userId", tbDemandReport.getUserId()).  
                                    append("userName", tbDemandReport.getUserName()).
                                    append("reason", tbDemandReport.getReason()). 
                                    append("userId", tbDemandReport.getUserId()).
                                    append("createTime", date.getTime()).
                                    append("content", tbDemandReport.getContent());
                                    collection.insertOne(newDocument);  
                                    
                                    //日志数据写入
                                    Document logDocument = new Document("oldId",tbDemandReport.getId()).
                                            append("type","Report");
                                    demandlog.insertOne(logDocument);
                                }
                                
 
                            }                            
                        } catch (RemoteException ex) {
                            Logger.getLogger(DMT.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {           
                Logger.getLogger(DMT.class.getName()).log(Level.SEVERE, null, ex);
            }           
        });
        panel.add(button4);     
   
        JButton button5 = new JButton("统计");
        button5.addActionListener((ActionEvent arg0) -> {
            
 
                //mongodb192.168.101.131
                MongoClient mongoClient = new MongoClient("192.168.120.133", 27017);
                //MongoDatabase mongoDatabase = mongoClient.getDatabase("demand-test-2");
                MongoDatabase mongoDatabase = mongoClient.getDatabase("demand-new");
                MongoCollection<Document> collection = mongoDatabase.getCollection("DemandCount");        
                MongoCollection<Document> demandCountlog = mongoDatabase.getCollection("Demandlog");            
            
                try {            

                               //读取mysql-统计数据
                               Configuration cfgold = new Configuration();
                               cfgold.configure("hibernate-phoenix_demand.cfg.xml");
                               sessionFactoryold = cfgold.buildSessionFactory();

                               List<TbDemandCount> tbDemandCountList = tbDemandCountList();
                               for(TbDemandCount tbDemandCount : tbDemandCountList){

                                   System.out.println("id:" + tbDemandCount.getId());                                                                   
                                   long demandType = tbDemandCount.getDemandType();
                                   if(demandType == 1){
                                       demandType = 257;
                                   }
                                   if(demandType == 2){
                                       demandType = 258;
                                   }                                

                                   //统计数据写入    
                                   Document newDocument = new Document("_id",tbDemandCount.getId()).
                                   append("_class", "com.ginkgocap.ywxt.demand.model.DemandCount"). 
                                   append("demandId", tbDemandCount.getDemandId()).
                                   append("readcount", tbDemandCount.getReadCount()).  
                                   append("sharecount", tbDemandCount.getShareCount()).
                                   append("collectcount", tbDemandCount.getCollectCount()). 
                                   append("evaluatecount", tbDemandCount.getEvaluateCount()).
                                   append("score", (tbDemandCount.getReadCount()*1 + tbDemandCount.getShareCount()*5 + tbDemandCount.getCollectCount()*5 + tbDemandCount.getEvaluateCount()*2)).
                                   append("demandType", demandType);
                                   collection.insertOne(newDocument);
                                   
                                    //日志数据写入
                                    Document logDocument = new Document("oldId",tbDemandCount.getId()).
                                            append("type","DemandCount");
                                    demandCountlog.insertOne(logDocument);                                   
                               }                            
                           } catch (RemoteException ex) {
                               Logger.getLogger(DMT.class.getName()).log(Level.SEVERE, null, ex);
                           } 
           
            
            
//                try {            
//                
//            
//                    //mongodb
//                    MongoClient mongoClient = new MongoClient("192.168.101.131", 27017);
//                    MongoDatabase mongoDatabase = mongoClient.getDatabase("demand-test");
//                    MongoCollection<Document> collection = mongoDatabase.getCollection("DemandCount");    
//
//                    //mysql
//                    Configuration cfgold = new Configuration();
//                    cfgold.configure("hibernate-phoenix_demand.cfg.xml");
//                    sessionFactorynew = cfgold.buildSessionFactory();                
//
//                    FindIterable<Document> findIterable = collection.find();
//                    MongoCursor<Document> mongoCursor = findIterable.iterator(); 
//                    while(mongoCursor.hasNext()){
//
//                        Session session = sessionFactorynew.openSession();
//                        TbDemandCount tbDemandCount = new TbDemandCount();
//                        Transaction tx = session.beginTransaction();                    
//
//                        Document document = mongoCursor.next();
//                        System.out.println(document.get("_id"));
//                        System.out.println(document.get("demandId"));
//                        System.out.println(document.get("readcount"));
//                        System.out.println(document.get("sharecount"));
//                        System.out.println(document.get("collectcount"));
//                        System.out.println(document.get("evaluatecount"));
//                        System.out.println(document.get("_class"));
//                        System.out.println(document.get("score"));
//                        System.out.println(document.get("demandType"));
//
//                        tx.commit();
//                    }   
//                    
//                } catch (Exception ex) {
//                            Logger.getLogger(DMT.class.getName()).log(Level.SEVERE, null, ex);
//                }         
        });
        panel.add(button5);
        
        textArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(textArea);
        this.add(scroll, BorderLayout.CENTER);
        
        this.add(panel,BorderLayout.NORTH);
    }

    public User userGet(String code) throws RemoteException {
        User user = null;
        String hql = "from User where code='" + code + "'";
        System.out.println("hql=" + hql);
        try {
            Session session = sessionFactory.openSession();
            user = (User)session.createQuery(hql)
            .setMaxResults(1)
            .uniqueResult();
  //          session.get
            session.close();
        } catch(Exception ex) {
        }

        return user;
    }
    
    //查询老标签集合第一部分tb_user_tag
    public List<TbUserTag> tbUserTagList() throws RemoteException {
        
        List<TbUserTag> tbUserTagList = null;
        try {        
            String hql = "from TbUserTag";
            System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbUserTagList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbUserTagList;
    } 
    
    //查询老标签集合第二部分tb_demand_tag
    public List<TbDemandTag> tbDemandTagList() throws RemoteException {
        
        List<TbDemandTag> tbDemandTagList = null;
        try {        
            String hql = "from TbDemandTag";
            System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbDemandTagList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbDemandTagList;
    }   
    
    //查询老目录集合第一部分tb_user_category
    public List<TbUserCategory> tbUserCategoryList() throws RemoteException {
        
        List<TbUserCategory> tbUserCategoryList = null;
        try {        
            String hql = "from TbUserCategory order by id desc";
            System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbUserCategoryList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbUserCategoryList;
    }   
    
     //查询新需求目录集合第一部分,按分类查询
    public List<TbDirectory> tbDirectoryListByTypeId() throws RemoteException {
        
        List<TbDirectory> tbDirectoryList = null;
        try {        
            String hql = "from TbDirectory where typeId = 7 order by id desc";
            System.out.println("hql=" + hql);
            Session ss = sessionFactorynew.openSession();
            tbDirectoryList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbDirectoryList;
    }    
    
    //查询老目录集合第二部分tb_user_category
    public List<TbDemandCategory> tbDemandCategoryList() throws RemoteException {
        
        List<TbDemandCategory> tbDemandCategoryList = null;
        try {        
            String hql = "from TbDemandCategory order by id desc";
            System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbDemandCategoryList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbDemandCategoryList;
    }  
    
    
    //查询老关联集合
    public List<TbConnectInfo> tbConnectInfoList() throws RemoteException {
        
        List<TbConnectInfo> tbConnectInfoList = null;
        try {        
            String hql = "from TbConnectInfo order by id desc";
            System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbConnectInfoList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbConnectInfoList;
    } 
  
    //查询老关联集合
    public List<TbUserDemandPermission> tbUserDemandPermissionList() throws RemoteException {
        
        List<TbUserDemandPermission> tbUserDemandPermissionList = null;
        try {        
            String hql = "from TbUserDemandPermission order by id desc";
            System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbUserDemandPermissionList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbUserDemandPermissionList;
    }    
    
    
    
    //查询老举报集合
    public List<TbDemandReport> tbDemandReportList() throws RemoteException {
        
        List<TbDemandReport> tbDemandReportList = null;
        try {        
            String hql = "from TbDemandReport order by id desc";
            System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbDemandReportList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbDemandReportList;
    } 
    
    //查询老数据需求统计集合
    public List<TbDemandCount> tbDemandCountList() throws RemoteException {
        
        List<TbDemandCount> tbDemandCountList = null;
        try {        
            String hql = "from TbDemandCount order by id desc";
            System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbDemandCountList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbDemandCountList;
    }    
    
    //通过TagId查询tb_tag获取UserId
    public long getUserIdByTagId(long tagId) throws RemoteException{
        long userId = 0;
        
         try {        
            String hql = "from TbTag where id = " + tagId;
            System.out.println("hql=" + hql);
            Session ss = sessionFactorynew.openSession();
            TbTag tbTag = (TbTag) ss.createQuery(hql).setMaxResults(1).uniqueResult();
            if(tbTag != null){
                userId = tbTag.getUserId();
            }
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }       
        
        return userId;
    }
    
    //通过directoryId查询tb_directory获取UserId
    public long getTbDirectoryUserIdByTagId(long directoryId) throws RemoteException{
        long userId = 0;
        
         try {        
            String hql = "from TbDirectory where id = " + directoryId;
            System.out.println("hql=" + hql);
            Session ss = sessionFactorynew.openSession();
            TbDirectory tbDirectory = (TbDirectory) ss.createQuery(hql).setMaxResults(1).uniqueResult();
            if(tbDirectory != null){
                userId = tbDirectory.getUserId();
            }
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }       
        
        return userId;
    }    
    
    //通过id查询TbUserCategory获取TbUserCategory
    public TbUserCategory getTbUserCategoryById(long id) throws RemoteException{
        TbUserCategory tbUserCategory = null;
        
         try {        
            String hql = "from TbUserCategory where id = " + id;
            System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbUserCategory = (TbUserCategory) ss.createQuery(hql).setMaxResults(1).uniqueResult();

            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }       
        
        return tbUserCategory;
    }
    
    //通过id查询TbUserCategory获取TbUserCategory
    public TbUserCategory getTbUserCategoryByparentId(long id) throws RemoteException{
        TbUserCategory tbUserCategory = null;
        
         try {        
            String hql = "from TbUserCategory where id = " + id;
            System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbUserCategory = (TbUserCategory) ss.createQuery(hql).setMaxResults(1).uniqueResult();

            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }       
        
        return tbUserCategory;
    }     
    
    
    
    //
    public long getProvinceId(int pid, String cname) throws RemoteException{
        long id = 0;
        
         try {        
            String hql = "from TbCodeRegion where parentId = " + pid + " and cname = '" + cname + "'";
            System.out.println("hql=" + hql);
            Session ss = sessionFactorynew.openSession();
            TbCodeRegion tbCodeRegion = (TbCodeRegion) ss.createQuery(hql).setMaxResults(1).uniqueResult();
            if(tbCodeRegion != null){
                id = tbCodeRegion.getId();
            }
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }       
        
        return id;
    }   
    public Boolean getVirtual(long ownerId) throws RemoteException{
        System.out.println("调用获取getVirtual");
        Boolean virtual = false;
         try {        
            String hql = "from TbUser where id = " + ownerId;
            System.out.println("hql=" + hql);
            Session ss = sessionFactoryuser.openSession();
            TbUser tbUser = (TbUser) ss.createQuery(hql).setMaxResults(1).uniqueResult();
            if(tbUser != null){
                virtual = tbUser.getVirtual();
                if(virtual){
                    System.out.println("virtual:"+virtual);
                }
                
            }
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }       
        
        return virtual;
    }    
    
    
    //
    public long getCityIdId(String provinceId, String cname) throws RemoteException{
        long id = 0;
        
         try {        
            String hql = "from TbCodeRegion where parentId = " + provinceId + " and cname = '" + cname + "'";
            System.out.println("hql=" + hql);
            Session ss = sessionFactorynew.openSession();
            TbCodeRegion tbCodeRegion = (TbCodeRegion) ss.createQuery(hql).setMaxResults(1).uniqueResult();
            if(tbCodeRegion != null){
                id = tbCodeRegion.getId();
            }
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }       
        
        return id;
    }    
    

    //flag:true-递归中调用,flase-非递归调用
    public void getNumberCode(long id,long pid,String ides,boolean flag){
        String str = "";
        numberCode = "";
        if(ides.equals("")){
            str = id + "";
        }else{
            str = ides;
        }
         try {        
            TbUserCategory tbUserCategory = getTbUserCategoryByparentId(pid);
            //判断传入的id有没有父
            if(tbUserCategory != null){
                if(tbUserCategory.getParentId() == 0){
                    if(flag){
                        str = pid + "-" + str;
                    }else{
                        str = pid + "-" + str;
                    }
                    numberCode = str;
                }else{
                    str = pid + "-" + str;
                    getNumberCode(tbUserCategory.getId(),tbUserCategory.getParentId(),str,true);
                }
            }        
        } catch(Exception ex) {
            ex.printStackTrace();
        } 
        System.out.println("numberCode:" + numberCode);
    }     
}
