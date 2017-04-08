package client;

/**
 * Created by caoquan on 4/7/17.
 */
public class UserInterface {
    public void showDelimiter() {
        System.out.println("===================================");
    }

    public void showMainMenu() {
        showDelimiter();
        System.out.println("Are you a Tenant or Renter");
        System.out.println("1. Tenant");
        System.out.println("2. Renter");
        System.out.println("3. Exit");
        System.out.print("Please choose 1, 2 or 3: ");
    }

    public void showTenantMenu() {
        showDelimiter();
        System.out.println();
    }

}
