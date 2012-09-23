package att.hackathon.sparqcam;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

public class Login extends Activity implements View.OnClickListener{
	
	private Button loginBtn;
	private EditText username;
	private EditText password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		loginBtn = (Button) findViewById(R.id.loginBtn);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		
		loginBtn.setClickable(true);
		loginBtn.setOnClickListener(this);
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		MainActivity.show(Login.this);
	}
}
