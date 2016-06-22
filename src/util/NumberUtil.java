package util;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.function.Function;

/**
 * Created by Ethan on 16/6/21.
 */
public class NumberUtil {
    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static <T> DoubleBinding sum(ObservableList<T> list, Function<T, DoubleBinding> getNumber) {
        DoubleBinding result = Bindings.createDoubleBinding(() -> list.stream().map(getNumber)
                .map(DoubleBinding::get)
                .reduce(0.0, (a, b) -> a + b), list);
        InvalidationListener listener = e -> result.invalidate();
        list.addListener((ListChangeListener<? super T>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (T t : change.getAddedSubList()) {
                        getNumber.apply(t).addListener(listener);
                    }
                } else if (change.wasRemoved()) {
                    for (T t : change.getRemoved()) {
                        getNumber.apply(t).removeListener(listener);
                    }
                }
            }
        });
        for (T t : list) {
            getNumber.apply(t).addListener(listener);
        }
        return result;
    }
}
