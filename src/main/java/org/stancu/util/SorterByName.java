package org.stancu.util;

import org.stancu.model.MenuItem;

import java.util.Comparator;

public class SorterByName implements Comparator<MenuItem> {

    @Override
    public int compare(MenuItem o1, MenuItem o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
