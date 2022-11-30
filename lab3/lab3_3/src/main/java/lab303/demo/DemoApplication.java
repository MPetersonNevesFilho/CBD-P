package lab303.demo;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {

		System.out.println("Conectando ao Cassandra...");
		SpringApplication.run(DemoApplication.class, args);
		Cluster cluster = Cluster.builder().withoutJMXReporting().addContactPoint("127.0.0.1").build();
		Session session = cluster.connect("lab302");

		/* Letra A */
		System.out.println("Inserindo usuário...\n");
		session.execute("INSERT INTO User (email, username, name, register_time ) VALUES ('test01@test.com', 'TesT01', 'Test01 Nevis' dateof(now()));");
        ResultSet result = session.execute("SELECT json * FROM User;");
        System.out.println(result.all());

		System.out.println("Atualizando nome de usuário...\n");
		session.execute("UPDATE User SET name='Test01 Neves' WHERE email='test01@test.com';");
        ResultSet resultUpdate = session.execute("SELECT json * FROM User;");
        System.out.println(resultUpdate.all());

        System.out.println("Procurando usuário pelo e-mail...\n");
        ResultSet resultSearch = session.execute("SELECT * FROM User WHERE email='test01@test.com' ALLOW FILTERING");
        System.out.println(resultSearch.all());

		/* Letra B */
		System.out.println("Mimetizando exercício 3.2.d.1");
		ResultSet exd1 = session.execute("SELECT * FROM Comment_Video WHERE video_id = 26a11090-70d8-11ed-b3ed-f57c32dcb1b9 ORDER BY upload_timestamp DESC LIMIT 3;");
		System.out.println(exd1.all());

		System.out.println("Mimetizando exercício 3.2.d.2");
        ResultSet exd2 = session.execute("SELECT tags FROM Video WHERE id=26a11090-70d8-11ed-b3ed-f57c32dcb1b9;");
		System.out.println(exd2.all());

		System.out.println("Mimetizando exercício 3.2.d.4");
        ResultSet exd4 = session.execute("SELECT * FROM Event WHERE user='test20@test.com' AND video_id=26a9b320-70d8-11ed-b3ed-f57c32dcb1b9 ORDER BY real_timestamp DESC LIMIT 5;");
        System.out.println(exd4.all());

		System.out.println("Mimetizando exercício 3.2.d.7");
        ResultSet exd7 = session.execute("SELECT followers_list FROM Follower WHERE video_id=26a94df0-70d8-11ed-b3ed-f57c32dcb1b9;");
        System.out.println(exd7.all());

	}

}