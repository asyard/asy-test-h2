import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test1 {

    // https://github.com/JFL110/simple-hibernate-example/tree/master/src/main/java/org/jfl110/she

    //



    public static void main(String[] args) {
        System.out.println("creating table");
        createTable();
        System.out.println("table created");

        System.out.println("starting");
        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Users u1 = new Users();
        u1.setUsername("Aylin");
        session.saveOrUpdate(u1);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Users");
        Object o = query.list();
        System.out.println(o);
        session.getTransaction().commit();
        session.close();


        // configuration.buildSessionFactory(
       //         new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());

        sessionFactory.close();
        System.exit(0);


    }


    private static void createTable() {
        try
        {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection("jdbc:h2:~/test", "test", "" );
            Statement stmt = con.createStatement();
            //stmt.executeUpdate( "DROP TABLE table1" );
            stmt.executeUpdate( "CREATE TABLE TBL_USERS ( USERID int primary key, USERNAME varchar(50) )" );
            //stmt.executeUpdate( "INSERT INTO TBL_USERS ( USERNAME ) VALUES ( 'Claudio' )" );
            //stmt.executeUpdate( "INSERT INTO TBL_USERS ( USERNAME ) VALUES ( 'Bernasconi' )" );

            ResultSet rs = stmt.executeQuery("SELECT * FROM table1");
            while( rs.next() )
            {
                String name = rs.getString("user");
                System.out.println( name );
            }
            stmt.close();
            con.close();
        }
        catch( Exception e )
        {
            System.out.println( e.getMessage() );
        }

    }




}
