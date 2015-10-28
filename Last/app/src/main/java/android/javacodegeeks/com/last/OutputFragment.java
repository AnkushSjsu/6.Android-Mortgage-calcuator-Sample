package android.javacodegeeks.com.last;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.javacodegeeks.com.last.R;
public class OutputFragment extends Fragment {
    TextView output;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.output_fragment, viewGroup, false);
        output= (TextView)view.findViewById(R.id.word);
        return view;
    }
    public void display(String txt){
        output.setText(txt);
    }
}