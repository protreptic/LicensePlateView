package name.peterbukhal.android.licenseplateview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import name.peterbukhal.android.licenseplate.LicensePlateView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final LicensePlateView lpCarNumber = (LicensePlateView) findViewById(R.id.lpCarNumber);
        final EditText etCarNumber = (EditText) findViewById(R.id.etCa);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lpCarNumber.setCarNumber(etCarNumber.getText().toString());
            }
        });
    }
}
