package controller.handleInput.show;

public class ShowOption {

    private ShowController showController;

    public ShowOption() {
        this.showController = new ShowController();
    }

    public StringBuffer run(String nr, String value) throws NullPointerException {

        if (null == value) {
            throw new NullPointerException("Value is null");
        }

        switch (nr) {
            case "1":
                return this.showController.showFilter(value);
            case "2":
                return this.showController.showUsername(value);
            case "3":

                String param = null;
                if (value.equals("e")) {
                    param = "true";
                } else if (value.equals("i")) {
                    param = "false";
                } else {
                    return new StringBuffer().append("Enter 'e' or 'i'");
                }

                return this.showController.showUsedTags(param);
        }

        return null;
    }
}
