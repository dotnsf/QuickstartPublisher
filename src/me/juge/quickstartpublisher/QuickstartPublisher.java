package me.juge.quickstartpublisher;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class QuickstartPublisher implements MqttCallback {
	MqttClient myClient = null;
	MqttConnectOptions connOpt;
	
	//. MQTT ブローカー
	static final String BROKER_URL = "tcp://quickstart.messaging.internetofthings.ibmcloud.com:1883";
	
	/**
	 * runClient
	 */
	public void runClient( String payload, String deviceid, String devicetype, String eventtype ) {
		// setup MQTT Client
		String clientID = "d:quickstart:" + devicetype + ":" + deviceid;
		connOpt = new MqttConnectOptions();
		connOpt.setCleanSession( true );
		connOpt.setKeepAliveInterval( 30 );
		
		// Connect to Broker
		try {
			myClient = new MqttClient( BROKER_URL, clientID );
			myClient.setCallback( this );
			myClient.connect( connOpt );
		} catch (MqttException e) {
			e.printStackTrace();
		}
		
		if( myClient != null ){
			// setup topic
			String myTopic = "iot-2/evt/" + eventtype + "/fmt/json";
			MqttTopic topic = myClient.getTopic( myTopic );

	   		int pubQoS = 0;
			MqttMessage message = new MqttMessage( payload.getBytes() );
	    	message.setQos( pubQoS );
	    	message.setRetained( false );

	    	// Publish the message
	    	MqttDeliveryToken token = null;
	    	try{
	    		// パブリッシュして、
				token = topic.publish( message );
		    	// ブローカーに送られることを確認
				token.waitForCompletion();
			}catch( Exception e ){
				e.printStackTrace();
			}
			
			// disconnect
			try{
				myClient.disconnect();
			}catch( Exception e ){
				e.printStackTrace();
			}
		}
	}


	@Override
	public void connectionLost( Throwable t ){
		System.out.println( "Connection lost!" );
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// TODO Auto-generated method stub
	}
}
