import org.junit.Assert;
import org.junit.Test;

public class InputTest {

    OfficerRequirement requirementTester = new OfficerRequirement();

    @Test
    public void userIDTest () {
        String userIDInputTester;
        // Tester om et bogstav kan bruges som input
        userIDInputTester = "l";
        Assert.assertFalse(requirementTester.IDChecker(userIDInputTester));

        // Tester om et special tegn kan bruges som input
        userIDInputTester = "}";
        Assert.assertFalse(requirementTester.IDChecker(userIDInputTester));

        // Tester om værdier udenfor den satte grænse (11-99) kan bruges
        userIDInputTester = "10";
        Assert.assertFalse(requirementTester.IDChecker(userIDInputTester));
        userIDInputTester = "100";
        Assert.assertFalse(requirementTester.IDChecker(userIDInputTester));

        // Tester om korrekte værdier bliver modtaget
        userIDInputTester = "12";
        Assert.assertTrue(requirementTester.IDChecker(userIDInputTester));
        userIDInputTester = "99";
        Assert.assertTrue(requirementTester.IDChecker(userIDInputTester));

    }

    @Test
    public void passwordTest() {
        String passwordInputTester;

        // Starter med at teste om et password der ikke har nok special tegn
        passwordInputTester = "PasswordTest"; // <- Et password med kun 2 ud af 4 mulige "tegn typer". Den skal have 3 for at bestå.
        Assert.assertFalse(requirementTester.passwordChecker(passwordInputTester));

        // Så en test der checker om et password bliver godkendt hvis det opfylder kravene og har 3 ud af 4 mulige tegntyper:
        passwordInputTester = "Password!Der_Skal0Godkendes";
        Assert.assertTrue(requirementTester.passwordChecker(passwordInputTester));

        // Til sidst nogen test, der sikrer at visse tegn typer ikke kan modtages for at undgå SQL injections (tegn såsom ', / og })
        passwordInputTester = "Farligt0)Password";
        Assert.assertFalse(requirementTester.passwordChecker(passwordInputTester));

        passwordInputTester = "Igen!0;FarligtPass";
        Assert.assertFalse(requirementTester.passwordChecker(passwordInputTester));

        passwordInputTester = "Sidste'Farlige0!Pass";
        Assert.assertFalse(requirementTester.passwordChecker(passwordInputTester));
    }
}
