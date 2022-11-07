package DollarsBanking;

public class AccountNotFoundException extends Exception{

    public AccountNotFoundException(String errorMessage) {
        super("Your account was not found");
    }

}
