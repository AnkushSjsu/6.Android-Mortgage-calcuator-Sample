package android.javacodegeeks.com.apps;

import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.graphics.Color;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Calendar.*;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner1;
    private long etNum1;
    private long etNum2;
    float etNum3;
    float etNum4;
    float etNum5;
    float etNum6;
    EditText text;
    TextView tx1,tx2,tx3,tx4,tx;
    float result=0;

    RelativeLayout calculateTable;
    List<Fragment> fragList = new ArrayList<Fragment>();
   // TableLayout tble ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Fragment myfragment;
        myfragment = null;

        if(myfragment==null){
            myfragment = new FragmentTwo();
            fragList.add(myfragment);
        }



        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, myfragment);
        fragmentTransaction.commit();




    }

    public void FragmentOneClick(View view) {

     //   tble =(TableLayout)findViewById(R.id.calculateTable);
        long num1 = 0;
        long num2 = 0;


        spinner1 = (Spinner) findViewById(R.id.dropYear);
        String term = String.valueOf(spinner1.getSelectedItem());

        String h_Value = ((EditText) findViewById(R.id.editText1)).getText().toString();
        String d_Pay = ((EditText) findViewById(R.id.editText2)).getText().toString();
        String int_rate = ((EditText) findViewById(R.id.editText3)).getText().toString();
//        String term = ((EditText) findViewById(R.id.editText4)).getText().toString();
        String p_t_Rate = ((EditText) findViewById(R.id.editText6)).getText().toString();

        if (h_Value.matches("")||d_Pay.matches("")||int_rate.matches("")||term.matches("")||p_t_Rate.matches("")) {
            Toast.makeText(this, "Field Cannot be Empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        double home_val= Double.parseDouble(h_Value);

        double downPay= Double.parseDouble(d_Pay);

        if(home_val <= downPay){
            Toast.makeText(this, "DownPayment cannot be greater than or equal to HomeLoan!", Toast.LENGTH_LONG).show();
            return;
        }


        double interest_Rate= Double.parseDouble(int_rate);

        double term_val= Double.parseDouble(term);

        double p_tax_Rate= Double.parseDouble(p_t_Rate);


        double p = home_val-downPay;


        double n = term_val*12;


        double i = interest_Rate/100;
        i = i/12;


        double iPow = Math.pow(i+1,n);


        double monthPay = ( p * i * iPow) / (iPow - 1);


        double totalPropertyTax = home_val * 0.01 * term_val;


        double monthPTax = totalPropertyTax / n ;


        double totalMonthPay = monthPay + monthPTax;


        double totalInterest = (totalMonthPay * n) - home_val;


        double prpoerty_tax_paid = totalPropertyTax;


        String pay_off_date = "invalid";

        Date date = new Date();


        int month = Calendar.getInstance().get(Calendar.MONTH);

        if(month==0)
        {
            month = 12;
        }
        else{
            month = month - 1;
        }

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int fterm =(int)term_val;
        pay_off_date = String.valueOf(year + fterm);
        pay_off_date = getMonthForInt(month) + ", " + pay_off_date;

        Fragment myfragment;
        myfragment = null;

        Bundle data = new Bundle();
        data.putString("monthPay",String.valueOf(Math.round(totalMonthPay*100.00)/100.00 ));
        data.putString("interest",String.valueOf(Math.round(totalInterest*100.00)/100.00));
        data.putString("prpoerty_tax_paid",String.valueOf(Math.round(prpoerty_tax_paid*100.00)/100.00));
        data.putString("pay_off_data", pay_off_date);

        if(myfragment==null){
            myfragment = new FragmentOne();
            fragList.add(myfragment);
            myfragment.setArguments(data);
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, myfragment);
        fragmentTransaction.commit();


    }

    public void FragmentTwoClick(View view) {
        Fragment myfragment;
        myfragment = new FragmentTwo();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, myfragment);
        fragmentTransaction.commit();
    }
    String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }
}
