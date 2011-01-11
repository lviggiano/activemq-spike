import static javax.jms.Session.AUTO_ACKNOWLEDGE;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

public class Producer {

	public static void main(String[] args) throws Exception {
		Context ctx = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) ctx.lookup("connectionFactory");
		Destination destination = (Destination) ctx.lookup("MyQueue");
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);
		MessageProducer producer = session.createProducer(destination);
		String text = "The time is " + new Date();
		Message message = session.createTextMessage(text);
		producer.send(message);
		System.out.println("Message sent: " + text);
		producer.close();
		session.close();
		connection.close();
	}
}
