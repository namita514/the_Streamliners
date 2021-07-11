package com.example.intentsplayground;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.intentsplayground.databinding.ActivityIntentPlaygroundBinding;

public class IntentPlaygroundActivity extends AppCompatActivity {
    private static final int REQUEST_COUNT = 0;
    ActivityIntentPlaygroundBinding b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialising the binding
        b=ActivityIntentPlaygroundBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setTitle("Intents Playground");
        setupHideErrorForEditText();

    }
//initial setup ----------------
    private void setupHideErrorForEditText() {
        //adding callback to the editTexts to take some action when the text changes
        TextWatcher myTextWatcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                hideError();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        b.data.getEditText().addTextChangedListener(myTextWatcher);
        b.initialCounter.getEditText().addTextChangedListener(myTextWatcher);
    }
//event handler
       //setting us explicit intent as the particular location to shift is known
        public void callExplicitIntent(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
}

    // setting up implicit intent as the particular location is not known
    public void setImplicitIntent(View view) {
        // validate data input
        String input=b.data.getEditText().getText().toString().trim();
        if(input.isEmpty()){
            b.data.setError("Please Enter Something");
            return;
        }
        // validate intent type
        int type=b.intentTypeRadioGroup.getCheckedRadioButtonId();
        //handle implicit intent cases by comparing the type of radio button selected

            if(type==R.id.openWebpageBtn){
            openWebPage(input);}
            else if(type==R.id.dialNumberBtn) {
                dialNumber(input);}
            else if(type==R.id.ShareTextBtn){
                shareText(input);}
            else{
                Toast.makeText(this,"please enter an intent type!",Toast.LENGTH_SHORT).show();
        }

    }
    //sending count to the main activity
    public void sendData(View view) {
        //validate the input
        //store the data entered by the user in the String variable
        String input=b.initialCounter.getEditText().getText().toString().trim();
        if(input.isEmpty()){
            b.initialCounter.setError("Please Enter Something");
            return;
        }
        //get count
        int initialCount=Integer.parseInt(input);
        //create intent
        Intent intent=new Intent(this,MainActivity.class);
     //create bundle to pass
        Bundle bundle=new Bundle();
        bundle.putInt(Constants.INITIAL_COUNT_KEY,initialCount);
        bundle.putInt(Constants.MIN_VAL,-100);
        bundle.putInt(Constants.MAX_VAL,100);

        // pass bundle
        intent.putExtras(bundle);
        startActivityForResult(intent,REQUEST_COUNT);


    }
//implement onActivityResult callback
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //checking the incomming data
        if (requestCode == REQUEST_COUNT && resultCode == RESULT_OK) {
            assert data != null;
           int finalCount = data.getIntExtra(Constants.FINAL_VALUE, Integer.MIN_VALUE);
            b.result.setText("Final count received = " + finalCount);
            b.result.setVisibility(View.VISIBLE);
        }
    }


    //implicit intent sender
    private void shareText(String text) {
        //share the text with the intents
        Intent intent2 = new Intent(); intent2.setAction(Intent.ACTION_SEND);
        intent2.setType("text/plain");
        intent2.putExtra(Intent.EXTRA_TEXT, text );
        startActivity(Intent.createChooser(intent2, "Share via"));
        hideError();

    }

    private void dialNumber(String number) {
        //validating number with regExp
        if(!number.matches("^\\d{10}")){
            b.data.setError("Invalid Number");
            return;
        }
        //to dial a number with the calling application of particular device
        Uri uri= Uri.parse("tel:"+number);
        Intent intent=new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
        hideError();
    }


    private void openWebPage(String url) {
        //validating url with regExp
        if(!url.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")){
            b.data.setError("Invalid URL");
            return;
        }
        //to open the url with any of the browser
        Uri uri= Uri.parse(url);
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
        hideError();
    }
    // utils
    private  void hideError(){
        b.data.setError(null);
    }



}
