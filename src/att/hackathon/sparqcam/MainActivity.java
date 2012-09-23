package att.hackathon.sparqcam;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

	final static int REQUEST_VIDEO_CAPTURED = 1;
	Uri uriVideo = null;
	VideoView videoviewPlay;
	boolean isRecorded = false;

	public static void show(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
	
	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button buttonRecording = (Button)findViewById(R.id.recording);
//		Button buttonPlayback = (Button)findViewById(R.id.playback);
		videoviewPlay = (VideoView)findViewById(R.id.videoview);
	    
	    buttonRecording.setOnClickListener(new Button.OnClickListener(){
	    	@Override
	    	public void onClick(View arg0) {
	    		if(!isRecorded) {
		    		// TODO Auto-generated method stub
		    		Intent intent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
		    		startActivityForResult(intent, REQUEST_VIDEO_CAPTURED);
		    		isRecorded = true;
	    		} else {
	    			if(uriVideo == null){
		    			Toast.makeText(MainActivity.this, "No Video", Toast.LENGTH_LONG).show();
		    		}else{
		    			Toast.makeText(MainActivity.this, "Playback: " + uriVideo.getPath(),
		    					Toast.LENGTH_LONG).show();
		    			//videoviewPlay.setVisibility(visibility);
		    			videoviewPlay.setVideoURI(uriVideo);
		    			videoviewPlay.start();
		    		}
	    			isRecorded = false;
	    		}
	    	}});
	    
//	    buttonPlayback.setOnClickListener(new Button.OnClickListener(){
//	    	@Override
//	    	public void onClick(View arg0) {
//	    		// TODO Auto-generated method stub
//	    		if(uriVideo == null){
//	    			Toast.makeText(MainActivity.this, "No Video", Toast.LENGTH_LONG).show();
//	    		}else{
//	    			Toast.makeText(MainActivity.this, "Playback: " + uriVideo.getPath(),
//	    					Toast.LENGTH_LONG).show();
//	    			videoviewPlay.setVideoURI(uriVideo);
//	    			videoviewPlay.start();
//	    		}
//	    	}});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(resultCode == RESULT_OK){
			if(requestCode == REQUEST_VIDEO_CAPTURED){
				uriVideo = data.getData();
				Toast.makeText(MainActivity.this, uriVideo.getPath(),Toast.LENGTH_LONG).show();
			}
		}else if(resultCode == RESULT_CANCELED){
			uriVideo = null;
			Toast.makeText(MainActivity.this,"Cancelled!", Toast.LENGTH_LONG).show();
		}
	}
}
