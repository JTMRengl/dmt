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
import gintong.parasol.associate.test.now.TbAssociate;
import gintong.parasol.directory.test.now.TbDirectory;
import gintong.parasol.directory.test.now.TbDirectorySource;
import gintong.permission.test.now.TbPermission;
import gintong.phoenix.knowledge.old.vo.TbKnowledgeBase;
import gintong.phoenix.knowledge.old.vo.TbKnowledgeCategory;
import gintong.phoenix.knowledge.old.vo.TbKnowledgeCollection;
import gintong.phoenix.knowledge.old.vo.TbKnowledgeComment;
import gintong.phoenix.knowledge.old.vo.TbKnowledgeReport;
import gintong.phoenix.knowledge.old.vo.TbKnowledgeStatics;
import gintong.phoenix.knowledge.old.vo.TbUserCategory;
import gintong.phoenix.knowledge.old.vo.TbUserPermission;
import gintong.phoenix.knowledge.test.now.vo.TbKnowledgeCount;
import gintong.phoenix.knowledge.test.now.vo.TbKnowledgeCountId;
import gintong.tagsTest.now.vo.TbTag;
import gintong.tagsTest.now.vo.TbTagSource;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class DMTKL extends JFrame {
    private SessionFactory sessionFactory;
    
    private SessionFactory sessionFactoryold;
    private SessionFactory sessionFactorynew;
    private Map<Long,String> notOnlyMap = new HashMap<Long,String>();    
    
    private JTextArea textArea;
    private String numberCode = "";    


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DMTKL dmt = new DMTKL();
        dmt.init();
        
        dmt.setSize(900, 600);
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
            Logger.getLogger(DMTKL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JLabel jl = new JLabel("知识数据迁移:");
        JButton openButton = new JButton("知识数据迁移:");
        
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
                Logger.getLogger(DMTKL.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //panel.add(openButton);
        panel.add(jl);
        
        

 //------------------------------   mongo --->> mongo start  ------------------------------------------------------------------------------
 
        JButton button11 = new JButton("知识标签更新");
        button11.addActionListener((ActionEvent arg0) -> {
            int n = 0;
            //mongodb-old
            MongoClient mongoClient = new MongoClient("192.168.101.131", 27017);
            MongoDatabase mongoDatabase = mongoClient.getDatabase("knowledge-test-2");
            MongoCollection<Document> collection = mongoDatabase.getCollection("Knowledge");

            //写入mysql-标签数据
            Configuration cfgnew = new Configuration();
            cfgnew.configure("hibernate-phoenix_knowledge_test.cfg.xml");
            sessionFactorynew = cfgnew.buildSessionFactory();

            try {
                FindIterable<Document> findIterable = collection.find();
                MongoCursor<Document> mongoCursor = findIterable.iterator();
                while (mongoCursor.hasNext()) {
                    n++;
                    Document document = mongoCursor.next();
                    System.out.println("当前数据知识id即:_id = " + document.get("_id"));

                    List<String> tags = new ArrayList<String>();
                    
                    //根据知识id查询base
                    gintong.phoenix.knowledge.test.now.vo.TbKnowledgeBase tbKnowledgeBase = getTbKnowledgeBaseByKnowledgeId(Long.valueOf(document.get("_id").toString()));
                    
                    if (tbKnowledgeBase != null) {
                        if(tbKnowledgeBase.getTags() != null && tbKnowledgeBase.getTags().length() > 0){
                            String[] tagsStr = tbKnowledgeBase.getTags().split(" ");
                            if(tagsStr != null && tagsStr.length > 0){
                                for(int i = 0;i<tagsStr.length; i++){
                                    tags.add(tagsStr[i]);
                                }
                            }
                        }

                        Document newDocument = new Document();
                        newDocument.append("tags", tags);

                        collection.updateOne(document, new Document("$set", newDocument));
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(DMT.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            System.out.println("总计修改知识标签字段条数:" + n);
        });
        panel.add(button11);  
        
        
        JButton button12 = new JButton("知识目录更新");
        button12.addActionListener((ActionEvent arg0) -> {
            int n = 0;
            //mongodb-old
            MongoClient mongoClient = new MongoClient("192.168.101.131", 27017);
            MongoDatabase mongoDatabase = mongoClient.getDatabase("knowledge-test-2");
            MongoCollection<Document> collection = mongoDatabase.getCollection("Knowledge");

            //写入mysql-标签数据
            Configuration cfgnew = new Configuration();
            cfgnew.configure("hibernate-parasol_tags_test.cfg.xml");
            sessionFactorynew = cfgnew.buildSessionFactory();

            try {
                FindIterable<Document> findIterable = collection.find();
                MongoCursor<Document> mongoCursor = findIterable.iterator();
                while (mongoCursor.hasNext()) {
                    n++;
                    Document document = mongoCursor.next();
                    System.out.println("当前数据知识id即:_id = " + document.get("_id"));

                    List<String> categoryIds = new ArrayList<String>();
                    
                    //根据知识id查询tb_directory_source
                    
                    List<TbDirectorySource> tbDirectorySourceList = getTbDirectorySourceListByKnowledgeId(Long.valueOf(document.get("_id").toString()));
                    
                    if(tbDirectorySourceList != null && tbDirectorySourceList.size() > 0){
                        for(TbDirectorySource tbDirectorySource:tbDirectorySourceList){
                            categoryIds.add(tbDirectorySource.getDirectoryId()+"");
                        }
                        
                        Document newDocument = new Document();
                        newDocument.append("categoryIds", categoryIds);

                        collection.updateOne(document, new Document("$set", newDocument));                          
                    }else{
                         Document newDocument = new Document();
                         newDocument.append("categoryIds", categoryIds);

                        collection.updateOne(document, new Document("$set", newDocument));                   
                    }
                    
                  
                }
            } catch (Exception ex) {
                Logger.getLogger(DMT.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            System.out.println("总计修改知识目录字段条数:" + n);
        });
        panel.add(button12);          
                        
 
 //------------------------------   mongo --->> mongo end  --------------------------------------------------------------------------------        
        
        
        
 //------------------------------   mysql --->> mysql start  ------------------------------------------------------------------------------
        JButton button1 = new JButton("标签");
        button1.addActionListener((ActionEvent arg0) -> {
            int j = 0;
            int d = 0;
            int k = 0;
            List<TbKnowledgeBase> tbKnowledgeBaseList = new PhoenixKnowledgeHandler().run();
            try {            
                            
                            //写入mysql-标签数据
                            Configuration cfgnew = new Configuration();
                            cfgnew.configure("hibernate-parasol_tags_test.cfg.xml");
                            sessionFactorynew = cfgnew.buildSessionFactory();
                            
                            //mongodb
                            MongoClient mongoClient = new MongoClient("192.168.101.131", 27017);
                            MongoDatabase mongoDatabase = mongoClient.getDatabase("knowledge-test-2");
                            MongoCollection<Document> knowledgelog = mongoDatabase.getCollection("Knowledgelog");
                            
//                            //第一部分,查询旧知识base集合
//                            for(TbKnowledgeBase tbKnowledgeBase : tbKnowledgeBaseList){
//                                
//                                Session session = sessionFactorynew.openSession();
//                                try{
//                                   
//                                   //处理tag
//                                   String[] tags = null;
//                                   if(tbKnowledgeBase.getTag() != null && tbKnowledgeBase.getTag().length() > 0){
//                                       System.out.println("tbKnowledgeBase.getKnowledgeId() ----------id:" + tbKnowledgeBase.getKnowledgeId() + " 准备入库标签：" + tbKnowledgeBase.getTag());
//                                       String tag = tbKnowledgeBase.getTag();
//                                       tag = tag.replaceAll("'", "''");
//                                       tags = tag.split(" ");
//                                   }
//                                   if(tags != null && tags.length > 0){
//                                        for(int i = 0; i < tags.length; i++){
//                                            String tag = tags[i];                                      
//                                            //根据userId,tagType,tagName联合查询标签表中是否存在,存在跳过,不存在插入
//                                            if(getTagByUserIdTagtypTagname(tbKnowledgeBase.getUserId(),3,tag)){
//                                                continue;
//                                            }else{
//                                                //标签数据写入    
//                                                TbTag tbTag = new TbTag();
//                                                Transaction tx = session.beginTransaction();
//
//                                                tbTag.setId(j+352+1);
//                                                //id自增      
//                                                tbTag.setUserId(tbKnowledgeBase.getUserId());
//                                                //固定字段，需求为1
//                                                tbTag.setAppId(1);
//                                                //固定字段，需求为3
//                                                tbTag.setTagType(3);                                                
//                                                tbTag.setTagName(tags[i]);
//                                                
//                                                session.save(tbTag);
//                                                tx.commit();
//                                                
//                                                System.out.println("新数据的id:" + tbTag.getId() + " 对应的标签名称 ： " + tags[i]);
//                                                j++;
//                                                System.out.println("标签成功插入为： " + j + "条");
//                                                
//                                                                                    //日志数据写入
//                                                 Document logKnowledge = new Document("oldId",tbTag.getId()).
//                                                         append("type","tb_tag");
//                                                 knowledgelog.insertOne(logKnowledge);                                              
//                                            }
//                                        }         
//                                   }
//                                   
//                                }catch(Exception ex){
//                                    //textArea.append( "id" + i + " 发生异常,\n");
//                                    ex.printStackTrace();
//                                    continue;
//                                }finally{
//                                    session.close();
//                                }
//                                
//                            }  
                            
                            //第二部分
//                            for(TbKnowledgeBase tbKnowledgeBase : tbKnowledgeBaseList){
//                                
//                                
//                                
//                                BasicDBObject query = new BasicDBObject();
//                                query.put("_id",tbKnowledgeBase.getKnowledgeId());
//                                Document document = collection.find(query).first();
//                                if(document == null){
//                                    continue;
//                                }                                
//                                
//                                Session session = sessionFactorynew.openSession();
//                                try{
//                                   //处理tag
//                                   String[] tags = null;
//                                   if(tbKnowledgeBase.getTag() != null && tbKnowledgeBase.getTag().length() > 0){
//                                       tags = tbKnowledgeBase.getTag().split(" "); 
//                                       String tag = tbKnowledgeBase.getTag();                                       
//                                       tag = tag.replaceAll("'", "''");
//                                       tags = tag.split(" ");                                        
//                                   }
//                                   if(tags != null && tags.length > 0){
//                                        
//                                        for(int i = 0; i < tags.length; i++){
//                                            //遍历获取新标签id
//                                            long id = 0;
//                                            if(tags[i] != null && tags[i].length() > 0){
//                                                
//                                                id = getTagIdByUserIdTagtypTagname(tbKnowledgeBase.getUserId(),3,tags[i]);
//                                                if(id != 0){
//                                                    //标签source数据写入    
//                                                   TbTagSource tbTagSource = new TbTagSource();
//                                                   Transaction tx = session.beginTransaction();
//                                                   tbTagSource.setId(j + 1 + 3902);
//                                                   //tbTagSource.set
//                                                   tbTagSource.setTagId(id);
//                                                   //固定字段，需求为1
//                                                   tbTagSource.setAppId(1);
//                                                   //userId,tag的分类（比如是：知识、组织、人、图片）默认0,需要通过tagId从tb_tag中查询userId
//                                                   tbTagSource.setUserId(tbKnowledgeBase.getUserId());
//                                                   tbTagSource.setSourceId(tbKnowledgeBase.getKnowledgeId());
//                                                   //固定字段，知识为3
//                                                   tbTagSource.setSourceType(3);    
//
//                                                    //将日期格式转换成毫秒
//                                                    SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                                                    String time = tbKnowledgeBase.getCreatetime() + "";
//                                                    Date date = format.parse(time);
//                                                    System.out.print("Format To times:" + date.getTime());   
//
//                                                    tbTagSource.setCreateAt(date.getTime());
//                                                    
//                                                    //sourceTitle,从mongo的demand中根基demand_id查询demandTitle
//                                                    tbTagSource.setSourceTitle(document.get("title") + "");
//                                                    if(tbTagSource.getTagId() != 0){
//                                                         session.save(tbTagSource);
//                                                         tx.commit();    
//                                                         System.out.println("新数据的id:" + tbTagSource.getId() + " 对应的标签名称 ： " + tags[i]);
//                                                         j++;                                                         
//                                                    }                                          
//                                                }                                                
//                                            }
//                                        }
//                                   }                                 
//       
//                                }catch(Exception ex){
//                                    textArea.append( "id" + tbKnowledgeBase.getKnowledgeId() + " 发生异常,\n");      
//                                    ex.printStackTrace();
//                                    continue;
//                                }finally{
//                                    session.close();
//                                }
//
//                            } 
                            
                            
                            //第三部分,插入base
                            for(TbKnowledgeBase tbKnowledgeBase : tbKnowledgeBaseList){
//                                if(tbKnowledgeBase.getKnowledgeId() <= 6370273){
//                                    continue;
//                                }

                                Configuration cfg = new Configuration();
                                cfg.configure("hibernate-phoenix_knowledge_test.cfg.xml");
                                sessionFactory = cfg.buildSessionFactory();                                
 
                                Session session = sessionFactory.openSession();
                                try{
                                    System.out.println("tbKnowledgeBase.getKnowledgeId() ----------id:" + tbKnowledgeBase.getKnowledgeId());

                                   //base数据写入    
                                   gintong.phoenix.knowledge.test.now.vo.TbKnowledgeBase tbBase = new gintong.phoenix.knowledge.test.now.vo.TbKnowledgeBase();
                                   Transaction tx = session.beginTransaction();
                                   
                                   tbBase.setCoverPic(tbKnowledgeBase.getPicPath());
                                   tbBase.setId(tbKnowledgeBase.getKnowledgeId());
                                   tbBase.setKnowledgeId(tbKnowledgeBase.getKnowledgeId());
                                   
                                   tbBase.setCreateUserId(tbKnowledgeBase.getUserId());
                                   
                                   tbBase.setType(tbKnowledgeBase.getColumnType());

                                   
                                          // tbKnowledgeBase.getPicPath();
                                   tbBase.setCreateUserName(tbKnowledgeBase.getAuthor());
                                   tbBase.setModifyUserName(tbKnowledgeBase.getAuthor());
                                   tbBase.setTitle(tbKnowledgeBase.getTitle());
                                   tbBase.setColumnId(tbKnowledgeBase.getColumnId());
                                   tbBase.setTaskId(tbKnowledgeBase.getTaskid());
                                   tbBase.setCpath(tbKnowledgeBase.getPath());
                                   
                                   String idStr = "";
                                   //标签名称串封装成标签id串
                                   
                                   String[] tags = null;
                                   if(tbKnowledgeBase.getTag() != null && tbKnowledgeBase.getTag().length() > 0){
                                        tags = tbKnowledgeBase.getTag().split(" "); 
                                   }          
                                   
                                   if(tags != null && tags.length > 0){
                                        for(int i = 0; i < tags.length; i++){  
                                            //根据名称查询id
                                            long id = 0;
                                            if(tags[i] != null && tags[i].length() > 0){
                                                id = getTagIdByUserIdTagtypTagname(tbKnowledgeBase.getUserId(),3,tags[i]);
                                                if(id != 0){
                                                    idStr = idStr + " " + id;
                                                }
                                            }                                           
                                        }
                                        
                                        tbBase.setTags(idStr.trim());
                                   }
                                   
                                   //将日期格式转换成毫秒
                                   SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                   String time = tbKnowledgeBase.getCreatetime() + "";
                                   Date date = format.parse(time);
                                   System.out.print("Format To times:" + date.getTime());  
                                   tbBase.setCreateDate(date.getTime());
                                   tbBase.setPublicDate(date.getTime());   
                                   
                                   if(tbKnowledgeBase.getEssence() == null  || tbKnowledgeBase.getEssence() == 0){
                                       Short s = 0;
                                       tbBase.setEssence(s);
                                   }
                               
                                   //tbBase.setModifyDate(System.currentTimeMillis());
                                   
                                   Byte b1 = 2;
                                   tbBase.setAuditStatus(b1);

                                   Byte b2 = 1;
                                   tbBase.setReportStatus(b2);
                                   tbBase.setContentDesc(tbKnowledgeBase.getCDesc());
                                  
                                   
                                   
                                   
//                                    if(tbKnowledgeBase.getEssence() == 0){
//                                        char c = 0;
//                                       tbBase.setEssence(c);
//                                    }else if(tbKnowledgeBase.getEssence() == 1){
//                                        char c = 1;
//                                        tbBase.setEssence(c);                              
//                                    }                                   
     

                                   
                                   //tbBase.setAttachmentId(Integer.parseInt(tbKnowledgeBase.getTaskid()));
                                   
                                   Integer i = tbKnowledgeBase.getUserStar();
                                   tbBase.setUserStar(Byte.valueOf(i.byteValue()));
                                   tbBase.setIsOld(true);
                                   
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
                                  
                                   session.save(tbBase);
                                   tx.commit();        
                                   j++;
                                   System.out.println("base成功插入为： " + j + "条");    
                                   
                                                                                    //日志数据写入
                                    Document logbaseKnowledge = new Document("oldId",tbBase.getId()).
                                    append("type","tb_knowledge_base");
                                    knowledgelog.insertOne(logbaseKnowledge);                                   
                                }catch(Exception ex){
                                    textArea.append( "id" + tbKnowledgeBase.getKnowledgeId() + " 发生异常,\n");      
                                    ex.printStackTrace();
                                    continue;
                                }finally{
                                    session.close();
                                }

                            }
                            System.out.println("标签base迁移完毕,共计： " + j + "条");  
                            
                        } catch (Exception ex) {
                            ex.printStackTrace();
                          //  Logger.getLogger(DMT.class.getName()).log(Level.SEVERE, null, ex);
                        } 
            
        });
        panel.add(button1);        
        
        JButton button2 = new JButton("目录");
        button2.addActionListener((ActionEvent arg0) -> {
             int k = 0;
             int w = 0;
            // List<TbUserCategory> tbUserCategoryList = new PhoenixKnowledgeHandler().run1();
             Map<String,String> map = new HashMap<String,String>();
             try {            
                            //读取mysql-目录数据
                            Configuration cfgold = new Configuration();
                            cfgold.configure("hibernate-phoenix_knowledge.cfg.xml");
                            sessionFactoryold = cfgold.buildSessionFactory();
                            
                            //写入mysql-目录数据
                            Configuration cfgnew = new Configuration();
                            cfgnew.configure("hibernate-parasol_tags_test.cfg.xml");
                            sessionFactorynew = cfgnew.buildSessionFactory();    
                            
                            //mongodb
                            MongoClient mongoClient = new MongoClient("192.168.101.131", 27017);
                            MongoDatabase mongoDatabase = mongoClient.getDatabase("demand-test");
                            MongoCollection<Document> collection = mongoDatabase.getCollection("Demand");    
                            
                            //mongodb
                            MongoClient mongoClientl = new MongoClient("192.168.101.131", 27017);                            
                            MongoDatabase mongoDatabasel = mongoClientl.getDatabase("knowledge-test-2");                            
                            MongoCollection<Document> knowledgelog = mongoDatabase.getCollection("Knowledgelog");         

                            
//                            List notOnlytbUserCategoryList = notOnlytbUserCategoryList();
//                            for(int i=0;i<notOnlytbUserCategoryList.size();i++){
//                                Long id = Long.valueOf(notOnlytbUserCategoryList.get(i).toString());
//                                notOnlyMap.put(id, 1+"");
//                                
//                                //找出当前id的所有子目录
//                                List<BigInteger> ides = sonTbUserCategoryIdList(id);
//                                if(ides != null && ides.size() > 0){
//                                    recursionId(ides);
//                                }
//                            }
                            //第一部分
                            List<TbUserCategory> tbUserCategoryList = tbUserCategoryList();                            
                            for(TbUserCategory tbUserCategory : tbUserCategoryList){
                                                                    
                                Session session = sessionFactorynew.openSession();
                                try{
                                    
                                    TbDirectory tbDirectory = new TbDirectory();
                                    Transaction tx = session.beginTransaction();
                                    tbDirectory.setId(k+1 + 448);
                                    //tbDirectory.setId(k+1 );
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
                                    
                                    tbDirectory.setOrderNo(0);
                                    tbDirectory.setTypeId(3);
                                    
                                    tbDirectory.setUpdateAt(System.currentTimeMillis());
                                    
                                   session.save(tbDirectory);
                                   tx.commit();                                    
                                   
                                   k++;
                                   //System.out.println("旧id:" + tbUserCategory.getId() + " 新id" + tbDirectory.getId());
                                   map.put(tbUserCategory.getId() + "", tbDirectory.getId() + "");
                                   
                                                                       //日志数据写入
                                    Document logDocument = new Document("oldId",tbDirectory.getId()).
                                            append("type","tb_directory");
                                    knowledgelog.insertOne(logDocument);                                   
                                }catch(Exception ex){ 
                                    System.out.println( "旧库id:" + tbUserCategory.getId() + " 发生异常,\n");
                                    textArea.append( "旧库id:" + tbUserCategory.getId() + " 发生异常,\n");   
                                    ex.printStackTrace();
                                }finally{
                                    session.close();
                                }
                                
                            } 
                            System.out.println("知识目录迁移完毕,共计 "+ k + "条");
                            
                             //循环将pid,numberCode中旧id用新id替换
                                List<TbDirectory> tbDirectoryList = tbDirectoryListByTypeId();
                                for(TbDirectory tbDirectory : tbDirectoryList){
                                    Session ss = sessionFactorynew.openSession();
                                    try{
                                        Transaction tx = ss.beginTransaction();
                                        if(tbDirectory.getPid() != 0){
                                            String pid = map.get(tbDirectory.getPid() + "");
                                            if(pid != null){
                                                tbDirectory.setPid(Long.parseLong(pid));
                                            }
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
                                            System.out.println("NumberCode------------------------" + str);
                                            tbDirectory.setNumberCode(str);
                                        }

                                        ss.update(tbDirectory);
                                        tx.commit();
                                    }catch(Exception ex){
                                       textArea.append( "id" + tbDirectory.getId() + " 发生异常,\n");      
                                       ex.printStackTrace();
                                   }finally{
                                        ss.close();
                                   }   
                                }                            
                                                    
                            
                            
                            //第二部分
                            
                            List<TbKnowledgeCategory> tbKnowledgeCategoryList = tbKnowledgeCategoryList();
                            for(TbKnowledgeCategory tbKnowledgeCategory : tbKnowledgeCategoryList){

                                String dId = map.get(tbKnowledgeCategory.getCategoryId() + "");
                                if(dId == null){
                                    continue;
                                }
                                
                                Session session = sessionFactorynew.openSession();
                                try{
                                    System.out.println("id:" + tbKnowledgeCategory.getId());                                   

                                   //目录数据写入    
                                   TbDirectorySource tbDirectorySource = new TbDirectorySource();
                                   Transaction tx = session.beginTransaction();
                                   tbDirectorySource.setId(w + 1 + 6545);
                                   //tbDirectorySource.setId(w + 1);
                                   tbDirectorySource.setDirectoryId(Long.parseLong(dId));
                                   tbDirectorySource.setUserId(getTbDirectoryUserIdByTagId(Long.parseLong(map.get(tbKnowledgeCategory.getCategoryId() + ""))));
                                   
                                   tbDirectorySource.setSourceId(tbKnowledgeCategory.getKnowledgeId());
                                   tbDirectorySource.setSourceType(3);
                                   tbDirectorySource.setSourceUrl("");
                                   
                                   //sourceTitle,从mongo的demand中根基demand_id查询demandTitle
                                   BasicDBObject query = new BasicDBObject();
                                   query.put("_id",tbKnowledgeCategory.getKnowledgeId());
                                   Document document = collection.find(query).first();
                                   if(document != null){
                                            System.out.println("title:" + document.get("title")); 
                                            tbDirectorySource.setSourceTitle(document.get("title") + "");        
                                   }                                   
                                   
                                   tbDirectorySource.setSourceData("");
                                   tbDirectorySource.setInvokeMethod("");
                                   
                                   //将日期格式转换成毫秒
                                   SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;

                                   session.save(tbDirectorySource);
                                   tx.commit();        
                                   w++;
                        
                                }catch(Exception ex){
                                    textArea.append( "id" + tbKnowledgeCategory.getId() + " 发生异常,\n");   
                                    ex.printStackTrace();
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
                            cfgold.configure("hibernate-phoenix_knowledge.cfg.xml");
                            sessionFactoryold = cfgold.buildSessionFactory();
                            
                            //写入mysql-关联数据
                            Configuration cfgnew = new Configuration();
                            cfgnew.configure("hibernate-parasol_tags_test.cfg.xml");
                            sessionFactorynew = cfgnew.buildSessionFactory();    
                            
                            MongoClient mongoClient = new MongoClient("192.168.101.131", 27017);
                            MongoDatabase mongoDatabase = mongoClient.getDatabase("knowledge-test-2");
                            MongoCollection<Document> knowledgelog = mongoDatabase.getCollection("Knowledgelog");   
                            
                            
                            List<gintong.phoenix.knowledge.old.vo.TbConnectInfo> tbConnectInfoKLList = tbConnectInfoKLList();
                            for(gintong.phoenix.knowledge.old.vo.TbConnectInfo tbConnectInfo : tbConnectInfoKLList){
                                
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
                                        tbAssociate.setSourceTypeId(666);
                                        tbAssociate.setSourceId(tbConnectInfo.getKnowledgeId());
                                        
                                        if(tbConnectInfo.getConnName() != null){
                                            tbAssociate.setAssocTitle(tbConnectInfo.getConnName());
                                        }else{
                                           tbAssociate.setAssocTitle("");
                                        }
                                        
                                        if(tbConnectInfo.getTag() != null && tbConnectInfo.getTag().length() > 30){
                                            tbAssociate.setAssocDesc(tbConnectInfo.getTag().substring(0,30));
                                        }else{
                                            tbAssociate.setAssocDesc(tbConnectInfo.getTag());
                                        }
                                        

                                        //assocTypeId
                                        if(tbConnectInfo.getConnType() == 1){
                                             tbAssociate.setAssocTypeId(666);
                                        }
                                        if(tbConnectInfo.getConnType() == 2){
                                             tbAssociate.setAssocTypeId(444);
                                        }
                                        if(tbConnectInfo.getConnType() == 5){
                                             tbAssociate.setAssocTypeId(555);
                                        }
                                        if(tbConnectInfo.getConnType() == 6){
                                             tbAssociate.setAssocTypeId(666);
                                        }                                   

                                        tbAssociate.setAssocId(tbConnectInfo.getConnId());
                                        tbAssociate.setAssocMetadata("");

                                        //将日期格式转换成毫秒;                
                                        tbAssociate.setCreateAt(System.currentTimeMillis());
                                        session.save(tbAssociate);
                                        tx.commit();        

                                        k++;    
                                                                       //日志数据写入
                                    Document logDocument = new Document("oldId",tbAssociate.getId()).
                                            append("type","tb_associate");
                                    knowledgelog.insertOne(logDocument);                                    
                                }
                                   
                                }catch(Exception ex){
                                    textArea.append( "id" + tbConnectInfo.getId() + " 发生异常,\n");     
                                    ex.printStackTrace();
                                }finally{
                                     session.close(); 
                                }
                                
                            }  
                            
                            System.out.println("知识关联迁移条数:" + k); 
                        } catch (RemoteException ex) {
                            ex.printStackTrace();
                          //  Logger.getLogger(DMT.class.getName()).log(Level.SEVERE, null, ex);
                        }           
        });
        panel.add(button3);   
        
        
        JButton button6 = new JButton("权限");
        button6.addActionListener((ActionEvent arg0) -> {
            int j = 0;
             try {            
                            //读取mysql-权限数据
                            Configuration cfgold = new Configuration();
                            cfgold.configure("hibernate-phoenix_knowledge.cfg.xml");
                            sessionFactoryold = cfgold.buildSessionFactory();
                            
                            //写入mysql-权限数据
                            Configuration cfgnew = new Configuration();
                            cfgnew.configure("hibernate-parasol_tags_test.cfg.xml");
                            sessionFactorynew = cfgnew.buildSessionFactory();   
                            
                            MongoClient mongoClient = new MongoClient("192.168.101.131", 27017);
                            MongoDatabase mongoDatabase = mongoClient.getDatabase("knowledge-test-2");
                            MongoCollection<Document> knowledgelog = mongoDatabase.getCollection("Knowledgelog");                            
                            
                            List<TbUserPermission> tbUserPermissionList = tbUserPermissionList();

                            for(TbUserPermission tbUserPermission : tbUserPermissionList){
                                j++;
                                if(j == 1){
                                    continue;
                                }
                                
                                Session session = sessionFactorynew.openSession();
                                try{
                                    System.out.println("tbUserPermission.getId() ----------id:" + tbUserPermission.getId());

                                   //权限数据写入    
                                   TbPermission tbPermission = new TbPermission();
                                   Transaction tx = session.beginTransaction();
                                   
                                   //tbPermission.setPerId(tbUserDemandPermission.getId());
                                   tbPermission.setResId(tbUserPermission.getKnowledgeId());
                                   short st = 3;
                                   tbPermission.setResType(st);
                                   tbPermission.setResAccRule("");
                                   tbPermission.setResOwnerId(tbUserPermission.getSendUserId());
                                   if(tbUserPermission.getType() == 1){
                                       tbPermission.setPublicFlag(0);
                                       tbPermission.setConnectFlag(0);
                                       tbPermission.setShareFlag(0);
                                   }
                                   if(tbUserPermission.getType() == 2){
                                       if(tbUserPermission.getReceiveUserId() == 0 || tbUserPermission.getReceiveUserId() == -1){
                                            tbPermission.setPublicFlag(1);
                                            tbPermission.setConnectFlag(1);
                                            tbPermission.setShareFlag(1);                                       
                                       }else{
                                            tbPermission.setPublicFlag(0);
                                            tbPermission.setConnectFlag(1);
                                            tbPermission.setShareFlag(1);                                       
                                       }
                                   }
                                   if(tbUserPermission.getType() == 3){
                                            tbPermission.setPublicFlag(0);
                                            tbPermission.setConnectFlag(1);
                                            tbPermission.setShareFlag(0);                                  
                                   }
                                   if(tbUserPermission.getType() == 4){
                                            tbPermission.setPublicFlag(0);
                                            tbPermission.setConnectFlag(1);
                                            tbPermission.setShareFlag(0);                                   
                                   }   
                                   
                                   //将日期格式转换成毫秒
                                   SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                   String time = tbUserPermission.getCreatetime() + "";
                                   Date date = format.parse(time);
                                   System.out.print("Format To times:" + date.getTime());  
                                   tbPermission.setPerTime(tbUserPermission.getCreatetime());
                                   tbPermission.setAppId(1);
                                   
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
                                  
                                   session.save(tbPermission);
                                   tx.commit();
                                   System.out.println("知识权限保存成功发的 ----------id:" + tbPermission.getPerId());
                                    //日志数据写入
                                    Document logDocument = new Document("oldId",tbPermission.getPerId()).
                                    append("type","tb_permission");
                                    knowledgelog.insertOne(logDocument);                                 
                                   
                                }catch(Exception ex){
                                    textArea.append( "id" + tbUserPermission.getId() + " 发生异常,\n");      
                                    ex.printStackTrace();
                                    continue;
                                }finally{
                                    session.close();
                                }

                            }

                            
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }           
        });
        panel.add(button6);   
        
        JButton button9 = new JButton("统计");
        button9.addActionListener((ActionEvent arg0) -> {
            int k = 0;
             try {            
                            //读取mysql-base数据
                            Configuration cfgold = new Configuration();
                            cfgold.configure("hibernate-phoenix_knowledge.cfg.xml");
                            sessionFactoryold = cfgold.buildSessionFactory();
                            
//                            //写入mysql-权限数据
                            Configuration cfgnew = new Configuration();
                            cfgnew.configure("hibernate-phoenix_knowledge_test.cfg.xml");
                            sessionFactorynew = cfgnew.buildSessionFactory();
                            
                            MongoClient mongoClient = new MongoClient("192.168.101.131", 27017);
                            MongoDatabase mongoDatabase = mongoClient.getDatabase("knowledge-test-2");
                            MongoCollection<Document> knowledgelog = mongoDatabase.getCollection("Knowledgelog");                             
                            
                            List<TbKnowledgeStatics> tbKnowledgeStaticsList = tbKnowledgeStaticsList();
                            for(TbKnowledgeStatics tbKnowledgeStatics : tbKnowledgeStaticsList){
                                
                                
                                Session session = sessionFactorynew.openSession();
                                try{
                                    System.out.println("tbKnowledgeStatics.getKnowledgeId() ----------id:" + tbKnowledgeStatics.getKnowledgeId());
                                    long num = tbKnowledgeStatics.getCollectionCount()*5 + tbKnowledgeStatics.getClickCount() + tbKnowledgeStatics.getShareCount()*2 + tbKnowledgeStatics.getCommentCount()*2;
                                    if(num > 0){
                                        TbKnowledgeCount tbKnowledgeCount = new TbKnowledgeCount();
                                        Transaction tx = session.beginTransaction();

                                        TbKnowledgeCountId tbKnowledgeCountId = new TbKnowledgeCountId();
                                        tbKnowledgeCountId.setId(k+1);
                                        //hot=收藏次数*5+阅读次数+分享次数*2+评论次数*2
                                        tbKnowledgeCountId.setHotCount(num);
                                        tbKnowledgeCountId.setKnowledgeId(tbKnowledgeStatics.getKnowledgeId());
                                        //tbKnowledgeCount.setId(tbKnowledgeCountId);
                                        tbKnowledgeCount.setId(tbKnowledgeStatics.getKnowledgeId());
                                        //评论数
                                        tbKnowledgeCount.setCommentCount(tbKnowledgeStatics.getCommentCount());
                                        //分享数
                                        tbKnowledgeCount.setShareCount(tbKnowledgeStatics.getShareCount());
                                        //收藏数
                                        tbKnowledgeCount.setCollectCount(tbKnowledgeStatics.getCollectionCount());
                                        //点击数
                                        tbKnowledgeCount.setClickCount(tbKnowledgeStatics.getClickCount());

                                        tbKnowledgeCount.setHotCount(num);

                                        tbKnowledgeCount.setUserId(null);

                                        //知识来源
                                        if(tbKnowledgeStatics.getSource() == null){
                                             Short s = 1;
                                             tbKnowledgeCount.setSource(s);
                                        }else{
                                             tbKnowledgeCount.setSource(tbKnowledgeStatics.getSource());
                                        }


                                        //类型
                                        tbKnowledgeCount.setType(tbKnowledgeStatics.getType());

                                        session.save(tbKnowledgeCount);
                                        tx.commit();        
                                        k++;

                                         //日志数据写入
                                         Document logDocument = new Document("oldId",tbKnowledgeCount.getId()).
                                         append("type","tb_knowledge_count");
                                         knowledgelog.insertOne(logDocument);                                            
                                                                           
                                    }    
                                   
                                }catch(Exception ex){
                                    textArea.append( "id" + tbKnowledgeStatics.getKnowledgeId() + " 发生异常,\n");      
                                    ex.printStackTrace();
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
        panel.add(button9);    
       
       
                
        
//------------------------------   mysql --->> mysql end  ------------------------------------------------------------------------------
        
 //------------------------------   mysql --->> mongo start ------------------------------------------------------------------------------    
 
         JButton button8 = new JButton("评论");
        button8.addActionListener((ActionEvent arg0) -> {
            
            
                //mongodb
                MongoClient mongoClient = new MongoClient("192.168.101.131", 27017);
                MongoDatabase mongoDatabase = mongoClient.getDatabase("knowledge-test");
                MongoCollection<Document> collection = mongoDatabase.getCollection("KnowledgeComment");    
                MongoCollection<Document> knowledgelog = mongoDatabase.getCollection("Knowledgelog");                
            
             try {            
                
                            //读取mysql-评论数据
                            Configuration cfgold = new Configuration();
                            cfgold.configure("hibernate-phoenix_knowledge.cfg.xml");
                            sessionFactoryold = cfgold.buildSessionFactory();
                            
                            List<TbKnowledgeComment> tbKnowledgeCommentList = tbKnowledgeCommentList();
                            for(TbKnowledgeComment tbKnowledgeComment : tbKnowledgeCommentList){
                                
                                System.out.println("id:" + tbKnowledgeComment.getId());
                                
                                //将日期格式转换成毫秒
                                SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String time = tbKnowledgeComment.getCreatetime() + "";
                                Date date = format.parse(time);                                                                    
                                 
                                
                                //知识评论数据写入    
                                Document newDocument = new Document("_id",tbKnowledgeComment.getId()).
                                append("_class", "com.ginkgocap.ywxt.knowledge.model.KnowledgeComment").
                                append("knowledgeId", tbKnowledgeComment.getKnowledgeId()).
                                append("content", tbKnowledgeComment.getContent()).  
                                append("createTime", date.getTime()).
                                append("ownerId", tbKnowledgeComment.getUserId()).  
                                append("ownerName", tbKnowledgeComment.getUsername()).                                                
                                append("visible", tbKnowledgeComment.getIsVisible());
                                collection.insertOne(newDocument);
                                
                                   
                                    //日志数据写入
                                    Document logDocument = new Document("oldId",tbKnowledgeComment.getId()).
                                    append("type","KnowledgeComment");
                                    knowledgelog.insertOne(logDocument);                                
                            }                            
                        } catch (Exception ex) {
                            Logger.getLogger(DMTKL.class.getName()).log(Level.SEVERE, null, ex);
                        } 
        });
        panel.add(button8);
 
 
        JButton button4 = new JButton("举报");
        button4.addActionListener((ActionEvent arg0) -> {
            
            
                //mongodb
                MongoClient mongoClient = new MongoClient("192.168.101.131", 27017);
                MongoDatabase mongoDatabase = mongoClient.getDatabase("knowledge-test");
                MongoCollection<Document> collection = mongoDatabase.getCollection("KnowledgeReport");    
                MongoCollection<Document> knowledgelog = mongoDatabase.getCollection("Knowledgelog");
            
            
             try {            
                
                            //读取mysql-举报数据
                            Configuration cfgold = new Configuration();
                            cfgold.configure("hibernate-phoenix_knowledge.cfg.xml");
                            sessionFactoryold = cfgold.buildSessionFactory();
                            
                            List<TbKnowledgeReport> tbKnowledgeReportList = tbKnowledgeReportList();
                            for(TbKnowledgeReport tbKnowledgeReport : tbKnowledgeReportList){
                                try{
                                    System.out.println("id:" + tbKnowledgeReport.getId());

                                    //将日期格式转换成毫秒
                                    SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    String time = tbKnowledgeReport.getCreatetime() + "";
                                    Date date = format.parse(time);                                                                    


                                    //举报数据写入    
                                    Document newDocument = new Document("_id",tbKnowledgeReport.getId()).
                                    append("_class", "com.ginkgocap.ywxt.knowledge.model.KnowledgeReport").  
                                    append("knowledgeId", tbKnowledgeReport.getKnowledgeId()).
                                    append("reason", tbKnowledgeReport.getType()).
                                    append("content", tbKnowledgeReport.getRepDesc()). 
                                    append("createTime", date.getTime()).                               
                                    append("userId", tbKnowledgeReport.getUserId()).  
                                    append("userName", tbKnowledgeReport.getUserName()).                                        
                                    append("columnId", tbKnowledgeReport.getColumnType());
                                    collection.insertOne(newDocument);  
                                    
                                    //日志数据写入
                                    Document logDocument = new Document("oldId",tbKnowledgeReport.getId()).
                                    append("type","KnowledgeReport");
                                    knowledgelog.insertOne(logDocument);                                    
                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                               
                            }                            
                        } catch (Exception ex) {
                            Logger.getLogger(DMTKL.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                        }      
        });
        panel.add(button4);     
   
        JButton button5 = new JButton("集合");
        button5.addActionListener((ActionEvent arg0) -> {
            
 
                //mongodb192.168.101.131
                MongoClient mongoClient = new MongoClient("192.168.101.131", 27017);
                MongoDatabase mongoDatabase = mongoClient.getDatabase("knowledge-test");
                MongoCollection<Document> collection = mongoDatabase.getCollection("KnowledgeCollection");
                MongoCollection<Document> knowledgelog = mongoDatabase.getCollection("Knowledgelog");
            
            
                try {            

                               //读取mysql-集合数据
                               Configuration cfgold = new Configuration();
                               cfgold.configure("hibernate-phoenix_knowledge.cfg.xml");
                               sessionFactoryold = cfgold.buildSessionFactory();
                               

                               List<TbKnowledgeCollection> tbKnowledgeCollectionList = tbKnowledgeCollectionList();
                               for(TbKnowledgeCollection tbKnowledgeCollection : tbKnowledgeCollectionList){

                                   System.out.println("id:" + tbKnowledgeCollection.getId());   
                                   
                                    //将日期格式转换成毫秒
                                    SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    String time = tbKnowledgeCollection.getCreatetime() + "";
                                    Date date = format.parse(time);                                    

                                   //集合数据写入    
                                   Document newDocument = new Document("_id",tbKnowledgeCollection.getId()).
                                   append("_class", "com.ginkgocap.ywxt.knowledge.model.KnowledgeCollect"). 
                                           
                                    append("knowledgeId", tbKnowledgeCollection.getKnowledgeId()).
                                    append("createTime", date.getTime()).         
                                    append("source  ", tbKnowledgeCollection.getSource()).       
                                    append("userId", tbKnowledgeCollection.getUserId());                                       
                                    collection.insertOne(newDocument);
                                    
                                    //日志数据写入
                                    Document logDocument = new Document("oldId",tbKnowledgeCollection.getId()).
                                    append("type","KnowledgeCollection");
                                    knowledgelog.insertOne(logDocument);                                    
                               }                            
                           } catch (Exception ex) {
                               Logger.getLogger(DMTKL.class.getName()).log(Level.SEVERE, null, ex);
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
   
    
    //查询老知识目录集合第一部分tb_user_category
    public List<TbUserCategory> tbUserCategoryList() throws RemoteException {
        
        List<TbUserCategory> tbUserCategoryList = null;
        try {        
            String hql = "from TbUserCategory where categoryType = 0";
            System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbUserCategoryList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbUserCategoryList;
    } 
    
    //查询老知识目录集合第一部分tb_user_category,不唯一的数据
    //SELECT T1.* FROM phoenix_knowledge.tb_user_category T1,(select user_id,parent_id,categoryName from phoenix_knowledge.tb_user_category b2 group by user_id,parent_id,categoryName having count(*)>1) T2
    //where T1.user_id = T2.user_id AND T1.categoryName = T2.categoryName AND T1.parent_id = T2.parent_id
    public List<TbUserCategory> notOnlytbUserCategoryList() throws RemoteException {
        
        List<TbUserCategory> notOnlytbUserCategoryList = null;
        try {        
            String sql = "SELECT T1.id FROM phoenix_knowledge.tb_user_category T1,(select user_id,parent_id,categoryName from phoenix_knowledge.tb_user_category b2 group by user_id,parent_id,categoryName having count(*)>1) T2\n" +
            "where T1.user_id = T2.user_id AND T1.categoryName = T2.categoryName AND T1.parent_id = T2.parent_id";
            System.out.println("hql=" + sql);
            Session ss = sessionFactoryold.openSession();
            notOnlytbUserCategoryList = ss.createSQLQuery(sql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return notOnlytbUserCategoryList;
    }  
    
    //查询id的子目录集合
    public List<BigInteger> sonTbUserCategoryIdList(Long parentId){
        List<BigInteger> sonTbUserCategoryIdList = null;
        try {        
            String sql = "SELECT id FROM phoenix_knowledge.tb_user_category where parent_id = " + parentId;
            System.out.println("hql=" + sql);
            Session ss = sessionFactoryold.openSession();
            sonTbUserCategoryIdList = ss.createSQLQuery(sql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return sonTbUserCategoryIdList;       
    }
    
    //查询老知识目录集合第二部分tb_knowledge_category
    public List<TbKnowledgeCategory> tbKnowledgeCategoryList() throws RemoteException {
        
        List<TbKnowledgeCategory> tbKnowledgeCategoryList = null;
        try {        
            String hql = "from TbKnowledgeCategory";
            System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbKnowledgeCategoryList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbKnowledgeCategoryList;
    }    
    
 
    
    //查询老关联集合
    public List<gintong.phoenix.knowledge.old.vo.TbConnectInfo> tbConnectInfoKLList() throws RemoteException {
        
        List<gintong.phoenix.knowledge.old.vo.TbConnectInfo> tbConnectInfoList = null;
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
    public List<TbUserPermission> tbUserPermissionList() throws RemoteException {
        
        List<TbUserPermission> tbUserPermissionList = null;
        try {        
            String hql = "from TbUserPermission order by id desc";
            System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbUserPermissionList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbUserPermissionList;
    }    
    
    //查询老知识评论集合
    public List<TbKnowledgeComment> tbKnowledgeCommentList() throws RemoteException {
        
        List<TbKnowledgeComment> tbKnowledgeCommentList = null;
        try {        
            String hql = "from TbKnowledgeComment order by id desc";
            System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbKnowledgeCommentList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbKnowledgeCommentList;
    } 
    
    //查询老知识统计集合
    public List<TbKnowledgeStatics> tbKnowledgeStaticsList() throws RemoteException {
        
        List<TbKnowledgeStatics> tbKnowledgeStaticsList = null;
        try {        
            String hql = "from TbKnowledgeStatics";
            System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbKnowledgeStaticsList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbKnowledgeStaticsList;
    }    
    
    
 
    //查询老知识举报集合
    public List<TbKnowledgeReport> tbKnowledgeReportList() throws RemoteException {
        
        List<TbKnowledgeReport> tbKnowledgeReportList = null;
        try {        
            String hql = "from TbKnowledgeReport order by id desc";
            System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbKnowledgeReportList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbKnowledgeReportList;
    }  
    
    //查询老知识集合集合
    public List<TbKnowledgeCollection> tbKnowledgeCollectionList() throws RemoteException {
        
        List<TbKnowledgeCollection> tbKnowledgeCollectionList = null;
        try {        
            String hql = "from TbKnowledgeCollection order by id desc";
            System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbKnowledgeCollectionList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbKnowledgeCollectionList;
    }

    //查询老知识base集合
    public List<gintong.phoenix.knowledge.old.vo.TbKnowledgeBase> tbKnowledgeBaseList() throws RemoteException {
        
        List<gintong.phoenix.knowledge.old.vo.TbKnowledgeBase> tbKnowledgeBaseList = null;
        try {        
            String hql = "from TbKnowledgeBase";
            System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbKnowledgeBaseList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbKnowledgeBaseList;
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
    
     //查询新需求目录集合第一部分,按分类查询
    public List<TbDirectory> tbDirectoryListByTypeId() throws RemoteException {
        
        List<TbDirectory> tbDirectoryList = null;
        try {        
            String hql = "from TbDirectory where typeId = 3 order by id desc";
            System.out.println("hql=" + hql);
            Session ss = sessionFactorynew.openSession();
            tbDirectoryList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbDirectoryList;
    }   
    
     //查询新需求目录集合第一部分,按分类查询
    public List<TbDirectorySource> getTbDirectorySourceListByKnowledgeId(long knowledgeId) throws RemoteException {
        
        List<TbDirectorySource> tbDirectorySourceList = null;
        try {        
            String hql = "from TbDirectorySource where sourceType = 3 and sourceId = " + knowledgeId;
            System.out.println("hql=" + hql);
            Session ss = sessionFactorynew.openSession();
            tbDirectorySourceList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbDirectorySourceList;
    }    
    

    //根据userId,tagType,tagName联合查询标签表中是否存在
    public Boolean getTagByUserIdTagtypTagname(long userId, long tagType, String tagName) throws RemoteException{
        Boolean flag = false;
        
         try {        
            String hql = "from TbTag where userId = " + userId + " and tagType = " + tagType + " and tagName = '" + tagName + "'";
            System.out.println("hql=" + hql);
            Session ss = sessionFactorynew.openSession();
            TbTag tbTag = (TbTag) ss.createQuery(hql).setMaxResults(1).uniqueResult();
            if(tbTag != null){
                flag = true;
            }
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }       
        return flag;
    } 
    //根据userId,tagType,tagName联合查询标签表中是否存在,返回id
    public long getTagIdByUserIdTagtypTagname(long userId, long tagType, String tagName) throws RemoteException{
        long id = 0;
        
         try {        
            String hql = "from TbTag where userId = " + userId + " and tagType = " + tagType + " and tagName = '" + tagName + "'";
            System.out.println("hql=" + hql);
            Session ss = sessionFactorynew.openSession();
            TbTag tbTag = (TbTag) ss.createQuery(hql).setMaxResults(1).uniqueResult();
            if(tbTag != null){
                id = tbTag.getId();
            }
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }       
        
        return id;
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
    
     //查询新需求目录集合第一部分
    public List<TbDirectory> tbDirectoryList() throws RemoteException {
        
        List<TbDirectory> tbDirectoryList = null;
        try {        
            String hql = "from TbDirectory order by id desc";
            System.out.println("hql=" + hql);
            Session ss = sessionFactorynew.openSession();
            tbDirectoryList = ss.createQuery(hql).list();
            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tbDirectoryList;
    }     
    
    //通过id查询TbUserCategory获取TbUserCategory
    public TbUserCategory getTbUserCategoryById(long id) throws RemoteException{
        TbUserCategory tbUserCategory = null;
        
         try {        
            String hql = "from TbUserCategory where id = " + id;
            //System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbUserCategory = (TbUserCategory) ss.createQuery(hql).setMaxResults(1).uniqueResult();

            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }       
        
        return tbUserCategory;
    }    
    
    public void recursionId(List<BigInteger> ides){
        
        for(int i=0;i<ides.size();i++){
            notOnlyMap.put(((BigInteger)ides.get(i)).longValue(), 1 + "");
            if(ides != null && ides.size() > 0){
                recursionId(sonTbUserCategoryIdList(((BigInteger)ides.get(i)).longValue()));
            }
        }
    }
    
    //通过id查询TbUserCategory获取TbUserCategory
    public TbUserCategory getTbUserCategoryByparentId(long id) throws RemoteException{
        TbUserCategory tbUserCategory = null;
        
         try {        
            String hql = "from TbUserCategory where id = " + id;
            //System.out.println("hql=" + hql);
            Session ss = sessionFactoryold.openSession();
            tbUserCategory = (TbUserCategory) ss.createQuery(hql).setMaxResults(1).uniqueResult();

            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }       
        
        return tbUserCategory;
    }   
    
    //通过知识id查询TbUserCategory获取TbUserCategory
    public gintong.phoenix.knowledge.test.now.vo.TbKnowledgeBase getTbKnowledgeBaseByKnowledgeId(long knowledgeId) throws RemoteException{
        gintong.phoenix.knowledge.test.now.vo.TbKnowledgeBase tbKnowledgeBase = null;
        
         try {        
            String hql = "from TbKnowledgeBase where knowledgeId = " + knowledgeId;
            //System.out.println("hql=" + hql);
            Session ss = sessionFactorynew.openSession();
            tbKnowledgeBase = (gintong.phoenix.knowledge.test.now.vo.TbKnowledgeBase) ss.createQuery(hql).setMaxResults(1).uniqueResult();

            ss.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }       
        
        return tbKnowledgeBase;
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
