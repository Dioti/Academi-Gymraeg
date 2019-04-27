package database;
import java.util.Scanner;

public class DatabaseTextInterface
{
	// global variables
	Scanner in;
	boolean running;
	String currentMenu;
	DatabaseManager manager;
	
	
	
	/**
	 * Constructor for the interface
	 */
	public DatabaseTextInterface(DatabaseManager manager) {
		in = new Scanner(System.in);
		running = true;
		currentMenu = "Main";
		this.manager = manager;
	}
	
	
	
	/**
	 * Starts the interface
	 */
	public void start() {
		while(running) {
			printMenu(currentMenu);
			String option = getOption();
			System.out.println();
			doOption(option);
		}
		System.out.println("Quit!");
	}
	
	
	
	/**
	 * Prints the current menu
	 * @param currentMenu
	 */
	public void printMenu(String currentMenu) {
		if(currentMenu.equals("Main")) {
			System.out.println("Main Menu");
			System.out.println("******************");
			System.out.println("1. Students");
			System.out.println("2. Modules");
			System.out.println("3. Staff");
			System.out.println("4. Registrations");
			System.out.println("5. Course Tutors");
			System.out.println("6. Reports");
			System.out.println("0. Quit");
		} else if(currentMenu.equals("Students")) {
			System.out.println("Sub-Menu (Students)");
			System.out.println("******************");
			System.out.println("1. Add student");
			System.out.println("2. Remove student");
			System.out.println("3. Update student");
			System.out.println("4. List students");
			System.out.println("0. Return to main menu");
		} else if(currentMenu.equals("Modules")) {
			System.out.println("Sub-Menu (Modules)");
			System.out.println("******************");
			System.out.println("1. Add module");
			System.out.println("2. Remove module");
			System.out.println("3. Update module");
			System.out.println("4. List modules");
			System.out.println("0. Return to main menu");
		} else if(currentMenu.equals("Staff")) {
			System.out.println("Sub-Menu (Staff)");
			System.out.println("******************");
			System.out.println("1. Add staff");
			System.out.println("2. Remove staff");
			System.out.println("3. Update staff");
			System.out.println("4. List staff");
			System.out.println("0. Return to main menu");
		} else if(currentMenu.equals("Registrations")) {
			System.out.println("Sub-Menu (Registrations)");
			System.out.println("******************");
			System.out.println("1. Add registration");
			System.out.println("2. Remove registration");
			System.out.println("3. Update registration");
			System.out.println("4. List registration");
			System.out.println("0. Return to main menu");
		} else if(currentMenu.equals("Teaches")) {
			System.out.println("Sub-Menu (Course Tutors)");
			System.out.println("******************");
			System.out.println("1. Add course tutor");
			System.out.println("2. Remove course tutor");
			System.out.println("3. Update course tutor");
			System.out.println("4. List course tutor");
			System.out.println("0. Return to main menu");
		} else if(currentMenu.equals("Reports")) {
			System.out.println("Sub-Menu (Reports)");
			System.out.println("******************");
			System.out.println("1. Modules taught by");
			System.out.println("2. Students registered on");
			System.out.println("3. Staff who teach student");
			System.out.println("4. Staff who teach more than");
			System.out.println("0. Return to main menu");
		} else {
			System.out.println("Current menu is unknown.\n");
		}
	}
	
	
	
