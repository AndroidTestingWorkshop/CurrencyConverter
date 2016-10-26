package net.chiragaggarwal.currencyconverter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    private TextWatcher inrTextWatcher;
    private TextWatcher idrTextWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editTextIdr = (EditText) findViewById(R.id.edit_text_idr);
        final EditText editTextInr = (EditText) findViewById(R.id.edit_text_inr);
        Button buttonClear = (Button) findViewById(R.id.button_clear);
        inrTextWatcher = getInrTextWatcher(editTextIdr);
        idrTextWatcher = getIdrTextWatcher(editTextInr);

        setEditTextIdrTextChangedListener(editTextIdr);
        setEditTestInrTextChangedListener(editTextInr);
        setButtonClearOnClickListener(editTextIdr, editTextInr, buttonClear);
    }

    @NonNull
    private TextWatcher getIdrTextWatcher(final EditText editTextInr) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                unsetTextChangedListener(editTextInr, inrTextWatcher);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String editTextIdrValue = s.toString();
                if (editTextIdrValue.isEmpty()) {
                    editTextInr.setText("0");
                } else {
                    BigDecimal idrValue = new BigDecimal(editTextIdrValue);
                    BigDecimal inrValue = idrValue.divide(new BigDecimal(200));
                    editTextInr.setText(inrValue.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                setEditTestInrTextChangedListener(editTextInr);
            }
        };
    }

    @NonNull
    private TextWatcher getInrTextWatcher(final EditText editTextIdr) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                unsetTextChangedListener(editTextIdr, idrTextWatcher);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String editTextInrValue = s.toString();
                if (editTextInrValue.isEmpty()) {
                    editTextIdr.setText("0");
                } else {
                    BigDecimal inrValue = new BigDecimal(editTextInrValue);
                    BigDecimal idrValue = inrValue.multiply(new BigDecimal(200));
                    editTextIdr.setText(idrValue.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                setEditTextIdrTextChangedListener(editTextIdr);
            }
        };
    }

    private void setButtonClearOnClickListener(final EditText editTextIdr, final EditText editTextInr, Button buttonClear) {
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextIdr.setText("");
                editTextInr.setText("");
            }
        });
    }

    private void setEditTextIdrTextChangedListener(EditText editTextIdr) {
        editTextIdr.addTextChangedListener(idrTextWatcher);
    }

    private void unsetTextChangedListener(EditText editText, TextWatcher textWatcher) {
        editText.removeTextChangedListener(textWatcher);
    }

    private void setEditTestInrTextChangedListener(final EditText editTextInr) {
        editTextInr.addTextChangedListener(inrTextWatcher);
    }
}
