package android.javacodegeeks.com.apps;

import android.os.Bundle;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentOne extends Fragment {

    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle data = getArguments();
        System.out.println("monthly Pay"+data.getString("monthPay"));
//        montlypay.setText(data.getString("monthPay"));
        View view =  inflater.inflate(R.layout.fragment_one, container, false);
        TextView montlypay = (TextView) view.findViewById(R.id.textView);
        TextView interestPaid = (TextView) view.findViewById(R.id.textView2);
        TextView  ptaxPaid= (TextView) view.findViewById(R.id.textView3);
        TextView payoffdate = (TextView) view.findViewById(R.id.textView4);
        montlypay.setText(data.getString("monthPay"));
        interestPaid.setText(data.getString("interest"));
        ptaxPaid.setText(data.getString("prpoerty_tax_paid"));
        payoffdate.setText(data.getString("pay_off_data"));
        return view;

    }
}