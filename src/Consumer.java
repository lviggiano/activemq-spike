import static javax.jms.Session.AUTO_ACKNOWLEDGE;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class Consumer implements MessageListener {

	public static void main(String[] args) throws Exception {
		Context ctx = new InitialContext();
		try {
			ConnectionFactory factory = (ConnectionFactory) ctx.lookup("connectionFactory");
			Connection connection = factory.createConnection();
			Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);
			connection.start();
	
			Destination destination = (Destination) ctx.lookup("MyQueue");
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(new Consumer());
		} finally {
			ctx.close();
		}
		System.out.println("Consumer is running");
	}

	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			System.out.println("Message Received: " + textMessage.getText());
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
}
