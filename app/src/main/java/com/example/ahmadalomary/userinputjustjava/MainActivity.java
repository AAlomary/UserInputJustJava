package com.example.ahmadalomary.userinputjustjava;

    import android.content.Context;
    import android.content.Intent;
    import android.net.Uri;
    import android.os.Bundle;
    import android.os.SystemClock;
    import android.support.v7.app.AppCompatActivity;
    import android.view.MotionEvent;
    import android.view.View;
    import android.view.ViewGroup;
    import android.view.inputmethod.InputMethodManager;
    import android.widget.CheckBox;
    import android.widget.EditText;
    import android.widget.TextView;
    import android.widget.Toast;

    import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int total=0;
    int totalPrice=0;
    boolean wIsCheck = false;
    boolean IsCheck = false;
    boolean cIsCheck = false;
    String output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View activity = (View) findViewById(R.id.activity_main);
        closeKeyboardAndFocus(activity,R.id.activity_main);
        setupUI(findViewById(R.id.activity_main));
    }

    public void addMore(View view) {
        display(1);
    }

    public void minusMore(View view) {
        if(total > 0) {
            display(-1);
        }
    }

    public void submitOrder(View view) {
        displayPrice();
        composeEmail("ahmad.alomary@autotrader.co.uk", output);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(this, "fsd",Toast.LENGTH_SHORT);
    }


    public void composeEmail(String address, String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

//
//        String url =  "http://www.google.com" ;
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//           startActivity(intent);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantityTextView);
        total += number;
        quantityTextView.setText("" + total);
    }

    private void displayPrice() {
        TextView priceTextView = (TextView) findViewById(R.id.priceTextView);
        EditText name = (EditText) findViewById(R.id.Name);
        int price = calcPriceWithExtras();
        output = ("Name: " + name.getText()  + "\nQuantity: " + total + "\nTotal: " + NumberFormat.getCurrencyInstance().format(price) + "\nWhipped cream? " + wIsCheck + "\nChocolate? " + cIsCheck + "\nThank You!");
        priceTextView.setText(output);

    }

    private int calculatePrice() {
        totalPrice = total * 5;
        return totalPrice;
    }

    public int calcPriceWithExtras(){
        if(cIsCheck && wIsCheck){
            totalPrice = calculatePrice() + (3 * total);
        }else if(cIsCheck){
            totalPrice = calculatePrice() + (2 * total);
        }else if(wIsCheck){
            totalPrice = calculatePrice() + total;
        }else{
            totalPrice = calculatePrice();
        }
        return totalPrice;
    }
    public boolean isChecked(View view){

        boolean checked = ((CheckBox) view).isChecked();
        System.out.println(checked);
        switch(view.getId()) {
            case R.id.whipped_cream_checkbox:
                if (checked){
                    IsCheck = true;
                }else{
                    IsCheck = false;
                }
                break;
            case R.id.chocolate_checkbox:
                if (checked){
                    IsCheck = true;
                }else{
                    IsCheck = false;
                }
                break;
        }
        return IsCheck;
    }

    public boolean whippedIsChecked(View view){
        wIsCheck = isChecked(view);
        return wIsCheck;
    }

    public boolean chocolateIsChecked(View view){
        cIsCheck = isChecked(view);
        return cIsCheck;
    }

    //stackoverflow code
    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    closeKeyboard(v);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }




    public static void closeKeyboard(View view) {
        toggleKeyboard(view, false);
    }

    public static void showKeyboard(View view) {
        toggleKeyboard(view, true);
    }

    private static void toggleKeyboard(View view, boolean show) {
        if (view != null && view.getContext() != null) {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

            if (inputMethodManager != null) {
                if (show) {
                    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
                } else {
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }
    }

    public static void closeKeyboardAndFocus(View view, int resourceId) {
        if (view != null) {
            closeKeyboard(view);
            View viewToFocus = view.findViewById(resourceId);

            if (viewToFocus != null) {
                viewToFocus.setFocusableInTouchMode(true);
                viewToFocus.requestFocus();
            }
        }
    }
}

