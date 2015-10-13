package me.juge.quickstartpublisher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuickstartPublishServlet extends HttpServlet {
//	MqttClient myClient;
//	MqttConnectOptions connOpt;
	
//	static final String BROKER_URL = "tcp://quickstart.messaging.internetofthings.ibmcloud.com:1883";

	@Override
	protected void doGet( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException{
		doPost( req, res );
	}
	
	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException{
		String contenttype = "application/json; charset=UTF-8";
		String payload = "", deviceid = "", devicetype = "MyDevice", eventtype = "MyEvent";
		String status = "No operation";

		req.setCharacterEncoding( "UTF-8" );

		try{
			String _payload = req.getParameter( "payload" );
			if( _payload != null && _payload.length() > 0 ){
				payload = _payload;
			}
			String _deviceid = req.getParameter( "deviceid" );
			if( _deviceid != null && _deviceid.length() > 0 ){
				deviceid = _deviceid;
			}
			String _devicetype = req.getParameter( "devicetype" );
			if( _devicetype != null && _devicetype.length() > 0 ){
				devicetype = _devicetype;
			}
			String _eventtype = req.getParameter( "eventtype" );
			if( _eventtype != null && _eventtype.length() > 0 ){
				eventtype = _eventtype;
			}
			
			//System.out.println( "QuickstartPublishServlet: deviceid=" + deviceid + ", payload=" + payload );

			
			if( payload.length() > 0 && deviceid.length() > 0 && devicetype.length() > 0 && eventtype.length() > 0 ){
				status = "OK";
				QuickstartPublisher smc = new QuickstartPublisher();
				smc.runClient( payload, deviceid, devicetype, eventtype );
			}
		}catch( Exception e ){
			e.printStackTrace();
			status = "Error\", \"exception\": \"" + e;
		}
		
		String out = "{ \"status\": \"" + status + "\" }";
		
		res.setContentType( contenttype );
		res.setCharacterEncoding( "UTF-8" );
		res.getWriter().println( out );
	}
}
