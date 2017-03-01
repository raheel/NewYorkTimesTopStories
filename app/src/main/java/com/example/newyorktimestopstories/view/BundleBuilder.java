package com.example.newyorktimestopstories.view;

import android.os.Bundle;

/**
 * Created by raheelkhan on 2/28/17.
 */

public class BundleBuilder {
    private final Bundle bundle;

    public BundleBuilder(Bundle bundle) {
        this.bundle = bundle;
    }

    public BundleBuilder putString(String key, String value) {
        bundle.putString(key, value);
        return this;
    }

    public Bundle build() {
        return bundle;
    }
}
