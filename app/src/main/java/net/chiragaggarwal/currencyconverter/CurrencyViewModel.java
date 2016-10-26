package net.chiragaggarwal.currencyconverter;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.math.BigDecimal;

public class CurrencyViewModel extends BaseObservable {
    private BigDecimal inr = new BigDecimal(0);
    private BigDecimal idr = new BigDecimal(0);

    @Bindable
    public String getInr() {
        return idr.divide(new BigDecimal(200)).toString();
    }

    @Bindable
    public String getIdr() {
        return inr.multiply(new BigDecimal(200)).toString();
    }

    public void setInr(String editTextInrValue) {
        if (editTextInrValue.isEmpty()) {
            inr = new BigDecimal(0);
        } else {
            inr = new BigDecimal(editTextInrValue);
        }
        notifyPropertyChanged(BR.idr);
    }

    public void setIdr(String editTextIdrValue) {
        if (editTextIdrValue.isEmpty()) {
            idr = new BigDecimal(0);
        } else {
            idr = new BigDecimal(editTextIdrValue);
        }
        notifyPropertyChanged(BR.inr);
    }
}
