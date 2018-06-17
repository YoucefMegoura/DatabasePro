package dz.youcefmegoura.test.databasepro.Views.Menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import dz.youcefmegoura.test.databasepro.R;
import es.dmoral.toasty.Toasty;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }

    public void back_click(View view) {
        finish();
    }

    public void Website(View view) {
        Toasty.info(this,"www.SphinxKids.com", Toast.LENGTH_SHORT).show();
    }

    public void Facebook(View view) {
        Toasty.info(this,"Sphinx Kids", Toast.LENGTH_SHORT).show();
    }

    public void Google(View view) {
        Toasty.info(this,"SphinxKids@gmail.com", Toast.LENGTH_SHORT).show();
    }
}
