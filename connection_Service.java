//package /Users/mac/IdeaProjects/java_Project/src/db_connection;
import java.util.*;
import java.sql.*;
public class db_connection {
    /*** Java database connection driver ***/
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    /***
     * Required Values for connection
     * 1. Database connection url
     * 2. Database Username
     * 3. Database Password
     * ***/
    static final String _DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/report_db";
    static final String _USERNAME = "root"; //----DATABASE USERNAME
    static final String _PASSWORD = "";  //-----DATABASE PASSWORD
    public static void main(String[] args) {
        /** Connection is an sql connection session objecct **/
        Connection _db_connection = null;
        /** Statement is an sql statement  object **/
        Statement _db_statement = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            System.out.println("Connecting to the database...");
            _db_connection = DriverManager.getConnection(_DATABASE_URL,_USERNAME,_PASSWORD);
            // 执行查询
            System.out.println(" Instantiating the database object...");
            _db_statement = _db_connection.createStatement();
            String sql_query;
            sql_query = "SELECT * FROM complete_reports order by ID asc limit 10";
            ResultSet query_Result = _db_statement.executeQuery(sql_query);

            //----fetch sql related data while results of query
            while(query_Result.next()){
            
                int id  = query_Result.getInt("ID");
                String name = query_Result.getString("DOWNLOADED");
                String url = query_Result.getString("URL_");
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + name);
                System.out.print(", 站点 URL: " + url);
                System.out.print("\n");
            }
            // 完成后关闭
            query_Result.close();
            _db_statement.close();
            _db_connection.close();
        }catch(SQLException se){
           
            se.printStackTrace();
        }catch(Exception e){
           
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(_db_statement!=null) _db_statement.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(_db_connection!=null) _db_connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
