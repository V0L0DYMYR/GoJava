package kickstarter.view;

import static kickstarter.control.State.*;

import java.io.IOException;

import kickstarter.control.State;
import kickstarter.exception.IncorrectInputException;

public class AskQuestionView extends AbstractView {

	@Override
	public State getDirection(int item) throws IncorrectInputException {
		if (item == 0 || item == 1) {
			return PROJECT;
		}
		throw new IncorrectInputException("Unknown item");
	}

	@Override
	protected String getHead() {
		return "---QUESTION---";
	}

	@Override
	protected String getMenu() {
		return String.format("%d - %s\r\n", 0, "EXIT");
	}

	@Override
	public int choiceItem(StringBuilder input) throws IncorrectInputException {
		try {
			view("enter a question please:");
			String line = getLine();
			input.append(line);
			return getItem(line);
		} catch (IOException e) {
			throw new IncorrectInputException(e);
		}
	}

	private int getItem(String line) {
		try {
			int item = Integer.parseInt(line);
			if (item == 0) {
				return item;
			}
		} catch (NumberFormatException ignore) {
		}
		return 1;
	}
}
