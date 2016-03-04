package ua.nenya.alex.util;

import java.util.List;

import ua.nenya.alex.project.GetNameInterface;

public class ListUtilits {
	
	public int choseIndexFromList(List<?> list, IO io) {
		int index = 0;
		showList(list, io);
		do {
			try {
				String str = io.readConsole();
				index = Integer.parseInt(str);
				if(index < 0 || index > list.size()){
					io.writeln("Number is out of range! Try again!");
				}
			} catch (NumberFormatException e) {
				io.writeln("Wrong entering!!! Try again!");
				index = list.size()+1;
			}
		} while (index < 0 || index > list.size());
		return index;
	}

	private void showList(List<?> list, IO io) {
		io.writeln("Choose one of the items bellow");
		io.writeEmpty();
		io.writeln("0	-	Exit");
		for (int i = 1; i <= list.size(); i++) {
				GetNameInterface it = (GetNameInterface) list.get(i-1);
				io.writeln(i + "	-	" + it.getName());
		}
	}
}