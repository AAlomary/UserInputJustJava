package com.example.ahmadalomary.userinputjustjava;

    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.view.View;
    import android.widget.CheckBox;
    import android.widget.TextView;

    import java.math.BigDecimal;
    import java.text.NumberFormat;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int total=0;
    int totalPrice=0;
    boolean wIsCheck = false;
    boolean IsCheck = false;
    boolean cIsCheck = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantityTextView);
        total += number;
        quantityTextView.setText("" + total);
    }

    private void displayPrice() {
        TextView priceTextView = (TextView) findViewById(R.id.priceTextView);
        int price = calculatePrice();
        priceTextView.setText("Name: Ahmad\nQuantity: " + total + "\nTotal: " + NumberFormat.getCurrencyInstance().format(price) + "\nWhipped cream? " + wIsCheck + "\nChocolate? " + cIsCheck + "\nThank You!");

    }

    private int calculatePrice() {
        totalPrice = total * 5;
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

}

