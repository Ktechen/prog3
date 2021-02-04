package controller.handleInput.show;

public class ShowOption {

    private ShowController showController;

    public ShowOption() {
        this.showController = new ShowController();
    }

    public StringBuffer run(String nr, String value) {
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
                }

                return this.showController.showUsedTags(param);
        }

        return null;
    }
}