	/**
	 * Chooses the action to be executed based on the input option and the current menu
	 * @param option
	 */
	public void doOption(String option) {
		String id;
		if(currentMenu.equals("Main")) {
			switch(option) {
				case "1":
					currentMenu = "Students";
					break;
				case "2":
					currentMenu = "Modules";
					break;
				case "3":
					currentMenu = "Staff";
					break;
				case "4":
					currentMenu = "Registrations";
					break;
				case "5":
					currentMenu = "Teaches";
					break;
				case "6":
					currentMenu = "Reports";
					break;
				case "0":
					System.out.println("*************\nQuitting...\n");
					running = false;
					break;
				default:
					System.out.println("Error choosing option. Please try again.\n");
					break;
			}
		} else if (currentMenu.equals("Students")) {
			switch(option) {
				case "1":
					manager.addEntry("student", getNewEntry("student"));
					break;
				case "2":
					manager.removeEntry("student", getExistingEntry());
					break;
				case "3":
					id = getExistingEntry();
					String[] updatedEntry = getUpdatedEntry("student");
					manager.updateEntry("student", id, updatedEntry);
					break;
				case "4":
					manager.printTable("Student");
					break;
				case "0":
					currentMenu = "Main";
					break;
				default:
					System.out.println("Error choosing option. Please try again.\n");
					break;
			}
		} else if(currentMenu.equals("Modules")) {
			switch(option) {
				case "1":
					manager.addEntry("module", getNewEntry("module"));
					break;
				case "2":
					manager.removeEntry("module", getExistingEntry());
					break;
				case "3":
					id = getExistingEntry();
					String[] updatedEntry = getUpdatedEntry("module");
					manager.updateEntry("module", id, updatedEntry);
					break;
				case "4": 
					manager.printTable("Module");
					break;
				case "0":
					currentMenu = "Main";
					break;
				default:
					System.out.println("Error choosing option. Please try again.\n");
					break;
			}
		} else if(currentMenu.equals("Staff")) {
			switch(option) {
				case "1":
					manager.addEntry("staff", getNewEntry("staff"));
					break;
				case "2":
					manager.removeEntry("staff", getExistingEntry());
					break;
				case "3":
					id = getExistingEntry();
					String[] updatedEntry = getUpdatedEntry("staff");
					manager.updateEntry("staff", id, updatedEntry);
					break;
				case "4": 
					manager.printTable("Staff");
					break;
				case "0":
					currentMenu = "Main";
					break;
				default:
					System.out.println("Error choosing option. Please try again.\n");
					break;
			}
		} else if(currentMenu.equals("Registrations")) {
			switch(option) {
				case "1":
					manager.addEntry("registered", getNewEntry("registered"));
					break;
				case "2":
					manager.removeEntry("registered", getExistingEntry());
					break;
				case "3":
					id = getExistingEntry();
					String[] updatedEntry = getUpdatedEntry("registered");
					manager.updateEntry("registered", id, updatedEntry);
					break;
				case "4": 
					manager.printTable("Registered");
					break;
				case "0":
					currentMenu = "Main";
					break;
				default:
					System.out.println("Error choosing option. Please try again.\n");
					break;
			}
		} else if(currentMenu.equals("Teaches")) {
			switch(option) {
				case "1":
					manager.addEntry("teaches", getNewEntry("teaches"));
					break;
				case "2":
					manager.removeEntry("teaches", getExistingEntry());
					break;
				case "3":
					id = getExistingEntry();
					String[] updatedEntry = getUpdatedEntry("teaches");
					manager.updateEntry("teaches", id, updatedEntry);
					break;
				case "4": 
					manager.printTable("Teaches");
					break;
				case "0":
					currentMenu = "Main";
					break;
				default:
					System.out.println("Error choosing option. Please try again.\n");
					break;
			}
		} else if(currentMenu.equals("Reports")) {
			switch(option) {
				case "1":
					System.out.print("staff_");
					manager.doReport(1, getExistingEntry());
					break;
				case "2":
					System.out.print("module_");
					manager.doReport(2, getExistingEntry());
					break;
				case "3":
					System.out.print("student_");
					manager.doReport(3, getExistingEntry());
					break;
				case "4":
					manager.doReport(4, "dummy_id"); // this report requires no input
					break;
				case "0":
					currentMenu = "Main";
					break;
				default:
					System.out.println("Error choosing option. Please try again.\n");
					break;
			}
		} else {
			System.out.println("Current menu is unknown\n");
		}
	}
	
	
	
	/**
	 * Returns the option the user enters
	 * @return
	 */
	public String getOption() {
		System.out.print("\n> ");
		return in.nextLine();
	}
	
	
	
	/**
	 * Allows the user to enter the id for an existing entry
	 * Returns the student ID
	 * @return
	 */
	public String getExistingEntry() {
		System.out.print("id > ");
		String id = in.nextLine();
		return id;
	}
	
	
	
	/**
	 * Allows the user to enter details for a new entry, based on the given table
	 * Returns entry details as a String array
	 * @param table
	 * @return
	 */
	public String[] getNewEntry(String table) {
		String[] arr = manager.getColumns(table);
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " > ");
			arr[i] = in.nextLine();
		}
		return arr;
	}
	
	
	
	/**
	 * Allows the user to enter new details for an existing entry from a given table
	 * 
	 * @param table
	 * @return
	 */
	public String[] getUpdatedEntry(String table) {
		String[] columns = manager.getColumns(table); // gets number of columns in table
		String[] update = new String[columns.length - 1]; // get length of array containing updated details
		for(int i = 0; i < update.length; i++) {
			System.out.print("new " + columns[i + 1] + " > ");
			update[i] = in.nextLine();
		}
		return update;
	}
	
	

}
