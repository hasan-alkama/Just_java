package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 2;

    public void increment(View view) {
        quantity += 1;
        display(quantity);
    }

    public void decrement(View view) {
        quantity -= 1;
        if (quantity > 0)
            display(quantity);
        else {
            quantity = 1;
            display(1);
        }
    }
//    public int claculatePrice(int quantity2)
//    {
//        return 5*quantity2;
//    }

    public void submitOrder(View view)
    {
        EditText edt =(EditText) findViewById(R.id.edittxtview);
        String val=edt.getText().toString();
        CheckBox checkBoxwh=(CheckBox) findViewById(R.id.checkBox_whiped);
        CheckBox checkBox1ch=(CheckBox) findViewById(R.id.checkBoxch);
        boolean checkedwh=checkBoxwh.isChecked();
        boolean checed1ch=checkBox1ch.isChecked();
          int Price=CalculatePrice(checed1ch,checkedwh);
         String body=createOrderSummary(Price,checkedwh,checed1ch,val);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
      intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "hasanalkamal786@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for "+val);
        intent.putExtra(intent.EXTRA_TEXT,body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

private int CalculatePrice(boolean checed1ch,boolean checkedwh)
{
    int price=5*quantity;
    if(checed1ch)price+=2;
    if(checkedwh)price+=1;
    return price;
}
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
//    private void displayPrice(int number) {
//        TextView OrderSummaryTextView = (TextView) findViewById(R.id.OrderSummary_text_view);
//       OrderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }
//
//    private void displayMessage(String message) {
//        TextView OrderSummaryTextView = (TextView) findViewById(R.id.OrderSummary_text_view);
//        OrderSummaryTextView.setText(message);
//    }

    public String createOrderSummary(int price,boolean check,boolean ch1,String name)
    {
        String message = "NAME:"+name+"\nAdd whipedCream?"+check+ "\nAdd chocolate?"+ch1+"\nQUANTITY: " + quantity + "\nTOTAL PRICE: " + price+"\nTHANK YOU\nHAVE A GOOD DAY";
        return message;
    }

}