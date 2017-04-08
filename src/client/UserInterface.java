package client;

/**
 * Created by caoquan on 4/7/17.
 */
public class UserInterface {
    public void showDelimiter() {
        System.out.println("===================================");
    }

    public void showChooseOptions(int start, int end) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = start; i < end; i++) {
            if (i == end - 1) {
                stringBuilder.append(end - 1 + " or " + end);
            } else {
                stringBuilder.append(1 + ", ");
            }

        }
        System.out.println(stringBuilder);
    }

    public void showMainMenu() {
        showDelimiter();
        System.out.println("Are you a Tenant or Renter");
        System.out.println("1. Tenant");
        System.out.println("2. Renter");
        System.out.println("3. Exit");
        showChooseOptions(1, 3);
    }

    public void showTenantMenu() {
        showDelimiter();
        System.out.println("1. Request Rentals by criteria.");
        System.out.println("2. Reserve a rental.");
        System.out.println("3. Back");
        showChooseOptions(1, 3);
    }

    public void showCriteriaMenu() {
        showDelimiter();
        System.out.println("1. All available rental");
        System.out.println("2. All available rental monthly below.");
        System.out.println("3. All available rental monthly with number of rooms.");
        System.out.println("4. Back");
        showChooseOptions(1, 4);
    }

}
