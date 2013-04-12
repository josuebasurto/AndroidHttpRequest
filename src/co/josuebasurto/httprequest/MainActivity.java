package co.josuebasurto.httprequest;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class MainActivity extends Activity {

	protected static final String K_TAG = "httprequest";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setStrictMode();
		getControls();
		setEvents();
	}

	private void setStrictMode() {
	    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().penaltyDeath().build());
	    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().penaltyDeath().build());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	EditText urlToRequest;
	Button request;
	
	private void setEvents() {
		
		request.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String url = urlToRequest.getText().toString();
//				
//				HttpClient httpclient = new DefaultHttpClient();
//				HttpGet httpget = new HttpGet(url);
//				HttpResponse response;
//				
//				try {
//					setActionLog("HttpRequest: " + url);
//					response = httpclient.execute(httpget);
//					
//					setActionLog("Response Status " + response.getStatusLine().toString());
//					HttpEntity httpentity = response.getEntity();
//					
//					if (httpentity != null){
//						setActionLog("HttpRequest is NOT Null");
//						
//						InputStream instream = httpentity.getContent();
//						String result = Tools.convertStreamToString(instream);
//						instream.close();
//						setActionLog("Result: " + result);
//						
//					}
//					else{
//						setActionLog("HttpEntity is null!");
//					}
//					
//				} catch (ClientProtocolException e) {
//					e.printStackTrace();
//					setActionLog(e.getMessage());
//				} catch (IOException e) {
//					e.printStackTrace();
//					setActionLog(e.getMessage());
//				} 
//				setActionLog("End Action.");
				
				AsyncHttpClient client = new AsyncHttpClient();
				client.get(url, new AsyncHttpResponseHandler(){
					@Override
					public void onSuccess(String response)
					{
						setActionLog(response);
					}
				});
			}
		
		});
		
	}

	private void getControls() {
		urlToRequest = (EditText) findViewById(R.id.urlToRequest);
		request = (Button) findViewById(R.id.request);
	}

	private void setActionLog(String mensaje) {
		Log.v(K_TAG,mensaje);
		Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
	}
}
