public class OfficerRequirement {
    // Fundet online
    public boolean isThisAnInt(String s) {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }
    // Fundet online
    public boolean isThisAString(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }
    public boolean isThisWithinBorder(int value, int minValue, int maxValue) {
        if (value >= minValue && value <= maxValue) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean passwordChecker(String newUserPassword) {
        char newUserPasswordCheck;
        boolean capitalCheck = false;
        boolean lowerCaseCheck = false;
        boolean numberCheck = false;
        boolean specialCharCheck = false;
        if (newUserPassword.length()>=6 && newUserPassword.length()<=50) {
            for (int j=0; j < newUserPassword.length(); j++) {
                newUserPasswordCheck = newUserPassword.charAt(j);
                if (Character.isDigit(newUserPasswordCheck)) {
                    numberCheck = true;
                }
                else if (Character.isUpperCase(newUserPasswordCheck)) {
                    capitalCheck = true;
                }
                else if (Character.isLowerCase(newUserPasswordCheck)) {
                    lowerCaseCheck = true;
                }
                else if (newUserPasswordCheck == '.' || newUserPasswordCheck == '-' || newUserPasswordCheck == '_' ||
                        newUserPasswordCheck == '+' || + newUserPasswordCheck == '!' || newUserPasswordCheck == '?' || newUserPasswordCheck == '=') {
                    specialCharCheck = true;
                }
            }
        }
        int requirementCheck = 0;
        if (numberCheck) {
            requirementCheck += 1;
        }
        if (capitalCheck) {
            requirementCheck += 1;
        }
        if (lowerCaseCheck) {
            requirementCheck += 1;
        }
        if (specialCharCheck) {
            requirementCheck += 1;
        }
        if (requirementCheck>=3) {
            return true;
        }
        else {
            return false;
        }

    }
    public boolean IDChecker(String newUserID) {
        if (isThisAnInt(newUserID)) {
            int newUserIDInt = Integer.parseInt(newUserID);
            if (isThisWithinBorder(newUserIDInt,11,99)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    public boolean usernameChecker(String newUserUsername) {
        char newUserUsernameCheck;
        if (newUserUsername.length()>=4 && newUserUsername.length()<=20) {
            for (int j = 0; j < newUserUsername.length(); j++) {
                newUserUsernameCheck = newUserUsername.charAt(j);
                if (!Character.isDigit(newUserUsernameCheck) && !Character.isLetter(newUserUsernameCheck)) {
                    return false;
                }
            }
        }
        else {
            return false;
        }
        return true;
    }
    public boolean iniChecker(String newUserIni) {
        if (isThisAString(newUserIni) == true && newUserIni.length()>=2 && newUserIni.length()<=4) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean cprChecker(String newUserCpr) {
        if (isThisAnInt(newUserCpr) == true && newUserCpr.length() == 10) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean intWithinBoundaryChecker(String choice,int minValue, int maxValue) {
        if (isThisAnInt(choice)) {
            int intChoice = Integer.parseInt(choice);
            if (isThisWithinBorder(intChoice,minValue,maxValue)) return true;
            else return false;
        }
        else return false;
    }

}
