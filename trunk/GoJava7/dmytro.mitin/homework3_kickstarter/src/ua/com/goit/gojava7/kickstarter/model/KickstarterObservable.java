package ua.com.goit.gojava7.kickstarter.model;

import ua.com.goit.gojava7.kickstarter.view.exception.ExitException;
import ua.com.goit.gojava7.kickstarter.view.View;

/**
 * Created by Dmytro on 07.11.2015.
 */
public interface KickstarterObservable {

    void notifyView() throws ExitException;

    void addObserver(View view);

    void removeObserver(View view);
}