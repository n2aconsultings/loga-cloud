package startup.loga.client.app.factory;

public enum InvoiceConfirmationAction {
    CONFIRM {
        @Override
        public String getAction() {
            return "confirm";
        }
    },CANCEL {
        @Override
        public String getAction() {
            return "cancel";
        }
    };

    public abstract String getAction();
}
