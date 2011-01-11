import org.apache.activemq.broker.BrokerService;


public class Broker {
	public static void main(String[] args) throws Exception {
    	BrokerService broker = new BrokerService();
    	broker.setPersistent(false);
    	broker.setSchedulerSupport(false);
    	broker.setUseJmx(true);
        broker.addConnector("tcp://localhost:61616"); 
        broker.start();
        broker.waitUntilStopped();
	}
}
